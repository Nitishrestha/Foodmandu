package com.foodorderingapp.responsedto;

import java.util.Date;

public class UserListMapperDto {
    private int orderId;
    private int userId;
    private Date orderedDate;
    private boolean confirm;

    public UserListMapperDto(){

    }

    public UserListMapperDto(int orderId, int userId, Date orderedDate, boolean confirm) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderedDate = orderedDate;
        this.confirm = confirm;
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

        UserListMapperDto that = (UserListMapperDto) o;

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
