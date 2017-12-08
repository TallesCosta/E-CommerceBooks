/*
	Create delivery-address table
*/
CREATE TABLE DeliveryAddresses (
  id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
  alias VARCHAR(255) NOT NULL,
  observation VARCHAR(255),
  publicPlaceType VARCHAR(255) NOT NULL,
  publicPlace VARCHAR(255) NOT NULL,
  number VARCHAR(255) NOT NULL,
  district VARCHAR(255) NOT NULL,
  postalCode VARCHAR(255) NOT NULL,
  homeType VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,

	id_state INT NOT NULL,
	id_customer INT,
	
	CONSTRAINT PK_DeliveryAddress PRIMARY KEY (id)
);

ALTER TABLE DeliveryAddresses ADD FOREIGN KEY (id_state) REFERENCES States(id);
ALTER TABLE DeliveryAddresses ADD FOREIGN KEY (id_customer) REFERENCES Customers(id);