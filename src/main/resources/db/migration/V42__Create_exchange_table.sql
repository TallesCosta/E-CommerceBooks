/*
  Create exchange table
 */
CREATE TABLE Exchanges (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	justification VARCHAR(255) NOT NULL,

	id_sale INT NOT NULL,

	CONSTRAINT PK_Exchange PRIMARY KEY (id)
);

ALTER TABLE Exchanges ADD FOREIGN KEY (id_sale) REFERENCES Sales(id);