package com.foodorderingapp.dao;

import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.responsedto.UserListMapperDto;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;



import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UserDaoTest {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserDAO userDAO;

    User u;
    Orders o;

    @Before
    public void init() {
        u=new User("ram","bahadur","shah","ram",
                "ram@yahoo.com","91839183","ktm","user",1200);
        o=new Orders(true,true,new Date(),u);
    }

    public User addUser(){
        User user=userDAO.addUser(u);
        orderDAO.add(o);
        return user;
    }

    @Test
    public void addUser_whenAdded_thenReturnOK() {
        User user=addUser();
        Assert.assertNotNull(user);
    }

    @Test
    public void getUser_thenResultUserList() {
        addUser();
        List<User> userList = userDAO.getUsers();
        Assert.assertNotNull(userList);
    }

    @Test
    public void getUser_thenResultUser() {
        addUser();
        User user = userDAO.getUser(u.getUserId());
        Assert.assertNotNull(user);
    }

    @Test
    public void getUserByEmailId_thenResultUser() {
        addUser();
        User user = userDAO.getUserByEmailId(u.getEmail());
        Assert.assertNotNull(user);
    }

    @Test
    public void update_thenResultTrue() {
        User user=addUser();
        boolean b = userDAO.update(user);
        Assert.assertTrue(b);
    }

    @Test
    public void getUsersByUserForAMonth_thenResult_UserListMapperDtoList() {
        addUser();
        List<UserListMapperDto> userListMapperDtos = userDAO.getUsersByUserForAMonth(u.getUserId());
        Assert.assertNotNull(userListMapperDtos);
    }

    @Test
    public void getByUserForToday_thenResult_UserListMapperDtoList() {
        addUser();
        List<UserListMapperDto> userListMapperDtos = userDAO.getByUserForToday(u.getUserId());
        Assert.assertNotNull(userListMapperDtos);
    }

    @Test
    public void updateBalance_thenResultBalance() {
        addUser();
        boolean b = userDAO.updateBalance();
        Assert.assertTrue(b);
    }

    @Test(expected = DataNotFoundException.class)
    public void addUser_whenAddedReturnNull_thenResultUserNotFoundException() {
        userDAO.addUser(null);
        Assert.assertNull("This result will return null user",u);
    }

    @Test(expected = DataNotFoundException.class)
    public void getUsers_whenReturnNullOrSizeZero_thenResultDataNotFoundException() {
        List<User> userList = userDAO.getUsers();
        Assert.assertNull("This result will return null userList",userList);
    }

    @Test(expected = DataNotFoundException.class)
    public void getUser_whenNoUserIsFound_thenResultUserNotFoundException() {
        User user = userDAO.getUser(u.getUserId());
        Assert.assertNull("This result will return null userList",user);
    }

    @Test(expected = BadRequestException.class)
    public void update_whenUpdateFail_thenResultBadRequestException() {
        boolean b = userDAO.update(null);
        Assert.assertFalse(b);
    }

    @Test(expected = DataNotFoundException.class)
    public void getUsersByUserForAMonth_whenResultReturnNullOrSizeZero_thenResultDataNotFoundException() {
        List<UserListMapperDto> userListMapperDtos = userDAO.getUsersByUserForAMonth(u.getUserId());
        Assert.assertNull(userListMapperDtos);
    }

    @Test(expected = DataNotFoundException.class)
    public void getByUserForToday_whenResultReturnNullOrSizeZero_thenResultDataNotFoundException() {
        List<UserListMapperDto> userListMapperDtos = userDAO.getByUserForToday(u.getUserId());
        Assert.assertNull(userListMapperDtos);
    }
}