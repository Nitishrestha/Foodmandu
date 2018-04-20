/*

package com.foodorderingapp.test;

import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.dto.UserDto;
import com.foodorderingapp.model.User;
import com.foodorderingapp.serviceImpl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UserTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private  BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
   public void testAdd(){

        User user=new User();
        user.setFirstName("hari");
        user.setMiddleName("bahadur");
        user.setLastName("rai");
        user.setUserPassword("hari");

        user.setEmail("hari1@yahoo.com");
        user.setAddress("ktm");
        user.setContactNo("981615475");

        UserDto dto=new UserDto();
        dto.setEmail("hari@yahoo.com");

        when(userDAO.getUserByEmailId(user.getEmail())).thenReturn(null);
        when(userDAO.addUser(user)).thenReturn(user);
        Assert.assertNotNull(userService.addUser(dto));
    }

    @Test
    public void testGetList(){
        List<User> userList=new ArrayList<>();

        User user1=new User();
        user1.setEmail("yy");
        user1.setUserPassword("op");
        userList.add(user1);

        User user2=new User();
        user2.setEmail("yy");
        user2.setUserPassword("op");
        userList.add(user2);

        when(userDAO.getUsers()).thenReturn(userList);
        Assert.assertEquals(userService.getUsers().size(),2);
    }

    @Test
    public void testVerifyUser(){
        User user=new User();
        user.setUserPassword("ram");
        user.setEmail("rr");

        when(userDAO.getUserByEmailId(user.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(anyString(),anyString())).thenReturn(true);
        Assert.assertEquals(userService.verifyUser(user.getUserPassword(),user.getEmail()).getEmail(),user.getEmail());
    }

    @Test
    public void testGetUser(){
        User user=new User();
        user.setUserId(1);
        when(userDAO.getUser(user.getUserId())).thenReturn(user);
        Assert.assertEquals(userService.getUser(user.getUserId()),user);
    }

    @Test
    public void testUpdateUser(){
        User user=new User();
        user.setUserId(1);
        when(userDAO.getUser(user.getUserId())).thenReturn(user);
        Assert.assertEquals(userService.update(user),user);
    }
}
*/
