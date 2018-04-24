/*
package com.foodorderingapp.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodorderingapp.Application;
import com.foodorderingapp.requestdto.*;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.responsedto.OrderResponseDto;
import com.foodorderingapp.responsedto.OrderListResponseDto;
import com.foodorderingapp.responsedto.UserListResponseDto;
import com.foodorderingapp.service.OrdersService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderControllerTest {

    private MockMvc mvc;

    @Mock
    private OrdersService ordersService;

    @InjectMocks
    private OrdersController ordersController;

    OrderRequestDto orderRequestDto;
    FoodQuantityRequestDto foodQuantityRequestDto;
    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(ordersController).build();
        foodQuantityRequestDto =new FoodQuantityRequestDto("momo",100,"kfc",1);
        orderRequestDto = new OrderRequestDto();
        orderRequestDto.setUserId(1);
        orderRequestDto.setFoodList(Arrays.asList(foodQuantityRequestDto));
    }

    @Test
    public void addOrder_whenAdded_thenReturnStatusOK() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(orderRequestDto);
        given(ordersService.add(orderRequestDto)).willReturn(new OrderResponseDto(1,Arrays.asList(new Food())));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/order")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        OrderResponseDto returnedOrderResponseDto = mapper.readValue(outputInJson, OrderResponseDto.class);
        assertThat(returnedOrderResponseDto).isNotNull();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getOrderLogForAdminForToday_thenReturnListOfOrderListDto() throws Exception {

        given(ordersService.getOrderLogForAdminForToday()).willReturn(Arrays.asList(new OrderListResponseDto()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/admin/today")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    public void getOrderLogForAdminForAMonth_thenReturnListOfOrderListDto() throws Exception{

        given(ordersService.getOrderForAdminForMonth()).willReturn(Arrays.asList(new OrderListResponseDto()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/admin/month")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getByUserForAMonth_thenReturnListOfUserListDto() throws Exception{

        given(ordersService.getUsersByUserForAMonth(1)).willReturn(Arrays.asList(new UserListResponseDto()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/userList/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getByUserForParticularDay_thenReturnListOfUserListDto() throws Exception{

        given(ordersService.getUsersByUserForToday(1)).willReturn(Arrays.asList(new UserListResponseDto()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/user/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void updateConfirm_thenReturnUpdatedObjectOfOrders() throws Exception {

        given(ordersService.updateConfirm(1)).willReturn(new Orders());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/order/confirm/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void updateWatched_thenReturnUpdatedObjectOfOrders() throws Exception {

        given(ordersService.updateWatched(1)).willReturn(new Orders());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/order/watched/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void addOrder_whenNoRecordsAreFoundInOrderDto_thenReturnNotFoundException() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(orderRequestDto);
        given(ordersService.add(orderRequestDto)).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/order")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }


    @Test
    public void getOrderLogForAdminForToday_whenThereIsNoOrdersInTodaysDate_thenReturnStatusNOT_FOUND() throws Exception {

        given(ordersService.getOrderLogForAdminForToday()).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/admin/today")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

    }

    @Test
    public void getOrderLogForAdminForAMonth_whenThereIsNoOrdersInCurrentMonth_thenReturnStatusNOT_FOUND() throws Exception{

        given(ordersService.getOrderForAdminForMonth()).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/admin/month")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void getByUserForAMonth_whenThereIsNoOrdersForThatUser_forThatCurrentMonth_thenReturnStatusNOT_FOUND() throws Exception{

        given(ordersService.getUsersByUserForAMonth(1)).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/userList/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void getByUserForParticularDay_whenUserDidntGiveAnyOrderInThisDay_thenReturnStatusNOT_FOUND() throws Exception{

        given(ordersService.getUsersByUserForToday(1)).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/user/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void updateConfirm_whenThereIsNoSuchOrderExit_thenReturnStatusNOT_FOUND() throws Exception {

        given(ordersService.updateConfirm(1)).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/order/confirm/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void updateWatched_whenThereIsNoSuchOrderExit_thenReturnStatusNOT_FOUND() throws Exception {

        given(ordersService.updateWatched(1)).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/order/watched/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}


*/
