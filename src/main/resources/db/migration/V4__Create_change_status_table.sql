/*
	Create change-status table
*/
CREATE TABLE ChangeStatus (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    justification VARCHAR(255),
	
	id_activationCategory INT,
	id_deactivationCategory INT,
	
	CONSTRAINT PK_ChangeStatus PRIMARY KEY (id)
);

ALTER TABLE ChangeStatus ADD FOREIGN KEY (id_activationCategory) REFERENCES ActivationCategories(id);
ALTER TABLE ChangeStatus ADD FOREIGN KEY (id_deactivationCategory) REFERENCES DeactivationCategories(id);