package com.foodorderingapp.dao;

import com.foodorderingapp.requestdto.OrderListMapperDto;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.DataConflictException;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest
public class OrderDaoTest {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserDAO userDAO;

    Orders o;
    User u;

    @Before
    public void init() {
        u=new User("shyam","bahadur","shah","shyam",
                "shyam@yahoo.com","91839183","ktm","user",1200);
        o=new Orders(true,true,new Date(),u);

    }

    public Orders addOrder(){
        userDAO.addUser(u);
      Orders orders=orderDAO.add(o);
      return orders;
    }

    @Test
    public void addOrder_whenAdded_thenReturnOK() {
        u=new User("ram","bahadur","shah","ram",
                "ram@yahoo.com","91839183","ktm","user",1200);
        userDAO.addUser(u);
        o=new Orders(true,true,new Date(),u);
        Orders orders= orderDAO.add(o);
        System.out.println(orders);
        Assert.assertEquals(orders, o);
    }

    @Test
    public void getOrderForAdminForMonth_thenReturnOrderListMapperDtoList() {
        addOrder();
        List<OrderListMapperDto> orderListMapperDtoList=orderDAO.getOrderForAdminForMonth();
        Assert.assertNotNull("This will return order log detail for a month", orderListMapperDtoList);
    }

    @Test
    public void getOrderLogForAdminForTodayThenReturnOrderListMapperDtoList() {
        addOrder();
        List<OrderListMapperDto> orderListMapperDtoList=orderDAO.getOrderLogForAdminForToday();
        Assert.assertNotNull("This will return order log detail for the current day",orderListMapperDtoList);
    }

    @Test
    public void updateThenResultTrue() {
        addOrder();
        boolean b=orderDAO.update(o);
        Assert.assertTrue("This will return true as boolean value",b);
    }

    @Test
    public void getOrderThenResultOrder() {
        addOrder();
        Orders orders=orderDAO.getOrder(o.getOrderId());
        Assert.assertNotNull(orders);
    }

    @Test(expected = BadRequestException.class)
    public void addOrder_whenResultIsNull_thenReturnDataNotFoundException() {
        o=null;
        Orders orders= orderDAO.add(o);
        Assert.assertNull("This will return null restaurant",orders);
    }

    @Test(expected = DataNotFoundException.class)
    public void getOrderForAdminForMonthWhenResultDataRelatedToThatMonthThenReturnDataNotFoundException() {
        List<OrderListMapperDto> orderListMapperDtoList = orderDAO.getOrderForAdminForMonth();
        Assert.assertNull("This will return null order.",orderListMapperDtoList);
    }

    @Test(expected = DataNotFoundException.class)
    public void getOrderForAdminForToday_whenResultDataRelatedToTheCurrentDay_thenReturnDataNotFoundException() {
        List<OrderListMapperDto> orderListMapperDtoList = orderDAO.getOrderLogForAdminForToday();
        Assert.assertNull("This will return null order.",orderListMapperDtoList);
    }

    @Test(expected = DataConflictException.class)
    public void update_thenResultFalse() {
      boolean b= orderDAO.update(null);
      Assert.assertFalse("This will return false",b);
    }
}
