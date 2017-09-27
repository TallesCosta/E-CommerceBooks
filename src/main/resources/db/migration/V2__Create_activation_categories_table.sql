/*
	Create activation-category table
*/
CREATE TABLE ActivationCategories (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
	
	CONSTRAINT PK_ActivationCategories PRIMARY KEY (id)
);