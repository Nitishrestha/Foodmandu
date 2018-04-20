package com.foodorderingapp.dao;

import com.foodorderingapp.requestdto.OrderDetailRequestDto;
import com.foodorderingapp.model.OrderDetail;

import java.util.List;

public interface OrderDetailDAO {
     OrderDetail add(OrderDetail orderDetail);
     List<OrderDetailRequestDto> getOrderDetail();
     List<OrderDetail> getOrderDetailByOrderId(int orderId);
     Boolean updateOrderDetail(OrderDetail orderDetail);
     OrderDetail getOrderDetailByUserId(int userId,String foodName,String restaurantName);

}
