/*
	Create publishing-company table
*/
CREATE TABLE PublishingCompanies (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    name VARCHAR(255) NOT NULL,
	
	CONSTRAINT PK_PublishingCompany PRIMARY KEY (id)
);