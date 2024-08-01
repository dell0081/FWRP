
INSERT INTO RetailInventory (itemName, price, quantity, surplus, expiryDate, userId, address) VALUES
-- Surplus = 0
('Apple', 2, 50, 0, DATE_ADD(CURRENT_DATE(), INTERVAL 2 DAY), 1, '123 Veggie Street, Greensville'),
('Banana', 1, 100, 0, DATE_ADD(CURRENT_DATE(), INTERVAL 3 DAY), 1, '123 Veggie Street, Greensville'),
('Carrot', 3, 30, 0, DATE_ADD(CURRENT_DATE(), INTERVAL 4 DAY), 2, '456 Tropical Avenue, Fruitland'),
('Lettuce', 2, 20, 0, DATE_ADD(CURRENT_DATE(), INTERVAL 5 DAY), 2, '456 Tropical Avenue, Fruitland'),
('Chicken Breast', 8, 40, 0, DATE_ADD(CURRENT_DATE(), INTERVAL 6 DAY), 3, '789 Starchy Lane, Carb City'),
('White Rice', 5, 60, 0, DATE_ADD(CURRENT_DATE(), INTERVAL 7 DAY), 3, '789 Starchy Lane, Carb City'),
('Milk', 4, 80, 0, DATE_ADD(CURRENT_DATE(), INTERVAL 8 DAY), 1, '123 Veggie Street, Greensville'),
('Bread', 2, 70, 0, DATE_ADD(CURRENT_DATE(), INTERVAL 9 DAY), 2, '456 Tropical Avenue, Fruitland'),
('Tomato', 2, 40, 0, DATE_ADD(CURRENT_DATE(), INTERVAL 10 DAY), 3, '789 Starchy Lane, Carb City'),
('Ground Beef', 10, 35, 0, DATE_ADD(CURRENT_DATE(), INTERVAL 11 DAY), 1, '123 Veggie Street, Greensville'),

-- Surplus = 1
('Orange', 3, 45, 1, DATE_ADD(CURRENT_DATE(), INTERVAL 6 DAY), 2, '456 Tropical Avenue, Fruitland'),
('Pear', 2, 60, 1, DATE_ADD(CURRENT_DATE(), INTERVAL 2 DAY), 2, '456 Tropical Avenue, Fruitland'),
('Broccoli', 4, 25, 1, DATE_ADD(CURRENT_DATE(), INTERVAL 3 DAY), 3, '789 Starchy Lane, Carb City'),
('Salmon', 12, 20, 1, DATE_ADD(CURRENT_DATE(), INTERVAL 4 DAY), 3, '789 Starchy Lane, Carb City'),
('Potato', 2, 55, 1, DATE_ADD(CURRENT_DATE(), INTERVAL 10 DAY), 1, '123 Veggie Street, Greensville'),
('Pasta', 3, 65, 1, DATE_ADD(CURRENT_DATE(), INTERVAL 1 DAY), 2, '456 Tropical Avenue, Fruitland'),
('Yogurt', 2, 30, 1, DATE_ADD(CURRENT_DATE(), INTERVAL 12 DAY), 3, '789 Starchy Lane, Carb City'),
('Cheese', 6, 40, 1, DATE_ADD(CURRENT_DATE(), INTERVAL 13 DAY), 1, '123 Veggie Street, Greensville'),
('Turkey', 9, 25, 1, DATE_ADD(CURRENT_DATE(), INTERVAL 14 DAY), 2, '456 Tropical Avenue, Fruitland'),
('Eggs', 4, 50, 1, DATE_ADD(CURRENT_DATE(), INTERVAL 15 DAY), 3, '789 Starchy Lane, Carb City'),

-- Surplus = 2
('Grapes', 4, 35, 2, DATE_ADD(CURRENT_DATE(), INTERVAL 4 DAY), 1, '123 Veggie Street, Greensville'),
('Cucumber', 1, 75, 2, DATE_ADD(CURRENT_DATE(), INTERVAL 6 DAY), 2, '456 Tropical Avenue, Fruitland'),
('Bell Pepper', 2, 45, 2, DATE_ADD(CURRENT_DATE(), INTERVAL 1 DAY), 3, '789 Starchy Lane, Carb City'),
('Ground Turkey', 7, 30, 2, DATE_ADD(CURRENT_DATE(), INTERVAL 2 DAY), 1, '123 Veggie Street, Greensville'),
('Olive Oil', 8, 20, 2, DATE_ADD(CURRENT_DATE(), INTERVAL 3 DAY), 2, '456 Tropical Avenue, Fruitland'),
('Tofu', 3, 40, 2, DATE_ADD(CURRENT_DATE(), INTERVAL 13 DAY), 3, '789 Starchy Lane, Carb City'),
('Spinach', 2, 55, 2, DATE_ADD(CURRENT_DATE(), INTERVAL 14 DAY), 1, '123 Veggie Street, Greensville'),
('Quinoa', 6, 25, 2, DATE_ADD(CURRENT_DATE(), INTERVAL 15 DAY), 2, '456 Tropical Avenue, Fruitland'),
('Almond Milk', 3, 30, 2, DATE_ADD(CURRENT_DATE(), INTERVAL 16 DAY), 3, '789 Starchy Lane, Carb City'),
('Shrimp', 15, 20, 2, DATE_ADD(CURRENT_DATE(), INTERVAL 17 DAY), 1, '123 Veggie Street, Greensville');


INSERT INTO User (userName, password, email, phoneNumber, address, subscriptionPreference, displayName, userType) VALUES
-- Retailers
('CaptainKale', 'sup3rK4l3', 'captainkale@example.com', '123-456-7890', '123 Veggie Street, Greensville', 0, 'Captain Kale', 1),
('MangoMaven', 'fruityP4ssw0rd', 'mango@example.com', '987-654-3210', '456 Tropical Avenue, Fruitland', 0, 'Mango Maven', 1),
('SultanofSpuds', 'potatoP0w3r', 'spuds@example.com', '555-123-4567', '789 Starchy Lane, Carb City', 0, 'Sultan of Spuds', 1),

-- Charities
('SoupSavior', 'bowlful0fch4nge', 'soupsavior@example.com', '111-222-3333', '321 Ladle Lane, Brothville', 0, 'Soup Savior', 2),
('BreadBenevolent', 'crustyc4res', 'breadbenevolent@example.com', '444-555-6666', '654 Loaf Street, Gluten Town', 0, 'Bread Benevolent', 2),
('FruitFairy', 'juicyd3lights', 'fruitfairy@example.com', '777-888-9999', '987 Orchard Avenue, NutriVille', 0, 'Fruit Fairy', 2),

-- Consumers
('HungryHippo', 'munchies4eva', 'hungryhippo@example.com', '222-333-4444', '101 Snack Lane, Crave City', 1, 'Hungry Hippo', 3),
('PizzaPirate', 'cheeseytreasure', 'pizzapirate@example.com', '666-777-8888', '202 Pepperoni Drive, PizzaLand', 1, 'Pizza Pirate', 3),
('SweetToothSiren', 'sug4rC0nsum3r', 'sweettooth@example.com', '999-000-1111', '303 Candy Lane, Sugarville', 1, 'Sweet Tooth Siren', 3);