/*
	Create phone table
*/
CREATE TABLE Phones (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    ddd VARCHAR(255) NOT NULL,
    number VARCHAR(255) NOT NULL,
    phoneType VARCHAR(255) NOT NULL,
	
	CONSTRAINT PK_Phone PRIMARY KEY (id)
);