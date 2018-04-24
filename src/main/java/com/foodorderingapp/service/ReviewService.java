package com.foodorderingapp.service;

import com.foodorderingapp.requestdto.ReviewRequestDto;
import com.foodorderingapp.responsedto.MessageResponseDto;
import com.foodorderingapp.responsedto.ReviewResponseDto;

import java.util.List;

public interface ReviewService {

    MessageResponseDto addReview(int userId, int restaurantId, ReviewRequestDto review);
    MessageResponseDto deleteReview(int userId, int restaurantId);
    List<ReviewResponseDto> getReviewByUserId(int userId);
    ReviewResponseDto getReviewByUserAndRestaurantId(int userId,int restaurantId);
    List<ReviewResponseDto> getAllReview();
}
