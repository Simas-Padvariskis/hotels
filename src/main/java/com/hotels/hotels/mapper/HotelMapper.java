package com.hotels.hotels.mapper;

import com.hotels.hotels.dto.HotelCreateDTO;
import com.hotels.hotels.dto.HotelResponseDTO;
import com.hotels.hotels.entity.Hotel;
import com.hotels.hotels.entity.User;

import java.time.LocalDateTime;

public class HotelMapper {

    public static Hotel toEntity(HotelCreateDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setName(dto.getName());
        hotel.setAddress(dto.getAddress());
        hotel.setRanking_average(dto.getRanking_average());
        hotel.setRoom_price(dto.getRoom_price());
        hotel.setPrice_discount(dto.getPrice_discount());
        hotel.setComfort(dto.getComfort());
        hotel.setSummary(dto.getSummary());
        hotel.setDescription(dto.getDescription());
        hotel.setImage_cover(dto.getImage_cover());
        hotel.setCreated_at(LocalDateTime.now());
        hotel.setUpdated_at(LocalDateTime.now());
        return hotel;
    }

    public static HotelResponseDTO toResponseDTO(Hotel entity) {
        HotelResponseDTO dto = new HotelResponseDTO();
        dto.setId(entity.getId());
        dto.setUser_id(entity.getUser().getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setRanking_average(entity.getRanking_average());
        dto.setRoom_price(entity.getRoom_price());
        dto.setPrice_discount(entity.getPrice_discount());
        dto.setComfort(entity.getComfort());
        dto.setSummary(entity.getSummary());
        dto.setDescription(entity.getDescription());
        dto.setImage_cover(entity.getImage_cover());
        dto.setCreated_at(entity.getCreated_at());
        dto.setUpdated_at(entity.getUpdated_at());
        return dto;
    }
}
