package com.foodorderingapp.service;

import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.requestdto.LoginRequestDto;
import com.foodorderingapp.requestdto.UserRequestDto;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.DataConflictException;
import com.foodorderingapp.model.User;
import com.foodorderingapp.responsedto.UserResponseDto;
import com.foodorderingapp.serviceImpl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl userService;

    LoginRequestDto loginRequestDto;
    User user;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
         loginRequestDto =new LoginRequestDto("ram","ram@yahoo.com");
         user=new User("ram","bahadur","shah","ram",
                 "ram@yahoo.com","91839183","ktm","user",1200);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    public User addUser(){
        userDAO.addUser(user);
        return  user;
    }

    @Test
    public void addUser_whenadded_thenResultOK(){
        UserRequestDto userRequestDto =new UserRequestDto();
        userRequestDto.setEmail("ram@ayahoo.com");
        when(userDAO.getUserByEmailId(userRequestDto.getEmail())).thenReturn(null);
        when(userDAO.addUser(new User())).thenReturn(new User());
        Assert.assertEquals(userService.addUser(new UserRequestDto()),new UserResponseDto());
    }

    @Test
    public void getList_thenReturnListOfUsers(){
        addUser();
        when(userDAO.getUsers()).thenReturn(Arrays.asList(user));
        Assert.assertNotNull(userService.getUsers());
    }

    @Test
    public void verifyUser_thenResultLoginDto(){
        when(userDAO.getUserByEmailId(user.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(user.getUserPassword(),user.getUserPassword())).thenReturn(true);
        Assert.assertEquals(userService.verifyUser(user.getUserPassword(),user.getEmail()).getEmail(), loginRequestDto.getEmail());
    }

    @Test
    public void getUser_thenResultUser(){
        User user=addUser();
        UserResponseDto userResponseDto=new UserResponseDto();
        BeanUtils.copyProperties(user,userResponseDto);
        when(userDAO.getUser(user.getUserId())).thenReturn(user);
        Assert.assertEquals(userService.getUser(user.getUserId()),userResponseDto);
    }

    @Test
    public void updateUser_thenResultUser(){
        User user=addUser();
        UserResponseDto userResponseDto=new UserResponseDto();
        BeanUtils.copyProperties(user,userResponseDto);
        when(userDAO.update(user)).thenReturn(true);
        Assert.assertEquals(userService.update(new UserRequestDto()),userResponseDto);
    }

    @Test
    public void verifyUser_whenGetUserByEmailId_thenResultNull(){
        expectedException.expectMessage("email not found.");
        userService.verifyUser(user.getUserPassword(),user.getEmail());
    }

    @Test
    public void verifyUser_whenPasswordDidntMatched_thenReturnUserConflictException(){
        when(userDAO.getUserByEmailId(user.getEmail())).thenReturn(user);
        expectedException.expect(DataConflictException.class);
        expectedException.expectMessage("userpassword didnt match.");
        userService.verifyUser(user.getUserPassword(),user.getEmail());
    }

    @Test
    public void getUsers_IfThereIsNoRecordInUserTable_thenResult(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find userList.");
        userService.getUsers();
    }

    @Test
    public void getUser_whenGetUser_thenResultNull(){
        expectedException.expectMessage("cannot find user.");
        userService.getUser(user.getUserId());
    }
}
