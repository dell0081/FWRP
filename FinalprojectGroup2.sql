CREATE DATABASE FWRP;

USE FWRP;

CREATE TABLE User (
    UserId INT PRIMARY KEY AUTO_INCREMENT,
    UserName VARCHAR(45),
    Email VARCHAR(45),
    Password VARCHAR(45),
    PhoneNumber VARCHAR(45),
    Address VARCHAR(45),
    UserType ENUM('retailer', 'consumer', 'charitable_organization'),
    AlertSubscription BOOLEAN
);

CREATE TABLE RetailInventory (
    FoodItemId INT PRIMARY KEY AUTO_INCREMENT,
    ItemName VARCHAR(45),
    Quantity INT,
    Price DECIMAL(10, 2),
    ExpiryDate DATETIME,
    Address VARCHAR(45),
    UserId INT,
    FOREIGN KEY (UserId) REFERENCES User(UserId)
);

CREATE TABLE Bookkeeping (
    LogId INT PRIMARY KEY AUTO_INCREMENT,
    TransactionType ENUM('donation', 'purchase'),
    FoodItemId INT,
    UserId INT,
    Quantity INT,
    Price DECIMAL(10, 2),
    TransactionDate DATETIME,
    Address VARCHAR(45),
    FOREIGN KEY (FoodItemId) REFERENCES RetailInventory(FoodItemId),
    FOREIGN KEY (UserId) REFERENCES User(UserId)
);

CREATE TABLE Subscription (
    SubscriptionId INT PRIMARY KEY AUTO_INCREMENT,
    UserId INT,
    FoodItemId INT,
    FOREIGN KEY (UserId) REFERENCES User(UserId),
    FOREIGN KEY (FoodItemId) REFERENCES RetailInventory(FoodItemId)
);

CREATE TABLE Coupons (
    CouponId INT PRIMARY KEY AUTO_INCREMENT,
    UserId INT,
    CouponThreshold DECIMAL(10, 2),
    FOREIGN KEY (UserId) REFERENCES User(UserId)
);
