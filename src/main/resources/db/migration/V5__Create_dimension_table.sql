/*
	Create dimension table
*/
CREATE TABLE Dimensions (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
    height DECIMAL(4,2) NOT NULL,
    widht DECIMAL(4,2) NOT NULL,
    weight DECIMAL(4,3) NOT NULL,
    depth DECIMAL(4,2) NOT NULL,
	
	CONSTRAINT PK_Dimension PRIMARY KEY (id)
);