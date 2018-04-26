package com.foodorderingapp.controller;

import com.foodorderingapp.requestdto.OrderDetailRequestDto;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.foodorderingapp.commons.WebUrlConstant.OrderDetail.*;
import java.util.List;

@RestController
@RequestMapping(ORDER_DETAIL)
public class OrderDetailController {


    private OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService){
        this.orderDetailService=orderDetailService;
    }

    @PostMapping
    public ResponseEntity<OrderDetail> add(OrderDetail orderDetail)
    {
        OrderDetail orderDetail1=orderDetailService.add(orderDetail);
        if(orderDetail1==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderDetail1, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailRequestDto>> getOrderDetail()
    {
        List<OrderDetailRequestDto> orderDetailRequestDtoList =orderDetailService.getOrderDetails();
        if(orderDetailRequestDtoList ==null|| orderDetailRequestDtoList.size()==0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderDetailRequestDtoList, HttpStatus.OK);
    }

}