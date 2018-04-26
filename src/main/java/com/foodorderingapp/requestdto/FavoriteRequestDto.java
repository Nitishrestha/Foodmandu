package com.foodorderingapp.requestdto;

import javax.validation.constraints.NotNull;

public class FavoriteRequestDto {

    @NotNull(message = "This field is required.")
    private int userId;
    @NotNull(message = "This field is required.")
    private int restaurantId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
