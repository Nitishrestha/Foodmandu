package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.requestdto.OrderDetailRequestDto;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailDAO orderDetailDAO;

    @Autowired
    public  OrderDetailServiceImpl(OrderDetailDAO orderDetailDAO){
        this.orderDetailDAO=orderDetailDAO;
    }

    @Override
    public OrderDetail add(OrderDetail orderDetail) {
        OrderDetail orderDetail1= orderDetailDAO.add(orderDetail);
        if(orderDetail1==null){
            throw new DataNotFoundException("cannot add orderDetail.");
        }else{
            return orderDetail1;
        }
    }

    public List<OrderDetailRequestDto> getOrderDetails() {
        List<OrderDetailRequestDto> orderDetails = orderDetailDAO.getOrderDetail();
        if(orderDetails==null || orderDetails.size()==0){
            throw new DataNotFoundException("cannot find orderDetailDto.");
        }else{
            return orderDetails;
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderId(int orderId) {
        List<OrderDetail> orderDetails = orderDetailDAO.getOrderDetailByOrderId(orderId);
        if(orderDetails==null || orderDetails.size()==0){
            throw new DataNotFoundException("cannot find orderDetail.");
        }else{
            return orderDetails;
        }

    }

    public OrderDetail getOrderDetailByUserId(int userId,String foodName,String restaurantName ){
        return  orderDetailDAO.getOrderDetailByUserId(userId,foodName,restaurantName);

    }

    @Override
    public OrderDetail updateOrderDetail(OrderDetail orderDetail) {
        orderDetailDAO.updateOrderDetail(orderDetail);
        return orderDetail;
    }

    }


