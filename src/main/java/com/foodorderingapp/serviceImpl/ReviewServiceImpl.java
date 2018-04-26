package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.ReviewDAO;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.model.Review;
import com.foodorderingapp.model.User;
import com.foodorderingapp.requestdto.ReviewRequestDto;
import com.foodorderingapp.responsedto.MessageResponseDto;
import com.foodorderingapp.responsedto.ReviewResponseDto;
import com.foodorderingapp.responsedto.UserResponseDto;
import com.foodorderingapp.service.RestaurantService;
import com.foodorderingapp.service.ReviewService;
import com.foodorderingapp.service.UserService;
import com.foodorderingapp.utils.ReviewUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final ReviewDAO reviewDAO;

    @Autowired
    public ReviewServiceImpl(UserService userService, RestaurantService restaurantService,ReviewDAO reviewDAO) {
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.reviewDAO=reviewDAO;
    }

    @Override
    public MessageResponseDto addReview(int userId, int restaurantId, ReviewRequestDto reviewRequestDto) {
        User user=new User();
        Review review = reviewDAO.getReviewByUserAndRestaurantId(userId, restaurantId);
        UserResponseDto userResponseDto = userService.getUser(userId);
        BeanUtils.copyProperties(userResponseDto,user);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        if (review == null) {
            Review review1 = new Review();
            review1.setMessage(reviewRequestDto.getMessage());
            review1.setRating(reviewRequestDto.getRating());
            review1.setUser(user);
            review1.setRestaurant(restaurant);
            reviewDAO.addReview(review1);
            return new MessageResponseDto(user.getFirstName()+" has reviewed "+review1.getMessage()+" to "+restaurant.getName());
        } else {
            review.setMessage(reviewRequestDto.getMessage());
            review.setRating(reviewRequestDto.getRating());
            review.setUser(user);
            review.setRestaurant(restaurant);
            review.setReviewedDate(new Date());
            reviewDAO.updateReview(review);
            return new MessageResponseDto(user.getFirstName()+" has updated reviewed "+review.getMessage()+" written in "+restaurant.getName());
        }
    }

    @Override
    public MessageResponseDto deleteReview(int userId, int restaurantId) {
        Review review=reviewDAO.getReviewByUserAndRestaurantId(userId,restaurantId);
        reviewDAO.deleteReview(review);
        return new MessageResponseDto("review of "+review.getUser().getFirstName()+" has been deleted." );
    }

    @Override
    public List<ReviewResponseDto> getReviewByUserId(int userId) {
        List<ReviewResponseDto> reviewResponseDtos=new ArrayList<>();
        List<Review> reviewList=reviewDAO.getReviewByUserId(userId);
        if(reviewList==null||reviewList.size()==0){
            throw  new DataNotFoundException("review list of this user empty");
        }
        UserResponseDto userResponseDto=userService.getUser(userId);
        for(Review review:reviewList){
            ReviewResponseDto reviewResponseDto=ReviewUtils.getReviewResponseDto(review,userResponseDto);
            reviewResponseDtos.add(reviewResponseDto);
        }
        return reviewResponseDtos;
    }

    @Override
    public ReviewResponseDto getReviewByUserAndRestaurantId(int userId, int restaurantId) {
        UserResponseDto userResponseDto=userService.getUser(userId);
        Review review=reviewDAO.getReviewByUserAndRestaurantId(userId,restaurantId);
        ReviewResponseDto reviewResponseDto=ReviewUtils.getReviewResponseDto(review,userResponseDto);
        return reviewResponseDto;
    }

    @Override
    public List<ReviewResponseDto>  getAllReview() {
        List<ReviewResponseDto> reviewResponseDtos=new ArrayList<>();
        List<Review> reviewList=reviewDAO.getAllReview();
        for(Review review:reviewList){
            UserResponseDto userResponseDto=userService.getUser(review.getUser().getUserId());
            ReviewResponseDto reviewResponseDto=ReviewUtils.getReviewResponseDto(review,userResponseDto);
            reviewResponseDtos.add(reviewResponseDto);
        }
        return reviewResponseDtos;
    }
}
