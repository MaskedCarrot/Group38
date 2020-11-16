DROP DATABASE ship_booking;

CREATE DATABASE ship_booking;

USE ship_booking;

CREATE TABLE adminTable (
adminID INT AUTO_INCREMENT,
password VARCHAR(30) NOT NULL,
email VARCHAR(30) NOT NULL UNIQUE,
PRIMARY KEY (adminID)
);

CREATE TABLE userTable (
userID INT AUTO_INCREMENT,
phoneNumber BIGINT(11),
name VARCHAR(30),
age INT,
gender ENUM('M' , 'F') ,
password VARCHAR(30) NOT NULL,
email VARCHAR(30) NOT NULL UNIQUE,
PRIMARY KEY (userID)
);

CREATE TABLE cruiseShipsTable (
cruiseShipID INT AUTO_INCREMENT,
fromLocation VARCHAR(30) NOT NULL,
toLocation VARCHAR(30) NOT NULL,
departureTime BIGINT NOT NULL,
arrivalTime BIGINT NOT NULL,
totalSeats INT NOT NULL,
cost INT NOT NULL,
bookedSeats INT DEFAULT '0',
PRIMARY KEY (cruiseShipID)
);

CREATE TABLE cargoShipsTable (
cargoShipID INT AUTO_INCREMENT,
fromLocation VARCHAR(30) NOT NULL,
toLocation VARCHAR(30) NOT NULL,
departureTime BIGINT NOT NULL,
arrivalTime BIGINT NOT NULL,
capacity INT NOT NULL,
chargesPerTonne INT NOT NULL,
bookedCapacity INT DEFAULT '0',
PRIMARY KEY (cargoShipID)
);

CREATE TABLE cruiseBookingTable (
cruiseBookingID INT AUTO_INCREMENT,
cruiseShipID INT,
userID INT,
-- seats INT,
cost INT,
statusFlag INT NOT NULL,
PRIMARY KEY (cruiseBookingID),
constraINT fk1 foreign key(cruiseShipID) REFERENCES cruiseShipsTable(cruiseShipID),
constraINT fk2 foreign key(userID) REFERENCES userTable(userID)
);

CREATE TABLE cargoBookingTable (
cargoBookingID INT AUTO_INCREMENT,
cargoShipID INT,
userID INT,
-- capacity INT,
cost INT,
statusFlag INT NOT NULL,
PRIMARY KEY (cargoBookingID),
constraINT fk3 foreign key(cargoShipID) REFERENCES cargoShipsTable(cargoShipID),
constraINT fk4 foreign key(userID) REFERENCES userTable(userID)
);

INSERT INTO adminTable(password,email) VALUES
("testAdmin1","admin1@38.com"),
("testAdmin2","admin2@38.com");

INSERT INTO userTable( phoneNumber , name , age , gender , password , email ) VALUES 
(1234567890 , "user1" , 33 , 'M' , "testUser1" , "user1@38.com"),
(1234567899 , "user2" , 32 , 'F' , "testUser2" , "user2@38.com");

INSERT INTO cargoShipsTable( fromLocation , toLocation , departureTime , arrivalTime , capacity , chargesPerTonne , bookedCapacity) VALUES 
("chicago" , "London" , 209842042 , 04234234 , 433 , 34 , 343) ,
("new york" , "amsterdam" , 209842042 , 04234234 , 433 , 34 , 343) ,
("singapore" , "sydney" , 209842042 , 04234234 , 433 , 34 , 343) ,
("tokyo" , "mumbai" , 209842042 , 04234234 , 433 , 34 , 343),
("tokyo" , "london" , 209842042 , 04234234 , 433 , 34 , 343),
("tokyo" , "mumbai" , 2098422342 , 0423422334 , 4332 , 323 , 3);


INSERT INTO cruiseShipsTable( fromLocation , toLocation , departureTime , arrivalTime , totalSeats , cost , bookedSeats) VALUES 
("chicago" , "London" , 2098042 , 04234234 , 433 , 34 , 343) ,
("new york" , "amsterdam" , 2842042 , 04234234 , 433 , 34 , 343) ,
("singapore" , "sydney" , 209842042 , 04234234 , 433 , 34 , 343) ,
("tokyo" , "mumbai" , 209842042 , 04234234 , 433 , 34 , 343),
("tokyo" , "london" , 209842042 , 04234234 , 433 , 34 , 343),
("tokyo" , "mumbai" , 2098422342 , 0423422334 , 4332 , 323 , 3);

CREATE VIEW cargoShipBooking AS SELECT cargoBookingID,userID,cargoBookingTable.cargoShipID,fromLocation,toLocation,departureTime,arrivalTime,(cost)/(chargesPerTonne)AS capacity,cargoBookingTable.cost FROM cargoBookingTable INNER JOIN cargoShipsTable ON cargoBookingTable.cargoShipID=cargoShipsTable.cargoShipID;

CREATE VIEW cruiseShipBooking AS SELECT cruiseBookingID,userID,cruiseBookingTable.cruiseShipID,fromLocation,toLocation,departureTime,arrivalTime,(cruiseBookingTable.cost)/(cruiseShipsTable.cost)AS seats,cruiseBookingTable.cost FROM cruiseBookingTable INNER JOIN cruiseShipsTable ON cruiseBookingTable.cruiseShipID=cruiseShipsTable.cruiseShipID;

CREATE TRIGGER update_bookedSeats
AFTER INSERT
ON cruiseBookingTable
FOR EACH ROW
UPDATE cruiseShipsTable SET bookedSeats=((NEW.cost)/(cruiseShipsTable.cost))+bookedSeats WHERE cruiseShipsTable.cruiseShipID=NEW.cruiseShipID;

CREATE TRIGGER update_bookedCapacity
AFTER INSERT
ON cargoBookingTable
FOR EACH ROW
UPDATE cargoShipsTable SET bookedCapacity=((NEW.cost)/(chargesPerTonne))+bookedCapacity WHERE cargoShipsTable.cargoShipID=NEW.cargoShipID;

-- CREATE TRIGGER add_no_of_seats
-- BEFORE INSERT
-- ON cruiseBookingTable
-- FOR EACH ROW
-- UPDATE cruiseBookingTable SET NEW.seats=(NEW.cost)/(SELECT cost FROM cruiseShipsTable WHERE cruiseShipID=NEW.cruiseShipID);



-- insert INTo cruiseBookingTable(cruiseShipID,userID,cost,statusFlag) VALUES(1,2,100,1); 
-- select * from cruiseBookingTable;
-- select * from cruiseShipsTable;



-- TODOs:
-- Making cost Decimal
-- Remove Commented part from Create table Statement
-- Change Name of cost in one of the tables
-- View Full Details of a particulat journey option
