package com.foodorderingapp.dao;

import com.foodorderingapp.Application;
import com.foodorderingapp.config.HibernateConfig;
import com.foodorderingapp.requestdto.OrderDetailRequestDto;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.UserConflictException;
import com.foodorderingapp.model.OrderDetail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        Application.class,
        HibernateConfig.class})
@Transactional
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    OrderDetail od;

    @Before
    public void init() {
        od = new OrderDetail();
        od.setOrderDetailId(1);
        od.setFoodName("pizza");
        od.setFoodPrice(100);
        od.setRestaurantName("Alinas");
        od.setQuantity(2);
    }

    @Test
    public void addOrderDetail_whenAdded_thenReturnOK() {
        OrderDetail orderDetail= orderDetailDAO.add(od);
        Assert.assertEquals(orderDetail, od);
    }

    @Test
    public void getOrderDetail_thenReturnOrderDetailDtoList() {
        List<OrderDetailRequestDto> orderDetailRequestDtoList =orderDetailDAO.getOrderDetail();
        Assert.assertEquals(orderDetailRequestDtoList, orderDetailRequestDtoList);
    }

    @Test
    public void getOrderDetailByOrderId_thenReturnOrderDetailList() {
        List<OrderDetail> orderDetailList=orderDetailDAO.getOrderDetailByOrderId(65);
        Assert.assertEquals(orderDetailList, orderDetailList);
    }

    @Test
    public void updateOrderDetail_thenReturnTrue() {
        boolean b=orderDetailDAO.updateOrderDetail(od);
        Assert.assertTrue(b);
    }

    @Test
    public void getOrderDetailByUserId_thenReturnOrderDetail() {
        OrderDetail orderDetail=orderDetailDAO
                .getOrderDetailByUserId(17,od.getFoodName(),od.getRestaurantName());
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
        List<OrderDetail> orderDetailList=orderDetailDAO.getOrderDetailByOrderId(1);
        Assert.assertNull(orderDetailList);
    }

    @Test(expected = UserConflictException.class)
    public void updateOrderDetail_whenThereIsNullOrderObject_thenResultDataNotFoundException() {
       boolean b= orderDetailDAO.updateOrderDetail(null);
        Assert.assertFalse(b);
    }
}
