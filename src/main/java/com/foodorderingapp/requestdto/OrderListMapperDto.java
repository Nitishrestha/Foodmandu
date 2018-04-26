package com.foodorderingapp.requestdto;

import java.util.Date;

public class OrderListMapperDto {

    private int orderId;
    private int userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date orderedDate;
    private boolean confirm;

    public OrderListMapperDto(int orderId, int userId, String firstName, String middleName,
                              String lastName, Date orderedDate, boolean confirm) {
        this.orderId = orderId;
        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.orderedDate = orderedDate;
        this.confirm = confirm;
    }

    public OrderListMapperDto() {
    }

    public boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

        OrderListMapperDto that = (OrderListMapperDto) o;

        return orderId == that.orderId;
    }

    @Override
    public int hashCode() {
        return orderId;
    }
}
