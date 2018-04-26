package com.foodorderingapp.controller;

import com.foodorderingapp.requestdto.*;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.responsedto.OrderResponseDto;
import com.foodorderingapp.responsedto.OrderListResponseDto;
import com.foodorderingapp.responsedto.UserListResponseDto;
import com.foodorderingapp.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.foodorderingapp.commons.WebUrlConstant.Order.*;

@RestController
@RequestMapping(ORDER)
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> addOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        OrderResponseDto orderResponseDto = ordersService.add(orderRequestDto);
        if(orderResponseDto ==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(orderResponseDto,HttpStatus.OK);
    }

    @GetMapping(TODAY_ORDER_TO_ADMIN)
    public ResponseEntity<List<OrderListResponseDto>> getOrderLogForAdminForToday() {
        List<OrderListResponseDto> orderListMapperDtoList = ordersService.getOrderLogForAdminForToday();
        if(orderListMapperDtoList==null||orderListMapperDtoList.size()==0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderListMapperDtoList, HttpStatus.OK);
    }

    @GetMapping(MONTHLY_ORDER_TO_ADMIN)
    public ResponseEntity<List<OrderListResponseDto>> getOrderLogForAdminForAMonth() {
        List<OrderListResponseDto> orderListDtoListResponse = ordersService.getOrderForAdminForMonth();
        if(orderListDtoListResponse ==null|| orderListDtoListResponse.size()==0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderListDtoListResponse, HttpStatus.OK);
    }

    @GetMapping(MONTHLY_ORDER_TO_USER)
    public ResponseEntity<List<UserListResponseDto>> getByUserForAMonth(@PathVariable("userId") int userId) {
        List<UserListResponseDto> userListDtoListResponse = ordersService.getUsersByUserForAMonth(userId);
        if(userListDtoListResponse ==null|| userListDtoListResponse.size()==0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userListDtoListResponse, HttpStatus.OK);
    }

    @GetMapping(TODAY_ORDER_TO_USER)
    public ResponseEntity<List<UserListResponseDto>> getByUserForParticularDay(@PathVariable("userId") int userId) {
        List<UserListResponseDto> userListDtoListResponse = ordersService.getUsersByUserForToday(userId);
        if(userListDtoListResponse ==null|| userListDtoListResponse.size()==0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userListDtoListResponse, HttpStatus.OK);
    }


    @PutMapping(CONFIRM)
    public ResponseEntity<Orders> updateConfirm(@PathVariable int orderId) {
        Orders orders = ordersService.updateConfirm(orderId);
        if(orders==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping(WATCHED)
    public ResponseEntity<Orders> updateWatched(@PathVariable int orderId) {
        Orders orders = ordersService.updateWatched(orderId);
        if(orders==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
