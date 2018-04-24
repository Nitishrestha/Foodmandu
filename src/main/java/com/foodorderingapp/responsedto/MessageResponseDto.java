package com.foodorderingapp.responsedto;

public class MessageResponseDto<T> {
    private double balance;
    private T message;

    public MessageResponseDto(double balance, T message) {
        this.balance = balance;
        this.message = message;
    }

    public MessageResponseDto(T message) {
        this.message = message;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
