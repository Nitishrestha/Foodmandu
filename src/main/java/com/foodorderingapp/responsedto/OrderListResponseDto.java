package com.foodorderingapp.responsedto;

import com.foodorderingapp.requestdto.FoodResRequestDto;

import java.util.Date;
import java.util.List;

public class OrderListResponseDto {

    private int orderId;
    private int userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date orderedDate;
    private Boolean confirm;
    private List<FoodResRequestDto> foodResRequestDtoList;

    public OrderListResponseDto(int orderId, int userId, String firstName,
                                String middleName, String lastName, Date orderedDate,
                                Boolean confirm, List<FoodResRequestDto> foodResRequestDtoList) {
        this.orderId = orderId;
        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.orderedDate = orderedDate;
        this.confirm = confirm;
        this.foodResRequestDtoList = foodResRequestDtoList;
    }

    public OrderListResponseDto() {
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

        OrderListResponseDto that = (OrderListResponseDto) o;

        return orderId == that.orderId;
    }

    @Override
    public int hashCode() {
        return orderId;
    }
}
