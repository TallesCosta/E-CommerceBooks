/*
	Create category-history table
*/
CREATE TABLE CategoriesHistory (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
									
	version INT NOT NULL,
	date TIMESTAMP NOT NULL,
	
	id_category INT NOT NULL,	
	id_user INT NOT NULL,

	CONSTRAINT PK_CategoryHistory PRIMARY KEY (id)
);

ALTER TABLE CategoriesHistory ADD FOREIGN KEY (id_category) REFERENCES Categories(id);