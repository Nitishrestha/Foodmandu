/*
package com.foodorderingapp.test;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.serviceImpl.RestaurantServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class RestaurantTest {

    @Mock
    private RestaurantDAO restaurantDAO;

    @InjectMocks
    RestaurantServiceImpl restaurantService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTest(){

        Restaurant restaurant=new Restaurant();
        when(restaurantDAO.addRestaurant(restaurant)).thenReturn(new Restaurant());
        Assert.assertNotNull(restaurantService.addRestaurant(restaurant));
    }

    @Test
    public void deleteTest(){
        Restaurant restaurant=new Restaurant();
        when(restaurantDAO.deleteRestaurant(restaurant)).thenReturn(true);
        Assert.assertNotNull(restaurantService.deleteRestaurant(restaurant));
    }

    @Test
    public void updateTest(){

        Restaurant restaurant=new Restaurant();
        when(restaurantDAO.getRestaurantById(anyInt())).thenReturn(restaurant);
       when(restaurantDAO.updateRestaurant(restaurant)).thenReturn(true);
        Assert.assertNotNull(restaurantService.updateRestaurant(restaurant,anyInt()));
    }

    @Test
    public void getAllTest(){
        List<Restaurant> restaurantList=new ArrayList<>();
        when(restaurantDAO.getAll()).thenReturn(restaurantList);
        Assert.assertNotNull(restaurantService.getAll());
    }

    @Test
    public void getPaginatedRestaurantTest(){
        List<Restaurant> restaurantList=new ArrayList<>();
        when(restaurantDAO.getPaginatedRestaurant(any(PageModel.class))).thenReturn(restaurantList);
        Assert.assertNotNull(restaurantService.getPaginatedRestaurant(any(PageModel.class)));
    }

    @Test
    public void getByIdTest(){
        Restaurant restaurant=new Restaurant();
        when(restaurantDAO.getRestaurantById(anyInt())).thenReturn(restaurant);
        Assert.assertNotNull(restaurantService.getRestaurantById(anyInt()));
    }

    @Test
    public void deactivateTest(){

        Restaurant restaurant=new Restaurant(1,"kfc","ktm","9841447",new ArrayList<>());
        when(restaurantDAO.getRestaurantById(restaurant.getId())).thenReturn(restaurant);
        when(restaurantDAO.getStatus(restaurant.getId())).thenReturn(true);
        when(restaurantDAO.deactivate(restaurant.getId())).thenReturn(restaurant.getId());
        Assert.assertNotNull(restaurantService.deactivate(restaurant.getId()));
    }

    @Test
    public void activateTest(){
        Restaurant restaurant=new Restaurant(1,"kfc","ktm","9841447",new ArrayList<>());
        when(restaurantDAO.getRestaurantById(restaurant.getId())).thenReturn(restaurant);
        when(restaurantDAO.getStatus(restaurant.getId())).thenReturn(false);
        when(restaurantDAO.activate(restaurant.getId())).thenReturn(restaurant.getId());
        Assert.assertNotNull(restaurantService.deactivate(restaurant.getId()));

    }

    @Test
    public void getStatusTest(){
        Restaurant restaurant=new Restaurant(1,"kfc","ktm","9841447",new ArrayList<>());
        when(restaurantDAO.getStatus(restaurant.getId())).thenReturn(true);
        Assert.assertNotNull(restaurantService.getStatus(restaurant.getId()));
    }

    @Test
    public void getRestaurantByNameTest(){
        Restaurant restaurant=new Restaurant(1,"kfc","ktm","9841447",new ArrayList<>());
        when(restaurantDAO.getRestaurantByName(restaurant.getName())).thenReturn(restaurant);
        Assert.assertNotNull(restaurantService.getRestaurantByName(restaurant.getName()));
    }

    @Test
    public void countRestaurantTest(){
        Long i=5L;
        when( restaurantDAO.countRestaurant()).thenReturn(i);
        Assert.assertNotNull( restaurantService.countRestaurant());
    }
}
*/
