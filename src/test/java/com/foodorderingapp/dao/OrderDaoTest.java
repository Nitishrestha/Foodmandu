package com.foodorderingapp.dao;

import com.foodorderingapp.Application;
import com.foodorderingapp.config.HibernateConfig;
import com.foodorderingapp.requestdto.OrderListMapperDto;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.UserConflictException;
import com.foodorderingapp.model.Orders;
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
@Transactional
@SpringBootTest(classes = {
        Application.class,
        HibernateConfig.class})
public class OrderDaoTest {

    @Autowired
    private OrderDAO orderDAO;

    Orders o;

    @Before
    public void init() {
        o = new Orders();
        o.setOrderId(1);
        o.setUser(new User());
        o.setConfirm(true);
        o.setOrderedDate(new Date());
        o.setWatched(true);
    }

    @Test
    public void addOrder_whenAdded_thenReturnOK() {
        Orders orders= orderDAO.add(o);
        System.out.println(orders);
        Assert.assertEquals(orders.getOrderId(), o.getOrderId());
    }

    @Test
    public void getOrderForAdminForMonth_thenReturnOrderListMapperDtoList() {
        List<OrderListMapperDto> orderListMapperDtoList=orderDAO.getOrderForAdminForMonth();
        Assert.assertEquals(orderListMapperDtoList, orderListMapperDtoList);
    }

    @Test
    public void getOrderLogForAdminForToday_thenReturnOrderListMapperDtoList() {
        List<OrderListMapperDto> orderListMapperDtoList=orderDAO.getOrderLogForAdminForToday();
        Assert.assertEquals(orderListMapperDtoList, orderListMapperDtoList);
    }

    @Test
    public void update_thenResultTrue() {
        boolean b=orderDAO.update(o);
        Assert.assertTrue(b);
    }

    @Test
    public void getOrder_thenResultOrder() {
        Orders orders=orderDAO.getOrder(65);
        Assert.assertNotNull(orders);
    }

    @Test(expected = BadRequestException.class)
    public void addOrder_whenResultIsNull_thenReturnDataNotFoundException() {
        o=null;
        Orders orders= orderDAO.add(o);
        Assert.assertNull(orders);
    }

    @Test(expected = DataNotFoundException.class)
    public void getOrderForAdminForMonth_whenResultIsNullOrSizeIsZero_thenReturnDataNotFoundException() {

        List<OrderListMapperDto> orderListMapperDtoList = orderDAO.getOrderForAdminForMonth();
        Assert.assertNull(orderListMapperDtoList);
    }

    @Test(expected = DataNotFoundException.class)
    public void getOrderForAdminForToday_whenResultIsNullOrSizeIsZero_thenReturnDataNotFoundException() {
        List<OrderListMapperDto> orderListMapperDtoList = orderDAO.getOrderLogForAdminForToday();
        Assert.assertNull(orderListMapperDtoList);
    }

    @Test(expected = UserConflictException.class)
    public void update_thenResultFalse() {
      boolean b= orderDAO.update(null);
        Assert.assertFalse(b);
    }
}
