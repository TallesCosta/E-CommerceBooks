/*
	Create customer table
*/
CREATE TABLE Customers (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    registry VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    birthDate DATE NOT NULL,
    gender VARCHAR(255) NOT NULL,
    
	id_phone INT NOT NULL,
	id_user INT NOT NULL,
	id_homeAddress INT NOT NULL,
	id_chargeAddress INT NOT NULL,
	
	CONSTRAINT PK_Customer PRIMARY KEY (id)
);

ALTER TABLE Customers ADD FOREIGN KEY (id_phone) REFERENCES Phones(id);
ALTER TABLE Customers ADD FOREIGN KEY (id_user) REFERENCES Users(id);
ALTER TABLE Customers ADD FOREIGN KEY (id_homeAddress) REFERENCES Addresses(id);
ALTER TABLE Customers ADD FOREIGN KEY (id_chargeAddress) REFERENCES Addresses(id);