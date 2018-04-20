/*
package com.foodorderingapp.test;

import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.dto.OrderDetailDto;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.serviceImpl.OrderDetailServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;


public class OrderDetailTest {

    @Mock
    private OrderDetailDAO orderDetailDAO;

    @InjectMocks
    OrderDetailServiceImpl orderDetailService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddTest(){
        OrderDetail orderDetail=new OrderDetail();
      when(orderDetailDAO.add(orderDetail)).thenReturn(new OrderDetail());
        Assert.assertNotNull(orderDetailService.add(orderDetail));

    }

    @Test
    public void testGetOrderDetailsTest(){
        List<OrderDetailDto> orderDetailList=new ArrayList<>();
        when(orderDetailDAO.getOrderDetail()).thenReturn(orderDetailList);
        Assert.assertNotNull(orderDetailService.getOrderDetails());
    }

    @Test
    public void testGetOrderDetailByOrderId(){
        List<OrderDetail> orderDetailList=new ArrayList<>();
        when(orderDetailDAO.getOrderDetailByOrderId(anyInt())).thenReturn(orderDetailList);
        Assert.assertNotNull(orderDetailService.getOrderDetailByOrderId(anyInt()));
    }

}
*/
