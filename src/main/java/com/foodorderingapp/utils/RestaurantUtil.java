package com.foodorderingapp.utils;

import com.foodorderingapp.model.Restaurant;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class RestaurantUtil {

    public static Restaurant getRestaurant(MultipartHttpServletRequest request) {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setContact(contact);
        return restaurant;
    }

    public static Restaurant copyRestaurant(Restaurant table, Restaurant form) {
        table.setName(form.getName());
        table.setAddress(form.getAddress());
        table.setContact(form.getContact());
        table.setRestaurantCode(form.getRestaurantCode());
        table.setFile(form.getFile());
        return table;
    }
}