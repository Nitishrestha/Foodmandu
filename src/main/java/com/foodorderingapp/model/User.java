package com.foodorderingapp.model;

import com.foodorderingapp.responsedto.UserListMapperDto;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tbl_users")
@SqlResultSetMapping(
        name="UserMapping",
        classes =
                {@ConstructorResult(targetClass = UserListMapperDto.class,
                        columns = {
                                @ColumnResult(name="order_id", type=Integer.class),
                                @ColumnResult(name="user_id", type=Integer.class),
                                @ColumnResult(name="ordered_date",type=Date.class),
                                @ColumnResult(name="confirm",type=Boolean.class)
                        })})

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;
    @NotNull(message = "Can't be null.")
    @Column(name="first_name")
    private String firstName;
    @Column(name="middle_name")
    private String middleName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="user_password")
    private String userPassword;
    @Column(name="email")
    private String email;
    @Column(name="contact_no")
    private String contactNo;
    @Column(name="address")
    private String address;
    @Column(name="user_role")
    private String userRole="user";
    @Column(name="balance")
    private double balance=1200;

    public User(String firstName, String middleName, String lastName, String userPassword,
                String email, String contactNo, String address, String userRole, double balance) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.userPassword = userPassword;
        this.email = email;
        this.contactNo = contactNo;
        this.address = address;
        this.userRole = userRole;
        this.balance = balance;
    }

    public User(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return firstName != null ? firstName.equals(user.firstName) : user.firstName == null;
    }

    @Override
    public int hashCode() {
        return firstName != null ? firstName.hashCode() : 0;
    }
}
