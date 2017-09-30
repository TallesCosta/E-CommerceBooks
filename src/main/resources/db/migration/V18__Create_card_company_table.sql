/*
	Create card-company table
*/
CREATE TABLE CardCompanies (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    code VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
	
	
	CONSTRAINT PK_CardCompany PRIMARY KEY (id)
);