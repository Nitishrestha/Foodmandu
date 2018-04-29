package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.commons.GenericResponse;
import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.FoodDAO;
import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.service.FoodService;
import jdk.nashorn.internal.runtime.ECMAException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class FoodServiceImpl implements FoodService {

    private final FoodDAO foodDAO;

    @Autowired
    public FoodServiceImpl(FoodDAO foodDAO) {
        this.foodDAO = foodDAO;
    }

    public boolean deleteFood(int foodId) {
        Food food = foodDAO.getFoodById(foodId);
        if (food == null) {
            throw new DataNotFoundException("cannot delete food.");
        }
        foodDAO.deleteFood(food);
        return true;
    }

    public boolean updateFood(Food food, int id) {
        Food food1 = foodDAO.getFoodById(id);
        if (food1 == null) {
            throw new DataNotFoundException("cannot find food.");
        }
        food1.setName(food.getName());
        food1.setPrice(food.getPrice());
        return foodDAO.updateFood(food1);
    }

    public List<Food> getAll() {
        List<Food> foodList = foodDAO.getAll();
        if (foodList == null) {
            throw new DataNotFoundException("cannot find foodlist.");
        } else {
            return foodList;
        }
    }

    public Food getFoodById(int id) {
        Food food = foodDAO.getFoodById(id);
        if (food == null) {
            throw new DataNotFoundException("cannot find food.");
        } else {
            return food;
        }
    }

    public List<Food> getFoodByRestaurantId(int id) {
        List<Food> foodList = foodDAO.getFoodByRestaurantId(id);
        return foodList;
    }

    public List<Food> addFoodsToRestaurant(List<Food> foodList) {
        Set<Food> foods = new HashSet<>(foodList);
        int restaurantId = foodList.get(0).getRestaurantId();
        List<Food> foodByRestaurantId = getFoodByRestaurantId(restaurantId);
        List<Food> newFoodList = new ArrayList<>();
        if (foodByRestaurantId == null || foodByRestaurantId.size() == 0) {
            List<Food> foodList1 = new ArrayList<>(foods);
            return foodDAO.addFoodsToRestaurant(foodList1);
        }
        for (Food food : foods) {
            if (!foodByRestaurantId.contains(food)) {
                newFoodList.add(food);
            }
        }
        if (newFoodList != null && newFoodList.size() > 0) {
            return foodDAO.addFoodsToRestaurant(newFoodList);
        }
        throw new DataNotFoundException("This restaurant already consists new food list!");
    }

    @Override
    public Food getFoodByResName(String restaurantName, String foodName) {

        Food food = foodDAO.getFoodByResName(restaurantName, foodName);
        if (food == null) {
            throw new DataNotFoundException("cannot find foodList1.");
        } else {
            return food;
        }
    }

    @Override
    public GenericResponse getPaginatedFood(int id, int firstResult, int maxResult) {
        PageModel pageModel = new PageModel();
        pageModel.setFirstResult(firstResult);
        pageModel.setMaxResult(maxResult);
        Long count = countFood(id);
        pageModel.setCount(count);
        List<Food> foodList = foodDAO.getPaginatedFood(id, firstResult, maxResult);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponseData(foodList);
        genericResponse.setPageModel(pageModel);
        return genericResponse;
    }

    @Override
    public long countFood(int id) {
        return foodDAO.countFood(id);
    }
}
