package com.foodorderingapp.service;

import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.requestdto.*;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.*;
import com.foodorderingapp.responsedto.BillResponseDto;
import com.foodorderingapp.responsedto.OrderListResponseDto;
import com.foodorderingapp.responsedto.UserListResponseDto;
import com.foodorderingapp.responsedto.UserListMapperDto;
import com.foodorderingapp.serviceImpl.OrdersServiceImpl;
import com.foodorderingapp.utils.OrderUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    @Mock
    private OrderDAO orderDAO;

    @Mock
    private FoodService foodService;

    @Mock
    private OrderUtil orderUtil;


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

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void add_thenResultOrder() {

        User user = new User();
        user.setUserId(1);
        Orders orders = new Orders();
        Food food = new Food(1, "momo", 100, new Restaurant());
        OrderRequestDto orderRequestDto = new OrderRequestDto(1, new ArrayList<>());
        Restaurant restaurant = new Restaurant(1, "f1soft", "hattisar", "9817651648");
        OrderDetail orderDetail = new OrderDetail();
        FoodQuantityRequestDto foodQuantityRequestDto = new FoodQuantityRequestDto(food.getName(), food.getPrice(), restaurant.getName(), 1);

        when(userService.getUser(orderRequestDto.getUserId())).thenReturn(user);
        when(orderDAO.add(orders)).thenReturn(orders);
        when(foodService.getFoodByResName(foodQuantityRequestDto.getFoodName(), foodQuantityRequestDto.getRestaurantName())).thenReturn(food);
        when(userService.update(user)).thenReturn(user);
        when(orderDetailService.add(new OrderDetail())).thenReturn(orderDetail);
        Assert.assertEquals(ordersService.add(orderRequestDto), new BillResponseDto());
    }

    @Test
    public void getOrderForAdminForMonth_thenResult_OrderListDtoList() {

        OrderListResponseDto orderListResponseDto =new OrderListResponseDto(1,1,"ram","bahadur",
                "pant",new Date(),true,Arrays.asList(new FoodResRequestDto()));

        List<OrderListResponseDto> orderListDtoListResponse =new ArrayList<>();
        orderListDtoListResponse.add(orderListResponseDto);

        OrderListMapperDto orderListMapperDto=new
                OrderListMapperDto(1,1,"shyam","bahadur","pant",new Date(),true);
        List<OrderListMapperDto> orderListMapperDtoList=new ArrayList<>();

        orderListMapperDtoList.add(orderListMapperDto);
        OrderDetail orderDetail=new OrderDetail("momo",100,"kfc",1,new Orders());
        List<OrderDetail> orderDetailList=new ArrayList<>();
        orderDetailList.add(orderDetail);

        when(orderDAO.getOrderForAdminForMonth())
                .thenReturn(orderListMapperDtoList);
        when(orderUtil.getOrderListMapperDtoList(orderListMapperDto))
                .thenReturn(orderListResponseDto);

        Assert.assertEquals(ordersService.getOrderForAdminForMonth(), orderListDtoListResponse);
    }



    @Test
    public void getOrderLogForAdminForToday_thenResult_OrderListDtoList() {
        OrderListResponseDto orderListResponseDto =new OrderListResponseDto(1,1,"ram","bahadur",
                "pant",new Date(),true,Arrays.asList(new FoodResRequestDto()));

        List<OrderListResponseDto> orderListDtoListResponse =new ArrayList<>();
        orderListDtoListResponse.add(orderListResponseDto);

        OrderListMapperDto orderListMapperDto=new
                OrderListMapperDto(1,1,"shyam","bahadur","pant",new Date(),true);
        List<OrderListMapperDto> orderListMapperDtoList=new ArrayList<>();

        orderListMapperDtoList.add(orderListMapperDto);
        OrderDetail orderDetail=new OrderDetail("momo",100,"kfc",1,new Orders());
        List<OrderDetail> orderDetailList=new ArrayList<>();
        orderDetailList.add(orderDetail);

        when(orderDAO.getOrderLogForAdminForToday())
                .thenReturn(orderListMapperDtoList);
        when(orderUtil.getOrderListMapperDtoList(orderListMapperDto))
                .thenReturn(orderListResponseDto);
        Assert.assertEquals(ordersService.getOrderLogForAdminForToday(), orderListDtoListResponse);
    }

    @Test
    public void getUsersByUserForAMonth_thenResult_UserListDtoList() {
        UserListMapperDto userListMapperDto=new UserListMapperDto(1,1,new Date(),true);
        UserListResponseDto userListResponseDto =new UserListResponseDto(1,1,new Date(),
                true,Arrays.asList(new FoodResRequestDto()));
        User user=new User();
        user.setUserId(1);
        when(userService.getUsersByUserForAMonth(user.getUserId()))
                .thenReturn(Arrays.asList(userListMapperDto));
        when(orderUtil.getUserListDto(userListMapperDto)).thenReturn(userListResponseDto);
        Assert.assertEquals(ordersService.getUsersByUserForAMonth(user.getUserId()),Arrays.asList(userListResponseDto));
    }

    @Test
    public void getUsersByUserForAToday_thenResult_UserListDtoList() {
        UserListMapperDto userListMapperDto=new UserListMapperDto(1,1,new Date(),true);
        UserListResponseDto userListResponseDto =new UserListResponseDto(1,1,new Date(),
                true,Arrays.asList(new FoodResRequestDto()));
        User user1=new User();
        user1.setUserId(2);
        when(userService.getByUserForToday(user1.getUserId()))
                .thenReturn(Arrays.asList(userListMapperDto));
        when(orderUtil.getUserListDto(userListMapperDto)).thenReturn(userListResponseDto);
        Assert.assertEquals(ordersService.getUsersByUserForToday(user1.getUserId()),Arrays.asList(userListResponseDto));
    }

    @Test
    public void testToUpdateOrder() {
        User user=new User();
        user.setUserId(1);
        Orders orders = new Orders(true,true,new Date(),user);
        orders.setOrderId(1);
        OrderDetail orderDetail=new OrderDetail("momo",100,"kfc",1,orders);
        orderDetail.setOrderDetailId(1);
        when(orderDAO.getOrder(orders.getOrderId())).thenReturn(orders);
        when(orderDAO.update(orders)).thenReturn(true);
        when(orderDetailService.getOrderDetailByOrderId(orders.getOrderId()))
                .thenReturn(Arrays.asList(orderDetail));
        when(userService.update(user))
                .thenReturn(user);
        when(userService.getUser(orders.getUser().getUserId())).thenReturn(user);
        Assert.assertEquals(ordersService.updateConfirm(orders.getOrderId()), orders);
    }

    @Test
    public void add_whenUserIsNull_thenResultDataNotFoundException() {
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("user cannot be null.");
        ordersService.add(new OrderRequestDto());
    }

    @Test
    public void add_whenThereNoSuchFoodInTheFoodTable_thenResultNull() {
        User user = new User();
        OrderDetail orderDetail=new
                OrderDetail("momo",100,"kfc",1,new Orders());
        FoodQuantityRequestDto foodQuantityRequestDto = new FoodQuantityRequestDto("momo", 200, "kfc", 1);
        OrderRequestDto orderRequestDto = new OrderRequestDto(1,Arrays.asList(foodQuantityRequestDto));
        when(userService.getUser(orderRequestDto.getUserId())).thenReturn(user);
        when(orderDetailService.
                getOrderDetailByUserId(orderRequestDto.getUserId(),
                        foodQuantityRequestDto.getFoodName(), foodQuantityRequestDto.getRestaurantName()))
                .thenReturn(orderDetail);
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find food.");
        ordersService.add(orderRequestDto);
    }

    @Test
    public void getOrderForAdminForMonth_whenGetOrderForAdminForMonthThenResultNull_thenResultDataNotFoundException() {
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find orderListMapperDtoList for a month.");
        ordersService.getOrderForAdminForMonth();
    }


    @Test
    public void getOrderForAdminForMonth_whenGetOrderListMapperDtoListThenReturnNull_thenReturnDataNotFoundException() {
        OrderListMapperDto orderListMapperDto=new OrderListMapperDto(1,1,"ram","bahadur","shah",new Date(),true);
        when(orderDAO.getOrderForAdminForMonth()).thenReturn(Arrays.asList(orderListMapperDto));
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find orderListResponseDto for a month.");
        ordersService.getOrderForAdminForMonth();
    }

   @Test
    public void getOrderLogForAdminForToday_whenGetOrderLogForAdminForTodayThenReturnNull_thenReturnDataNotFoundException() {
       expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find orderListMapperDtoList for a today.");
        ordersService.getOrderLogForAdminForToday();
    }

    @Test
    public void getOrderLogForAdminForToday_whenGetOrderListMapperDtoListThenResultNull_thenResultDataNotFoundException() {
        OrderListMapperDto orderListMapperDto=new OrderListMapperDto(1,1,"ram","bahadur","shah",new Date(),true);
        when(orderDAO.getOrderLogForAdminForToday()).thenReturn(Arrays.asList(orderListMapperDto));
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find orderListResponseDto for a today.");
        ordersService.getOrderLogForAdminForToday();
    }

    @Test
    public void getUsersByUserForAMonth_whenGetUsersByUserForAMonthThenReturnNull_thenReturnDataNotFoundException() {

        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find userListMapperDtos for a month.");
        ordersService.getUsersByUserForAMonth(anyInt());
    }

    @Test
    public void getUsersByUserForAMonth_whenThereUserListResponseDtoThenReturnNull_thenReturnDataNotFoundException() {
        UserListMapperDto userListMapperDto=new UserListMapperDto(1,1,new Date(),true);
        when(userService.getUsersByUserForAMonth(userListMapperDto.getUserId()))
                .thenReturn(Arrays.asList(userListMapperDto));
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find userListResponseDto  for a month.");
        ordersService.getUsersByUserForAMonth(userListMapperDto.getUserId());
    }

    @Test
    public void getUsersByUserForToday_whenGetByUserForTodayThenReturnNull_thenReturnDataNotFoundException() {

        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find userListMapperDtos for a today.");
        ordersService.getUsersByUserForToday(anyInt());
    }

    @Test
    public void getUsersByUserForToday_whenGetUserListDtoThenReturnNull_thenReturnDataNotFoundException() {
        UserListMapperDto userListMapperDto=new UserListMapperDto(1,1,new Date(),true);
        when(userService.getByUserForToday(userListMapperDto.getUserId())).thenReturn(Arrays.asList(userListMapperDto));
        expectedException.expect(DataNotFoundException.class);
        expectedException.expectMessage("cannot find userListResponseDto  for a today.");
        ordersService.getUsersByUserForToday(userListMapperDto.getUserId());
    }
}

