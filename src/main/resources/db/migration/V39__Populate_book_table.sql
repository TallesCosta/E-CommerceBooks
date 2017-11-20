/*
	Populate book table
*/
/* First book */
INSERT INTO Dimensions (enabled, height, widht, weight, depth)
VALUES (true, 16.0, 12.0, 0.255, 3.0);

INSERT INTO ChangeStatus (enabled, justification, id_statusCategory)
VALUES (true, 'Livro novo', -1);

INSERT INTO SaleParameterizations (enabled, minSaleLimit, periodicity)
VALUES (true, 1, 10);

INSERT INTO Books (enabled, title, edition, publicationYear, numberOfPages, synopsis, isbn, ean13, 
					id_author, id_publishingCompany, id_dimension, id_priceGroup, id_saleParameterization, id_changeStatus)
VALUES (true, 'Como passar em LES em 583 passos!', '1º', 2017, 6667, 'São passos exaustivos, mas se perseverar, verá que dará certo!', 
			'159', '155', 4, 2, 1, 3, 1, 1);

INSERT INTO BooksCategories (id_book, id_category)
VALUES (1, 1),
		(1, 2);

/* Second book */
INSERT INTO Dimensions (enabled, height, widht, weight, depth)
VALUES (true, 15.0, 10.0, 0.170, 2.0);

INSERT INTO ChangeStatus (enabled, justification, id_statusCategory)
VALUES (true, 'Livro novo', -1);

INSERT INTO SaleParameterizations (enabled, minSaleLimit, periodicity)
VALUES (true, 1, 10);

INSERT INTO Books (enabled, title, edition, publicationYear, numberOfPages, synopsis, isbn, ean13, 
					id_author, id_publishingCompany, id_dimension, id_priceGroup, id_saleParameterization, id_changeStatus)
VALUES (true, 'Como superar uma DP!', '1º', 2017, 954, 'Uma DP pode indicar um recomeço!', 
			'147', '177', 4, 3, 2, 1, 2, 2);

INSERT INTO BooksCategories (id_book, id_category)
VALUES (2, 2);