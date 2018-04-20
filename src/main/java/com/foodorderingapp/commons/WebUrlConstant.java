package com.foodorderingapp.commons;

public class WebUrlConstant {

    public interface Order {
        String ORDER = "/order";
        String TODAY_ORDER_TO_ADMIN = "/admin/today";
        String MONTHLY_ORDER_TO_ADMIN = "/admin/month";
        String TODAY_ORDER_TO_USER = "/user/{userId}";
        String MONTHLY_ORDER_TO_USER = "/userList/{userId}";
        String CONFIRM = "/confirm/{orderId}";
        String WATCHED = "/watched/{orderId}";
    }

    public interface User {
        String USER = "/user";
        String VERIFY_USER = "/verify";
        String GET_USER_BY_ID = "/{userId}";
    }

    public interface OrderDetail {
        String ORDER_DETAIL = "/orderDetail";
    }

    public interface Food {
        String FOOD = "/foods";
        String GET_FOOD_BY_ID = "/{id}";
        String DELETE_FOOD_BY_ID = "/{id}";
        String UPDATE_FOOD_BY_ID = "/{id}";
    }

    public interface Restaurant {
        String RESTAURANT = "/restaurants";
        String GET_FOOD_BY_RESTAURANT = "/{id}/foods";
        String ACTIVATE_RESTAURANT = "/{id}/activate";
        String DEACTIVATE_RESTAURANT = "/{id}/deactivate";
        String GET_PAGINATED_RESTAURANT_TO_ADMIN = "admin/paginate/{firstResult}/{maxResult}";
        String GET_PAGINATED_RESTAURANT_TO_USER = "user/{id}/paginate/{firstResult}/{maxResult}";
        String GET_PAGINATED_FOOD = "/{id}/foods/{firstResult}/{maxResult}";
        String GET_RESTAURANT_BY_ID = "/{id}";
        String DELETE_RESTAURANT_BY_ID = "/{id}";
        String UPDATE_RESTAURANT_BY_ID = "/{id}";
    }
}
