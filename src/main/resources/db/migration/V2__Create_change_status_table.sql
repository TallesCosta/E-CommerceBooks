/*
	Create change-status table
*/
CREATE TABLE ChangeStatus (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    justification VARCHAR(255),
	
	
	CONSTRAINT PK_ChangeStatus PRIMARY KEY (id)
);