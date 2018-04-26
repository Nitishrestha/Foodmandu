package com.foodorderingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@SqlResultSetMapping(
        name="RestaurantMapping",
        classes =
                {@ConstructorResult(targetClass = Restaurant.class,
                        columns = {
                                @ColumnResult(name="restaurant_id", type=Integer.class),
                                @ColumnResult(name="restaurant_name", type=String.class),
                                @ColumnResult(name="restaurant_address",type=String.class),
                                @ColumnResult(name="restaurant_contact",type=String.class),
                                @ColumnResult(name="status",type=Boolean.class),
                                @ColumnResult(name="favorated",type=Boolean.class),

                        })})
@Entity
@Table(name = "tbl_restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id", nullable = false, updatable = false)
    private int id;

    @Column(name = "restaurant_name")
    @NotNull(message = "please enter the restaurant name")
    @Size(min = 3, max = 25)
    private String name;

    @Column(name = "restaurant_address")
    @NotBlank(message = "please enter the restaurant address")
    @Size(min = 3, max = 25)
    private String address;

    @Column(name = "restaurant_contact")
    @NotBlank(message = "please enter the restaurant contact")
    @Size(min = 7, max = 10)
    private String contact;

    @Column(name = "status")
    private boolean isActive = true;

    @Column(name = "restaurant_code")
    private String restaurantCode;

    @Transient
    private boolean favorated;

    public Restaurant(int id,String name, String address, String contact, boolean isActive, boolean favorated) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.isActive = isActive;
        this.favorated = favorated;
    }

    @Transient
    @JsonIgnore
    private MultipartFile file;

    public boolean isFavorated() {
        return favorated;
    }

    public void setFavorated(boolean favorated) {
        this.favorated = favorated;
    }

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Food> foodList;

/*

    @OneToMany(mappedBy = "restaurant")
    private List<FavoriteRestaurant> favoriteRestaurant;

    public List<FavoriteRestaurant> getFavoriteRestaurantList() {
        return favoriteRestaurant;
    }

    public void setFavoriteRestaurantList(List<FavoriteRestaurant> favoriteRestaurant) {
        this.favoriteRestaurant = favoriteRestaurant;
    }
*/

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getRestaurantCode() {
        return restaurantCode;
    }

    public void setRestaurantCode(String restaurantCode) {
        this.restaurantCode = restaurantCode;
    }


    public Restaurant(int id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public Restaurant() {
        this.restaurantCode = "REST" + UUID.randomUUID().toString().substring(26).toUpperCase();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
