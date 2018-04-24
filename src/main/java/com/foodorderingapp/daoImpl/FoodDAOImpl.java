package com.foodorderingapp.daoImpl;

import com.foodorderingapp.dao.FoodDAO;
import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Restaurant;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodDAOImpl implements FoodDAO {

    private final SessionFactory sessionFactory;
    private final RestaurantDAO restaurantDAO;

    @Autowired
    public FoodDAOImpl(SessionFactory sessionFactory, RestaurantDAO restaurantDAO) {
        this.sessionFactory = sessionFactory;
        this.restaurantDAO = restaurantDAO;
    }


    public List<Food> addFoodsToRestaurant(List<Food> foodList) {
        try {
            for (Food food : foodList) {
                food.getRestaurant().setId(food.getRestaurantId());
                sessionFactory
                        .getCurrentSession()
                        .save(food);
            }
            sessionFactory.getCurrentSession().flush();
            return foodList;

        } catch (Exception e) {
            throw new DataNotFoundException("cannot add food to restaurant" + e.getMessage());
        }
    }


    public boolean deleteFood(Food food) {
        try {
            sessionFactory.getCurrentSession().delete(food);
            return true;
        } catch (Exception e) {
            throw new BadRequestException("cannot delete food");
        }
    }

    public boolean updateFood(Food food) {
        try {
            sessionFactory.getCurrentSession().update(food);
            return true;
        } catch (Exception e) {
            throw new BadRequestException("cannot update food");
        }
    }

    public List<Food> getAll() {
        List<Food> foodList=sessionFactory
                .getCurrentSession()
                .createQuery("FROM Food", Food.class)
                .getResultList();
        if(foodList.size()==0||foodList==null){
            throw new DataNotFoundException("cannot find list");
        }
        return foodList;
    }

    public Food getFoodById(int id) {
        if (sessionFactory.getCurrentSession().get(Food.class, id) == null) {
            throw new DataNotFoundException("cannot find food");
        } else {
            return sessionFactory.getCurrentSession().get(Food.class, id);
        }
    }


    public List<Food> getFoodByRestaurantId(int id) {
        String query = "FROM Food WHERE restaurant= :restaurant";
        Restaurant restaurant = restaurantDAO.getRestaurantById(id);
        if (null == restaurant) {
            throw new DataNotFoundException("Restaurant Not found.");
        }
        System.out.println("food dao called");
        System.out.println(restaurant);
        return sessionFactory
                .getCurrentSession()
                .createQuery(query, Food.class)
                .setParameter("restaurant", restaurant)
                .getResultList();
    }


    public Food getFoodByResName(String restaurantName, String foodName) {
        try {
            return sessionFactory
                    .getCurrentSession()
                    .createQuery("FROM Food where restaurant.name=:restaurantName and name=:foodName", Food.class)
                    .setParameter("restaurantName", restaurantName)
                    .setParameter("foodName", foodName)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DataNotFoundException("foodName or restaurant is not in the list" + e.getMessage());
        }
    }

    @Override
    public List<Food> getPaginatedFood(int id, int firstResult, int maxResult) {
        List<Food> foodList = sessionFactory
                .getCurrentSession()
                .createQuery("FROM Food where restaurant.id = :id", Food.class)
                .setParameter("id", id)
                .setFirstResult(firstResult*maxResult)
                .setMaxResults(maxResult)
                .getResultList();
        if (foodList == null || foodList.size() == 0) {
            throw new DataNotFoundException("cannot find list of foods");
        }
        return foodList;
    }

    @Override
    public long countFood(int id) {
        try {
            long count = sessionFactory.getCurrentSession()
                    .createQuery("select count(1) from Food where restaurant.id = :id", Long.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return count;
        } catch (Exception e) {
            throw new DataNotFoundException("id is null");
        }
    }

    @Override
    public boolean deleteAllFood() {
        sessionFactory.getCurrentSession()
                .createQuery("delete from Food")
                .executeUpdate();
        return true;
    }
}