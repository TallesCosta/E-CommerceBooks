/*
	Create sale-parameterization table
*/
CREATE TABLE SaleParameterizations (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    minSaleLimit INT NOT NULL,
    periodicity INT NOT NULL,
	
	CONSTRAINT PK_SaleParameterization PRIMARY KEY (id)
);