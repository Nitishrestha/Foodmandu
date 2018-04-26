package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.requestdto.*;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.model.User;
import com.foodorderingapp.responsedto.*;
import com.foodorderingapp.service.FoodService;
import com.foodorderingapp.service.OrderDetailService;
import com.foodorderingapp.service.OrdersService;
import com.foodorderingapp.service.UserService;
import com.foodorderingapp.utils.OrderUtil;
import org.springframework.beans.BeanUtils;
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
    private final OrderDAO orderDAO;
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrdersServiceImpl(UserService userService, OrderUtil orderUtil,
                             OrderDAO orderDAO, OrderDetailService orderDetailService) {
        this.userService = userService;
        this.orderDAO = orderDAO;
        this.orderDetailService = orderDetailService;
        this.orderUtil = orderUtil;
    }

    double balance;
    public OrderResponseDto add(OrderRequestDto orderRequestDto) {
        User user=new User();
        UserResponseDto userResponseDto = userService.getUser(orderRequestDto.getUserId());
        BeanUtils.copyProperties(userResponseDto,user);
        if(userResponseDto==null){
            throw new DataNotFoundException("user cannot be null.");
        }
        Orders orders=new Orders();
        orders.setUser(user);
        OrderResponseDto orderResponseDto= orderUtil.getOrderResponseDto(orderRequestDto,orders);
        return orderResponseDto;
    }
    public Orders updateConfirm(int orderId) {
        UserRequestDto userRequestDto=new UserRequestDto();
        Orders orders1 = orderDAO.getOrder(orderId);
        if (orders1 == null){
            throw new DataNotFoundException("cannot find order.");
        }
        orders1.setConfirm(true);
        orderDAO.update(orders1);
        UserResponseDto userResponseDto = userService.getUser(orders1.getUser().getUserId());
        BeanUtils.copyProperties(userResponseDto,userRequestDto);
        List<OrderDetail> orderDetailList=orderDetailService.getOrderDetailByOrderId(orders1.getOrderId());
        for(OrderDetail orderDetail:orderDetailList){
            double amount=orderDetail.getFoodPrice()*orderDetail.getQuantity();
            balance = userRequestDto.getBalance() - amount;
            userRequestDto.setBalance(balance);
            userService.update(userRequestDto);
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

