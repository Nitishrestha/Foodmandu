package com.foodorderingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodorderingapp.Application;
import com.foodorderingapp.controller.UserController;
import com.foodorderingapp.requestdto.LoginRequestDto;
import com.foodorderingapp.requestdto.UserRequestDto;
import com.foodorderingapp.responsedto.UserResponseDto;
import com.foodorderingapp.service.UserService;
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
public class UserControllerTest {

    private MockMvc mvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    UserRequestDto userRequestDto;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(userController).build();

        userRequestDto = new UserRequestDto();
        userRequestDto.setUserId(1);
        userRequestDto.setEmail("ram@yahoo.com");
        userRequestDto.setAddress("bkt");
        userRequestDto.setFirstName("ram");
        userRequestDto.setMiddleName("bahadur");
        userRequestDto.setLastName("thapa");
        userRequestDto.setUserPassword("ram");
        userRequestDto.setContactNo("981646176");
    }

    @Test
    public void addUser_whenAdded_thenReturnUser() throws Exception {

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(userRequestDto.getUserId());
        userResponseDto.setEmail(userRequestDto.getEmail());
        userResponseDto.setAddress(userRequestDto.getAddress());
        userResponseDto.setFirstName(userRequestDto.getFirstName());
        userResponseDto.setMiddleName(userRequestDto.getMiddleName());
        userResponseDto.setLastName(userRequestDto.getLastName());
        userResponseDto.setUserPassword(userRequestDto.getUserPassword());
        userResponseDto.setContactNo(userRequestDto.getContactNo());

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(userRequestDto);

        System.out.println(jsonString);
        given(userService.addUser(userRequestDto)).willReturn(userResponseDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        UserResponseDto returnedUser = mapper.readValue(outputInJson, UserResponseDto.class);
        assertThat(returnedUser).isEqualTo(userResponseDto);
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getUsers_thenReturnUserList() throws Exception {
        given(userService.getUsers()).willReturn(Arrays.asList(new UserResponseDto()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println(response);
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getUsers_thenReturnListOfUserDtoResponse() throws Exception {
        given(userService.getUsers()).willReturn(Arrays.asList(new UserResponseDto()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void verifyUser() throws Exception {
        LoginRequestDto loginRequestDto = new LoginRequestDto( "ram","ram@yahoo.com");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(loginRequestDto);
        given(userService.verifyUser(loginRequestDto.getUserPassword(), loginRequestDto.getEmail())).willReturn(new UserResponseDto(1,"ram","ram@yahoo.com",
                "ktm","ram","kumar","verma",
                "93833","user",1200));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/verify")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(userRequestDto);
        given(userService.getUser(userRequestDto.getUserId())).willReturn(new UserResponseDto());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/1")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isNotEmpty();
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void addUser_whenSomePropertyAreMissing_thenReturnBadRequest() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(null);
        System.out.println(jsonString);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void getUsers_whenThereAreNoRecordOfUserInDatabase_thenReturnBadRequest() throws Exception {

        given(userService.getUsers()).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isEmpty();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void verifyUser_whenThereAreNoUserPasswordAndEmailInUserTable_thenReturnBadRequest() throws Exception {

        LoginRequestDto loginRequestDto = new LoginRequestDto("ram","ram@yahoo.com");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(loginRequestDto);
        given(userService.verifyUser(loginRequestDto.getUserPassword(), loginRequestDto.getEmail())).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/verify")
                .accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isEmpty();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void getUser_whenThereAreNoSuchUserInUserTable_thenReturnBadRequest() throws Exception {

        given(userService.getUser(2)).willReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/2")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isEmpty();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}
