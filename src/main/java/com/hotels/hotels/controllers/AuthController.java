package com.hotels.hotels.controllers;

import com.hotels.hotels.dao.RoleRepository;
import com.hotels.hotels.dao.UserRepository;
import com.hotels.hotels.dto.JwtResponse;
import com.hotels.hotels.dto.LoginRequestDTO;
import com.hotels.hotels.dto.MessageResponse;
import com.hotels.hotels.dto.SignupRequestDTO;
import com.hotels.hotels.entity.Role;
import com.hotels.hotels.entity.User;
import com.hotels.hotels.enums.Roles;
import com.hotels.hotels.security.jwt.JwtUtils;
import com.hotels.hotels.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(items -> items.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                                                 userDetails.getId(),
                                                 userDetails.getUsername(),
                                                 userDetails.getEmail(),
                                                 roles
                ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDTO signupRequest) {
        if(userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken."));
        }
        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken."));
        }
        // Create new user

        User user = new User(signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if(strRoles == null) {
            Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role not found"));
            roles.add(userRole);
        }else{
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(Roles.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role not found"));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(Roles.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role not found"));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role not found"));
                        roles.add(userRole);
                        break;
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
}
