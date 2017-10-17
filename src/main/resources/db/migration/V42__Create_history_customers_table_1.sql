/*
	Create history-customer table
*/
CREATE TABLE HistoryCustomers (
    id INT NOT NULL AUTO_INCREMENT,
	enabled BOOLEAN NOT NULL,
	date TIMESTAMP NOT NULL,
	path VARCHAR(255),

	id_customer INT NOT NULL,
	id_user INT NOT NULL,

	CONSTRAINT PK_HistoryCustomer PRIMARY KEY (id)
);

ALTER TABLE HistoryCustomers ADD FOREIGN KEY (id_customer) REFERENCES Customers(id);
ALTER TABLE HistoryCustomers ADD FOREIGN KEY (id_user) REFERENCES Users(id);