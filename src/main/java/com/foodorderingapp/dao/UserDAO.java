package com.foodorderingapp.dao;

import com.foodorderingapp.responsedto.UserListMapperDto;
import com.foodorderingapp.model.User;

import java.util.List;

public interface UserDAO {
    User addUser(User user);
    List<User> getUsers();
    User getUser(int userId);
    User getUserByEmailId(String email);
    boolean update(User user);
    List<UserListMapperDto> getUsersByUserForAMonth(int userId);
    List<UserListMapperDto> getByUserForToday(int userId);
    boolean updateBalance();
    boolean deleteUser(User user);
}
