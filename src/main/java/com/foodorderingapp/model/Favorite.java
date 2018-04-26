package com.foodorderingapp.model;

import javax.persistence.*;

@Entity
@Table(name="tbl_favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="favorite_id")
    private int favId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    @Column(name="favorated")
    private boolean favorated;

    public Favorite(User user, Restaurant restaurant, boolean favorated) {
        this.user = user;
        this.restaurant = restaurant;
        this.favorated = favorated;
    }

    public Favorite() {
    }

    public int getFavId() {
        return favId;
    }

    public void setFavId(int favId) {
        this.favId = favId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isFavorated() {
        return favorated;
    }

    public void setFavorated(boolean favorated) {
        this.favorated = favorated;
    }
}
