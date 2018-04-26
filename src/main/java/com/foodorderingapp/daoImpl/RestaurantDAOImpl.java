package com.foodorderingapp.daoImpl;

import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataConflictException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Favorite;
import com.foodorderingapp.model.Restaurant;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
//                    .setHint("org.hibernate.cacheable",true)
                    .getResultList();
        } catch (RuntimeException ex) {
            throw new DataNotFoundException("cannot find restaurants");
        }
    }

    @Override
    public List<Restaurant> getPaginatedRestaurantToUser(int userId,int firstResult, int maxResult) {
        try {
            List<Restaurant> restaurantList = sessionFactory
                    .getCurrentSession()
                    .createNativeQuery("select R.*, F.favorated  from tbl_restaurant R " +
                            "left join tbl_favorite F on R.restaurant_id=F.restaurant_id" +
                            " where F.user_id=? order by F.favorated DESC", "RestaurantMapping")
                    .setParameter(1, userId)
                    .setFirstResult(firstResult * maxResult)
                    .setMaxResults(maxResult)
                    .getResultList();
            if(restaurantList==null||restaurantList.size()==0){
                throw new DataNotFoundException("cannot find list1.");
            }
            return restaurantList;
        }catch(Exception e){
            System.out.println("??????"+e.getMessage());
            throw new DataNotFoundException("cannot find list.");
        }

    }

    @Override
    public List<Restaurant> getPaginatedRestaurantToAdmin(int firstResult, int maxResult) {
        List<Restaurant> restaurantList = sessionFactory
                .getCurrentSession()
                .createQuery("From Restaurant", Restaurant.class)
                .setFirstResult(maxResult * firstResult)
                .setMaxResults(maxResult).getResultList();
        if (restaurantList == null || restaurantList.size() == 0) {
            throw new DataNotFoundException("cannot get paginated restaurant list");
        }
        return restaurantList;
    }


    public Restaurant getRestaurantById(int id) {

        try{
            Restaurant restaurant = sessionFactory.getCurrentSession().get(Restaurant.class, id);
            return restaurant;
        } catch (Exception ex){
            throw new DataNotFoundException("cannot find restaurant.");
        }
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