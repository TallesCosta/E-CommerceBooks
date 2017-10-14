/*
	Create status-category-history table
*/
CREATE TABLE StatusCategoriesHistory (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	name VARCHAR(255) NOT NULL,
	activationStatus BOOLEAN NOT NULL,
	description VARCHAR(255),
	
	version INT NOT NULL,
	date TIMESTAMP NOT NULL,
	
	id_statusCategory INT NOT NULL,	
	id_user INT NOT NULL,

	CONSTRAINT PK_StatusCategoryHistory PRIMARY KEY (id)
);

ALTER TABLE StatusCategoriesHistory ADD FOREIGN KEY (id_statusCategory) REFERENCES StatusCategories(id);
