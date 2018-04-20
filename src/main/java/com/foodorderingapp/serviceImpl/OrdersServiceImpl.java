package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.requestdto.*;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.model.User;
import com.foodorderingapp.responsedto.BillResponseDto;
import com.foodorderingapp.responsedto.OrderListResponseDto;
import com.foodorderingapp.responsedto.UserListResponseDto;
import com.foodorderingapp.responsedto.UserListMapperDto;
import com.foodorderingapp.service.FoodService;
import com.foodorderingapp.service.OrderDetailService;
import com.foodorderingapp.service.OrdersService;
import com.foodorderingapp.service.UserService;
import com.foodorderingapp.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {


    private final OrderUtil orderUtil;
    private final UserService userService;
    private final FoodService foodService;
    private final OrderDAO orderDAO;
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrdersServiceImpl(UserService userService, OrderUtil orderUtil,
                             FoodService foodService, OrderDAO orderDAO,
                             OrderDetailService orderDetailService) {
        this.userService = userService;
        this.foodService = foodService;
        this.orderDAO = orderDAO;
        this.orderDetailService = orderDetailService;
        this.orderUtil = orderUtil;
    }

    double balance;

    public BillResponseDto add(OrderRequestDto orderRequestDto) {
        BillResponseDto bal = new BillResponseDto();
        List<Food> foodList = new ArrayList<>();
        User user = userService.getUser(orderRequestDto.getUserId());
        if(user==null){
            throw new DataNotFoundException("user cannot be null.");
        }
        Orders orders=new Orders();
        orders.setUser(user);
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
                bal.setBalance(user.getBalance());
                bal.setFoodList(foodList);
        }
        return bal;
    }

    public Orders updateConfirm(int orderId) {
        Orders orders1 = orderDAO.getOrder(orderId);
        orders1.setConfirm(true);
        orderDAO.update(orders1);
        User user = userService.getUser(orders1.getUser().getUserId());
        List<OrderDetail> orderDetailList=orderDetailService.getOrderDetailByOrderId(orders1.getOrderId());
        for(OrderDetail orderDetail:orderDetailList){
            double amount=orderDetail.getFoodPrice()*orderDetail.getQuantity();
            balance = user.getBalance() - amount;
            user.setBalance(balance);
            userService.update(user);
        }
        if (orders1 == null){
            throw new DataNotFoundException("cannot find order.");
        }
        return orders1;
    }


    public List<OrderListResponseDto> getOrderForAdminForMonth() {

            List<OrderListMapperDto> orderListMapperDtoList = orderDAO.getOrderForAdminForMonth();
            if(orderListMapperDtoList==null || orderListMapperDtoList.size()==0){
                throw new DataNotFoundException("cannot find orderListMapperDtoList for a month.");
            }
            List<OrderListResponseDto> orderListDtoListResponse = new ArrayList<>();
            for (OrderListMapperDto orderListMapperDto : orderListMapperDtoList) {
                OrderListResponseDto orderListResponseDto = orderUtil.getOrderListMapperDtoList(orderListMapperDto);
                if(orderListResponseDto ==null ){
                    throw new DataNotFoundException("cannot find orderListResponseDto for a month.");
                }
                orderListDtoListResponse.add(orderListResponseDto);
            }
            return orderListDtoListResponse;
        }

    public List<OrderListResponseDto> getOrderLogForAdminForToday() {

            List<OrderListMapperDto> orderListMapperDtoList = orderDAO.getOrderLogForAdminForToday();
            if(orderListMapperDtoList==null || orderListMapperDtoList.size()==0){
                throw new DataNotFoundException("cannot find orderListMapperDtoList for a today.");
            }
            List<OrderListResponseDto> orderListDtoListResponse = new ArrayList<>();
            for (OrderListMapperDto orderListMapperDto : orderListMapperDtoList) {
                OrderListResponseDto orderListResponseDto = orderUtil.getOrderListMapperDtoList(orderListMapperDto);
                if(orderListResponseDto ==null){
                    throw new DataNotFoundException("cannot find orderListResponseDto for a today.");
                }
                orderListDtoListResponse.add(orderListResponseDto);
            }
            return orderListDtoListResponse;
    }

    public List<UserListResponseDto> getUsersByUserForAMonth(int userId) {

            List<UserListMapperDto> userListMapperDtos = userService.getUsersByUserForAMonth(userId);
            if(userListMapperDtos==null || userListMapperDtos.size()==0){
                throw new DataNotFoundException("cannot find userListMapperDtos for a month.");
            }
            List<UserListResponseDto> userListDtoListResponse = new ArrayList<>();
            for (UserListMapperDto userListMapperDto : userListMapperDtos) {
                UserListResponseDto userListResponseDto =orderUtil.getUserListDto(userListMapperDto);
                if(userListResponseDto ==null ){
                    throw new DataNotFoundException("cannot find userListResponseDto  for a month.");
                }
                userListDtoListResponse.add(userListResponseDto);
            }
            return userListDtoListResponse;
        }

    public List<UserListResponseDto> getUsersByUserForToday(int userId) {

            List<UserListMapperDto> userListMapperDtos = userService.getByUserForToday(userId);
            if(userListMapperDtos==null || userListMapperDtos.size()==0){
                throw new DataNotFoundException("cannot find userListMapperDtos for a today.");
            }
            List<UserListResponseDto> userListDtoListResponse = new ArrayList<>();
            for (UserListMapperDto userListMapperDto : userListMapperDtos) {
                UserListResponseDto userListResponseDto =orderUtil.getUserListDto(userListMapperDto);
                if(userListResponseDto ==null ){
                    throw new DataNotFoundException("cannot find userListResponseDto  for a today.");
                }

                userListDtoListResponse.add(userListResponseDto);
            }
            return userListDtoListResponse;
        }

    public Orders updateWatched(int orderId) {
        Orders orders1 = orderDAO.getOrder(orderId);
        if (orders1 == null) {
            throw new DataNotFoundException("cannot find order.");
        }
        orders1.setWatched(true);
        orderDAO.update(orders1);
        return orders1;
    }
}

