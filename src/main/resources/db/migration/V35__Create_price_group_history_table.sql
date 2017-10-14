/*
	Create price-group-history table
*/
CREATE TABLE PriceGroupsHistory (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	markup DECIMAL(4,2) NOT NULL,
					
	version INT NOT NULL,
	date TIMESTAMP NOT NULL,
	
	id_priceGroup INT NOT NULL,	
	id_user INT NOT NULL,

	CONSTRAINT PK_PriceGroupHistory PRIMARY KEY (id)
);

ALTER TABLE PriceGroupsHistory ADD FOREIGN KEY (id_priceGroup) REFERENCES PriceGroups(id);
