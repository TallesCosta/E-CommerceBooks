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
	deliveryForecast DATE NOT NULL,

	id_status INT NOT NULL,
	id_promotionalCoupon INT NOT NULL,
	id_deliveryAddress INT NOT NULL,
	id_chargeAddress INT NOT NULL,
	id_creditCard INT NOT NULL,
	id_customer INT NOT NULL,
	
	CONSTRAINT PK_Sale PRIMARY KEY (id)
);

ALTER TABLE Sales ADD FOREIGN KEY (id_status) REFERENCES Status(id);
ALTER TABLE Sales ADD FOREIGN KEY (id_promotionalCoupon) REFERENCES PromotionalCoupons(id);
ALTER TABLE Sales ADD FOREIGN KEY (id_deliveryAddress) REFERENCES Addresses(id);
ALTER TABLE Sales ADD FOREIGN KEY (id_chargeAddress) REFERENCES Addresses(id);
ALTER TABLE Sales ADD FOREIGN KEY (id_creditCard) REFERENCES CreditCards(id);
ALTER TABLE Sales ADD FOREIGN KEY (id_customer) REFERENCES Customers(id);