package com.hotels.hotels.service;

import com.hotels.hotels.entity.Hotel;

import java.util.List;

public interface HotelsService {
    List<Hotel> findAll();

    Hotel findById(int id);

    Hotel save(Hotel hotel);

    void deleteById(int id);

    List<Hotel> getHotelsByUser();
}
