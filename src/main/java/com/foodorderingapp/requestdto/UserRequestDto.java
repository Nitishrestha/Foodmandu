package com.foodorderingapp.requestdto;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class UserRequestDto {

    private int userId;
    @NotBlank(message = "This field is required.")
    private String userPassword;
    @NotBlank(message = "This field is required.")
    @Email
    private String email;
    @NotBlank(message = "This field is required.")
    @Size(min=3,max=40,message = "address must be between 3 and 40.")
    private String address;
    @NotBlank(message = "This field is required.")
    @Size(min=2,max=20,message = "first name must be between 2 and 20.")
    private String firstName;
    @Size(min=1,max=20,message = "middle name must be between 2 and 20.")
    private String middleName;
    @NotBlank(message = "This field is required.")
    @Size(min=2,max=20,message = "last name must be between 2 and 20.")
    private String lastName;
    @NotBlank(message = "This field is required.")
    @Size(min=7,max=10,message = "middle name must be between 2 and 20.")
    private String contactNo;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRequestDto userRequestDto = (UserRequestDto) o;

        return email != null ? email.equals(userRequestDto.email) : userRequestDto.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
