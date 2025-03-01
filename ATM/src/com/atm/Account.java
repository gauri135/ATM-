package com.atm;

public class Account {
    private int balance;
    private int pin;

    public Account(int balance, int pin) {
        this.balance = balance;
        this.pin = pin;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public boolean withdraw(int amount) {
        if (balance < amount) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }

    public void changePin(int newPin) {
        this.pin = newPin;
    }
}

