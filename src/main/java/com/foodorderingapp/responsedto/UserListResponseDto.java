package com.foodorderingapp.responsedto;

import com.foodorderingapp.requestdto.FoodResRequestDto;

import java.util.Date;
import java.util.List;

public class UserListResponseDto {

    private int orderId;
    private int userId;
    private Date orderedDate;
    private boolean confirm;
    private List<FoodResRequestDto> foodResRequestDtoList;

    public UserListResponseDto(int orderId, int userId, Date orderedDate, boolean confirm, List<FoodResRequestDto> foodResRequestDtoList) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderedDate = orderedDate;
        this.confirm = confirm;
        this.foodResRequestDtoList = foodResRequestDtoList;
    }

    public UserListResponseDto() {
    }

    public boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<FoodResRequestDto> getFoodResRequestDtoList() {
        return foodResRequestDtoList;
    }

    public void setFoodResRequestDtoList(List<FoodResRequestDto> foodResRequestDtoList) {
        this.foodResRequestDtoList = foodResRequestDtoList;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserListResponseDto that = (UserListResponseDto) o;

        if (orderId != that.orderId) return false;
        return userId == that.userId;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + userId;
        return result;
    }
}
