package com.foodorderingapp.controller;
import com.foodorderingapp.requestdto.ReviewRequestDto;
import com.foodorderingapp.responsedto.MessageResponseDto;
import com.foodorderingapp.responsedto.ReviewResponseDto;
import com.foodorderingapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.foodorderingapp.commons.WebUrlConstant.Review.*;
import static com.foodorderingapp.commons.WebUrlConstant.Review.GET_REVIEW_BY_USERID;

@RestController
@RequestMapping(REVIEW)
public class ReviewController {

    private final ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(ADD_REVIEW)
    public ResponseEntity<MessageResponseDto> addReview(@PathVariable int userId, @PathVariable int restaurantId,
                                                        @RequestBody ReviewRequestDto reviewRequestDto){
        MessageResponseDto messageResponseDto=reviewService.addReview(userId,restaurantId,reviewRequestDto);
        return new ResponseEntity<>(messageResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getAllReview(){
        List<ReviewResponseDto> reviewResponseDtos=reviewService.getAllReview();
        return new ResponseEntity<>(reviewResponseDtos, HttpStatus.OK);
    }


    @DeleteMapping(DELETE_REVIEW)
    public ResponseEntity<MessageResponseDto> deleteReview(@PathVariable int userId, @PathVariable int restaurantId){
        MessageResponseDto messageResponseDto=reviewService.deleteReview(userId, restaurantId);
        return new ResponseEntity<>(messageResponseDto, HttpStatus.OK);
    }

    @GetMapping(GET_ALL_REVIEWS_BY_USERID)
    public ResponseEntity<List<ReviewResponseDto>> getReviewByRestaurant(@PathVariable int userId){
        List<ReviewResponseDto> reviewResponseDtos=reviewService.getReviewByUserId(userId);
        return new ResponseEntity<>(reviewResponseDtos, HttpStatus.OK);
    }

    @GetMapping(GET_REVIEW_BY_USERID)
    public ResponseEntity<ReviewResponseDto> getReviewByRestaurant(@PathVariable int userId,@PathVariable int restaurantId){
        ReviewResponseDto reviewResponseDto=reviewService.getReviewByUserAndRestaurantId(userId,restaurantId);
        return new ResponseEntity<>(reviewResponseDto, HttpStatus.OK);
    }
}
