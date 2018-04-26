package com.foodorderingapp.responsedto;

public class UserResponseDto {

    private int userId;
    private String userPassword;
    private String email;
    private String address;
    private String firstName;
    private String middleName;
    private String lastName;
    private String contactNo;
    private String userRole;
    private double balance;

    public UserResponseDto(int userId, String userPassword, String email, String address, String firstName,
                           String middleName, String lastName, String contactNo, String userRole, double balance) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.address = address;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.userRole = userRole;
        this.balance = balance;
    }

    public  UserResponseDto(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserResponseDto that = (UserResponseDto) o;

        return userId == that.userId;
    }

    @Override
    public int hashCode() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "userId=" + userId +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", userRole='" + userRole + '\'' +
                ", balance=" + balance +
                '}';
    }
}
