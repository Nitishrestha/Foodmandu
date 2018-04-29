package com.foodorderingapp.controller;

import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.requestdto.FavoriteRequestDto;
import com.foodorderingapp.responsedto.FavoriteResponseDto;
import com.foodorderingapp.responsedto.MessageResponseDto;
import com.foodorderingapp.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.foodorderingapp.commons.WebUrlConstant.Favorite.*;

@RestController
@RequestMapping(FAVORITE)
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping(ADD_FAVORITE)
    public ResponseEntity<MessageResponseDto> addFavorite(@RequestBody FavoriteRequestDto favoriteRequestDto) {
        MessageResponseDto messageResponseDto = favoriteService.addFavorite(favoriteRequestDto.getUserId(), favoriteRequestDto.getRestaurantId());
        return new ResponseEntity<>(messageResponseDto, HttpStatus.OK);
    }

    @GetMapping(GET_FAVORITE)
    public ResponseEntity<List<Restaurant>> getFavoriteRestaurant(@PathVariable int userId) {
        List<Restaurant> restaurant = favoriteService.getFavoriteRestaurant(userId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping(DELETE_FAVORITES)
    public ResponseEntity<MessageResponseDto> deleteFavoriteRestaurant(@PathVariable int userId, @PathVariable int restaurantId) {
        MessageResponseDto messageResponseDto = favoriteService.deleteFavorite(userId, restaurantId);
        return new ResponseEntity<>(messageResponseDto, HttpStatus.OK);
    }

    @GetMapping(GET_FAVORITES_USER_BY_RESTAURANTID)
    public ResponseEntity<List<FavoriteResponseDto>> getUsersOfFavoratedRestaurant(@PathVariable int restaurantId) {
        List<FavoriteResponseDto> favoriteResponseDtos = favoriteService.getUsersOfFavoratedRestaurant(restaurantId);
        return new ResponseEntity<>(favoriteResponseDtos, HttpStatus.OK);
    }
}
