/*
package com.foodorderingapp.test;

import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.serviceImpl.OrderDetailServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class OrderDetailFailTest {

    @Mock
    private OrderDetailDAO orderDetailDAO;

    @InjectMocks
    OrderDetailServiceImpl orderDetailService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = DataNotFoundException.class)
    public void testFailToAdd()throws NullPointerException{
        OrderDetail orderDetail=null;
        orderDetailService.add(orderDetail);
    }

    @Test(expected = DataNotFoundException.class)
    public void testFailToGetOrderDetailsTest(){
        when(orderDetailDAO.getOrderDetail()).thenReturn(null);
        orderDetailService.getOrderDetails();
    }

    @Test(expected = DataNotFoundException.class)
    public void testGetOrderDetailByOrderId(){
        when(orderDetailDAO.getOrderDetailByOrderId(anyInt())).thenReturn(null);
        orderDetailService.getOrderDetailByOrderId(anyInt());
    }
}
*/
