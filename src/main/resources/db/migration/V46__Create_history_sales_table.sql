/*
	Create history-sale table
*/
CREATE TABLE SaleHistories (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	date TIMESTAMP NOT NULL,
	serializedObject OTHER,

	id_sale INT NOT NULL,
	id_user INT NOT NULL,

	CONSTRAINT PK_SaleHistory PRIMARY KEY (id)
);

ALTER TABLE SaleHistories ADD FOREIGN KEY (id_sale) REFERENCES Sales(id);
ALTER TABLE SaleHistories ADD FOREIGN KEY (id_user) REFERENCES Users(id);