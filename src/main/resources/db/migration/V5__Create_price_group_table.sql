/*
	Create price-group table
*/
CREATE TABLE PriceGroups (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    markup DECIMAL(4,2) NOT NULL,
	
	CONSTRAINT PK_PriceGroup PRIMARY KEY (id)
);