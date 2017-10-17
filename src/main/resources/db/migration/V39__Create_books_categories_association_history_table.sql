/*
	Create book-category-history association table
*/
CREATE TABLE BooksCategoriesHistory (
	id INT NOT NULL AUTO_INCREMENT,
	
	id_bookHistory INT NOT NULL,
	id_categoryHistory INT NOT NULL,
								
	-- --------------------
	date TIMESTAMP NOT NULL,
	
	id_booksCategories INT NOT NULL,	
	id_user INT NOT NULL,

	CONSTRAINT PK_BooksCategoriesHistory PRIMARY KEY (id)
);

ALTER TABLE BooksCategoriesHistory ADD FOREIGN KEY (id_bookHistory) REFERENCES BooksHistory(id);
ALTER TABLE BooksCategoriesHistory ADD FOREIGN KEY (id_categoryHistory) REFERENCES CategoriesHistory(id);

ALTER TABLE BooksCategoriesHistory ADD FOREIGN KEY (id_booksCategories) REFERENCES BooksCategories(id);
