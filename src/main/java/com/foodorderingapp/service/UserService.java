package com.foodorderingapp.service;

import com.foodorderingapp.requestdto.LoginRequestDto;
import com.foodorderingapp.requestdto.UserRequestDto;
import com.foodorderingapp.responsedto.UserListMapperDto;
import com.foodorderingapp.model.User;
import com.foodorderingapp.responsedto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto addUser(UserRequestDto userRequestDto);
    List<UserResponseDto> getUsers();
    LoginRequestDto verifyUser(String userPassword, String email);
    User getUser(int userId);
    User update(User user);
    List<UserListMapperDto> getUsersByUserForAMonth(int userId);
    List<UserListMapperDto> getByUserForToday(int userId);
    void updateBalance();
    boolean deleteAll();
}
