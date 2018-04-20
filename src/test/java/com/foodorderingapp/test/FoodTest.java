/*
package com.foodorderingapp.test;

import com.foodorderingapp.dao.FoodDAO;
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


public class FoodTest {

    @Mock
    private FoodDAO foodDAO;

    @InjectMocks
    FoodServiceImpl foodService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdd(){
        List<Food> foodList=new ArrayList<>();
        when(foodDAO.addFoodsToRestaurant(foodList)).thenReturn(foodList);
        Assert.assertEquals(foodService.addFoodsToRestaurant(foodList),foodList);
    }

    @Test
    public void testDelete(){
        Food food=new Food();
        when(foodDAO.deleteFood(any(Food.class))).thenReturn(true);
        Assert.assertNotNull(foodService.deleteFood(anyInt()));
    }

    @Test
    public void testUpdate(){
        Food food=new Food(1,"momo",100,new Restaurant());
        when(foodDAO.getFoodById(food.getId())).thenReturn(food);
        when(foodDAO.updateFood(food)).thenReturn(true);
        Assert.assertNotNull(foodService.updateFood(food,food.getId()));
    }

    @Test
    public void testGetAll(){
        List<Food> foodList=new ArrayList<>();
        when(foodDAO.getAll()).thenReturn(foodList);
        Assert.assertNotNull(foodService.getAll());
    }

    @Test
    public void testGetById(){
        Food food=new Food(1,"momo",100,new Restaurant());
        when(foodDAO.getFoodById(food.getId())).thenReturn(food);
        Assert.assertNotNull(foodService.getFoodById(food.getId()));
    }

    @Test
    public void testGetFoodByRestaurantId(){
        Food food=new Food(1,"momo",100,new Restaurant());
        when(foodDAO.getFoodByRestaurantId(food.getId())).thenReturn(anyList());
        Assert.assertNotNull(foodService.getFoodByRestaurantId(food.getId()));
    }

    @Test
    public void testGetFoodByResName(){
        Food food=new Food(1,"momo",100,new Restaurant());
        when(foodDAO.getFoodByResName(food.getName(),food.getRestaurant().getName())).thenReturn(food);
        Assert.assertNotNull(foodService.getFoodByResName(food.getName(),food.getRestaurant().getName()));
    }
}
*/
