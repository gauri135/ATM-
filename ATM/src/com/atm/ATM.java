package com.atm;

import java.util.Scanner;

public class ATM {
    private Account account;
    private Transaction transaction;
    private Menu menu;

    public ATM(int initialBalance, int pin) {
        account = new Account(initialBalance, pin);
        transaction = new Transaction();
        menu = new Menu();
    }

    private boolean authenticateUser(Scanner scanner) {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter the PIN: ");
            int inputPin = Integer.parseInt(scanner.nextLine());
            if (account.validatePin(inputPin)) {
                return true;
            }
            attempts++;
            System.out.println("Invalid PIN! Attempts remaining: " + (3 - attempts));
        }

        System.out.println("Account locked. Please wait for a few seconds before trying again.");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Error while waiting.");
        }
        return false;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        if (!authenticateUser(scanner)) {
            System.out.println("You have been locked out. Exiting the program.");
            return;
        }

        int option = 0;
        while (option != 6) {
            menu.display();
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    System.out.println("Balance: $" + account.getBalance());
                    transaction.addTransaction("Checked Balance: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter Amount to Deposit: ");
                    int depositAmount = Integer.parseInt(scanner.nextLine());
                    account.deposit(depositAmount);
                    transaction.addTransaction("Deposited: $" + depositAmount);
                    break;
                case 3:
                    System.out.print("Enter Amount to Withdraw: ");
                    int withdrawAmount = Integer.parseInt(scanner.nextLine());
                    if (account.withdraw(withdrawAmount)) {
                        transaction.addTransaction("Withdrew: $" + withdrawAmount);
                    } else {
                        System.out.println("Insufficient funds!");
                    }
                    break;
                case 4:
                    System.out.print("Enter New PIN: ");
                    int newPin = Integer.parseInt(scanner.nextLine());
                    account.changePin(newPin);
                    transaction.addTransaction("Changed PIN");
                    System.out.println("PIN successfully changed.");
                    break;
                case 5:
                    transaction.displayHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        ATM atm = new ATM(10000, 1234);
        atm.start();
    }
}
