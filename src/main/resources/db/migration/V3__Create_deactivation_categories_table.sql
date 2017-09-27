/*
	Create deactivation-category table
*/
CREATE TABLE DeactivationCategories (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
	
	CONSTRAINT PK_DeactivationCategories PRIMARY KEY (id)
);