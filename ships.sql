drop database ship_booking;
create database ship_booking;
use ship_booking;
create table adminTable (
adminId int AUTO_INCREMENT,
password varchar(30),
PRIMARY KEY (adminId)
);
create table userTable (
userID int AUTO_INCREMENT,
phoneNumber int,
name varchar(30),
age int,
gender ENUM('M' , 'F') ,
password varchar(30),
email varchar(30),
PRIMARY KEY (userID)
);
create table cruiseShipTable (
cruiseShipID int AUTO_INCREMENT,
fromLocation char,
toLocation char,
departureTime time,
arrivalTime time,
totalSeats int,
cost int,
bookedSeats int,
PRIMARY KEY (cruiseShipID)
);
create table cargoShipsTable (
cargoShipID int AUTO_INCREMENT,
fromLocation varchar(30),
toLocation varchar(30),
departureTime long,
arrivalTime long,
capacity int,
chargesPerTonne int,
bookedCapacity int,
PRIMARY KEY (cargoShipID)
);
create table cruiseBookingTable (
cruiseBookingID int AUTO_INCREMENT,
cruiseShipID int,
userID int,
seats int,
cost int,
PRIMARY KEY (cruiseBookingID),
constraint fk1 foreign key(cruiseShipID) references cruiseShipTable(cruiseShipID),
constraint fk2 foreign key(userID) references userTable(userID)
);
create table cargoBookingTable (
cargoBookingID int AUTO_INCREMENT,
cargoShipID int,
userID int,
capacity int,
cost int,
PRIMARY KEY (cargoBookingID),
constraint fk3 foreign key(cargoShipID) references cargoShipsTable(cargoShipID),
constraint fk4 foreign key(userID) references userTable(userID)
);

insert into adminTable(password) values
("testAdmin1"),
("testAdmin2");

insert into userTable( phoneNumber , name , age , gender , password , email ) values 
(1234567890 , "user1" , 33 , 'M' , "testUser1" , "user1@38.com"),
(1234567899 , "user2" , 32 , 'F' , "testUser2" , "user2@38.com");

insert into cargoShipsTable( fromLocation , toLocation , departureTime , arrivalTime , capacity , chargesPerTonne , bookedCapacity) values 
("chicago" , "London" , 209842042 , 04234234 , 433 , 34 , 343) ,
("new york" , "amsterdam" , 209842042 , 04234234 , 433 , 34 , 343) ,
("singapore" , "sydney" , 209842042 , 04234234 , 433 , 34 , 343) ,
("tokyo" , "mumbai" , 209842042 , 04234234 , 433 , 34 , 343),
("tokyo" , "london" , 209842042 , 04234234 , 433 , 34 , 343),
("tokyo" , "mumbai" , 2098422342 , 0423422334 , 4332 , 323 , 3);
