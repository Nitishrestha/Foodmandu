package com.foodorderingapp.requestdto;

public class FoodResRequestDto {

    private String foodName;
    private String restaurantName;
    private double foodPrice;
    private int quantity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodResRequestDto foodResRequestDto = (FoodResRequestDto) o;

        if (foodName != null ? !foodName.equals(foodResRequestDto.foodName) : foodResRequestDto.foodName != null) return false;
        return restaurantName != null ? restaurantName.equals(foodResRequestDto.restaurantName) : foodResRequestDto.restaurantName == null;
    }

    @Override
    public int hashCode() {
        int result = foodName != null ? foodName.hashCode() : 0;
        result = 31 * result + (restaurantName != null ? restaurantName.hashCode() : 0);
        return result;
    }
}
