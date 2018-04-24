package com.foodorderingapp.service;

import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.requestdto.OrderDetailRequestDto;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.serviceImpl.OrderDetailServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class OrderDetailServiceTest {

    @Mock
    private OrderDetailDAO orderDetailDAO;

    @InjectMocks
    OrderDetailServiceImpl orderDetailService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void add_thenResultOrderDetail(){
        OrderDetail orderDetail=new OrderDetail();
        when(orderDetailDAO.add(orderDetail)).thenReturn(new OrderDetail());
        Assert.assertEquals(orderDetailService.add(orderDetail),orderDetail);

    }

    @Test
    public void getOrderDetails_thenResultOrderDetailList(){
        when(orderDetailDAO.getOrderDetail()).thenReturn(Arrays.asList(new OrderDetailRequestDto()));
        Assert.assertEquals(orderDetailService.getOrderDetails(),Arrays.asList(new OrderDetailRequestDto()));
    }

    @Test
    public void getOrderDetailByOrderId_thenResultOrderDetail(){
        Orders orders=new Orders();
        orders.setOrderId(1);
        when(orderDetailDAO.getOrderDetailByOrderId(orders.getOrderId())).thenReturn(Arrays.asList(new OrderDetail()));
        Assert.assertEquals(orderDetailService.getOrderDetailByOrderId(orders.getOrderId()),Arrays.asList(new OrderDetail()));
    }

    @Test
    public void add_whenAddingOrderDetail_thenResultDataNotFoundException(){
        OrderDetail orderDetail=new OrderDetail();
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot add orderDetail.");
        orderDetailService.add(orderDetail);
    }

    @Test
    public void getOrderDetails_thenResultNullOrSizeIsZero(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find orderDetailDto.");
        orderDetailService.getOrderDetails();
    }

    @Test
    public void getOrderDetailByOrderId_thenResultNullOrSizeIsZero(){
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find orderDetail.");
        orderDetailService.getOrderDetailByOrderId(anyInt());
    }
}


