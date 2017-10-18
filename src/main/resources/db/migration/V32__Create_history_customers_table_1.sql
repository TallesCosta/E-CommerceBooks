/*
	Create history-customer table
*/
CREATE TABLE CustomerHistories (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	date TIMESTAMP NOT NULL,
	serializedObject OTHER,

	id_customer INT NOT NULL,
	id_user INT NOT NULL,

	CONSTRAINT PK_CustomerHistory PRIMARY KEY (id)
);

ALTER TABLE CustomerHistories ADD FOREIGN KEY (id_customer) REFERENCES Customers(id);
ALTER TABLE CustomerHistories ADD FOREIGN KEY (id_user) REFERENCES Users(id);