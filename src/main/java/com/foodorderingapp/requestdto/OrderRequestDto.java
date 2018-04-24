package com.foodorderingapp.requestdto;



import javax.validation.Valid;
import java.util.List;

public class OrderRequestDto {

    private int userId;
    @Valid
    private List<FoodQuantityRequestDto> foodList;

    public OrderRequestDto(int userId, List<FoodQuantityRequestDto> foodList) {
        this.userId = userId;
        this.foodList = foodList;
    }

    public OrderRequestDto(){

    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Valid
    public List<FoodQuantityRequestDto> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodQuantityRequestDto> foodList) {
        this.foodList = foodList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderRequestDto orderRequestDto = (OrderRequestDto) o;

        if (userId != orderRequestDto.userId) return false;
        return foodList.equals(orderRequestDto.foodList);
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + foodList.hashCode();
        return result;
    }
}
