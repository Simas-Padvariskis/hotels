package com.hotels.hotels.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="hotels")
public class Hotel {
    //define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="ranking_average")
    private Double ranking_average;

    @Column(name="room_price")
    private Double room_price;

    @Column(name="price_discount")
    private Double price_discount;

    @Column(name="comfort")
    private Integer comfort;

    @Column(name="summary")
    private String summary;

    @Column(name="description")
    private String description;

    @Column(name="image_cover")
    private String image_cover;

    @Column(name="created_at")
    private LocalDateTime created_at;

    @Column(name="updated_at")
    private LocalDateTime updated_at;

    //define constructors
    public Hotel() {

    }

    public Hotel(Long id, User user, String name, String address, Double ranking_average, Double room_price, Double price_discount, Integer comfort, String summary, String description, String image_cover, LocalDateTime created_at) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.address = address;
        this.ranking_average = ranking_average;
        this.room_price = room_price;
        this.price_discount = price_discount;
        this.comfort = comfort;
        this.summary = summary;
        this.description = description;
        this.image_cover = image_cover;
        this.created_at = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", ranking_average=" + ranking_average +
                ", room_price=" + room_price +
                ", price_discount=" + price_discount +
                ", comfort=" + comfort +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", image_cover='" + image_cover + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
