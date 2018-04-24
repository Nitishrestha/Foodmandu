package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.commons.GenericResponse;
import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.DataConflictException;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantDAO restaurantDAO;

    @Autowired
    public RestaurantServiceImpl(RestaurantDAO restaurantDAO){
        this.restaurantDAO=restaurantDAO;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
       Restaurant restaurant1= restaurantDAO.addRestaurant(restaurant);
        if (restaurant1==null) {
            throw new DataNotFoundException("cannot add restaurant.");
        } else {
            return restaurant1;
        }
    }


    public boolean deleteRestaurant(int id) {
        Restaurant restaurant=restaurantDAO.getRestaurantById(id);
        boolean b=restaurantDAO.deleteRestaurant(restaurant);
        if (b==false) {
            throw new DataConflictException("cannot delete restaurant.");
        } else {
            return true;
        }
    }

    public Restaurant updateRestaurant(Restaurant restaurant, int id) {
        Restaurant restaurant1 =restaurantDAO.getRestaurantById(id);
        if(restaurant1==null){
            throw new DataConflictException("cannot update restaurant.");
        }
        restaurant1.setName(restaurant.getName());
        restaurant1.setAddress(restaurant.getAddress());
        restaurant1.setContact(restaurant.getContact());
         restaurantDAO.updateRestaurant(restaurant1);
         return restaurant;
    }
    public List<Restaurant> getAll() {

        List<Restaurant> restaurantList= restaurantDAO.getAll();
        if(restaurantList==null || restaurantList.size()==0){
            throw new DataNotFoundException("cannot find restaurantList.");
        }else{
            return restaurantList;
        }
    }

    @Override
    public GenericResponse getPaginatedRestaurantToUser(int userId,int firstResult, int maxResult) {
        PageModel pageModel = new PageModel();
        pageModel.setFirstResult(firstResult);
        pageModel.setMaxResult(maxResult);
        Long count = countActiveRestaurant();
        pageModel.setCount(count);
        List<Restaurant> restaurantList = restaurantDAO.getPaginatedRestaurantToUser(userId,firstResult,maxResult);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponseData(restaurantList);
        genericResponse.setPageModel(pageModel);
        return genericResponse;
    }

    @Override
    public GenericResponse getPaginatedRestaurantToAdmin(int firstResult, int maxResult) {
        PageModel pageModel = new PageModel();
        pageModel.setFirstResult(firstResult);
        pageModel.setMaxResult(maxResult);
        Long count = countRestaurant();
        pageModel.setCount(count);
        List<Restaurant> restaurantList = restaurantDAO.getPaginatedRestaurantToAdmin(maxResult,firstResult);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponseData(restaurantList);
        genericResponse.setPageModel(pageModel);
        return genericResponse;
    }

    public Restaurant getRestaurantById(int id) {
        Restaurant restaurant= restaurantDAO.getRestaurantById(id);
        if(restaurant==null){
            throw new DataNotFoundException("cannot find restaurant.");
        }else{
            return restaurant;
        }
    }

    public boolean deactivate(int id) {
        Restaurant restaurant = getRestaurantById(id);
        if(restaurant==null){
            return false;
        }
        restaurant.setActive(false);
        return true;
    }

    public boolean activate(int id) {
        Restaurant restaurant = getRestaurantById(id);
        if(restaurant==null){
            return false;
        }
        restaurant.setActive(true);
        return true;
    }

    public boolean getStatus(int id) {
        return restaurantDAO.getStatus(id);
    }

    @Override
    public Restaurant getRestaurantByName(String restaurantName) {

        Restaurant restaurant= restaurantDAO.getRestaurantByName(restaurantName);
        if(restaurant==null){
            throw new DataNotFoundException("cannot find restaurantName.");
        }else{
            return  restaurant;
        }
    }
    @Override
    public Long countRestaurant(){
        return  restaurantDAO.countRestaurant();
    }

    @Override
    public long countActiveRestaurant() {
        return restaurantDAO.countActiveRestaurant();
    }
}