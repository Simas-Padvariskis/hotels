package com.hotels.hotels.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hotels.hotels.dto.HotelCreateDTO;
import com.hotels.hotels.dto.HotelResponseDTO;
import com.hotels.hotels.entity.Hotel;
import com.hotels.hotels.entity.User;
import com.hotels.hotels.enums.Roles;
import com.hotels.hotels.mapper.HotelMapper;
import com.hotels.hotels.security.services.UserDetailsImpl;
import com.hotels.hotels.service.HotelsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/hotels")
public class HotelsController {
    private HotelsService hotelsService;

    private ObjectMapper objectMapper;

    @Autowired
    public HotelsController(HotelsService hotelsService, ObjectMapper objectMapper) {
        this.hotelsService = hotelsService;
        this.objectMapper = objectMapper;
    }

    //Return all hotels

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isUser = authentication != null &&
                authentication.isAuthenticated() &&
                !(authentication.getPrincipal() instanceof String) &&
                authentication.getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"));

        List<HotelResponseDTO> hotels;

        if (isUser) {
            hotels = hotelsService.getHotelsByUser() 
                    .stream()
                    .map(HotelMapper::toResponseDTO)
                    .collect(Collectors.toList());
        } else {
            hotels = hotelsService.findAll()
                    .stream()
                    .map(HotelMapper::toResponseDTO)
                    .collect(Collectors.toList());
        }

        Map<String, Object> response = Map.of(
                "status", "success",
                "results", hotels.size(),
                "data", hotels
        );
        return ResponseEntity.ok(response);
    }

    //Return hotel by id
    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelResponseDTO> getHotel(@PathVariable Long hotelId) {

        Optional<Hotel> result = Optional.ofNullable(hotelsService.findById(hotelId.intValue()));

        return result.map(hotel -> ResponseEntity.ok(HotelMapper.toResponseDTO(hotel)))
                .orElse(ResponseEntity.notFound().build());
    }

    //Create new hotel
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HotelResponseDTO> addHotel(@Valid @RequestBody HotelCreateDTO hotelDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = new User(userDetails.getUsername(), userDetails.getEmail(), userDetails.getPassword());
        user.setId(userDetails.getId());

        Hotel hotel = HotelMapper.toEntity(hotelDTO);
        hotel.setUser(user);
        Hotel saved = hotelsService.save(hotel);

        return ResponseEntity.ok(HotelMapper.toResponseDTO(saved));
    }

    //Delete hotel by id
    @DeleteMapping("/{hotelId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> deleteHotel(@PathVariable int hotelId){
        Hotel theHotel = hotelsService.findById(hotelId);

        if(theHotel == null){
            return ResponseEntity.notFound().build();
        }

        hotelsService.deleteById(hotelId);

        return ResponseEntity.ok("Deleted hotel with id - " + hotelId);
    }

    //Update all hotel object
    @PutMapping("/{hotelId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HotelResponseDTO> updateHotel(@PathVariable int hotelId, @Valid @RequestBody HotelCreateDTO hotel) {
        Hotel existing = hotelsService.findById(hotelId);

        if(existing == null){
            return ResponseEntity.notFound().build();
        }

        Hotel updatedHotel = HotelMapper.toEntity(hotel);
        updatedHotel.setId((long) hotelId);
        updatedHotel.setCreated_at(existing.getCreated_at());
        updatedHotel.setUpdated_at(LocalDateTime.now());

        Hotel saved = hotelsService.save(updatedHotel);

        return ResponseEntity.ok(HotelMapper.toResponseDTO(saved));
    }

    @PatchMapping("/{hotelId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HotelResponseDTO> patchHotel(@PathVariable Integer hotelId, @RequestBody Map<String, Object> pathPayLoad){
        Hotel tempHotel = hotelsService.findById(hotelId.intValue());

        if(tempHotel == null){
            return ResponseEntity.notFound().build();
        }

        if(pathPayLoad.containsKey("id")){
            return ResponseEntity.badRequest().body(null);
        }

        ObjectNode hotelNode = objectMapper.convertValue(tempHotel, ObjectNode.class);
        ObjectNode pathNode = objectMapper.convertValue(pathPayLoad, ObjectNode.class);
        hotelNode.setAll(pathNode);

        Hotel pathedHotel = objectMapper.convertValue(hotelNode, Hotel.class);

        Hotel savedHotel = hotelsService.save(pathedHotel);

        return ResponseEntity.ok(HotelMapper.toResponseDTO(savedHotel));
    }

}
