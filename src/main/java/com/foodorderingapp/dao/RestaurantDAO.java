package com.foodorderingapp.dao;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.model.Restaurant;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface RestaurantDAO {
    Restaurant addRestaurant(Restaurant restaurant);
    boolean deleteRestaurant(Restaurant restaurant);
    boolean updateRestaurant(Restaurant restaurant);
    List<Restaurant> getAll();
    Restaurant getRestaurantById(int id);
    boolean deactivate(int id);
    boolean activate(int id);
    boolean getStatus(int id);
    Restaurant getRestaurantByName(String restaurantName);
    long countRestaurant();
    long countActiveRestaurant();
    List<Restaurant> getPaginatedRestaurantToUser(int firstResult, int maxResult);
    List<Restaurant> getPaginatedRestaurantToAdmin(int firstResult, int maxResult);

}
