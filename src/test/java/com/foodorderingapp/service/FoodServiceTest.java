package com.foodorderingapp.service;

import com.foodorderingapp.dao.FoodDAO;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.serviceImpl.FoodServiceImpl;
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
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

public class FoodServiceTest {

    @Mock
    private FoodDAO foodDAO;

    @InjectMocks
    FoodServiceImpl foodService;

    Food food;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
         food=new Food(1,"momo",200,new Restaurant());
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void addFoodsToRestaurant_thenResultFoodList(){

        when(foodDAO.addFoodsToRestaurant(Arrays.asList(food))).thenReturn(Arrays.asList(food));
        System.out.println(Arrays.asList(new Food()).size());
        Assert.assertEquals(foodService.addFoodsToRestaurant(Arrays.asList(food)),Arrays.asList(food));
    }

    @Test
    public void deleteFood_thenResultTrue(){
        when(foodDAO.getFoodById(food.getId())).thenReturn(food);
        when(foodDAO.deleteFood(food)).thenReturn(true);
        Assert.assertTrue(foodService.deleteFood(food.getId()));
    }

    @Test
    public void updateFood_thenResultTrue(){
        when(foodDAO.getFoodById(food.getId())).thenReturn(food);
        when(foodDAO.updateFood(food)).thenReturn(true);
        Assert.assertTrue(foodService.updateFood(food,food.getId()));
    }

    @Test
    public void getAll_thenResultFoodList(){
        List<Food> foodList=new ArrayList<>();
        when(foodDAO.getAll()).thenReturn(foodList);
        Assert.assertEquals(foodService.getAll(),foodList);
    }

    @Test
    public void getFoodById_thenResultFood(){
        when(foodDAO.getFoodById(food.getId())).thenReturn(food);
        Assert.assertEquals(foodService.getFoodById(food.getId()),food);
    }

    @Test
    public void getFoodByRestaurantId_thenResultFoodList(){
        when(foodDAO.getFoodByRestaurantId(food.getId())).thenReturn(Arrays.asList(food));
        Assert.assertEquals(foodService.getFoodByRestaurantId(food.getId()),Arrays.asList(food));
    }


    @Test
    public void getFoodByResName_thenResultFood(){
        when(foodDAO.getFoodByResName(food.getName(),food.getRestaurant().getName())).thenReturn(food);
        Assert.assertEquals(foodService.getFoodByResName(food.getName(),food.getRestaurant().getName()),food);
    }

    @Test
    public void getPaginatedFood_thenResultNotNull(){
        when(foodDAO.getPaginatedFood(1,0,1)).thenReturn(Arrays.asList(food));
        Assert.assertNotNull(foodService.getPaginatedFood(1,0,1));
    }


    @Test
    public void addFoodToRestaurant_whenFoodListIsNull_thenReturnDataNotFoundException()
    {
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot add foodList.");
        foodService.addFoodsToRestaurant(new ArrayList<>());
    }


    @Test
    public void deleteFood_whenFoodIsNull_thenReturnDataNotFoundException(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot delete food.");
        foodService.deleteFood(anyInt());
    }

    @Test
    public void updateFood_whenGetFoodByIdIsNull_thenReturnDataNotFoundException(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find food.");
        foodService.updateFood(food,anyInt());
    }

    @Test
    public void getAll_whenFoodListIsNullOrSizeIsZero_thenReturnDataNotFoundExcetion(){
        when(foodDAO.getAll()).thenReturn(null);
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find foodlist.");
        foodService.getAll();
    }

    @Test
    public void getFoodById_whenGetFoodByIdReturnsInvalidId_thenReturnDataNotFoundException(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find food.");
        foodService.getFoodById(anyInt());

    }

    @Test
    public void getFoodByRestaurantId_whenGetFoodByRestaurantId_thenReturnDataNotFoundException(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find foodList.");
        foodService.getFoodByRestaurantId(food.getId());
    }

    @Test
    public void getFoodByResName_whenGetFoodByResNameReturnInvalidParameter_thenReturnNull(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find foodList1.");
        foodService.getFoodByResName(food.getName(),food.getRestaurant().getName());
    }
}
