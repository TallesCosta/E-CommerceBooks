/*
  Populate customer table
*/

-- Customer 1 - Masculino --------------------------------------------------------------------------------------------------------
// Phone
INSERT INTO Phones (enabled, ddd, number, phoneType)
VALUES (true, '11', '8796-5244', 'Fixo');

// User
INSERT INTO Users (enabled, email, password)
VALUES (true, 't@t.t', 'Talles@123');

// Customer
INSERT INTO Customers (enabled, registry, name, birthDate, gender, id_phone, id_user)
VALUES (true, '456.789.123-88', 'Talles Costa', parsedatetime('28-08-1997', 'dd-MM-yyyy'), 'Masculino', 1, 2);

// Charge Addresses
INSERT INTO ChargeAddresses (enabled, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)
VALUES (true, 'Casa I', 'Tr.', 'Armando da Silva', '996', 'Centro', '985.25-308', 'Casa', 'Suzano', 26, 1);

INSERT INTO ChargeAddresses (enabled, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)
VALUES (true, 'Casa II', 'Rua', 'Matos grandes', '26', 'Centro', '977.20-108', 'Apartamento', 'Caucaia', 6, 1);

// Delivery Addresses
INSERT INTO DeliveryAddresses (enabled, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)
VALUES (true, 'Casa I', 'Tr.', 'Armando da Silva', '996', 'Centro', '985.25-308', 'Casa', 'Suzano', 26, 1);

INSERT INTO DeliveryAddresses (enabled, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)
VALUES (true, 'Casa dos Pais', 'Rua', 'Jose dos Campos', '10', 'Centro', '941.01-378', 'Prédio', 'Antas', 5, 1);

// Credit Cards
INSERT INTO CreditCards (enabled, number, printedName, securityCode, expirationDate, id_cardCompany, id_customer)
VALUES (true, '5698.7855.1002.7896', 'Talles Costa', '788', parsedatetime('01-01-2020', 'dd-MM-yyyy'), 3, 1);

INSERT INTO CreditCards (enabled, number, printedName, securityCode, expirationDate, id_cardCompany, id_customer)
VALUES (true, '7754.1123.5213.2010', 'Talles Costa', '151', parsedatetime('01-01-2023', 'dd-MM-yyyy'), 4, 1);

-- Customer 2 - Feminino --------------------------------------------------------------------------------------------------------
// Phone
INSERT INTO Phones (enabled, ddd, number, phoneType)
VALUES (true, '15', '6750-1055', 'Fixo');

// User
INSERT INTO Users (enabled, email, password)
VALUES (true, 'm@m.m', 'Maria@123');

// Customer
INSERT INTO Customers (enabled, registry, name, birthDate, gender, id_phone, id_user)
VALUES (true, '844.124.788-01', 'Maria Goncalves', parsedatetime('21-05-1995', 'dd-MM-yyyy'), 'Feminino', 2, 3);

// Charge Addresses
INSERT INTO ChargeAddresses (enabled, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)
VALUES (true, 'Trabalho', 'Av.', 'De todos os santos', '14', 'Centro', '357.11-748', 'Casa', 'Agua Branca', 2, 2);

INSERT INTO ChargeAddresses (enabled, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)
VALUES (true, 'Namorado', 'Rua', 'Juvenal dos Santos', '75', 'Centro', '474.12-008', 'Apartamento', 'Apui', 3, 2);

// Delivery Addresses
INSERT INTO DeliveryAddresses (enabled, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)
VALUES (true, 'Trabalho', 'Av.', 'De todos os santos', '14', 'Centro', '357.11-748', 'Casa', 'Agua Branca', 2, 2);

INSERT INTO DeliveryAddresses (enabled, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)
VALUES (true, 'Filhos', 'Rua', 'Joana Pedrosa', '44', 'Centro', '687.12-577', 'Condomínio', 'Barra do Mendes', 5, 2);

// Credit Cards
INSERT INTO CreditCards (enabled, number, printedName, securityCode, expirationDate, id_cardCompany, id_customer)
VALUES (true, '5070.4367.1005.6547', 'Maria Goncalves', '238', parsedatetime('01-01-2018', 'dd-MM-yyyy'), 1, 2);

INSERT INTO CreditCards (enabled, number, printedName, securityCode, expirationDate, id_cardCompany, id_customer)
VALUES (true, '5048.9851.5412.5011', 'Maria Goncalves', '541', parsedatetime('01-01-2022', 'dd-MM-yyyy'), 2, 2);

-- Customer 3 - Outros --------------------------------------------------------------------------------------------------------
// Phone
INSERT INTO Phones (enabled, ddd, number, phoneType)
VALUES (true, '12', '1150-8074', 'Celular');

// User
INSERT INTO Users (enabled, email, password)
VALUES (true, 'f@f.f', 'Fernanda@123');

// Customer
INSERT INTO Customers (enabled, registry, name, birthDate, gender, id_phone, id_user)
VALUES (true, '654.111.021-87', 'Fernanda Junqueira', parsedatetime('29-12-1999', 'dd-MM-yyyy'), 'Outros', 3, 4);

// Charge Addresses
INSERT INTO ChargeAddresses (enabled, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)
VALUES (true, 'Minha casa', 'Rua', 'Tartarugas vagarosas', '1', 'Centro', '157.00-158', 'Apartamento', 'Paraguaçu', 11, 3);

INSERT INTO ChargeAddresses (enabled, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)
VALUES (true, 'Casa da Praia', 'Rua', 'Dos enrolarados', '73', 'Centro', '123.11-789', 'Casa', 'Belenzinho', 11, 3);

// Delivery Addresses
INSERT INTO DeliveryAddresses (enabled, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)
VALUES (true, 'Minha casa', 'Rua', 'Tartarugas vagarosas', '1', 'Centro', '157.00-158', 'Apartamento', 'Paraguaçu', 11, 3);

INSERT INTO DeliveryAddresses (enabled, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)
VALUES (true, 'Emprego', 'Tr.', 'Joao e maria', '99', 'Centro', '132.45-111', 'Prédio', 'Varginha', 11, 3);

// Credit Cards
INSERT INTO CreditCards (enabled, number, printedName, securityCode, expirationDate, id_cardCompany, id_customer)
VALUES (true, '1234.4564.4475.6041', 'Fernanda Junqueira', '100', parsedatetime('01-01-2027', 'dd-MM-yyyy'), 5, 3);

INSERT INTO CreditCards (enabled, number, printedName, securityCode, expirationDate, id_cardCompany, id_customer)
VALUES (true, '1027.7564.4468.4568', 'Fernanda Junqueira', '751', parsedatetime('01-01-2030', 'dd-MM-yyyy'), 2, 3);