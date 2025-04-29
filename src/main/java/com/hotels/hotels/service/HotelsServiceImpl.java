package com.hotels.hotels.service;

import com.hotels.hotels.dao.HotelsRepository;
import com.hotels.hotels.entity.Hotel;
import com.hotels.hotels.entity.User;
import com.hotels.hotels.security.services.UserDetailsImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelsServiceImpl implements HotelsService {
    private HotelsRepository hotelRepository;

    @Autowired
    public HotelsServiceImpl(HotelsRepository theHotelRepository) {
        this.hotelRepository = theHotelRepository;
    }

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel findById(int id) {
        Optional<Hotel> result = hotelRepository.findById(id);

        Hotel hotel = null;

        if(result.isPresent()) {
            hotel = result.get();
        }else{
            throw new RuntimeException("Did not find hotel by id - " + id);
        }

        return hotel;
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteById(int id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public List<Hotel> getHotelsByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = new User(userDetails.getUsername(), userDetails.getEmail(), userDetails.getPassword());
        user.setId(userDetails.getId());

        return hotelRepository.getHotelByUser(user);
    }

}
