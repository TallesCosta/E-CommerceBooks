/*
	Create status table
*/
CREATE TABLE Status (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	name VARCHAR(255) NOT NULL,
	
	CONSTRAINT PK_Status PRIMARY KEY (id)
);
