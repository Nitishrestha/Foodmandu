
package com.foodorderingapp.dao;

import com.foodorderingapp.dao.FavoriteDAO;
import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Favorite;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.model.User;
import junit.framework.TestCase;
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
@SpringBootTest
@Transactional
//@Commit //create base and include all common code there.
public class RestaurantDaoTest extends TestCase{

    @Autowired
    private RestaurantDAO restaurantDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private FavoriteDAO favoriteDAO;


    Restaurant res;

    @Before
    public void init() {
        res = new Restaurant("kfc", "9827272", "daubarmarg", true);
    }

    public void testAddRestaurant() {
        Restaurant res = new Restaurant("pizzahut", "9827272", "daubarmarg", true);
        Restaurant restaurant = restaurantDAO.addRestaurant(res);
        Assert.assertEquals(res, restaurant);
    }


    @Test
    public void deleteRestaurant_thenReturnTrue() {
        boolean b = restaurantDAO.deleteRestaurant(res);
        Assert.assertTrue(b);
    }

    @Test
    public void updateRestaurant_thenReturnTrue() {
        boolean b = restaurantDAO.updateRestaurant(res);
        Assert.assertTrue(b);
    }

    @Test
    public void getAll_thenReturnListOfRestaurant() {
        List<Restaurant> restaurantList = restaurantDAO.getAll();
        Assert.assertNotNull(restaurantList);
    }

    @Test
    public void getPaginatedRestaurantToUser_thenReturnListOfRestaurant() {
        restaurantDAO.addRestaurant(res);
        User u=new User("ram","bahadur","shah","ram",
                "ram@yahoo.com","91839183","ktm","user",1200);
        userDAO.addUser(u);
        Favorite favorite=new Favorite(u,res,true);
        favoriteDAO.addFavorite(favorite);
        List<Restaurant> restaurantList = restaurantDAO.getPaginatedRestaurantToUser(favorite.getUser().getUserId(), 0,1);
        Assert.assertNotNull(restaurantList);
    }

    @Test
    public void getPaginatedRestaurantToAdmin_thenReturnListOfRestaurant() {
        restaurantDAO.addRestaurant(res);
        List<Restaurant> restaurantList = restaurantDAO.getPaginatedRestaurantToAdmin(0, 1);
        Assert.assertNotNull(restaurantList);
    }

    @Test
    public void getRestaurantById_thenReturnObjectOfRestaurant() {
        restaurantDAO.addRestaurant(res);
        Restaurant restaurantList = restaurantDAO.getRestaurantById(res.getId());
        Assert.assertNotNull(restaurantList);
    }

    @Test
    public void activate_thenReturnId() {
        restaurantDAO.addRestaurant(res);
        boolean id = restaurantDAO.activate(res.getId());
        Assert.assertTrue(id);
    }

    @Test
    public void getStatus_thenReturnId() {
        restaurantDAO.addRestaurant(res);
        boolean id = restaurantDAO.getStatus(res.getId());
        Assert.assertTrue(id);
    }

    @Test
    public void getRestaurantByName_thenReturnObjectOfRestaurant() {
        restaurantDAO.addRestaurant(res);
        Restaurant restaurant = restaurantDAO.getRestaurantByName("kfc");
        Assert.assertNotNull(restaurant);
    }

    @Test
    public void countActiveRestaurant_thenReturnTotalNumberOfActiveRestaurantPresentedInRestaurantTable() {
        long count = restaurantDAO.countActiveRestaurant();
        Assert.assertNotNull(count);
    }

    @Test(expected = BadRequestException.class)
    public void addRestaurant_whenObjectOfRestaurantIsNull_thenResultBadRequestException() {
        Restaurant restaurant = restaurantDAO.addRestaurant(null);
        Assert.assertNull(restaurant);
    }

    @Test(expected = BadRequestException.class)
    public void deleteRestaurant_whenObjectOfRestaurantIsNull_thenResultBadRequestException() {
        boolean b = restaurantDAO.deleteRestaurant(null);
        Assert.assertFalse(b);
    }

    @Test(expected = BadRequestException.class)
    public void updateRestaurant_whenObjectOfRestaurantIsNull_thenResultBadRequestException() {
        boolean b = restaurantDAO.updateRestaurant(null);
        Assert.assertFalse(b);
    }

    @Test
    public void testShouldGetNullWhenGettingRestaurantOfThatId() {
        res = getRestaurant();
        restaurantDAO.addRestaurant(res);
        restaurantDAO.deleteRestaurant(res);
        Restaurant restaurant = restaurantDAO.getRestaurantById(res.getId());
        Assert.assertNull("This test return null restaurant. ", restaurant);
    }

    @Test
    public void testShouldReturnNullWhileGettingRestaurantById() {
        Restaurant restaurant = restaurantDAO.getRestaurantById(0);
        Assert.assertNull("This test return null restaurant. ", restaurant);
    }

    @Test(expected = BadRequestException.class)
    public void deactivate_whenThereIsNotSuchRestaurantId_thenResultBadRequestException() {
        boolean b = restaurantDAO.deactivate(0);
        Assert.assertFalse("This test return false", b);
    }

    @Test(expected = BadRequestException.class)
    public void activate_whenThereIsNotSuchId_thenResultBadRequestException() {
        boolean b = restaurantDAO.activate(0);
        Assert.assertFalse("This test return false", b);
    }

    @Test(expected = DataNotFoundException.class)
    public void getRestaurantByName_whenThereIsNoSuchRstaurantName_thenResultDataNotFoundException() {
        Restaurant restaurant = restaurantDAO.getRestaurantByName(res.getName());
        Assert.assertNull("This test will return null",restaurant);
    }

    private Restaurant getRestaurant() {
        Restaurant restaurant = new Restaurant("pizzahut", "9827272", "daubarmarg", true);
        return restaurant;
    }
}

