package com.foodorderingapp.dao;

import com.foodorderingapp.Application;
import com.foodorderingapp.config.HibernateConfig;
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
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest(classes = {
        Application.class,
        HibernateConfig.class})
public class UserDaoTest {

    @Autowired
    private UserDAO userDAO;

    User u;

    @Before
    public void init() {
        u = new User();
        u.setUserId(1);
        u.setFirstName("Test");
        u.setLastName("Test");
        u.setUserRole("admin");
        u.setAddress("test");
        u.setEmail("test@gmail.com");
        u.setBalance(23);
        u.setUserPassword("tst");
        u.setMiddleName("test");
        u.setContactNo("039483984");
    }

    @Test
    public void addUser_whenAdded_thenReturnOK() {
        User user=new User();
        user.setUserId(1);
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setUserRole("admin");
        user.setAddress("test");
        user.setEmail("test@gmail.com");
        user.setBalance(23);
        user.setUserPassword("tst");
        user.setMiddleName("test");
        user.setContactNo("039483984");

        User user1 = userDAO.addUser(user);
//        User foundUser = userDAO.getUser(user.getUserId());
        System.out.println(user1);
        Assert.assertEquals(user1, user);
    }

    @Test
    public void getUser_thenResultUserList() {
        List<User> userList = userDAO.getUsers();
        Assert.assertEquals(userDAO.getUsers(), userList);
    }

    @Test
    public void getUser_thenResultUser() {
        User user = userDAO.getUser(17);
        Assert.assertNotNull(user);
    }

    @Test
    public void getUserByEmailId_thenResultUser() {
       u.setEmail("rowansiwakoti@gmail.com");
        User user = userDAO.getUserByEmailId(u.getEmail());
        assertThat(user).isEqualTo(u);
    }

    @Test
    public void update_thenResultTrue() {
        boolean b = userDAO.update(u);
        Assert.assertEquals(b, true);
    }

    @Test
    public void getUsersByUserForAMonth_thenResult_UserListMapperDtoList() {
        List<UserListMapperDto> userListMapperDtos = userDAO.getUsersByUserForAMonth(17);
        Assert.assertNotNull(userListMapperDtos);
    }

    @Test
    public void getByUserForToday_thenResult_UserListMapperDtoList() {
        List<UserListMapperDto> userListMapperDtos = userDAO.getByUserForToday(17);
        Assert.assertNotNull(userListMapperDtos);
    }

    @Test
    public void updateBalance_thenResultBalance() {
        boolean b = userDAO.updateBalance();
        Assert.assertTrue(b);
    }

    @Test(expected = DataNotFoundException.class)
    public void addUser_whenAddedReturnNull_thenResultUserNotFoundException() {
        u=null;
        User user = userDAO.addUser(u);
        Assert.assertNull(user);
    }

    @Test(expected = DataNotFoundException.class)
    public void getUsers_whenReturnNullOrSizeZero_thenResultDataNotFoundException() {
        userDAO.deleteAll();
        List<User> userList = userDAO.getUsers();
        Assert.assertNull(userDAO.getUsers());
    }

    @Test(expected = DataNotFoundException.class)
    public void getUser_whenNoUserIsFound_thenResultUserNotFoundException() {
        User user = userDAO.getUser(40);
        Assert.assertNull(user);
    }

    @Test(expected = BadRequestException.class)
    public void update_whenUpdateFail_thenResultBadRequestException() {
        u = null;
        boolean b = userDAO.update(u);
        Assert.assertFalse(b);
    }

    @Test(expected = DataNotFoundException.class)
    public void getUsersByUserForAMonth_whenResultReturnNullOrSizeZero_thenResultDataNotFoundException() {
        List<UserListMapperDto> userListMapperDtos = userDAO.getUsersByUserForAMonth(30);
        Assert.assertNull(userListMapperDtos);
    }

    @Test(expected = DataNotFoundException.class)
    public void getByUserForToday_whenResultReturnNullOrSizeZero_thenResultDataNotFoundException() {
        List<UserListMapperDto> userListMapperDtos = userDAO.getByUserForToday(30);
        Assert.assertNull(userListMapperDtos);
    }
}