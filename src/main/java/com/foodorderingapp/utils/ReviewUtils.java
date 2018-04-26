package com.foodorderingapp.utils;

import com.foodorderingapp.dao.RestaurantDAO;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.model.Review;
import com.foodorderingapp.responsedto.ReviewResponseDto;
import com.foodorderingapp.responsedto.UserResponseDto;

public class ReviewUtils {

    public static ReviewResponseDto getReviewResponseDto(Review review,UserResponseDto userResponseDto){
        ReviewResponseDto reviewResponseDto=new ReviewResponseDto();
        reviewResponseDto.setFirstName(userResponseDto.getFirstName());
        reviewResponseDto.setMiddleName(userResponseDto.getMiddleName());
        reviewResponseDto.setLastName(userResponseDto.getLastName());
        reviewResponseDto.setRestaurantName(review.getRestaurant().getName());
        reviewResponseDto.setMessage(review.getMessage());
        reviewResponseDto.setRating(review.getRating());
        reviewResponseDto.setReviewedDate(review.getReviewedDate());
        return reviewResponseDto;
    }
}
