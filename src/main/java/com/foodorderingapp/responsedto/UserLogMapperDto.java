package com.foodorderingapp.responsedto;

import java.util.Date;

public class UserLogMapperDto {


    private String foodName;
    private String restaurantName;
    private int foodPrice;
    private Date orderedDate;

    public UserLogMapperDto(){

    }

    public UserLogMapperDto(String foodName, String restaurantName, int foodPrice, Date orderedDate) {
        this.foodName = foodName;
        this.restaurantName = restaurantName;
        this.foodPrice = foodPrice;
        this.orderedDate = orderedDate;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }
}
