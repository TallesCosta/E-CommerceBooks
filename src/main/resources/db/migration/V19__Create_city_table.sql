/*
	Create city table
*/
CREATE TABLE Cities (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    name VARCHAR(255) NOT NULL,

	id_state INT NOT NULL,	
	
	CONSTRAINT PK_City PRIMARY KEY (id)
);

ALTER TABLE Cities ADD FOREIGN KEY (id_state) REFERENCES States(id);