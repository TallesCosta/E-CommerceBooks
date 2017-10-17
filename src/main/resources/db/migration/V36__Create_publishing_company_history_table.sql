/*
	Create publishing-company-history table
*/
CREATE TABLE PublishingCompaniesHistory (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	name VARCHAR(255) NOT NULL,
						
	-- --------------------
	date TIMESTAMP NOT NULL,
	
	id_publishingCompany INT NOT NULL,	
	id_user INT NOT NULL,

	CONSTRAINT PK_PublishingCompanyHistory PRIMARY KEY (id)
);

ALTER TABLE PublishingCompaniesHistory ADD FOREIGN KEY (id_publishingCompany) REFERENCES PublishingCompanies(id);
