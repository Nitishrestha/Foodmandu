package com.foodorderingapp.daoImpl;

import com.foodorderingapp.dao.ReviewDAO;
import com.foodorderingapp.model.Review;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDAOImpl implements ReviewDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ReviewDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addReview(Review review) {
        sessionFactory.getCurrentSession().save(review);
    }

    @Override
    public Review getReviewByUserAndRestaurantId(int userId, int restaurantId) {
        try {
            Review review = sessionFactory.getCurrentSession()
                    .createQuery("from Review where user.userId=:userId and restaurant.id=:restaurantId", Review.class)
                    .setParameter("restaurantId", restaurantId)
                    .setParameter("userId", userId).getSingleResult();
            return review;
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public boolean deleteReview(Review review) {
        sessionFactory.getCurrentSession().delete(review);
        return true;
    }

    @Override
    public boolean updateReview(Review review) {
        sessionFactory.getCurrentSession().update(review);
        return true;
    }

    @Override
    public List<Review> getAllReview() {
        List<Review> reviewList=sessionFactory.getCurrentSession()
                .createQuery("from Review",Review.class).getResultList();
        return reviewList;
    }

    @Override
    public List<Review> getReviewByUserId(int userId) {
        List<Review> reviewList=sessionFactory.getCurrentSession()
                .createQuery("from Review where user.userId=:userId",Review.class)
                .setParameter("userId",userId)
                .getResultList();
        return reviewList;
    }
}
