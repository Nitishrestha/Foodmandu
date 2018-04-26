package com.foodorderingapp.service;

import com.foodorderingapp.responsedto.OrderResponseDto;
import com.foodorderingapp.requestdto.OrderRequestDto;
import com.foodorderingapp.responsedto.OrderListResponseDto;
import com.foodorderingapp.responsedto.UserListResponseDto;
import com.foodorderingapp.model.Orders;

import java.util.List;

public interface OrdersService {
    OrderResponseDto add(OrderRequestDto orderRequestDto);
    Orders updateConfirm(int orderId);
    Orders updateWatched(int orderId);
    List<OrderListResponseDto> getOrderForAdminForMonth();
    List<OrderListResponseDto> getOrderLogForAdminForToday();
    List<UserListResponseDto> getUsersByUserForAMonth(int userId);
    List<UserListResponseDto> getUsersByUserForToday(int userId);

}
