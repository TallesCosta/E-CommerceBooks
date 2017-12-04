/*
  Create shipping cost base value
*/
CREATE TABLE ShippingCosts (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	baseValue DOUBLE NOT NULL,
	baseAdditionValue DOUBLE NOT NULL,

	id_state INT NOT NULL,

	CONSTRAINT PK_ShippingCosts PRIMARY KEY (id)
);

ALTER TABLE ShippingCosts ADD FOREIGN KEY (id_state) REFERENCES States(id);