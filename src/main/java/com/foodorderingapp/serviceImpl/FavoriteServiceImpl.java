package com.foodorderingapp.serviceImpl;

import com.foodorderingapp.dao.FavoriteDAO;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.Favorite;
import com.foodorderingapp.model.Restaurant;
import com.foodorderingapp.model.User;
import com.foodorderingapp.requestdto.UserRequestDto;
import com.foodorderingapp.responsedto.FavoriteResponseDto;
import com.foodorderingapp.responsedto.MessageResponseDto;
import com.foodorderingapp.responsedto.UserResponseDto;
import com.foodorderingapp.service.FavoriteService;
import com.foodorderingapp.service.RestaurantService;
import com.foodorderingapp.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    private final UserService userService;
    private final RestaurantService restaurantService;
    private final FavoriteDAO favoriteDAO;

    @Autowired
    public FavoriteServiceImpl(UserService userService,
                               FavoriteDAO favoriteDAO, RestaurantService restaurantService) {
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.favoriteDAO = favoriteDAO;

    }

    @Override
    public MessageResponseDto addFavorite(int userId, int restaurantId) {
        User user = new User();
        UserResponseDto userResponseDto = userService.getUser(userId);
        BeanUtils.copyProperties(userResponseDto, user);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        Favorite favorite1 = favoriteDAO.getUserAndRestaurantIds(userId, restaurantId);
        if (favorite1 == null) {
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setRestaurant(restaurant);
            favorite.setFavorated(true);
            favoriteDAO.addFavorite(favorite);
            return new MessageResponseDto("Your favorite restaurant has been added.");
        } else if (favorite1.isFavorated() == true) {
            favorite1.setFavorated(false);
            favoriteDAO.update(favorite1);
            return new MessageResponseDto("Your favourite restaurant has been removed from your favorite list.");
        } else {
            favorite1.setFavorated(true);
            favoriteDAO.update(favorite1);
            return new MessageResponseDto("Your previous favourite restaurant has been place in your favorite list.");
        }
    }

    @Override
    public List<Restaurant> getFavoriteRestaurant(int userId) {
        List<Restaurant> restaurantList = new ArrayList<>();
        List<Favorite> favoriteList = favoriteDAO.getFavoriteRestaurantByUserId(userId);
        for (Favorite favorite : favoriteList) {
            Restaurant restaurant = restaurantService.getRestaurantById(favorite.getRestaurant().getId());
            restaurantList.add(restaurant);
        }
        return restaurantList;
    }

    @Override
    public List<FavoriteResponseDto> getUsersOfFavoratedRestaurant(int restaurantId) {
        List<FavoriteResponseDto> favoriteResponseDtos = new ArrayList<>();
        List<Favorite> favoriteList = favoriteDAO.getFavoriteByRestaurantId(restaurantId);
        for (Favorite favorite : favoriteList) {
            UserResponseDto user = userService.getUser(favorite.getUser().getUserId());
            FavoriteResponseDto favoriteResponseDto = new FavoriteResponseDto();
            favoriteResponseDto.setFirstName(user.getFirstName());
            favoriteResponseDto.setMiddleName(user.getMiddleName());
            favoriteResponseDto.setLastName(user.getLastName());
            favoriteResponseDto.setRestaurantName(favorite.getRestaurant().getName());
            favoriteResponseDto.setAddress(user.getAddress());
            favoriteResponseDto.setContactNo(user.getContactNo());
            favoriteResponseDto.setEmail(user.getEmail());
            favoriteResponseDtos.add(favoriteResponseDto);
        }
        return favoriteResponseDtos;
    }

    @Override
    public MessageResponseDto deleteFavorite(int userId, int restaurantId) {
        Favorite favorite = favoriteDAO.getUserAndRestaurantIds(userId, restaurantId);
        if (favorite == null) {
            throw new DataNotFoundException("cannot find favorite restaurant for that user.");
        }
        favoriteDAO.delete(favorite);
        return new MessageResponseDto("favorite restaurant deleted.");
    }

}

