package com.foodorderingapp.dao;

import com.foodorderingapp.model.Orders;
import com.foodorderingapp.model.User;
import com.foodorderingapp.requestdto.OrderDetailRequestDto;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.DataConflictException;
import com.foodorderingapp.model.OrderDetail;
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
@SpringBootTest
@Transactional
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserDAO userDAO;



    OrderDetail od;
    Orders o;
    User u;

    @Before
    public void init() {
        u=new User("ram","bahadur","shah","ram",
                "ram@yahoo.com","91839183","ktm","user",1200);
        o=new Orders(true,true,new Date(),u);
        od=new OrderDetail("momo",100,"kfc",1,o);
    }

    public OrderDetail addOrderDetail() {
        userDAO.addUser(u);
        orderDAO.add(o);
        OrderDetail orderDetail= orderDetailDAO.add(od);
        return orderDetail;
    }

    @Test
    public void addOrderDetail_whenAdded_thenReturnOK() {

        OrderDetail orderDetail=addOrderDetail();
        OrderDetail orderDetail1= orderDetailDAO.add(orderDetail);
        Assert.assertEquals(orderDetail, orderDetail1);
    }

    @Test
    public void getOrderDetail_thenReturnOrderDetailDtoList() {
        addOrderDetail();
        List<OrderDetailRequestDto> orderDetailRequestDtoList =orderDetailDAO.getOrderDetail();
        Assert.assertEquals(orderDetailRequestDtoList, orderDetailRequestDtoList);
    }

    @Test
    public void getOrderDetailByOrderId_thenReturnOrderDetailList() {
        addOrderDetail();
        List<OrderDetail> orderDetailList=orderDetailDAO.getOrderDetailByOrderId(o.getOrderId());
        Assert.assertEquals(orderDetailList, orderDetailList);
    }

    @Test
    public void updateOrderDetail_thenReturnTrue() {
        addOrderDetail();
        boolean b=orderDetailDAO.updateOrderDetail(od);
        Assert.assertTrue(b);
    }

    @Test
    public void getOrderDetailByUserId_thenReturnOrderDetail() {
        addOrderDetail();
        OrderDetail orderDetail=orderDetailDAO
                .getOrderDetailByUserId(u.getUserId(),od.getFoodName(),od.getRestaurantName());
        Assert.assertNotNull(orderDetail);
    }

    @Test(expected = BadRequestException.class)
    public void addOrderDetail_whenOrderIsNull_thenReturnDataNotFoundException() {
        OrderDetail orderDetail=orderDetailDAO.add(null);
        Assert.assertNull(orderDetail);
    }

    @Test(expected = DataNotFoundException.class)
    public void getOrderDetail_whenThereIsNoRecordsInOrderDetailTable_thenReturnDataNotFoundException() {
        List<OrderDetailRequestDto> orderDetailList=orderDetailDAO.getOrderDetail();
        Assert.assertNull(orderDetailList);
    }

    @Test(expected = DataNotFoundException.class)
    public void getOrderDetailByOrderId_whenThereIsNoRecordsRelatedToThatId_thenResultDataNotFoundException() {
        orderDetailDAO.deleteAllOrderDetail();
        List<OrderDetail> orderDetailList=orderDetailDAO.getOrderDetailByOrderId(o.getOrderId());
        Assert.assertNull(orderDetailList);
    }

    @Test(expected = DataConflictException.class)
    public void updateOrderDetail_whenThereIsNullObjectOfOrder_thenResultDataNotFoundException() {
       boolean b= orderDetailDAO.updateOrderDetail(null);
        Assert.assertFalse(b);
    }
}
