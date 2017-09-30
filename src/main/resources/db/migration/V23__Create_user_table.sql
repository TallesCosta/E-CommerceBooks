/*
	Create user table
*/
CREATE TABLE Users (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
	
	CONSTRAINT PK_User PRIMARY KEY (id)
);