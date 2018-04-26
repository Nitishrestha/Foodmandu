package com.foodorderingapp.dao;

import com.foodorderingapp.model.Review;

import java.util.List;

public interface ReviewDAO {
    void addReview(Review review);
    Review getReviewByUserAndRestaurantId(int userId , int restaurantId);
    boolean deleteReview(Review review);
    boolean updateReview(Review review);
    List<Review> getAllReview();
    List<Review> getReviewByUserId(int userId);
}
