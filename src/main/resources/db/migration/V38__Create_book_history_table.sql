/*
	Create book-history table
*/
CREATE TABLE BooksHistory (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	title VARCHAR(255) NOT NULL,
	edition VARCHAR(255) NOT NULL,
	publicationYear INT NOT NULL,
	numberOfPages INT NOT NULL,
	synopsis VARCHAR(255) NOT NULL,
	isbn VARCHAR(255) NOT NULL UNIQUE,
	ean13 VARCHAR(255) NOT NULL UNIQUE,
	
	id_authorHistory INT NOT NULL,
	id_publishingCompanyHistory INT NOT NULL,
	id_dimensionHistory INT NOT NULL,
	id_priceGroupHistory INT NOT NULL,
	id_saleParameterizationHistory INT NOT NULL,
	id_changeStatusHistory INT,
							
	version INT NOT NULL,
	date TIMESTAMP NOT NULL,
	
	id_book INT NOT NULL,	
	id_user INT NOT NULL,

	CONSTRAINT PK_BookHistory PRIMARY KEY (id)
);

ALTER TABLE BooksHistory ADD FOREIGN KEY (id_authorHistory) REFERENCES AuthorsHistory(id);
ALTER TABLE BooksHistory ADD FOREIGN KEY (id_publishingCompanyHistory) REFERENCES PublishingCompaniesHistory(id);
ALTER TABLE BooksHistory ADD FOREIGN KEY (id_dimensionHistory) REFERENCES DimensionsHistory(id);
ALTER TABLE BooksHistory ADD FOREIGN KEY (id_priceGroupHistory) REFERENCES PriceGroupsHistory(id);
ALTER TABLE BooksHistory ADD FOREIGN KEY (id_saleParameterizationHistory) REFERENCES SaleParameterizationsHistory(id);
ALTER TABLE BooksHistory ADD FOREIGN KEY (id_changeStatusHistory) REFERENCES ChangeStatusHistory(id);

ALTER TABLE BooksHistory ADD FOREIGN KEY (id_book) REFERENCES Books(id);
