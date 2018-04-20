package com.foodorderingapp.test;

import com.foodorderingapp.dao.FoodDAO;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.serviceImpl.FoodServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;


public class FoodFailTest {
    @Mock
    private FoodDAO foodDAO;

    @InjectMocks
    FoodServiceImpl foodService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected=DataNotFoundException.class)
    public void addFoodToRestaurantWhenFoodListIsNullShouldThrow(){
        foodService.addFoodsToRestaurant(null);
    }


    @Test(expected=DataNotFoundException.class)
    public void testFailToDelete(){
        Food food=null;
        foodService.deleteFood(anyInt());
    }

    @Test(expected = DataNotFoundException.class)
    public void testFailToUpdate(){
        foodService.updateFood(null,anyInt());
    }

    @Test(expected = DataNotFoundException.class)
    public void testFailToGetAll() throws NullPointerException{
        List<Food> foodList=null;
        when(foodDAO.getAll()).thenReturn(foodList);
        foodService.getAll();
    }

    @Test(expected = DataNotFoundException.class)
    public void testFailToGetById(){
        Food food=new Food(1,"momo",100,new Restaurant());
        when(foodDAO.getFoodById(food.getId())).thenReturn(null);
        foodService.getFoodById(food.getId());
    }

    @Test(expected = DataNotFoundException.class)
    public void testFailToGetFoodByRestaurantId(){
        Food food=new Food(1,"momo",100,new Restaurant());
        when(foodDAO.getFoodByRestaurantId(food.getId())).thenReturn(null);
        foodService.getFoodByRestaurantId(food.getId());
    }

    @Test(expected = DataNotFoundException.class)
    public void testFailToGetFoodByResName(){
        Food food=new Food(1,"momo",100,new Restaurant());
        when(foodDAO.getFoodByResName(food.getName(),food.getRestaurant().getName())).thenReturn(null);
        foodService.getFoodByResName(food.getName(),food.getRestaurant().getName());
    }
}

