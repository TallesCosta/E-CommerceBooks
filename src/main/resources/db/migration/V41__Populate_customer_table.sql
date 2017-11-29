/*
  Populate customer table
*/

// Phone
INSERT INTO Phones (enabled, ddd, number, phoneType)
VALUES (true, '11', '8796-5244', 'Fixo');

// Home Address
INSERT INTO Addresses (enabled, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, id_city)
VALUES (true, 'Casa I', 'Rua', 'Travessa Armando da Silva', '996', 'Centro', '985.25-308', 'Casa', 26);

// Charge Address
INSERT INTO Addresses (enabled, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, id_city)
VALUES (true, 'Casa I', 'Rua', 'Travessa Armando da Silva', '996', 'Centro', '985.25-308', 'Casa', 26);

// User
INSERT INTO Users (enabled, email, password)
VALUES (true, 't@t.t', 'Talles@123');

// Customer
INSERT INTO Customers (enabled, registry, name, birthDate, gender,
                        id_phone, id_user, id_homeAddress, id_chargeAddress)
VALUES (true, '456.789.123-88', 'Talles Costa', parsedatetime('28-08-1997', 'dd-MM-yyyy'), 'Masculino',
          1, 2, 1, 2);

// Delivery Addresses
INSERT INTO DeliveryAddresses (enabled, preferential, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, id_city, id_customer)
VALUES (true, true, 'Casa I', 'Rua', 'Travessa Armando da Silva', '996', 'Centro', '985.25-308', 'Casa', 26, 1);

INSERT INTO DeliveryAddresses (enabled, preferential, alias, publicPlaceType, publicPlace, number, district, postalCode, homeType, id_city, id_customer)
VALUES (true, false, 'Casa dos Pais', 'Tr.', 'José dos Campos', '10', 'Centro', '941.01-378', 'Prédio', 26, 1);

// Credit Cards
INSERT INTO CreditCards (enabled, number, printedName, securityCode, expirationDate, id_cardCompany, id_customer)
VALUES (true, '5698.7855.1002.7896', 'Talles Costa', '788', parsedatetime('01-01-2020', 'dd-MM-yyyy'), 3, 1);

INSERT INTO CreditCards (enabled, number, printedName, securityCode, expirationDate, id_cardCompany, id_customer)
VALUES (true, '7754.1123.5213.2010', 'Maria Costa', '151', parsedatetime('01-01-2023', 'dd-MM-yyyy'), 4, 1);