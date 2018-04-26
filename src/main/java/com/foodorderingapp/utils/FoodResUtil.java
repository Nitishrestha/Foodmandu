package com.foodorderingapp.utils;

import com.foodorderingapp.requestdto.FoodResRequestDto;
import com.foodorderingapp.model.OrderDetail;

public class FoodResUtil {

    public static FoodResRequestDto addFoodRes(OrderDetail orderDetail){
        FoodResRequestDto foodResRequestDto =new FoodResRequestDto();
        foodResRequestDto.setRestaurantName(orderDetail.getRestaurantName());
        foodResRequestDto.setQuantity(orderDetail.getQuantity());
        foodResRequestDto.setFoodPrice(orderDetail.getFoodPrice());
        foodResRequestDto.setFoodName(orderDetail.getFoodName());
        return foodResRequestDto;
    }
}
