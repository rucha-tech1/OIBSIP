# ATM Interface

## Project Description

ATM Interface is a console-based Java application that simulates basic ATM banking operations.

The project allows users to log in using a User ID and PIN and perform different banking transactions such as checking balance, depositing money, withdrawing money, transferring money, and viewing transaction history.

## Features

- User ID and PIN authentication
- Maximum 3 login attempts
- Balance checking
- Deposit money
- Withdraw money
- Insufficient funds validation
- Transfer money to another account
- Transaction history
- Transaction records stored using ArrayList
- Multiple Java classes using Object-Oriented Programming
- Logout and quit option

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- ArrayList
- Encapsulation
- Classes and Objects
- Switch-case
- Scanner

## Java Classes

The project contains the following classes:

- `Main.java` - Starts the ATM application
- `ATM.java` - Handles ATM operations and menu
- `Account.java` - Stores account information and balance
- `Transaction.java` - Stores transaction details
- `Bank.java` - Manages accounts and banking operations

## ATM Operations

### 1. Transaction History

Displays the transactions performed during the current session.

### 2. Withdraw

Allows the user to withdraw money after checking whether sufficient balance is available.

### 3. Deposit

Allows the user to deposit money and updates the account balance.

### 4. Transfer

Allows the user to transfer money to another account using the recipient account ID.

### 5. Quit

Ends the ATM session and displays a goodbye message.

## Project Structure

```text
Java-Task3-ATMInterface/
│
├── ATM.java
├── Account.java
├── Transaction.java
├── Bank.java
├── Main.java
├── README.md
└── screenshots/
