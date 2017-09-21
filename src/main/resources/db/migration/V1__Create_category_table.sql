/*
	Create category table
*/
CREATE TABLE Categories (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
	
	CONSTRAINT PK_Category PRIMARY KEY (id)
);