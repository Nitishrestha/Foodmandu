package com.foodorderingapp.controller;

import com.foodorderingapp.model.Food;
import com.foodorderingapp.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.foodorderingapp.commons.WebUrlConstant.Food.*;

@RestController
@RequestMapping(FOOD)
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public ResponseEntity<List<Food>> addFoods(@RequestBody List<Food> foodList) {
        List<Food> foodList1 = foodService.addFoodsToRestaurant(foodList);
        if(foodList1==null||foodList1.size()==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>(foodList1, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Food>> allFood() {
        List<Food> foodList = foodService.getAll();
        if(foodList==null || foodList.size()==0 ){
            return new ResponseEntity(foodList,HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    @GetMapping(GET_FOOD_BY_ID)
    public ResponseEntity<Food> getFoodBydId(@PathVariable int id) {
        Food food = foodService.getFoodById(id);
        if(food==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @DeleteMapping(DELETE_FOOD_BY_ID)
    public ResponseEntity<Boolean> deleteFood(@PathVariable int id) {
        boolean b=foodService.deleteFood(id);
        if(b==false){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @PutMapping(UPDATE_FOOD_BY_ID)
    public ResponseEntity<Boolean> updateFood(@RequestBody Food food, @PathVariable int id) {
        boolean b=foodService.updateFood(food, id);
        if(b==false){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
}