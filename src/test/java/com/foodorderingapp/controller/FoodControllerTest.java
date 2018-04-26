package com.foodorderingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodorderingapp.Application;
import com.foodorderingapp.controller.FoodController;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.service.FoodService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
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
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class FoodControllerTest {

    private MockMvc mvc;

    @Mock
    private FoodService foodService;

    @InjectMocks
    private FoodController foodController;

    Food food;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(foodController).build();
        food = new Food(1, "momo", 100, new Restaurant());
    }

    @Test
    public void testaddFood_whenAdded_thenReturnStatusOK() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(Arrays.asList(food));
        given(foodService.addFoodsToRestaurant(Arrays.asList(food))).willReturn(Arrays.asList(food));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/food")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        List<Food> returnedFood = mapper.readValue(outputInJson, mapper.getTypeFactory()
                .constructCollectionType(List.class, Food.class));
        assertThat(returnedFood).isNotNull();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void allFood_thenReturnStatusOK() throws Exception {

        given(foodService.getAll()).willReturn(Arrays.asList(food));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/food")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getFoodBydId_thenReturnStatusOK() throws Exception {

        given(foodService.getFoodById(1)).willReturn(food);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/food/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void deleteFood_thenReturnStatusOK() throws Exception {
        given(foodService.deleteFood(1)).willReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/food/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        Boolean param = Boolean.parseBoolean(outputInJson);
        Assert.assertTrue(param);
    }

    @Test
    public void updateFood_thenReturnStatusOK() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(food);
        given(foodService.updateFood(food,1)).willReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/food/1")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        Boolean param = Boolean.parseBoolean(outputInJson);
        Assert.assertTrue(param);
    }

    @Test
    public void addFood_whenObjectOfFoodIsEmpty_thenReturnStatus_NOT_FOUND() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(Arrays.asList(food));
        given(foodService.addFoodsToRestaurant(Arrays.asList(food))).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/food")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void allFood_whenThereIsNoRecordInFoodTable_thenReturnStatus_NOT_FOUND() throws Exception {

        given(foodService.getAll()).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/food")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isEmpty();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void getFoodBydId_whenThereIsNoSuchRecordInFoodTable_thenReturnStatus_NOT_FOUND() throws Exception {

        given(foodService.getFoodById(1)).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/food/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void deleteFood_whenThereIsNoSuchRecordInFoodTable_thenReturnStatus_CONFLICT() throws Exception {
        given(foodService.deleteFood(1)).willReturn(false);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/food/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        Boolean param = Boolean.parseBoolean(outputInJson);
        Assert.assertFalse(param);
        Assert.assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
    }

    @Test
    public void updateFood_whenThereIsNoSuchRecordInFoodTable_thenReturnStatus_CONFLICT()throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(food);
        given(foodService.updateFood(food,1)).willReturn(false);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/food/1")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        Boolean param = Boolean.parseBoolean(outputInJson);
        Assert.assertFalse(param);
        Assert.assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
    }
}
