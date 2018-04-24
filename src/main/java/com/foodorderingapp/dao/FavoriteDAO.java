package com.foodorderingapp.dao;

import com.foodorderingapp.model.Favorite;

import java.util.List;

public interface FavoriteDAO {
    void addFavorite(Favorite favorite);
    Favorite getUserAndRestaurantIds(int userId,int restaurantId);
    boolean update(Favorite favorite);
    boolean delete(Favorite favorite);
    List<Favorite> getFavoriteRestaurantByUserId(int userId);
    List<Favorite> getFavoriteByRestaurantId(int restarurantId);
}
