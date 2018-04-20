/*
package com.foodorderingapp.test;

import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.dto.FoodQuantity;
import com.foodorderingapp.dto.OrderDto;
import com.foodorderingapp.dto.OrderLogForAdminMapperDto1;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.*;
import com.foodorderingapp.service.FoodService;
import com.foodorderingapp.service.OrderDetailService;
import com.foodorderingapp.service.UserService;
import com.foodorderingapp.serviceImpl.OrdersServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class OrderFailTest {

    @Mock
    private OrderDAO orderDAO;

    @Mock
    private FoodService foodService;

    @Mock
    private OrderDetailService orderDetailService;

    @Mock
    private UserService userService;

    @InjectMocks
    OrdersServiceImpl ordersService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = DataNotFoundException.class)
    public void testFailToAddWhenUserIsNull(){

        User user=new User();

        Orders orders=new Orders();

        Food food=new Food(1,"momo",100,new Restaurant());
        OrderDto orderDto=new OrderDto(1,new ArrayList<>());

        Restaurant restaurant=new Restaurant(1,"f1soft","hattisar","9817651648",new ArrayList<>());

        OrderDetail orderDetail=new OrderDetail();
        FoodQuantity foodQuantity=new FoodQuantity(food.getName(),food.getPrice(),restaurant.getName(),1);

        when(userService.getUser(orderDto.getUserId())).thenReturn(null);
        when(orderDAO.add(orders)).thenReturn(orders);
        when(foodService.getFoodByResName(foodQuantity.getFoodName(),foodQuantity.getRestaurantName())).thenReturn(food);
        when(userService.update(user)).thenReturn(user);
        when(orderDetailService.add(orderDetail)).thenReturn(orderDetail);
        ordersService.add(orderDto);
    }
    @Rule
    public ExpectedException expectedException=ExpectedException.none();
//  @Test(expected = DataNotFoundException.class)
  @Test()
    public void testFailToAdd(){

        User user=new User();
        Orders orders=new Orders();

     //   FoodQuantity foodQuantity=new FoodQuantity();

//        Food food=new Food(1,"momo",100,new Restaurant());
        List<FoodQuantity> foodQuantityList=new ArrayList<>();
        OrderDto orderDto=new OrderDto(1,foodQuantityList);
       // orderDto.setFoodList();
      //  orderDto.setUserId(1);

        Restaurant restaurant=new Restaurant(1,"f1soft","hattisar","9817651648",new ArrayList<>());

       FoodQuantity foodQuantity=new FoodQuantity("momo",100,"kfc",1);
        OrderDetail orderDetail=new OrderDetail("momo",100,"kfc",1);
      orderDto.setFoodList(Arrays.asList(foodQuantity));
        when(userService.getUser(orderDto.getUserId())).thenReturn(user);
    //    when(orderDAO.add(orders)).thenReturn(orders);
     //   when(foodService.getFoodByResName(foodQuantity.getFoodName(),foodQuantity.getRestaurantName())).thenReturn(null);
     //   when(userService.update(user)).thenReturn(user);
       // when(orderDetailService.add(orderDetail)).thenReturn(orderDetail);
      expectedException.expect( DataNotFoundException.class);
    //  expectedException.expectMessage("user not found.");
      expectedException.expectMessage("cannot find food");
        ordersService.add(orderDto);

    }

   @Test(expected = DataNotFoundException.class)
    public void getByUserId(){
        OrderLogForAdminMapperDto1 orderLogForAdminMapperDto1 =new OrderLogForAdminMapperDto1();
        orderLogForAdminMapperDto1.setOrderId(1);
        User user=new User();
        user.setUserId(1);
        when(userService.getByUserId(user.getUserId())).thenReturn(null);
        when(orderDetailService.getOrderDetailByOrderId(orderLogForAdminMapperDto1.getOrderId())).thenReturn(new ArrayList<>());
        ordersService.getUsersByUserId(user.getUserId());
    }

    @Test(expected = DataNotFoundException.class)
    public void getOrder(){
        Orders orders=new Orders();
        orders.setOrderId(1);
        when(orderDAO.getOrders()).thenReturn(null);
        when(orderDetailService.getOrderDetailByOrderId(orders.getOrderId())).thenReturn(new ArrayList<>());
        ordersService.getOrder();
    }
}
*/
