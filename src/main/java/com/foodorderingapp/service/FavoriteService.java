package com.foodorderingapp.service;

import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.responsedto.FavoriteResponseDto;
import com.foodorderingapp.responsedto.MessageResponseDto;

import java.util.List;

public interface FavoriteService {
    MessageResponseDto addFavorite(int userId, int restaurantId);
    List<Restaurant> getFavoriteRestaurant(int userId);
    MessageResponseDto deleteFavorite(int userId, int restaurantId);
    List<FavoriteResponseDto> getUsersOfFavoratedRestaurant(int restaurantId);

}
