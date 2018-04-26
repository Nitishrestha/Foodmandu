package com.foodorderingapp.responsedto;

import com.foodorderingapp.model.Food;

import java.util.List;

public class OrderResponseDto {

    private double balance;
    private List<Food> foodList;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public OrderResponseDto(double balance, List<Food> foodList) {
        this.balance = balance;
        this.foodList = foodList;
    }

    public OrderResponseDto(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderResponseDto orderResponseDto = (OrderResponseDto) o;

        return Double.compare(orderResponseDto.balance, balance) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(balance);
        return (int) (temp ^ (temp >>> 32));
    }
}
