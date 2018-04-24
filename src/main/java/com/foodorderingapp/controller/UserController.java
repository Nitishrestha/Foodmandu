package com.foodorderingapp.controller;

import com.foodorderingapp.requestdto.LoginRequestDto;
import com.foodorderingapp.requestdto.UserRequestDto;
import com.foodorderingapp.model.User;
import com.foodorderingapp.responsedto.UserResponseDto;
import com.foodorderingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.foodorderingapp.commons.WebUrlConstant.User.*;

@RestController
@RequestMapping(USER)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> add(@RequestBody @Valid UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        System.out.println(userResponseDto);
        return new ResponseEntity(userResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers()  {
        List<UserResponseDto> userResponseDtoList = userService.getUsers();
        if(userResponseDtoList ==null|| userResponseDtoList.size()==0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(userResponseDtoList, HttpStatus.OK);
    }


    @PostMapping(VERIFY_USER)
    public ResponseEntity<UserResponseDto> verifyUser(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        UserResponseDto userResponseDto =userService.verifyUser(loginRequestDto.getUserPassword(), loginRequestDto.getEmail());
        if(userResponseDto ==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(userResponseDto, HttpStatus.OK);
    }

    @GetMapping(GET_USER_BY_ID)
    public ResponseEntity<User> getUser(@PathVariable int userId) {
        UserResponseDto user = userService.getUser(userId);
        if(user==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
