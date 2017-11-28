/*
  Populate customer table
*/

INSERT INTO Phones ()
VALUES ();

// Home Address
INSERT INTO Addresses ()
VALUES ();

// Charge Address
INSERT INTO Addresses ()
VALUES ();

INSERT INTO Users (enabled, email, password)
VALUES (true, 't@t.t', 'Talles@123');

INSERT INTO Customers (enabled, registry, name, birthDate, gender,
                        id_phone, id_user, id_homeAddress, id_chargeAddress)
VALUES (true, '456.789.123-88', 'Talles Costa', parsedatetime('28-08-1997', 'dd-MM-yyyy'), 'Masculino',
          1, 2, 1, 2);