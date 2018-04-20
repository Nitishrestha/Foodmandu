package com.foodorderingapp.Validator;


import com.foodorderingapp.model.Restaurant;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RestaurantValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Restaurant.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Restaurant restaurant = (Restaurant) target;
        if (restaurant.getFile() == null || restaurant.getFile().getOriginalFilename().equals("")) {
            errors.rejectValue("file", null, "Please select a file to upload!");
            return;
        }
        if (!(restaurant.getFile().getContentType().equals("image/jpeg") ||
                restaurant.getFile().getContentType().equals("image/png")) ||
                restaurant.getFile().getContentType().equals("image/gif")
                ) {
            errors.rejectValue("file", null, "Please select an image file to upload!");
            return;
        }

    }

}

