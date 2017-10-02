/*
	Create country table
*/
CREATE TABLE Countries (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    name VARCHAR(255) NOT NULL,
    acrony VARCHAR(255) NOT NULL,
	
	
	CONSTRAINT PK_Country PRIMARY KEY (id)
);