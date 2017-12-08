/*
	Create exchange-coupons table
*/
CREATE TABLE ExchangeCoupons (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	code VARCHAR(255) NOT NULL,
	value DOUBLE NOT NULL,

	id_customer INT NOT NULL,

	CONSTRAINT PK_ExchangeCoupon PRIMARY KEY (id)
);

ALTER TABLE ExchangeCoupons ADD FOREIGN KEY (id_customer) REFERENCES Customers(id);