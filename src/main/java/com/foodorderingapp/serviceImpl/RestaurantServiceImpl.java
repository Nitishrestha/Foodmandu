package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.commons.GenericResponse;
import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.UserConflictException;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.service.RestaurantService;
import com.foodorderingapp.service.StorageService;
import com.foodorderingapp.utils.FileUtil;
import com.foodorderingapp.utils.RestaurantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

/**
 * Created by TOPSHI KREATS on 11/29/2017.
 */

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantDAO restaurantDAO;
    private final StorageService storageService;

    @Autowired
    public RestaurantServiceImpl(RestaurantDAO restaurantDAO, StorageService storageService) {
        this.restaurantDAO = restaurantDAO;
        this.storageService = storageService;
    }

    @Override
    public Restaurant addRestaurantWithImage(MultipartHttpServletRequest request) {
        Restaurant newRestaurant = RestaurantUtil.getRestaurant(request);
        MultipartFile multipartFile = FileUtil.getFile(request);
        newRestaurant.setFile(multipartFile);
        if (multipartFile.getName() == "NOIMAGE") {
            newRestaurant.setRestaurantCode(multipartFile.getName());
        }
        Restaurant restaurant1 = restaurantDAO.addRestaurant(newRestaurant);
        storageService.store(multipartFile, restaurant1.getRestaurantCode());
        if (restaurant1 == null) {
            throw new DataNotFoundException("Can not add Restaurant!");
        }
        return restaurant1;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        Restaurant restaurant1 = restaurantDAO.addRestaurant(restaurant);
        if (restaurant1 == null) {
            throw new DataNotFoundException("cannot add restaurant.");
        } else {
            return restaurant1;
        }
    }

    public boolean deleteRestaurant(int id) {
        Restaurant restaurant = restaurantDAO.getRestaurantById(id);
        boolean b = restaurantDAO.deleteRestaurant(restaurant);
        if (b == false) {
            throw new UserConflictException("cannot delete restaurant.");
        } else {
            return true;
        }
    }

    //old
    public Restaurant updateRestaurant(Restaurant restaurant, int id) {
        Restaurant restaurantFromTable = restaurantDAO.getRestaurantById(id);
        if (restaurantFromTable == null) {
            throw new UserConflictException("cannot update restaurant.");
        }
        restaurantFromTable.setName(restaurant.getName());
        restaurantFromTable.setAddress(restaurant.getAddress());
        restaurantFromTable.setContact(restaurant.getContact());
        restaurantDAO.updateRestaurant(restaurantFromTable);
        return restaurant;
    }

    @Override
    public Restaurant updateRestaurant(MultipartHttpServletRequest request, int id) {
        MultipartFile file = FileUtil.getFile(request);
        Restaurant restaurant = null;
        Restaurant restaurantFromTable = restaurantDAO.getRestaurantById(id);

        Restaurant requestRestaurant = RestaurantUtil.getRestaurant(request);

        if (restaurantFromTable.getRestaurantCode().equalsIgnoreCase("NOIMAGE")) {
            if (file.getName().equalsIgnoreCase("NOIMAGE")) {
                requestRestaurant.setRestaurantCode(file.getName());
                requestRestaurant.setFile(file);
                restaurant = RestaurantUtil.copyRestaurant(restaurantFromTable, requestRestaurant);
                return restaurant;
            }
        }

        if (restaurantFromTable.getRestaurantCode().equalsIgnoreCase("NOIMAGE") && !file.getName().equalsIgnoreCase("NOIMAGE")) {
            requestRestaurant.setRestaurantCode(file.getName());
            requestRestaurant.setFile(file);
            storageService.store(file, requestRestaurant.getRestaurantCode());
            restaurant = RestaurantUtil.copyRestaurant(restaurantFromTable, requestRestaurant);
            return restaurant;
        }
        if (!restaurantFromTable.getRestaurantCode().isEmpty()) {
            if (file.getName().equalsIgnoreCase("NOIMAGE")) {
                requestRestaurant.setFile(restaurantFromTable.getFile());
                requestRestaurant.setRestaurantCode(restaurantFromTable.getRestaurantCode());
                restaurant = RestaurantUtil.copyRestaurant(restaurantFromTable, requestRestaurant);
                return restaurant;
            }
        }

        if (!restaurantFromTable.getRestaurantCode().isEmpty()) {
            if (!file.getName().equalsIgnoreCase("NOIMAGE")) {
                System.out.println("Hello");
                requestRestaurant.setFile(file);
                restaurant = RestaurantUtil.copyRestaurant(restaurantFromTable, requestRestaurant);
                storageService.store(file, requestRestaurant.getRestaurantCode());
                return restaurant;
            }
        }

        if (restaurantFromTable == null) {
            throw new UserConflictException("cannot update restaurant.");
        }
        restaurantDAO.updateRestaurant(restaurant);
        return requestRestaurant;
    }

    public List<Restaurant> getAll() {
        List<Restaurant> restaurantList = restaurantDAO.getAll();
        if (restaurantList == null || restaurantList.size() == 0) {
            throw new DataNotFoundException("cannot find restaurantList.");
        } else {
            return restaurantList;
        }
    }

    @Override
    public GenericResponse getPaginatedRestaurantToUser(int firstResult, int maxResult) {
        PageModel pageModel = new PageModel();
        pageModel.setFirstResult(firstResult);
        pageModel.setMaxResult(maxResult);
        Long count = countActiveRestaurant();
        pageModel.setCount(count);
        List<Restaurant> restaurantList = restaurantDAO.getPaginatedRestaurantToUser(firstResult, maxResult);
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
        List<Restaurant> restaurantList = restaurantDAO.getPaginatedRestaurantToAdmin(firstResult, maxResult);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponseData(restaurantList);
        genericResponse.setPageModel(pageModel);
        return genericResponse;
    }

    public Restaurant getRestaurantById(int id) {
        Restaurant restaurant = restaurantDAO.getRestaurantById(id);
        if (restaurant == null) {
            throw new DataNotFoundException("cannot find restaurant.");
        } else {
            return restaurant;
        }
    }

    public boolean deactivate(int id) {
        Restaurant restaurant = getRestaurantById(id);
        if (restaurant == null) {
            return false;
        }
        restaurant.setActive(false);
        return true;
    }

    public boolean activate(int id) {
        Restaurant restaurant = getRestaurantById(id);
        if (restaurant == null) {
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
        Restaurant restaurant = restaurantDAO.getRestaurantByName(restaurantName);
        if (restaurant == null) {
            throw new DataNotFoundException("cannot find restaurantName.");
        } else {
            return restaurant;
        }
    }

    @Override
    public Long countRestaurant() {
        return restaurantDAO.countRestaurant();
    }

    @Override
    public long countActiveRestaurant() {
        return restaurantDAO.countActiveRestaurant();
    }

}