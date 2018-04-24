package com.foodorderingapp.service;

import com.foodorderingapp.requestdto.OrderDetailRequestDto;
import com.foodorderingapp.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail add(OrderDetail orderDetail);
    List<OrderDetailRequestDto> getOrderDetails();
    List<OrderDetail> getOrderDetailByOrderId(int orderId);
    OrderDetail updateOrderDetail(OrderDetail orderDetail);
    OrderDetail getOrderDetailByUserId(int userId,String foodName,String restaurantName);
}
