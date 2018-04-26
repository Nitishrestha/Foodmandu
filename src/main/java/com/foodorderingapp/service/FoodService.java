package com.foodorderingapp.service;

import com.foodorderingapp.commons.GenericResponse;
import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.model.Food;

import java.util.List;


public interface FoodService {
    boolean deleteFood(int foodId);
    boolean updateFood(Food food, int id);
    List<Food> getAll();
    Food getFoodById(int id);
    List<Food> getFoodByRestaurantId(int id);
    List<Food> addFoodsToRestaurant(List<Food> foodList);
    Food getFoodByResName(String restaurantName,String foodName);
    GenericResponse getPaginatedFood(int id, int firstResult, int maxResult);
    long countFood(int id);
}
