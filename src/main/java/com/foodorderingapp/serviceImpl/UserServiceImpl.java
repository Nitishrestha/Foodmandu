package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.requestdto.LoginRequestDto;
import com.foodorderingapp.requestdto.UserRequestDto;
import com.foodorderingapp.responsedto.UserListMapperDto;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.UserConflictException;
import com.foodorderingapp.model.User;
import com.foodorderingapp.responsedto.UserResponseDto;
import com.foodorderingapp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO,BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder=passwordEncoder;
        this.userDAO = userDAO;
    }

    public UserResponseDto addUser(UserRequestDto userRequestDto) {

        User user1 = userDAO.getUserByEmailId(userRequestDto.getEmail());
        if (user1 == null) {
            User user = new User();
            user.setFirstName(userRequestDto.getFirstName());
            user.setMiddleName(userRequestDto.getMiddleName());
            user.setLastName(userRequestDto.getLastName());
            user.setContactNo(userRequestDto.getContactNo());
            user.setUserPassword(passwordEncoder.encode(userRequestDto.getUserPassword()));
            user.setAddress(userRequestDto.getAddress());
            user.setEmail(userRequestDto.getEmail());
            userDAO.addUser(user);
            UserResponseDto userResponseDto =new UserResponseDto();
            BeanUtils.copyProperties(user, userResponseDto);
            return userResponseDto;

        } else {
            throw new UserConflictException("user already exit.");
        }
    }

    public List<UserResponseDto> getUsers() {
        List<User> userList= userDAO.getUsers();

        if(userList==null || userList.size()==0){
            throw new DataNotFoundException("cannot find userList.");
        }else{
            List<UserResponseDto> userResponseDtoList =new ArrayList<>();
            for(User user:userList){
                UserResponseDto userResponseDto =new UserResponseDto();
                BeanUtils.copyProperties(user, userResponseDto);
                userResponseDtoList.add(userResponseDto);
            }
            return userResponseDtoList;
        }
    }

    public LoginRequestDto verifyUser(String userPassword, String email) {

        User user = userDAO.getUserByEmailId(email);
        if (user == null) {
            throw new DataNotFoundException("email not found.");
        } else if (passwordEncoder.matches(userPassword, user.getUserPassword())) {
            LoginRequestDto loginRequestDto = new LoginRequestDto();
            BeanUtils.copyProperties(user, loginRequestDto);
            System.out.println(loginRequestDto);
            return loginRequestDto;
        } else {
            throw new UserConflictException("userpassword didnt match.");
        }
    }

    public User getUser(int userId) {
        User user= userDAO.getUser(userId);
        if(user==null){
            throw new DataNotFoundException("user not found.");
        }
        return user;
    }

    public User update(User user) {
        if(userDAO.update(user)==true) {
            return user;
        }else{
            throw new UserConflictException("cannot update user.");
        }
    }

    @Override
    public List<UserListMapperDto> getUsersByUserForAMonth(int userId) {
        return  userDAO.getUsersByUserForAMonth(userId);
    }

    @Override
    public List<UserListMapperDto> getByUserForToday(int userId) {
        return userDAO.getByUserForToday(userId);
    }

    public void updateBalance(){
        userDAO.updateBalance();
    }

    @Override
    public boolean deleteAll() {
        return userDAO.deleteAll();
    }
}
