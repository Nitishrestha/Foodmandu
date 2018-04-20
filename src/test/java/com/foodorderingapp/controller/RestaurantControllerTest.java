/*
package com.foodorderingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodorderingapp.Application;
import com.foodorderingapp.commons.GenericResponse;
import com.foodorderingapp.commons.PageModel;
import com.foodorderingapp.model.Food;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.service.FoodService;
import com.foodorderingapp.service.RestaurantService;
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
public class RestaurantControllerTest {

    private MockMvc mvc;

     @Mock
     private FoodService foodService;

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    Food food;
    Restaurant restaurant;


    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
        food = new Food(1, "momo", 100, new Restaurant());
        restaurant=new Restaurant(1,"kfc","kupondole","98153715");

    }

    @Test
    public void addRestaurant_whenAdded_thenReturnStatusOK() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(restaurant);
        given(restaurantService.addRestaurant(restaurant)).willReturn(restaurant);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/restaurants")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        Restaurant returnedRestaurant = mapper.readValue(outputInJson,Restaurant.class);
        assertThat(returnedRestaurant).isNotNull();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getAllRestaurants_thenReturnListOfRestaurants() throws Exception {
        given(restaurantService.getAll()).willReturn(Arrays.asList(restaurant));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurants")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getFoodsByRestaurant_thenReturnListOfFoods() throws Exception {
        given(foodService.getFoodByRestaurantId(1)).willReturn(Arrays.asList(food));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurants/1/foods")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void activateRestaurant_thenReturnIdOfThatRestaurant() throws Exception {
        given(restaurantService.activate(1)).willReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurants/1/activate")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void deactivateRestaurant_thenReturnIdOfThatRestaurant() throws Exception {
        given(restaurantService.deactivate(1)).willReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurants/1/deactivate")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getPaginatedRestaurantToAdmin_thenReturnGenericResponse() throws Exception {
        PageModel pageModel=new PageModel(7,1,2);
        GenericResponse genericResponse=new GenericResponse(pageModel,Arrays.asList(restaurant));
        given(restaurantService.getPaginatedRestaurantToAdmin(1,2))
                .willReturn(genericResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurants/admin/paginate/1/2")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.FOUND.value(), response.getStatus());
    }

    @Test
    public void getPaginatedRestaurantToUser_thenReturnGenericResponse() throws Exception {
        PageModel pageModel=new PageModel(7,0,1);
        GenericResponse genericResponse=new GenericResponse(pageModel,Arrays.asList(restaurant));
        given(restaurantService.getPaginatedRestaurantToUser(0,1))
                .willReturn(genericResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurants/user/paginate/0/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.FOUND.value(), response.getStatus());
    }

    @Test
    public void getPaginatedFood_thenReturnGenericResponse() throws Exception {
        PageModel pageModel=new PageModel(7,1,2);
        GenericResponse genericResponse=new GenericResponse(pageModel,Arrays.asList(food));
        given(foodService.getPaginatedFood(1,1,2))
                .willReturn(genericResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurants/1/foods/1/2")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.FOUND.value(), response.getStatus());
    }


    @Test
    public void getRestaurantById_thenReturnRestaurantOfRelatedToThatId() throws Exception {
        given(restaurantService.getRestaurantById(1)).willReturn(restaurant);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurants/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void deleteRestaurant_thenReturnTrue() throws Exception {
        given(restaurantService.deleteRestaurant(1)).willReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/restaurants/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void updateRestaurant_thenReturnUpdatedRestaurant() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(restaurant);
        given(restaurantService.updateRestaurant(restaurant,1)).willReturn(restaurant);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/restaurants/1")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void addRestaurant_whenObjectOfRestaurantIsNull_thenReturnStatusOK() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(restaurant);
        given(restaurantService.addRestaurant(restaurant)).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/restaurants")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void deactivateRestaurant_whenThereisNoSuchRestaurantRelatedToIdInRestaurantTable_thenReturn_NOT_FOUND() throws Exception {
        given(restaurantService.deactivate(1)).willReturn(false);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurants/1/deactivate")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void activateRestaurant_whenThereisNoSuchRestaurantRelatedToIdInRestaurantTable_thenReturn_NOT_FOUND() throws Exception {
        given(restaurantService.activate(1)).willReturn(false);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurants/1/activate")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void getRestaurantById_whenThereisNoSuchRestaurantRelatedToThatIdInRestaurantTable_thenReturn_NOT_FOUND() throws Exception {
        given(restaurantService.getRestaurantById(1)).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurants/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void deleteRestaurant_whenThereisNoIdInRestaurantTable_thenReturn_NOT_FOUND() throws Exception {
        given(restaurantService.deleteRestaurant(1)).willReturn(false);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/restaurants/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void updateRestaurant_whenThereIsNoSuchRestaurantRelatedToThatId_thenReturn_NOT_FOUND() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(restaurant);
        given(restaurantService.updateRestaurant(restaurant,1)).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/restaurants/1")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void getPaginatedRestaurantToAdmin_whenrThereisNoRestauranInRestaurantTablet_thenReturn_NOT_FOUND() throws Exception {
        given(restaurantService.getPaginatedRestaurantToAdmin(1,2))
                .willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurants/admin/paginate/1/2")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void getPaginatedRestaurantToUser_whenrThereisNoRestaurantInRestaurantTable_thenReturn_NOT_FOUND() throws Exception {
        given(restaurantService.getPaginatedRestaurantToUser(1,2))
                .willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurants/admin/paginate/1/2")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void getPaginatedFoods_whenrThereisNoFoodInFoodTable_thenReturn_NOT_FOUND() throws Exception {
        given(foodService.getPaginatedFood(1,1,2))
                .willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurants/admin/paginate/1/2")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

}
*/
