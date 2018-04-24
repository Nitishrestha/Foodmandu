package com.foodorderingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodorderingapp.Application;
import com.foodorderingapp.requestdto.OrderDetailRequestDto;
import com.foodorderingapp.model.OrderDetail;
import com.foodorderingapp.model.Orders;
import com.foodorderingapp.service.OrderDetailService;
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
public class OrderDetailControllerTest {

    private MockMvc mvc;

    @Mock
    private OrderDetailService orderDetailService;

    @InjectMocks
    private OrderDetailController orderDetailController;

    OrderDetail od;
    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(orderDetailController).build();
        od=new OrderDetail("momo",100,"kfc",1,new Orders());
    }

    @Test
    public void addOrderDetail_whenAdded_thenReturnOK() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(od);
        given(orderDetailService.add(od)).willReturn(od);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orderDetail")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        OrderDetail returnedOrderDetail = mapper.readValue(outputInJson, OrderDetail.class);
        assertThat(returnedOrderDetail).isNotNull();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getOrderDetails_thenReturnOK() throws Exception {

        given(orderDetailService.getOrderDetails()).willReturn(Arrays.asList(new OrderDetailRequestDto()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orderDetail")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputOrderDetail = response.getContentAsString();
        assertThat(outputOrderDetail).isNotNull();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void addOrderDetail_whenObjectOfOrderDetailIsEmpty_thenReturnStatus_NOT_FOUND() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(od);
        given(orderDetailService.add(od)).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orderDetail")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void getOrderDetails__whenTableOrderDetailIsEmpty_thenReturnStatus_NOT_FOUND() throws Exception {
        given(orderDetailService.getOrderDetails()).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orderDetail")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}
