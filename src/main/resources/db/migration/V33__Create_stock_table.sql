/*
	Create stock table
*/
CREATE TABLE Stocks (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	averagePrice DOUBLE NOT NULL,
	minimumPrice DOUBLE NOT NULL,
	salePrice DOUBLE NOT NULL,
	realAmount INT NOT NULL,
	/*virtualAmount INT NOT NULL,*/
	
	id_book INT NOT NULL,

	CONSTRAINT PK_Stock PRIMARY KEY (id)
);

ALTER TABLE Stocks ADD FOREIGN KEY (id_book) REFERENCES Books(id);