/*
package com.foodorderingapp.test;

import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.serviceImpl.RestaurantServiceImpl;
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

public class
RestaurantFailTest {
    @Mock
    private RestaurantDAO restaurantDAO;

    @InjectMocks
    RestaurantServiceImpl restaurantService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = DataNotFoundException.class)
    public void testFailToAdd(){
        Restaurant restaurant=new Restaurant();
        when(restaurantDAO.addRestaurant(restaurant)).thenReturn(null);
        restaurantService.addRestaurant(restaurant);
    }

    @Test(expected = DataNotFoundException.class)
    public void testFailToDelete(){
        Restaurant restaurant=new Restaurant(1,"kfc","ktm","9841447",new ArrayList<>());
        when(restaurantDAO.deleteRestaurant(restaurant)).thenReturn(false);
        restaurantService.deleteRestaurant(restaurant);
    }

    @Test(expected = DataNotFoundException.class)
    public void testFailToUpdate(){
        Restaurant restaurant=new Restaurant(1,"kfc","ktm","9841447",new ArrayList<>());
        when(restaurantDAO.updateRestaurant(restaurant)).thenReturn(false);
        restaurantService.updateRestaurant(null,restaurant.getId());
    }

    @Test(expected = DataNotFoundException.class)
    public void testFailToGetAll() {
        List<Restaurant> restaurantList=null;
        when(restaurantDAO.getAll()).thenReturn(restaurantList);
        restaurantService.getAll();
    }

    @Test(expected = DataNotFoundException.class)
    public void testFailToGetById() throws NullPointerException{
        Restaurant restaurant=new Restaurant(1,"kfc","ktm","9841447",new ArrayList<>());
        when(restaurantDAO.getRestaurantById(restaurant.getId())).thenReturn(null);
        restaurantService.getRestaurantById(restaurant.getId());
    }

    @Test(expected = DataNotFoundException.class)
    public void testFailToGetRestaurantByName(){
        restaurantService.getRestaurantByName(null);
    }
}
*/
