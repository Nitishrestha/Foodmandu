package com.foodorderingapp.requestdto;

import java.util.Date;
import java.util.List;

public class OrderLogDto1 {

    private int orderId;
    private int userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date orderedDate;
    private String userRole;
    private List<FoodResRequestDto> foodResRequestDtoList;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
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

        OrderLogDto1 that = (OrderLogDto1) o;

        return userId == that.userId;
    }

    @Override
    public int hashCode() {
        return userId;
    }
}
