package com.foodorderingapp.dao;

import com.foodorderingapp.requestdto.OrderListMapperDto;
import com.foodorderingapp.model.Orders;

import java.util.List;

public interface OrderDAO {
     Orders add(Orders orders);
     Boolean update(Orders orders);
     Orders getOrder(int orderId);
     List<OrderListMapperDto> getOrderForAdminForMonth();
     List<OrderListMapperDto> getOrderLogForAdminForToday();
     Orders getOrderByUserWithConfirm(int userId);
     boolean delete(Orders orders);
}
