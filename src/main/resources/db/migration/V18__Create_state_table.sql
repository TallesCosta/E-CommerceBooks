/*
	Create state table
*/
CREATE TABLE States (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    name VARCHAR(255) NOT NULL,
    acrony VARCHAR(255) NOT NULL,

	id_country INT NOT NULL,	
	
	CONSTRAINT PK_State PRIMARY KEY (id)
);

ALTER TABLE States ADD FOREIGN KEY (id_country) REFERENCES Countries(id);