/*
	Create author table
*/
CREATE TABLE Authors (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    name VARCHAR(255) NOT NULL,
	
	
	CONSTRAINT PK_Author PRIMARY KEY (id)
);