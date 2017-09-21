/*
	Create book table
*/
CREATE TABLE Books (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    title VARCHAR(255) NOT NULL,
    edition VARCHAR(255) NOT NULL,
    publicationYear INT NOT NULL,
    numberOfPages INT NOT NULL,
    synopsis VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL,
    ean13 VARCHAR(255) NOT NULL,
	
	id_author INT NOT NULL,
	id_publishingCompany INT NOT NULL,
	id_dimension INT NOT NULL,
	id_priceGroup INT NOT NULL,
	id_saleParameterization INT NOT NULL,
	id_changeStatus INT,

	CONSTRAINT PK_Book PRIMARY KEY (id)
);

ALTER TABLE Books ADD FOREIGN KEY (id_author) REFERENCES Authors(id);
ALTER TABLE Books ADD FOREIGN KEY (id_publishingCompany) REFERENCES PublishingCompanies(id);
ALTER TABLE Books ADD FOREIGN KEY (id_dimension) REFERENCES Dimensions(id);
ALTER TABLE Books ADD FOREIGN KEY (id_priceGroup) REFERENCES PriceGroups(id);
ALTER TABLE Books ADD FOREIGN KEY (id_saleParameterization) REFERENCES SaleParameterizations(id);
ALTER TABLE Books ADD FOREIGN KEY (id_changeStatus) REFERENCES ChangeStatus(id);