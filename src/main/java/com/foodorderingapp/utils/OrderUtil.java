package com.foodorderingapp.utils;

import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.requestdto.*;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.responsedto.OrderListResponseDto;
import com.foodorderingapp.responsedto.UserListResponseDto;
import com.foodorderingapp.responsedto.UserListMapperDto;
import com.foodorderingapp.service.OrderDetailService;
import com.foodorderingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class OrderUtil {

    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private UserService userService;


    public OrderListResponseDto getOrderListMapperDtoList(OrderListMapperDto orderListMapperDto) {
        OrderListResponseDto orderListResponseDto = new OrderListResponseDto();
        List<FoodResRequestDto> foodResRequestDtoList = new ArrayList<>();
        orderListResponseDto.setOrderId(orderListMapperDto.getOrderId());
        orderListResponseDto.setUserId(orderListMapperDto.getUserId());
        orderListResponseDto.setFirstName(orderListMapperDto.getFirstName());
        orderListResponseDto.setMiddleName(orderListMapperDto.getMiddleName());
        orderListResponseDto.setLastName(orderListMapperDto.getLastName());
        orderListResponseDto.setConfirm(orderListMapperDto.getConfirm());
        orderListResponseDto.setOrderedDate(orderListMapperDto.getOrderedDate());
        List<OrderDetail> orderDetailList = orderDetailService.getOrderDetailByOrderId(orderListMapperDto.getOrderId());
        if(orderDetailList.size()==0 || orderDetailList==null){
            throw new DataNotFoundException("orderDetail is empty");
        }
        for (OrderDetail orderDetail : orderDetailList) {
            foodResRequestDtoList.add(FoodResUtil.addFoodRes(orderDetail));
            orderListResponseDto.setFoodResRequestDtoList(foodResRequestDtoList);
        }
        return orderListResponseDto;
    }

    public UserListResponseDto getUserListDto(UserListMapperDto userListMapperDto) {
        UserListResponseDto userListResponseDto = new UserListResponseDto();
        List<FoodResRequestDto> foodResRequestDtoList = new ArrayList<>();
        userListResponseDto.setUserId(userListMapperDto.getUserId());
        userListResponseDto.setOrderId(userListMapperDto.getOrderId());
        userListResponseDto.setOrderedDate(userListMapperDto.getOrderedDate());
        userListResponseDto.setConfirm(userListMapperDto.getConfirm());
        List<OrderDetail> orderDetailList = orderDetailService.getOrderDetailByOrderId(userListMapperDto.getOrderId());
        if(orderDetailList.size()==0 || orderDetailList==null){
            throw new DataNotFoundException("orderDetail is empty");
        }
        for (OrderDetail orderDetail : orderDetailList) {
            foodResRequestDtoList.add(FoodResUtil.addFoodRes(orderDetail));
            userListResponseDto.setFoodResRequestDtoList(foodResRequestDtoList);
        }
        return userListResponseDto;
    }

}