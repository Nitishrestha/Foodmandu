package com.foodorderingapp.dao;


import com.foodorderingapp.Application;
import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.config.HibernateConfig;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Food;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        Application.class,
        HibernateConfig.class})
@Transactional
public class FoodDaoTest {

    @Autowired
    private FoodDAO foodDAO;

    Food f;

    @Before
    public void init() {
        f = new Food();
        f.setName("momo");
        f.setPrice(100);
        f.setRestaurantId(10);
    }


    @Test
    public void addFoodsToRestaurant_whenAdded_thenReturnOK() {
        List<Food> foodList= foodDAO.addFoodsToRestaurant(Arrays.asList(f));
        Assert.assertEquals(foodList, Arrays.asList(f));
    }

    @Test
    public void deleteFood_thenReturnTrue() {
        boolean b=foodDAO.deleteFood(f);
        Assert.assertTrue(b);
    }

    @Test
    public void updateFood_thenReturnTrue() {
        boolean b=foodDAO.updateFood(f);
        Assert.assertTrue(b);
    }

    @Test
    public void getAll_thenReturnFoodList() {
        List<Food> foodList=foodDAO.getAll();
        Assert.assertNotNull(foodList);
    }

    @Test
    public void getFoodById_thenReturnFoodObjectRelatedToThatId() {
        Food food=foodDAO.getFoodById(21);
        Assert.assertNotNull(food);
    }

    @Test
    public void getFoodByRestaurantId_thenReturnFoodListRelatedToThatId() {
        List<Food> foodList=foodDAO.getFoodByRestaurantId(10);
        Assert.assertNotNull(foodList);
    }

    @Test
    public void getFoodByResName_thenReturnFoodRelatedToThatName() {
        Food food=foodDAO.getFoodByResName("KFC","Chicken Wings");
        Assert.assertNotNull(food);
    }

    @Test
    public void getPaginatedFood_thenReturnListOfFood() {
        List<Food> foodList=foodDAO.getPaginatedFood(8,0,1);
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

    /*@Test(expected = DataNotFoundException.class)
    public void getAll_whenThereIsNoDataInDatabase_thenReturnDataNotFoundException() {
        List<Food> foodList=foodDAO.getAll();
        Assert.assertNull(foodList);
    }*/

    @Test(expected = DataNotFoundException.class)
    public void getFoodById_whenThereIsNoObjectOfFoodRelatedToThatId_thenReturnDataNotFoundException() {
        Food food=foodDAO.getFoodById(22);
        Assert.assertNull(food);
    }

    @Test(expected = DataNotFoundException.class)
    public void getFoodByRestaurantId_whenThereIsNoObjectOfFoodRelatedToThatId_thenReturnDataNotFoundException() {
        List<Food> foodList=foodDAO.getFoodByRestaurantId(f.getId());
        Assert.assertNull(foodList);
    }

    @Test(expected = DataNotFoundException.class)
    public void getFoodByResName_whenThereIsNoObjectOfFoodRelatedToThatName_thenReturnDataNotFoundException() {
        Food food=foodDAO.getFoodByResName(f.getRestaurant().getName(),f.getName());
        Assert.assertNull(food);
    }

    @Test(expected = DataNotFoundException.class)
    public void getPaginatedFood_whenThereIsNoObjectOfFoodRelatedToThatId_thenReturnDataNotFoundException() {
        List<Food> foodList=foodDAO.getPaginatedFood(1,0,1);
        Assert.assertNull(foodList);
    }
}
