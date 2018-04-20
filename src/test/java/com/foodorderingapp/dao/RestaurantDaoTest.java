/*
package com.foodorderingapp.dao;

import com.foodorderingapp.Application;
import com.foodorderingapp.config.HibernateConfig;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Restaurant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        Application.class,
        HibernateConfig.class})
@Transactional
public class RestaurantDaoTest {

    @Autowired
    private RestaurantDAO restaurantDAO;

    Restaurant res;

    @Before
    public void init() {
        res = new Restaurant();
        res.setName("Alinas");
        res.setContact("981646112");
        res.setAddress("daubarmarg");
        res.setActive(true);
    }

    @Test
    public void addRestaurant_whenAdded_thenReturnOK() {
        Restaurant restaurant= restaurantDAO.addRestaurant(res);
        Assert.assertEquals(res, restaurant);
    }

    @Test
    public void deleteRestaurant_thenReturnTrue() {
        boolean b= restaurantDAO.deleteRestaurant(res);
        Assert.assertTrue(b);
    }

    @Test
    public void updateRestaurant_thenReturnTrue() {
        boolean b= restaurantDAO.updateRestaurant(res);
        Assert.assertTrue(b);
    }

    @Test
    public void getAll_thenReturnListOfRestaurant() {
        List<Restaurant> restaurantList= restaurantDAO.getAll();
        Assert.assertNotNull(restaurantList);
    }

    @Test
    public void getPaginatedRestaurantToUser_thenReturnListOfRestaurant() {
        List<Restaurant> restaurantList= restaurantDAO.getPaginatedRestaurantToUser(0,1);
        Assert.assertNotNull(restaurantList);
    }

    @Test
    public void getPaginatedRestaurantToAdmin_thenReturnListOfRestaurant() {
        List<Restaurant> restaurantList= restaurantDAO.getPaginatedRestaurantToAdmin(0,1);
        Assert.assertNotNull(restaurantList);
    }

    @Test
    public void getRestaurantById_thenReturnObjectOfRestaurant() {
        Restaurant restaurantList= restaurantDAO.getRestaurantById(8);
        Assert.assertNotNull(restaurantList);
    }

    @Test
    public void activate_thenReturnId() {
        boolean id = restaurantDAO.activate(8);
        Assert.assertTrue(id);
    }

    @Test
    public void getStatus_thenReturnId() {
        boolean id = restaurantDAO.getStatus(8);
        Assert.assertTrue(id);
    }

    @Test
    public void getRestaurantByName_thenReturnObjectOfRestaurant() {
        Restaurant restaurant = restaurantDAO.getRestaurantByName("kfc");
        Assert.assertNotNull(restaurant);
    }

    @Test
    public void countRestaurant_thenReturnTotalNumberOfRestaurantPresentedInRestaurantTable() {
        long count = restaurantDAO.countRestaurant();
        Assert.assertNotNull(count);
    }

    @Test
    public void countActiveRestaurant_thenReturnTotalNumberOfActiveRestaurantPresentedInRestaurantTable() {
        long count = restaurantDAO.countActiveRestaurant();
        Assert.assertNotNull(count);
    }

    @Test(expected= BadRequestException.class)
    public void addRestaurant_whenObjectOfRestaurantIsNull_thenResultBadRequestException() {
        Restaurant restaurant= restaurantDAO.addRestaurant(null);
        Assert.assertNull(restaurant);
    }

    @Test(expected= BadRequestException.class)
    public void deleteRestaurant_whenObjectOfRestaurantIsNull_thenResultBadRequestException() {
        boolean b= restaurantDAO.deleteRestaurant(null);
        Assert.assertFalse(b);
    }

    @Test(expected= BadRequestException.class)
    public void updateRestaurant_whenObjectOfRestaurantIsNull_thenResultBadRequestException() {
        boolean b= restaurantDAO.updateRestaurant(null);
        Assert.assertFalse(b);
    }

    @Test(expected= DataNotFoundException.class)
    public void getRestaurantById_whenObjectOfRestaurantOfThatId_CannotBeFound_thenResultDataNotFoundException() {
        Restaurant restaurant=restaurantDAO.getRestaurantById(1);
        Assert.assertNull(restaurant);
    }

    @Test(expected= BadRequestException.class)
    public void deactivate_whenThereIsNotSuchId_thenResultBadRequestException() {
        boolean b=restaurantDAO.deactivate(1);
        Assert.assertFalse(b);
    }

    @Test(expected= BadRequestException.class)
    public void activate_whenThereIsNotSuchId_thenResultBadRequestException() {
        boolean b=restaurantDAO.activate(1);
        Assert.assertFalse(b);
    }

    @Test(expected= DataNotFoundException.class)
    public void getRestaurantByName_whenThereIsNoSuchName_thenResultDataNotFoundException() {
        Restaurant restaurant=restaurantDAO.getRestaurantByName("www");
        Assert.assertNull(restaurant);
    }
}
*/
