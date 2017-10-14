/*
	Create change-status-history table
*/
CREATE TABLE ChangeStatusHistory (
	id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	justification VARCHAR(255),
	
	id_statusCategoryHistory INT,
		
	version INT NOT NULL,
	date TIMESTAMP NOT NULL,
	
	id_changeStatus INT NOT NULL,	
	id_user INT NOT NULL,

	CONSTRAINT PK_ChangeStatusHistory PRIMARY KEY (id)
);

ALTER TABLE ChangeStatusHistory ADD FOREIGN KEY (id_changeStatus) REFERENCES ChangeStatus(id);

ALTER TABLE ChangeStatusHistory ADD FOREIGN KEY (id_statusCategoryHistory) REFERENCES StatusCategoriesHistory(id);
