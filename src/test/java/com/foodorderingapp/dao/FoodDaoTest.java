package com.foodorderingapp.dao;

import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Restaurant;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional

public class FoodDaoTest {

    @Autowired
    private FoodDAO foodDAO;
    @Autowired
    private RestaurantDAO restaurantDAO;

    Food f;
    Restaurant res;

    @Before
    public void init() {
        res = new Restaurant("kfc","9827272","daubarmarg",true);
        f= new Food(1,"chowmein",100,res);
    }

    @Test
    public void addFoodsToRestaurant_whenAdded_thenReturnOK() {
        Restaurant restaurant = new Restaurant("kfc","9827272","daubarmarg",true);
        restaurantDAO.addRestaurant(restaurant);
        Food food=new Food(1,"chowmein",100,restaurant);
        List<Food> foodList= foodDAO.addFoodsToRestaurant(Arrays.asList(food));
        Assert.assertNotNull(foodList);
    }

    @Test
    public void deleteFood_thenReturnTrue() {
        addFoodAndRestaurant();
        boolean b=foodDAO.deleteFood(f);
        Assert.assertTrue(b);
    }

    @Test
    public void updateFood_thenReturnTrue() {
        addFoodAndRestaurant();
        boolean b=foodDAO.updateFood(f);
        Assert.assertTrue(b);
    }

    @Test
    public void getAll_thenReturnFoodList() {
        addFoodAndRestaurant();
        List<Food> foodList=foodDAO.getAll();
        Assert.assertNotNull(foodList);
    }

    @Test
    public void getFoodById_thenReturnFoodObjectRelatedToThatId() {
        addFoodAndRestaurant();
        Food food=foodDAO.getFoodById(f.getId());
        Assert.assertNotNull(food);
    }

    @Test
    public void getFoodByRestaurantId_thenReturnFoodListRelatedToThatId() {
        addFoodAndRestaurant();
        List<Food> foodList=foodDAO.getFoodByRestaurantId(f.getRestaurantId());
        Assert.assertNotNull(foodList);
    }

    @Test
    public void getFoodByResName_thenReturnFoodRelatedToThatName() {
        addFoodAndRestaurant();
        Food food=foodDAO.getFoodByResName(f.getRestaurant().getName(),f.getName());
        Assert.assertNotNull(food);
    }

    @Test
    public void getPaginatedFood_thenReturnListOfFood() {
         addFoodAndRestaurant();
        List<Food> foodList=foodDAO.getPaginatedFood(f.getRestaurantId(),0,1);
        Assert.assertNotNull(foodList);
    }

    @Test
    public void countFood_thenReturnNumberOfRestaurantPresentInDatabase() {
        long count=foodDAO.countFood(27);
        Assert.assertNotNull(count);
    }

    @Test(expected = DataNotFoundException.class)
    public void addFoodsToRestaurant_whenNullObjectOfFoodIsAdded_thenReturnDataNotFoundException() {
        List<Food> foodList= foodDAO.addFoodsToRestaurant(null);
        Assert.assertNull(foodList);
    }

    @Test(expected = BadRequestException.class)
    public void deleteFood_whenThatObjectOfFoodDoesntExit_thenReturnDataNotFoundException() {
        boolean b=foodDAO.deleteFood(null);
        Assert.assertFalse(b);
    }

    @Test(expected = BadRequestException.class)
    public void updateFood_whenThatObjectOfFoodDoesntExit_thenReturnDataNotFoundException() {
        boolean b=foodDAO.updateFood(null);
        Assert.assertFalse(b);
    }

    @Test(expected = DataNotFoundException.class)
    public void getAll_whenThereIsNoDataInDatabase_thenReturnDataNotFoundException() {
        List<Food> foodList=addFoodAndRestaurant();
        for(Food food:foodList){
            foodDAO.deleteFood(food);
        }
        List<Food> foodList1=foodDAO.getAll();
        Assert.assertNull(foodList1);
    }

    @Test(expected = DataNotFoundException.class)
    public void getFoodById_whenThereIsNoObjectOfFoodRelatedToThatId_thenReturnDataNotFoundException() {
        Food food=foodDAO.getFoodById(0);
        Assert.assertNull(food);
    }

    @Test(expected = DataNotFoundException.class)
    public void getFoodByRestaurantId_whenThereIsNoObjectOfFoodRelatedToThatId_thenReturnDataNotFoundException() {
        List<Food> foodList=foodDAO.getFoodByRestaurantId(f.getId());
        Assert.assertNull(foodList);
    }

    @Test(expected = DataNotFoundException.class)
    public void getFoodByResName_whenThereIsNoObjectOfFoodRelatedToThatName_thenReturnDataNotFoundException() {
        foodDAO.deleteAllFood();
        Food food=foodDAO.getFoodByResName(f.getRestaurant().getName(),f.getName());
        Assert.assertNull(food);
    }

    @Test(expected = DataNotFoundException.class)
    public void getPaginatedFood_whenThereIsNoObjectOfFoodRelatedToThatId_thenReturnDataNotFoundException() {
        foodDAO.deleteAllFood();
        List<Food> foodList=foodDAO.getPaginatedFood(f.getRestaurantId(),0,1);
        Assert.assertNull(foodList);
    }

    List<Food> addFoodAndRestaurant(){
        restaurantDAO.addRestaurant(res);
       List<Food> foodList= foodDAO.addFoodsToRestaurant(Arrays.asList(f));
       return foodList;
    }
}
