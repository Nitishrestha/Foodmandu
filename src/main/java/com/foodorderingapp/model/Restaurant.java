package com.foodorderingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity

@SqlResultSetMapping(
        name="RestaurantMapping",
        classes =
                {@ConstructorResult(targetClass = Restaurant.class,
                        columns = {
                                @ColumnResult(name="restaurant_id", type=Integer.class),
                                @ColumnResult(name="restaurant_name", type=String.class),
                                @ColumnResult(name="restaurant_address", type=String.class),
                                @ColumnResult(name="restaurant_contact",type=String.class),
                                @ColumnResult(name="status",type=Boolean.class),
                                @ColumnResult(name="favorated",type=Boolean.class),
                        })})
@Table(name = "tbl_restaurant")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id",nullable=false,updatable = false)
    private int id;

    private int id2;

    @Column(name = "restaurant_name")
    @NotNull(message = "please enter the restaurant name")
    @Size(min=3,max=25)
    private String name;

    @Column(name = "restaurant_address")
    @NotBlank(message = "please enter the restaurant address")
    @Size(min=3,max=25)
    private String address;

    @Column(name = "restaurant_contact")
    @NotBlank(message = "please enter the restaurant contact")
    @Size(min=7,max=10)
    private String contact ;

    @Column(name = "status")
    private boolean isActive = true;

    @Transient
    private boolean favorated;

    public Restaurant(String name, String address, String contact, boolean isActive) {

        this.name = name;
        this.address = address;
        this.contact = contact;
        this.isActive = isActive;
    }

    public Restaurant(int id,String name, String address, String contact, boolean isActive, boolean favorated) {
        this.id=id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.isActive = isActive;
        this.favorated = favorated;
    }

    public Restaurant() {
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public boolean isFavorated() {
        return favorated;
    }

    public void setFavorated(boolean favorated) {
        this.favorated = favorated;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
