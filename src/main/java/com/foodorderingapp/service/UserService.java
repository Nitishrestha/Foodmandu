package com.foodorderingapp.service;

import com.foodorderingapp.requestdto.UserRequestDto;
import com.foodorderingapp.responsedto.UserListMapperDto;
import com.foodorderingapp.model.User;
import com.foodorderingapp.responsedto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto addUser(UserRequestDto userRequestDto);
    List<UserResponseDto> getUsers();
    UserResponseDto verifyUser(String userPassword, String email);
    UserResponseDto getUser(int userId);
    UserResponseDto update(UserRequestDto userRequestDto);
    List<UserListMapperDto> getUsersByUserForAMonth(int userId);
    List<UserListMapperDto> getByUserForToday(int userId);
    void updateBalance();
}
