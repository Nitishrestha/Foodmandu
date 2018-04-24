package com.foodorderingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_food")
public class Food{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id",nullable=false,updatable = false)
    private int id;
    @Column(name="food_name")
    @NotBlank(message = "This field is required.")
    @Size(min=4,max=15,message = "first name must be between 2 and 20.")
    private String name;
    @Column(name = "food_price")
    private double price;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    private transient int restaurantId;

    public Food(int id,String name, double price, Restaurant restaurant) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Food(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        if(this.restaurant==null){
            this.restaurant=new Restaurant();
        }
        return restaurant;
    }


    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getRestaurantId() {
      if(restaurantId==0){
          return this.getRestaurant().getId();
      }
      return restaurantId;

    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        return id == food.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
