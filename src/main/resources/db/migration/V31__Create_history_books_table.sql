/*
	Create history-book table
*/
CREATE TABLE HistoryBooks (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	date TIMESTAMP NOT NULL,
	serializedObject OTHER,

	id_book INT NOT NULL,
	id_user INT NOT NULL,

	CONSTRAINT PK_HistoryBook PRIMARY KEY (id)
);

ALTER TABLE HistoryBooks ADD FOREIGN KEY (id_book) REFERENCES Books(id);
ALTER TABLE HistoryBooks ADD FOREIGN KEY (id_user) REFERENCES Users(id);