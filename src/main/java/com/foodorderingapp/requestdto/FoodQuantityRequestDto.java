package com.foodorderingapp.requestdto;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FoodQuantityRequestDto {

    @NotNull(message = "This field is required.")
    @Size(min=2,max=20,message = "food name must be between 2 and 20.")
    private String foodName;
    @Range(min = 1)
    private double foodPrice;
    @NotBlank(message = "This field is required.")
    @Size(min=1,max=10,message = "restaurant name must be between 2 and 20.")
    private String restaurantName;
    @Range(min = 1)
    private int quantity;

    public FoodQuantityRequestDto(String foodName, double foodPrice, String restaurantName, int quantity) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.restaurantName = restaurantName;
        this.quantity = quantity;
    }

    public FoodQuantityRequestDto() {
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodQuantityRequestDto that = (FoodQuantityRequestDto) o;

        return foodName != null ? foodName.equals(that.foodName) : that.foodName == null;
    }

    @Override
    public int hashCode() {
        return foodName != null ? foodName.hashCode() : 0;
    }
}
