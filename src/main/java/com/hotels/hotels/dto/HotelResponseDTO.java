package com.hotels.hotels.dto;

import java.time.LocalDateTime;

public class HotelResponseDTO {

    private Long id;
    private Long user_id;
    private String name;
    private String address;
    private Double ranking_average;
    private Double room_price;
    private Double price_discount;
    private Integer comfort;
    private String summary;
    private String description;
    private String image_cover;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getRanking_average() {
        return ranking_average;
    }

    public void setRanking_average(Double ranking_average) {
        this.ranking_average = ranking_average;
    }

    public Double getRoom_price() {
        return room_price;
    }

    public void setRoom_price(Double room_price) {
        this.room_price = room_price;
    }

    public Double getPrice_discount() {
        return price_discount;
    }

    public void setPrice_discount(Double price_discount) {
        this.price_discount = price_discount;
    }

    public Integer getComfort() {
        return comfort;
    }

    public void setComfort(Integer comfort) {
        this.comfort = comfort;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_cover() {
        return image_cover;
    }

    public void setImage_cover(String image_cover) {
        this.image_cover = image_cover;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
