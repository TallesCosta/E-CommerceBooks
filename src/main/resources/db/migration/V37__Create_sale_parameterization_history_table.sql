/*
	Create sale-parameterization-history table
*/
CREATE TABLE SaleParameterizationsHistory (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	minSaleLimit INT NOT NULL,
	periodicity INT NOT NULL,
							
	-- --------------------
	date TIMESTAMP NOT NULL,
	
	id_saleParameterization INT NOT NULL,	
	id_user INT NOT NULL,

	CONSTRAINT PK_SaleParameterizationHistory PRIMARY KEY (id)
);

ALTER TABLE SaleParameterizationsHistory ADD FOREIGN KEY (id_saleParameterization) REFERENCES SaleParameterizations(id);
