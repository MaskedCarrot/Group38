create database ship_booking;
use ship_booking;
create table Admin (
AdminID int AUTO_INCREMENT,
password varchar(30),
PRIMARY KEY (AdminID)
);
create table UsersDatabase (
userID int AUTO_INCREMENT,
phoneNumber int,
name varchar(30),
age int,
Gender ENUM('M' , 'F') ,
password varchar(30),
email varchar(10),
PRIMARY KEY (userID)
);
create table cruiseShipsDatabase (
cruiseShipID int AUTO_INCREMENT,
fromLocation char,
toLocation char,
departureTime time,
arrivalTime time,
totalSeats int,
cost int,
bookedSets int,
PRIMARY KEY (cruiseShipID)
);
create table CargoShipsDatabase (
cargoShipID int AUTO_INCREMENT,
fromLocation char,
toLocation char,
departureTime time,
arrivalTime time,
capacity int,
chargesPerTonne int,
bookedCapacity int,
PRIMARY KEY (cargoShipID)
);
create table cruiseBookingDatabase (
cruiseBookingID int AUTO_INCREMENT,
cruiseShipID int,
userID int,
seats int,
cost int,
PRIMARY KEY (cruiseBookingID),
constraint fk1 foreign key(cruiseShipID) references cruiseShipsDatabase(cruiseShipID),
constraint fk2 foreign key(userID) references UsersDatabase(userID)
);
create table cargoBookingDatabase (
cargoBookingID int AUTO_INCREMENT,
cargoShipID int,
userID int,
capacity int,
cost int,
PRIMARY KEY (cargoBookingID),
constraint fk3 foreign key(cargoShipID) references cargoShipsDatabase(cargoShipID),
constraint fk4 foreign key(userID) references UsersDatabase(userID)
);
