/*
package com.foodorderingapp.service;

import com.foodorderingapp.commons.GenericResponse;
import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.UserConflictException;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.serviceImpl.RestaurantServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class RestaurantServiceTest {


    @Mock
    private RestaurantDAO restaurantDAO;

    @InjectMocks
    RestaurantServiceImpl restaurantService;

    Restaurant restaurant;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
         restaurant=new Restaurant(1,"ram","bkt","981471");
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void addRestaurant_thenReturnOk(){
        when(restaurantDAO.addRestaurant(restaurant)).thenReturn(restaurant);
        Assert.assertEquals(restaurantService.addRestaurant(restaurant),restaurant);
    }

    @Test
    public void deleteRestaurant_thenReturnTrue(){
        when(restaurantDAO.getRestaurantById(restaurant.getId())).thenReturn(restaurant);
        when(restaurantDAO.deleteRestaurant(restaurant)).thenReturn(true);
        Assert.assertTrue(restaurantService.deleteRestaurant(restaurant.getId()));
    }

    @Test
    public void updateRestaurant_thenReturnTrue(){
        when(restaurantDAO.getRestaurantById(restaurant.getId())).thenReturn(restaurant);
        when(restaurantDAO.updateRestaurant(restaurant)).thenReturn(true);
        Assert.assertEquals(restaurantService.updateRestaurant(restaurant,restaurant.getId()),restaurant);
    }

    @Test
    public void getAll_thenReturnRestaurantList(){
        when(restaurantDAO.getAll()).thenReturn(Arrays.asList(restaurant));
        Assert.assertEquals(restaurantService.getAll(),Arrays.asList(restaurant));
    }

    @Test
    public void getPaginatedRestaurantToUser_thenReturnRestaurantList(){
        when(restaurantDAO.getPaginatedRestaurantToUser(1,2)).thenReturn(Arrays.asList(restaurant));
        Assert.assertNotNull(restaurantService.getPaginatedRestaurantToUser(1,2));
    }

    @Test
    public void getPaginatedRestaurantToAdmin_thenReturnRestaurantList(){
        List<Restaurant> restaurantList=new ArrayList<>();
        when(restaurantDAO.getPaginatedRestaurantToAdmin(1,2)).thenReturn(restaurantList);
        Assert.assertNotNull(restaurantService.getPaginatedRestaurantToAdmin(1,2));
    }

    @Test
    public void getRestaurantById_thenReturnRestaurant(){
        Restaurant restaurant=new Restaurant();
        when(restaurantDAO.getRestaurantById(anyInt())).thenReturn(restaurant);
        Assert.assertEquals(restaurantService.getRestaurantById(anyInt()),restaurant);
    }

    @Test
    public void deactivate_thenReturnInteger(){
        Restaurant restaurant=new Restaurant(1,"kfc","ktm","9841447");
        when(restaurantDAO.getRestaurantById(restaurant.getId())).thenReturn(restaurant);
        when(restaurantDAO.getStatus(restaurant.getId())).thenReturn(true);
        when(restaurantDAO.deactivate(restaurant.getId())).thenReturn(true);
        Assert.assertTrue(restaurantService.deactivate(restaurant.getId()));
    }

    @Test
    public void activate_thenReturnInteger(){
        Restaurant restaurant=new Restaurant(1,"kfc","ktm","9841447");
        when(restaurantDAO.getRestaurantById(restaurant.getId())).thenReturn(restaurant);
        when(restaurantDAO.getStatus(restaurant.getId())).thenReturn(false);
        when(restaurantDAO.activate(restaurant.getId())).thenReturn(true);
        Assert.assertTrue(restaurantService.deactivate(restaurant.getId()));
    }

    @Test
    public void getStatus_thenReturnInt(){
        Restaurant restaurant=new Restaurant(1,"kfc","ktm","9841447");
        when(restaurantDAO.getStatus(restaurant.getId())).thenReturn(true);
        Assert.assertTrue(restaurantService.getStatus(restaurant.getId()));
    }

    @Test
    public void getRestaurantByName_thenReturnRestaurant(){
        Restaurant restaurant=new Restaurant(1,"kfc","ktm","9841447");
        when(restaurantDAO.getRestaurantByName(restaurant.getName())).thenReturn(restaurant);
        Assert.assertEquals(restaurantService.getRestaurantByName(restaurant.getName()),restaurant);
    }

    @Test
    public void countRestaurant_thenReturnLong(){
        Long i=5L;
        when( restaurantDAO.countRestaurant()).thenReturn(i);
        Assert.assertEquals( restaurantService.countRestaurant(),i);
    }

    @Test
    public void addRestaurant_whenAddRestaurantIsNull_thenReturnDataNotFoundException(){
        Restaurant restaurant=new Restaurant();
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot add restaurant.");
        restaurantService.addRestaurant(restaurant);
    }

    @Test
    public void deleteRestaurant_whenRestaurantIsNotFound_thenReturnUserConflictException(){
        Restaurant restaurant=new Restaurant(1,"kfc","ktm","9841447");
        expectedException.expect(UserConflictException.class);
        expectedException.expectMessage("cannot delete restaurant.");
        restaurantService.deleteRestaurant(restaurant.getId());
    }

    @Test
    public void updateRestaurant_whenGetRestaurantByIdReturnsInvalidParameter_thenReturnDataNotFoundException(){
        Restaurant restaurant=new Restaurant(1,"kfc","ktm","9841447");
        expectedException.expect(UserConflictException.class);
        expectedException.expectMessage("cannot update restaurant.");
        restaurantService.updateRestaurant(null,restaurant.getId());
    }

    @Test
    public void getAll_whenGetAllReturnNullOrSizeZero_thenReturnDataNotFoundException() {
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find restaurantList.");
        restaurantService.getAll();
    }

    @Test
    public void getRestaurantById_whenGetRestaurantByIdReturnNull_thenReturnDataNotFoundException(){
        Restaurant restaurant=new Restaurant(1,"kfc","ktm","9841447");
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find restaurant.");
        restaurantService.getRestaurantById(restaurant.getId());
    }

    @Test
    public void getRestaurantByName_whenGetRestaurantByNameReturnNull_thenReturnDataNotFoundException(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find restaurantName.");
        restaurantService.getRestaurantByName(null);
    }
}
*/
