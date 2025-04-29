package com.hotels.hotels.dao;

import com.hotels.hotels.entity.Hotel;
import com.hotels.hotels.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelsRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> getHotelByUser(User user);
}
