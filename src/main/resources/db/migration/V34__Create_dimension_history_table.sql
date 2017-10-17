/*
	Create dimension-history table
*/
CREATE TABLE DimensionsHistory (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	height DECIMAL(4,2) NOT NULL,
	widht DECIMAL(4,2) NOT NULL,
	weight DECIMAL(4,3) NOT NULL,
	depth DECIMAL(4,2) NOT NULL,
				
	-- --------------------
	date TIMESTAMP NOT NULL,
	
	id_dimension INT NOT NULL,	
	id_user INT NOT NULL,

	CONSTRAINT PK_DimensionHistory PRIMARY KEY (id)
);

ALTER TABLE DimensionsHistory ADD FOREIGN KEY (id_dimension) REFERENCES Dimensions(id);
