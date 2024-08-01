-- Inserting into User table
INSERT INTO User (UserName, Email, Password, PhoneNumber, Address, UserType, AlertSubscription) VALUES 
('David Brown', 'david@example.com', 'password123', '3456789012', '321 Pine St', 'retailer', true),
('Emma Green', 'emma@example.com', 'password123', '4567890123', '654 Cedar Rd', 'consumer', false),
('Frank Black', 'frank@example.com', 'password123', '5678901234', '987 Birch Ln', 'charitable_organization', true),
('Grace Lee', 'grace@example.com', 'password123', '6789012345', '123 Walnut Ave', 'consumer', true),
('Hannah Taylor', 'hannah@example.com', 'password123', '7890123456', '456 Spruce Blvd', 'retailer', false);

-- Inserting into RetailInventory table
INSERT INTO RetailInventory (ItemName, Quantity, Price, ExpiryDate, Address, UserId) VALUES 
('Oranges', 150, 0.40, '2024-07-26', '321 Pine St', 4),
('Lettuce', 80, 0.25, '2024-07-21', '321 Pine St', 4),
('Tomatoes', 120, 0.35, '2024-07-28', '321 Pine St', 4),
('Potatoes', 300, 0.15, '2024-08-01', '456 Spruce Blvd', 8),
('Onions', 250, 0.20, '2024-07-29', '456 Spruce Blvd', 8);

-- Inserting into Bookkeeping table
INSERT INTO Bookkeeping (TransactionType, FoodItemId, UserId, Quantity, Price, TransactionDate, Address) VALUES 
('purchase', 3, 5, 20, 0.35, '2024-07-02', '123 Walnut Ave'),
('purchase', 4, 6, 30, 0.15, '2024-07-03', '456 Cedar Rd'),
('donation', 6, 7, 80, 0.00, '2024-07-04', '987 Birch Ln'),
('donation', 7, 7, 100, 0.00, '2024-07-05', '987 Birch Ln'),
('purchase', 8, 9, 50, 0.20, '2024-07-06', '123 Walnut Ave');

-- Inserting into Subscription table
INSERT INTO Subscription (UserId, FoodItemId) VALUES 
(5, 3),
(5, 4),
(6, 6),
(7, 7),
(9, 8);

-- Inserting into Coupons table
INSERT INTO Coupons (UserId, CouponThreshold) VALUES 
(5, 15.00),
(6, 20.00),
(7, 25.00),
(8, 30.00),
(9, 35.00);
