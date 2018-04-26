package com.foodorderingapp.requestdto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class LoginRequestDto {

    @NotBlank(message = "This field is required.")
    @Size(min=4,max=10,message = "userPassword must be between 4 and 10.")
    private String userPassword;
    @NotBlank(message = "This field is required.")
    @Email
    private String email;

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
}
