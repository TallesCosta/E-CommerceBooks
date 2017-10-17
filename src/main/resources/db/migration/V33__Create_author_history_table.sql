/*
	Create author-history table
*/
CREATE TABLE AuthorsHistory (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	name VARCHAR(255) NOT NULL,
			
	-- --------------------
	date TIMESTAMP NOT NULL,
	
	id_author INT NOT NULL,	
	id_user INT NOT NULL,
	
	CONSTRAINT PK_AuthorHistory PRIMARY KEY (id)
);

ALTER TABLE AuthorsHistory ADD FOREIGN KEY (id_author) REFERENCES Authors(id);
