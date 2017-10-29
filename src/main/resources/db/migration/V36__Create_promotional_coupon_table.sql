/*
	Create promotional-coupons table
*/
CREATE TABLE PromotionalCoupons (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	code VARCHAR(255) NOT NULL,
	value DOUBLE NOT NULL,
	
	CONSTRAINT PK_PromotionalCoupon PRIMARY KEY (id)
);