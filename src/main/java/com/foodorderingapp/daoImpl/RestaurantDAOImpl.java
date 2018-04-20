package com.foodorderingapp.daoImpl;

import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;


@Repository
public class RestaurantDAOImpl implements RestaurantDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public RestaurantDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Restaurant addRestaurant(Restaurant restaurant) {
        try {
            sessionFactory.getCurrentSession().persist(restaurant);
            sessionFactory.getCurrentSession().flush();
            return restaurant;
        } catch (Exception e) {
            throw new BadRequestException("cannot add restaurant" + e.getMessage());
        }
    }

    public boolean deleteRestaurant(Restaurant restaurant) {
        try {
            sessionFactory.getCurrentSession().delete(restaurant);
            return true;
        } catch (Exception e) {
            throw new BadRequestException("restaurant doesn't exist");
        }
    }

    public boolean updateRestaurant(Restaurant restaurant) {
        try {
            sessionFactory
                    .getCurrentSession()
                    .update(restaurant);
            return true;
        } catch (Exception e) {
            throw new BadRequestException("cannot update restaurant");
        }
    }

    public List<Restaurant> getAll() {
        try {
            return sessionFactory
                    .getCurrentSession()
                    .createQuery("FROM Restaurant", Restaurant.class)
                    .getResultList();
        } catch (RuntimeException ex) {
            throw new DataNotFoundException("cannot find restaurants");
        }
    }


    @Override
    public List<Restaurant> getPaginatedRestaurantToUser(int firstResult, int maxResult) {
        List<Restaurant> restaurantList = sessionFactory
                .getCurrentSession().createQuery("From Restaurant where isActive = true", Restaurant.class)
                .setFirstResult(firstResult * maxResult)
                .setMaxResults(maxResult)
                .getResultList();
        if (restaurantList == null || restaurantList.size() == 0) {
            throw new DataNotFoundException("cannot get paginated restaurant list");
        }
        return restaurantList;
    }


    @Override
    public List<Restaurant> getPaginatedRestaurantToAdmin(int firstResult, int maxResult) {
        List<Restaurant> restaurantList = sessionFactory
                .getCurrentSession()
                .createQuery("From Restaurant", Restaurant.class)
                .setFirstResult(firstResult * maxResult)
                .setMaxResults(maxResult)
                .getResultList();
        if (restaurantList == null || restaurantList.size() == 0) {
            throw new DataNotFoundException("cannot get paginated restaurant list");
        }
        return restaurantList;
    }

    public Restaurant getRestaurantById(int id) {

        Restaurant restaurant = sessionFactory.getCurrentSession().get(Restaurant.class, id);
        if (restaurant == null) {
            throw new DataNotFoundException("cannot find restaurant.");
        }
        return restaurant;
    }

    public boolean deactivate(int id) {
        try {
            Restaurant restaurant = getRestaurantById(id);
            restaurant.setActive(false);
            updateRestaurant(restaurant);
            return true;
        } catch (Exception e) {
            throw new BadRequestException("cannot deactivate");
        }
    }

    public boolean activate(int id) {
        try {
            Restaurant restaurant = getRestaurantById(id);
            restaurant.setActive(true);
            updateRestaurant(restaurant);
            return true;
        } catch (Exception e) {
            throw new BadRequestException("cannot deactivate");
        }
    }

    public boolean getStatus(int id) {
        try {
            Restaurant restaurant =
                    sessionFactory
                            .getCurrentSession()
                            .createQuery("FROM Restaurant where id= :id", Restaurant.class)
                            .setParameter("id", id)
                            .getSingleResult();
            return restaurant.isActive();
        } catch (Exception e) {
            throw new BadRequestException("cannot get status");
        }
    }

    @Override
    public Restaurant getRestaurantByName(String restaurantName) {
        try {
            return sessionFactory
                    .getCurrentSession()
                    .createQuery("FROM Restaurant where name= :restaurantName", Restaurant.class)
                    .setParameter("restaurantName", restaurantName)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new DataNotFoundException("cannot find restaurantName");
        }
    }

    @Override
    public long countRestaurant() {
        try {
            return sessionFactory
                    .getCurrentSession()
                    .createQuery("select count(1) from  Restaurant", Long.class)
                    .getSingleResult();

        } catch (Exception ex) {
            throw new DataNotFoundException("no record found");
        }
    }

    @Override
    public long countActiveRestaurant() {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("select count(1) from Restaurant where isActive=true", Long.class)
                    .getSingleResult();

        } catch (Exception ex) {
            throw new DataNotFoundException("no record found");
        }
    }
}