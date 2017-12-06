/*
	Create sale table
*/
CREATE TABLE Sales (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	
	saleNumber VARCHAR(255) NOT NULL,
	date DATE NOT NULL,
	price DOUBLE NOT NULL,
	totalAmount INT NOT NULL,
    status VARCHAR(255) NOT NULL,
	deliveryForecast DATE NOT NULL,
	shippingCost DOUBLE NOT NULL,

	id_promotionalCoupon INT,
	id_deliveryAddress INT NOT NULL,
	id_creditCard INT NOT NULL,
	id_customer INT NOT NULL,
	
	CONSTRAINT PK_Sale PRIMARY KEY (id)
);

ALTER TABLE Sales ADD FOREIGN KEY (id_promotionalCoupon) REFERENCES PromotionalCoupons(id);
ALTER TABLE Sales ADD FOREIGN KEY (id_deliveryAddress) REFERENCES Addresses(id);
ALTER TABLE Sales ADD FOREIGN KEY (id_creditCard) REFERENCES CreditCards(id);
ALTER TABLE Sales ADD FOREIGN KEY (id_customer) REFERENCES Customers(id);