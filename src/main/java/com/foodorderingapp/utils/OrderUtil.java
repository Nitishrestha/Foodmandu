package com.foodorderingapp.utils;

import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.requestdto.*;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.responsedto.OrderListResponseDto;
import com.foodorderingapp.responsedto.OrderResponseDto;
import com.foodorderingapp.responsedto.UserListResponseDto;
import com.foodorderingapp.responsedto.UserListMapperDto;
import com.foodorderingapp.service.FoodService;
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
    private FoodService foodService;

    public OrderResponseDto getOrderResponseDto(OrderRequestDto orderRequestDto,Orders orders){
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        List<Food> foodList = new ArrayList<>();
        for (FoodQuantityRequestDto foodQuantityRequestDto : orderRequestDto.getFoodList()) {
            OrderDetail orderDetail1 = orderDetailService
                    .getOrderDetailByUserId(orderRequestDto.getUserId(),
                            foodQuantityRequestDto.getFoodName(), foodQuantityRequestDto.getRestaurantName());
            if (orderDetail1 != null) {
                int quantity = foodQuantityRequestDto.getQuantity() + orderDetail1.getQuantity();
                orderDetail1.setQuantity(quantity);
                orderDetailService.updateOrderDetail(orderDetail1);
            } else {
                Orders order = orderDAO.getOrderByUserWithConfirm(orderRequestDto.getUserId());
                OrderDetail orderDetail = new OrderDetail();
                if(order!=null) {
                    orderDetail.setOrders(order);
                }else {
                    orderDAO.add(orders);
                    orderDetail.setOrders(orders);
                }
                orderDetail.setQuantity(foodQuantityRequestDto.getQuantity());
                orderDetail.setFoodName(foodQuantityRequestDto.getFoodName());
                orderDetail.setRestaurantName(foodQuantityRequestDto.getRestaurantName());
                orderDetail.setFoodPrice(foodQuantityRequestDto.getFoodPrice());
                orderDetailService.add(orderDetail);
            }
            Food food = foodService.getFoodByResName(foodQuantityRequestDto.getRestaurantName(), foodQuantityRequestDto.getFoodName());
            if(food==null){
                throw new DataNotFoundException("cannot find food.");
            }
            foodList.add(food);
            orderResponseDto.setBalance(orders.getUser().getBalance());
            orderResponseDto.setFoodList(foodList);
        }
        return orderResponseDto;
    }
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