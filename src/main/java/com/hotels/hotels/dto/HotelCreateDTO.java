package com.hotels.hotels.dto;

import com.hotels.hotels.entity.Hotel;
import jakarta.validation.constraints.*;

public class HotelCreateDTO {

    @NotBlank(message = "A hotel must have a name")
    private String name;

    @NotBlank(message = "Must have an address")
    private String address;

    @DecimalMin(value = "1.0", message = "Ranking must be above 1")
    @DecimalMax(value = "5.0", message = "Ranking mus be below 5")
    private Double ranking_average = 4.5;

    @NotNull(message = "Must have a room price")
    @Positive(message = "Room price be be positive")
    private Double room_price;

    private Double price_discount;

    @NotNull(message = "Hotel must have a star rating")
    @Min(value = 1, message = "Comfort must be at least 1")
    @Max(value = 7, message = "Comfort can not be above 7")
    private Integer comfort;

    @NotBlank(message = "Hotel must have a summary")
    @Size(max = 150)
    private String summary;

    private String description;

    private String image_cover;

    //Getters and setters

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
}
