package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.requestdto.LoginRequestDto;
import com.foodorderingapp.requestdto.UserRequestDto;
import com.foodorderingapp.responsedto.UserListMapperDto;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.DataConflictException;
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
            throw new DataConflictException("user already exit.");
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

    @Override
    public UserResponseDto verifyUser(String userPassword, String email) {
        User user = userDAO.getUserByEmailId(email);
        if (user == null) {
            throw new RuntimeException("email not found.");
        } else if (passwordEncoder.matches(userPassword, user.getUserPassword())) {
            UserResponseDto userResponseDto = new UserResponseDto();
            BeanUtils.copyProperties(user, userResponseDto);
            return userResponseDto;
        } else {
            throw new DataConflictException("userpassword didnt match.");
        }
    }

    @Override
    public UserResponseDto getUser(int userId) {
        UserResponseDto userResponseDto = new UserResponseDto();
        User user=userDAO.getUser(userId);
        if(user==null){
            throw new DataNotFoundException("cannot find user.");
        }
        BeanUtils.copyProperties(user, userResponseDto);
        return userResponseDto;
    }

    @Override
    public UserResponseDto update(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto=new UserResponseDto();
        getUserRequestDto(userRequestDto);
        BeanUtils.copyProperties(userRequestDto,userResponseDto);
        return userResponseDto;

    }

    public void getUserRequestDto(UserRequestDto userRequestDto){
        User user=new User();
        BeanUtils.copyProperties(userRequestDto,user);
        userDAO.update(user);
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
}
