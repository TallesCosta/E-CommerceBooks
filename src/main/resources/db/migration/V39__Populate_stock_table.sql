/*
	Populate book table
*/

/* Stock to firt book */
INSERT INTO Stocks (enabled, averagePrice, minimumPrice, salePrice, realAmount, id_book)
VALUES (true, 0.0, 0.0, 0.0, 0, 1);

INSERT INTO StockItems (enabled, unitaryPrice, amount, id_stock)
VALUES (true, 35, 100, 1);

UPDATE Stocks
SET averagePrice = 35, minimumPrice = 47.25, salePrice = 47.25, realAmount = 100
WHERE id = 1;

/* Stock to second book */
INSERT INTO Stocks (enabled, averagePrice, minimumPrice, salePrice, realAmount, id_book)
VALUES (true, 0.0, 0.0, 0.0, 0, 2);

INSERT INTO StockItems (enabled, unitaryPrice, amount, id_stock)
VALUES (true, 24, 80, 2);

UPDATE Stocks
SET averagePrice = 30, minimumPrice = 30.17, salePrice = 30.17, realAmount = 80
WHERE id = 2;