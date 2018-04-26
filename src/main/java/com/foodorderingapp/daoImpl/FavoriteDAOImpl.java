package com.foodorderingapp.daoImpl;

import com.foodorderingapp.dao.FavoriteDAO;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Favorite;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoriteDAOImpl implements FavoriteDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public FavoriteDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addFavorite(Favorite favorite) {
        sessionFactory.getCurrentSession().save(favorite);
    }

    @Override
    public Favorite getUserAndRestaurantIds(int userId, int restaurantId) {
        try {
            Favorite favorite = sessionFactory.getCurrentSession()
                    .createQuery("from Favorite  where user.userId=:userId " +
                            "and restaurant.id=:restaurantId", Favorite.class)
                    .setParameter("userId", userId)
                    .setParameter("restaurantId", restaurantId)
                    .getSingleResult();
            return favorite;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(Favorite favorite) {
        sessionFactory.getCurrentSession().update(favorite);
        return true;
    }

    @Override
    public List<Favorite> getFavoriteRestaurantByUserId(int userId) {
        List<Favorite> favoriteList=sessionFactory.getCurrentSession()
                .createQuery("from Favorite where user.userId=:userId and favorated=true")
                .setParameter("userId",userId).getResultList();
        if(favoriteList==null||favoriteList.size()==0){
            throw new DataNotFoundException("cannot find user favorite list.");
        }
        return favoriteList;

    }

    @Override
    public boolean delete(Favorite favorite) {
        sessionFactory.getCurrentSession().delete(favorite);
        return true;
    }

    @Override
    public List<Favorite> getFavoriteByRestaurantId(int restarurantId) {
        List<Favorite> favoriteList=sessionFactory.getCurrentSession()
                .createQuery("from Favorite where restaurant.id=:restarurantId and favorated=true")
                .setParameter("restarurantId",restarurantId).getResultList();
        if(favoriteList==null||favoriteList.size()==0){
            throw new DataNotFoundException("cannot find favorite restaurant of users.");
        }

        return favoriteList;
    }
}
