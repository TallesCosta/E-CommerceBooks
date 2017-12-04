/*
	Create sale-item table
*/
CREATE TABLE SaleItems (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	unitaryPrice DOUBLE NOT NULL,
	amount INT NOT NULL,

	id_sale INT NOT NULL,
	id_book INT NOT NULL,
	
	CONSTRAINT PK_SaleItem PRIMARY KEY (id)
);

ALTER TABLE SaleItems ADD FOREIGN KEY (id_sale) REFERENCES Sales(id);
ALTER TABLE SaleItems ADD FOREIGN KEY (id_book) REFERENCES Books(id);