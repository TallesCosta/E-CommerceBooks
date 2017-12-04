/*
	Populate book table
*/

/* Stock to firt book */
INSERT INTO Stocks (enabled, averagePrice, minimumPrice, salePrice, realAmount, virtualAmount, id_book)
VALUES (true, 0.0, 0.0, 0.0, 0, 0, 1);

INSERT INTO StockItems (enabled, unitaryPrice, amount, id_stock)
VALUES (true, 350, 10, 1);

UPDATE Stocks
SET averagePrice = 35, minimumPrice = 35, salePrice = 35, realAmount = 10, virtualAmount = 10
WHERE id = 1;

/* Stock to second book */
INSERT INTO Stocks (enabled, averagePrice, minimumPrice, salePrice, realAmount, virtualAmount, id_book)
VALUES (true, 0.0, 0.0, 0.0, 0, 0, 2);

INSERT INTO StockItems (enabled, unitaryPrice, amount, id_stock)
VALUES (true, 240, 8, 2);

UPDATE Stocks
SET averagePrice = 30, minimumPrice = 30, salePrice = 30, realAmount = 8, virtualAmount = 8
WHERE id = 2;