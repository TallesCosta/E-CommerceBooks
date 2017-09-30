/*
	Create credit-card table
*/
CREATE TABLE CreditCards (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    number VARCHAR(255) NOT NULL,
    printedName VARCHAR(255) NOT NULL,
    securityCode VARCHAR(255) NOT NULL,
	expirationDate DATE NOT NULL,

	id_cardCompany INT NOT NULL,
	id_customer INT NOT NULL,
	
	CONSTRAINT PK_CreditCard PRIMARY KEY (id)
);

ALTER TABLE CreditCards ADD FOREIGN KEY (id_cardCompany) REFERENCES CardCompanies(id);
ALTER TABLE CreditCards ADD FOREIGN KEY (id_customer) REFERENCES Customers(id);