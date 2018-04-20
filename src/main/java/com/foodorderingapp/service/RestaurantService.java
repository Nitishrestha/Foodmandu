package com.foodorderingapp.service;

import com.foodorderingapp.commons.GenericResponse;
import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.model.Restaurant;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface RestaurantService {
    Restaurant addRestaurantWithImage(MultipartHttpServletRequest request);
    Restaurant addRestaurant(Restaurant restaurant);
    boolean deleteRestaurant(int id);
    Restaurant updateRestaurant(Restaurant restaurant, int id);
    Restaurant updateRestaurant(MultipartHttpServletRequest request, int id);
    List<Restaurant> getAll();
    GenericResponse getPaginatedRestaurantToUser(int firstResult, int maxResult);
    GenericResponse getPaginatedRestaurantToAdmin(int firstResult, int maxResult);
    Restaurant getRestaurantById(int id);
    boolean deactivate(int id);
    boolean activate(int id);
    boolean getStatus(int id);
    Restaurant getRestaurantByName(String restaurantName);
    Long countRestaurant();
    long countActiveRestaurant();
}
