/*
	Create change-status table
*/
CREATE TABLE ChangeStatus (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    justification VARCHAR(255),
	
	id_statusCategory INT,
	
	CONSTRAINT PK_ChangeStatus PRIMARY KEY (id)
);

ALTER TABLE ChangeStatus ADD FOREIGN KEY (id_statusCategory) REFERENCES StatusCategories(id);