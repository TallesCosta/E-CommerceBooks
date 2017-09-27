/*
	Create book-category association table
*/
CREATE TABLE BooksCategories (
    id INT NOT NULL AUTO_INCREMENT,
	
	id_book INT NOT NULL,
	id_category INT NOT NULL,
	
	CONSTRAINT PK_BooksCategories PRIMARY KEY (id)
);

ALTER TABLE BooksCategories ADD FOREIGN KEY (id_book) REFERENCES Books(id);
ALTER TABLE BooksCategories ADD FOREIGN KEY (id_category) REFERENCES Categories(id);