package com.foodorderingapp.controller;

import com.foodorderingapp.commons.GenericResponse;
import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.service.FoodService;
import com.foodorderingapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.foodorderingapp.commons.WebUrlConstant.Restaurant.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(RESTAURANT)
public class
RestaurantController {

    private static RestaurantService restaurantService;
    private static FoodService foodService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, FoodService foodService) {
        this.restaurantService = restaurantService;
        this.foodService = foodService;
    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody @Valid Restaurant restaurant) {
        Restaurant restaurant1 = restaurantService.addRestaurant(restaurant);
        if(restaurant1==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(restaurant1, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurantList = restaurantService.getAll();
        if(restaurantList==null || restaurantList.size()==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(restaurantList, HttpStatus.OK);
    }

    @GetMapping(GET_FOOD_BY_RESTAURANT)
    public ResponseEntity<List<Food>> getFoodsByRestaurant(@PathVariable int id) {
        List<Food> foodList = foodService.getFoodByRestaurantId(id);
        if(foodList==null || foodList.size()==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    @GetMapping(ACTIVATE_RESTAURANT)
    public ResponseEntity<Boolean> activateRestaurant(@PathVariable int id) {
       boolean b=restaurantService.activate(id);
       if(b==false){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>(b,HttpStatus.OK);
    }

    @GetMapping(DEACTIVATE_RESTAURANT)
    public  ResponseEntity<Boolean>  deactivateRestaurant(@PathVariable int id) {
        boolean b= restaurantService.deactivate(id);
        if(b==false){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(b,HttpStatus.OK);
    }

    @GetMapping(GET_PAGINATED_RESTAURANT_TO_ADMIN)
    public ResponseEntity<GenericResponse> getPaginatedRestaurantToAdmin(@PathVariable int maxResult, @PathVariable int firstResult) {
    GenericResponse genericResponse = restaurantService.getPaginatedRestaurantToAdmin(firstResult,maxResult);
        if(genericResponse == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(genericResponse, HttpStatus.FOUND);
    }

    @GetMapping(GET_PAGINATED_RESTAURANT_TO_USER)
    public ResponseEntity<GenericResponse> getPaginatedRestaurantToUser(@PathVariable int userId,@PathVariable int firstResult, @PathVariable int maxResult) {
        GenericResponse genericResponse = restaurantService.getPaginatedRestaurantToUser(userId,firstResult,maxResult);
        if(genericResponse == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(genericResponse, HttpStatus.FOUND);
    }

    @GetMapping(GET_PAGINATED_FOOD)
    public ResponseEntity<GenericResponse> getPaginatedFood(@PathVariable int id, @PathVariable int firstResult, @PathVariable int maxResult) {
        GenericResponse genericResponse = foodService.getPaginatedFood(id,firstResult,maxResult);
        if(genericResponse == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(genericResponse, HttpStatus.FOUND);
    }


    @GetMapping(GET_RESTAURANT_BY_ID)
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable int id) {
         Restaurant restaurant=restaurantService.getRestaurantById(id);
         if(restaurant==null){
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
         return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }

    @DeleteMapping(DELETE_RESTAURANT_BY_ID)
    public ResponseEntity<Boolean> deleteRestaurant(@PathVariable int id) {
        boolean b=restaurantService.deleteRestaurant(id);
        if(b==false){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(b,HttpStatus.OK);
    }

    @PutMapping(UPDATE_RESTAURANT_BY_ID)
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable int id) {
        Restaurant restaurant1=restaurantService.updateRestaurant(restaurant, id);
        if(restaurant1==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(restaurant1,HttpStatus.OK);
    }
}