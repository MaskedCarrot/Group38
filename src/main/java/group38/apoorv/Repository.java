package group38.apoorv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import group38.himanshu.CargoShip;
import group38.himanshu.CruiseShip;
import group38.ritik.User;
import group38.aniket.CargoBooking;
import group38.aniket.CruiseBooking;
import group38.apoorv.Util;

public class Repository extends Database implements Dao {

    public Repository() {
        refreshBookingTable();
    }

    public boolean checkUserCredentials(String userEmail, String password) {
        boolean b = false;
        try {
            String query = "SELECT COUNT(userID) AS \"userCount\" FROM userTable WHERE email = '" + userEmail
                    + "' AND password = '" + password + "'";
            List<Map<String, Object>> resultList = executeQuery(query);
            int result = convertObjectToInt(resultList.get(0).get("userCount"));
            if (result == 1)
                b = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean addUser(User user) {
        boolean result = false;
        try {
            String querry = "INSERT INTO userTable(phoneNumber , name , age , gender, password , email) VALUES("
                    + user.getPhoneNumber() + ",'" + user.getName() + "'," + user.getAge() + ",'" + user.getGender()
                    + "','" + user.getPassword() + "','" + user.getEmail() + "')";
            executeUpdate(querry);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getUserID(String emailID) {
        String Query = "SELECT userID FROM userTable WHERE email='" + emailID + "'";
        int result = 0;
        try {
            List<Map<String, Object>> resultList = executeQuery(Query);
            result = convertObjectToInt(resultList.get(0).get("userID"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isUniqueEmail(String userEmail) {
        boolean b = false;
        try {
            String query = "SELECT COUNT(userID) AS \"userCount\" FROM userTable WHERE email = '" + userEmail + "'";
            List<Map<String, Object>> resultList = executeQuery(query);
            int result = convertObjectToInt(resultList.get(0).get("userCount"));
            if (result == 0)
                b = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public User displayUserDetails(int userID) {
        User user;
        try {
            String query = "SELECT * FROM userTable WHERE userID = '" + userID + "'";
            List<Map<String, Object>> resultList = executeQuery(query);
            user = new User(userID, convertObjectToLong(resultList.get(0).get("phoneNumber")),
                    convertObjectToString(resultList.get(0).get("name")),
                    convertObjectToInt(resultList.get(0).get("age")),
                    convertObjectToString(resultList.get(0).get("gender")).charAt(0),
                    convertObjectToString(resultList.get(0).get("email")), "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return user;
    }

    public ArrayList<CargoShip> listAllCargoShips(String from, String to) {
        ArrayList<CargoShip> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM cargoShipsTable WHERE toLocation = \"" + to + "\" and fromLocation = \""
                    + from + "\" and departureTime >=" + (new Util().getCurrentTimeInMinutes() + 20)
                    + " order by arrivalTime asc";

            List<Map<String, Object>> resultList = executeQuery(query);

            for (Map<String, Object> row : resultList) {
                CargoShip cargoShip = new CargoShip(convertObjectToInt(row.get("cargoShipID")),
                        convertObjectToString(row.get("fromLocation")), convertObjectToString(row.get("toLocation")),
                        convertObjectToLong(row.get("departureTime")), convertObjectToLong(row.get("arrivalTime")),
                        convertObjectToInt(row.get("chargesPerTonne")), convertObjectToInt(row.get("capacity")),
                        convertObjectToInt(row.get("bookedCapacity")));
                list.add(cargoShip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public ArrayList<CruiseShip> listAllCruiseShips(String from, String to) {

        ArrayList<CruiseShip> list = new ArrayList<>();

        try {
            String query = "select * from cruiseShipsTable where toLocation = \"" + to + "\" and fromLocation = \""
                    + from + "\" and departureTime >=" + (new Util().getCurrentTimeInMinutes() + 20)
                    + " order by arrivalTime asc";

            List<Map<String, Object>> resultList = executeQuery(query);

            for (Map<String, Object> row : resultList) {
                CruiseShip cruiseShip = new CruiseShip(convertObjectToInt(row.get("cruiseShipID")),
                        convertObjectToString(row.get("fromLocation")), convertObjectToString(row.get("toLocation")),
                        convertObjectToLong(row.get("departureTime")), convertObjectToLong(row.get("arrivalTime")),
                        convertObjectToInt(row.get("totalSeats")), convertObjectToInt(row.get("cost")),
                        convertObjectToInt(row.get("bookedSeats")));

                list.add(cruiseShip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int bookCruiseShip(int shipID, int cost, int userID, int statusFlag) {

        int bookingResult = -2;
        try {
            if (!(statusFlag == 1 || statusFlag == 2)) {
                throw new AssertionError();
            }

            long time = new Util().getCurrentTimeInMinutes();
            String query0 = "SELECT IF(" + (time + 20)
                    + "< departureTime, 1 , 0) as result FROM cruiseShipsTable where cruiseShipID = " + shipID;

            List<Map<String, Object>> resultQuery = executeQuery(query0);
            int result = convertObjectToInt(resultQuery.get(0).get("result"));
            if (result == 0)
                return -1;

            String query = "INSERT INTO cruiseBookingTable(cruiseShipID,userID,cost,statusFlag) VALUES('" + shipID
                    + "','" + userID + "','" + cost + "','" + statusFlag + "')";
            executeUpdate(query);
            String query2 = "SELECT cruiseBookingID FROM cruiseBookingTable ORDER BY cruiseBookingID DESC LIMIT 1";
            List<Map<String, Object>> resultList = executeQuery(query2);
            bookingResult = convertObjectToInt(resultList.get(0).get("cruiseBookingID"));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return bookingResult;
    }

    public int bookCargoShip(int shipID, int cost, int userID, int statusFlag) {
        int bookingResult = -2;
        try {
            if (!(statusFlag == 1 || statusFlag == 2)) {
                throw new AssertionError();
            }

            long time = new Util().getCurrentTimeInMinutes();
            String query0 = "SELECT IF(" + (time + 20)
                    + "< departureTime, 1 , 0) as result FROM cargoShipsTable where cargoShipID = " + shipID;

            List<Map<String, Object>> resultQuery = executeQuery(query0);
            int result = convertObjectToInt(resultQuery.get(0).get("result"));

            if (result == 0)
                return -1;

            String query1 = "INSERT INTO cargoBookingTable(cargoShipID,userID,cost,statusFlag) VALUES('" + shipID
                    + "','" + userID + "','" + cost + "','" + statusFlag + "')";
            String query2 = "SELECT cargoBookingID , statusFlag FROM cargoBookingTable ORDER BY cargoBookingID DESC LIMIT 1";
            executeUpdate(query1);
            List<Map<String, Object>> resultList = executeQuery(query2);
            bookingResult = convertObjectToInt(resultList.get(0).get("cargoBookingID"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookingResult;
    }

    public CruiseBooking cruiseBookingStatus(int bookingID) {
        if (!checkIfCruiseBookingExists(bookingID))
            return null;
        int shipID, userID, cost, statusFlag, seats;
        try {
            String Query = "SELECT * FROM cruiseBookingJoin WHERE cruiseBookingID = '" + bookingID + "'";
            List<Map<String, Object>> resultList = executeQuery(Query);
            shipID = convertObjectToInt(resultList.get(0).get("cruiseShipID"));
            userID = convertObjectToInt(resultList.get(0).get("userID"));
            cost = convertObjectToInt(resultList.get(0).get("cost"));
            statusFlag = convertObjectToInt(resultList.get(0).get("statusFlag"));
            double st = convertObjectToDouble(resultList.get(0).get("seats"));
            seats = (int) st;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new CruiseBooking(bookingID, shipID, userID, seats, cost, statusFlag);
    }

    public CargoBooking cargoBookingStatus(int bookingID) {
        if (!checkIfCargoBookingExists(bookingID))
            return null;
        int shipID = 0, userID = 0, cost = 0, capacity = 0, statusFlag = 0;
        try {
            String Query = "SELECT * FROM cargoBookingTable WHERE cargoBookingID = '" + bookingID + "'";
            List<Map<String, Object>> resultList = executeQuery(Query);
            shipID = convertObjectToInt(resultList.get(0).get("cargoShipID"));
            userID = convertObjectToInt(resultList.get(0).get("userID"));
            cost = convertObjectToInt(resultList.get(0).get("cost"));
            statusFlag = convertObjectToInt(resultList.get(0).get("statusFlag"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new CargoBooking(bookingID, shipID, userID, statusFlag, capacity, cost);
    }

    private boolean checkIfCruiseBookingExists(int bookingID) {
        boolean result = false;
        try {
            String query = "SELECT COUNT(cruiseBookingID) AS result FROM cruiseBookingTable WHERE cruiseBookingID = "
                    + bookingID;
            List<Map<String, Object>> resultList = executeQuery(query);
            int check = convertObjectToInt(resultList.get(0).get("result"));
            if (check == 1)
                result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void updateCruiseWaiting(int bookingID) {
        String Query = "UPDATE cruiseBookingTable SET statusFlag='1' WHERE cruiseBookingID='" + bookingID + "'";
        executeUpdate(Query);

    }

    public boolean cancelCruiseBoooking(int bookingID) {
        try {
            if (!checkIfCruiseBookingExists(bookingID)) {
                return false;
            }

            String Query = "UPDATE cruiseBookingTable SET statusFlag='3' WHERE cruiseBookingID='" + bookingID + "'";
            executeUpdate(Query);

            Query = "SELECT * FROM cruiseBookingJoin WHERE cruiseBookingID='" + bookingID + "'";

            List<Map<String, Object>> resultList = executeQuery(Query);

            int cruiseShipID = convertObjectToInt(resultList.get(0).get("cruiseShipID"));
            int bookedSeats = convertObjectToInt(resultList.get(0).get("bookedSeats"));
            int totalSeats = convertObjectToInt(resultList.get(0).get("totalSeats"));
            Query = "SELECT * FROM cruiseBookingJoin WHERE statusFlag='2' AND cruiseShipID='" + cruiseShipID
                    + "' ORDER BY cruiseBookingID";
            resultList = executeQuery(Query);

            for (Map<String, Object> row : resultList) {

                int seats = convertObjectToInt(row.get("seats"));
                if (seats + bookedSeats <= totalSeats) {
                    updateCruiseWaiting(convertObjectToInt(row.get("cruiseBookingID")));
                    bookedSeats += seats;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean checkIfCargoBookingExists(int bookingID) {
        boolean result = false;
        try {
            String query = "SELECT COUNT(cargoBookingID) AS result FROM cargoBookingTable where cargoBookingID = "
                    + bookingID;
            List<Map<String, Object>> resultList = executeQuery(query);
            int check = convertObjectToInt(resultList.get(0).get("result"));
            if (check == 1)
                result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void updateCargoWaiting(int bookingID) {
        String Query = "UPDATE cargoBookingTable SET statusFlag='1' WHERE cargoBookingID='" + bookingID + "'";
        executeUpdate(Query);

    }

    public boolean cancelCargoBoooking(int bookingID) {
        try {
            if (!checkIfCargoBookingExists(bookingID))
                return false;

            String Query = "UPDATE cargoBookingTable SET statusFlag='" + 3 + "' WHERE cargoBookingID= '" + bookingID
                    + "'";
            executeUpdate(Query);

            Query = "SELECT * FROM cargoBookingJoin WHERE cargoBookingID='" + bookingID + "'";

            List<Map<String, Object>> resultList = executeQuery(Query);

            int cargoShipID = convertObjectToInt(resultList.get(0).get("cargoShipID"));
            int bookedCapacity = convertObjectToInt(resultList.get(0).get("bookedCapacity"));
            int totalCapacity = convertObjectToInt(resultList.get(0).get("capacity"));
            Query = "SELECT * FROM cargoBookingJoin WHERE statusFlag='2' AND cargoShipID='" + cargoShipID
                    + "' ORDER BY cargoBookingID";
            resultList = executeQuery(Query);
            for (Map<String, Object> row : resultList) {

                int cap = convertObjectToInt(row.get("capacityBooked"));
                if (cap + bookedCapacity <= totalCapacity) {
                    updateCargoWaiting(convertObjectToInt(row.get("cargoBookingID")));
                    bookedCapacity += cap;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addCargoShip(CargoShip cargoShip) {
        int capacity, chargesPerTonne, bookedCapacity;
        String from, to;
        Long departureTime, arrivalTime;
        capacity = cargoShip.getCapacity();
        chargesPerTonne = cargoShip.getChargesPerTonne();
        bookedCapacity = cargoShip.getBookedCapacity();
        from = cargoShip.getFrom();
        to = cargoShip.getTo();
        departureTime = cargoShip.getDepartureTime();
        arrivalTime = cargoShip.getArrivalTime();
        try {
            String Query = "INSERT INTO cargoShipsTable(fromLocation,toLocation,departureTime,arrivalTime,capacity,chargesPerTonne,bookedCapacity) VALUES('"
                    + from + "','" + to + "','" + departureTime + "','" + arrivalTime + "','" + capacity + "','"
                    + chargesPerTonne + "','" + bookedCapacity + "')";
            executeUpdate(Query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addCruiseShip(CruiseShip cruiseShip) {
        int totalSeats, costPerPerson, bookedSeats;
        String from, to;
        Long departureTime, arrivalTime;
        totalSeats = cruiseShip.getTotalSeats();
        costPerPerson = cruiseShip.getCostPerPerson();
        bookedSeats = cruiseShip.getBookedSeats();
        from = cruiseShip.getFrom();
        to = cruiseShip.getTo();
        departureTime = cruiseShip.getDepartureTime();
        arrivalTime = cruiseShip.getArrivalTime();
        try {
            String Query = "INSERT INTO cruiseShipsTable(fromLocation,toLocation,departureTime,arrivalTime,totalSeats,cost,bookedSeats) VALUES('"
                    + from + "','" + to + "','" + departureTime + "','" + arrivalTime + "','" + totalSeats + "','"
                    + costPerPerson + "','" + bookedSeats + "')";
            executeUpdate(Query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean checkAdminCredentials(String adminEmail, String password) {
        boolean b = false;
        try {
            String query = "SELECT COUNT(adminID) as \"adminCount\" from adminTable WHERE email = '" + adminEmail
                    + "' and password = '" + password + "'";
            List<Map<String, Object>> resultList = executeQuery(query);
            int result = convertObjectToInt(resultList.get(0).get("adminCount"));
            if (result == 1)
                b = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public int getAdminID(String emailID) {
        String Query = "SELECT adminID FROM adminTable WHERE email='" + emailID + "'";
        int result = 0;
        try {
            List<Map<String, Object>> resultList = executeQuery(Query);
            result = convertObjectToInt(resultList.get(0).get("adminID"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean removeCargoShip(int shipID) {
        try {
            String Query = "DELETE FROM cargoShipsTable WHERE cargoShipID='" + shipID + "'";
            executeUpdate(Query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeCruiseShip(int shipID) {
        try {
            String Query = "DELETE FROM cruiseShipsTable WHERE cruiseShipID='" + shipID + "'";
            executeUpdate(Query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateCargoShip(CargoShip cargoShip) {
        try {
            String Query = "UPDATE cargoShipsTable SET fromLocation='" + cargoShip.getFrom() + "',toLocation='"
                    + cargoShip.getTo() + "',departureTime='" + cargoShip.getDepartureTime() + "',arrivalTime='"
                    + cargoShip.getArrivalTime() + "',capacity='" + cargoShip.getCapacity() + "',chargesPerTonne='"
                    + cargoShip.getChargesPerTonne() + "',bookedCapacity='" + cargoShip.getBookedCapacity()
                    + "' WHERE cargoShipID='" + cargoShip.getShipID() + "'";
            executeUpdate(Query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateCruiseShip(CruiseShip cruiseShip) {
        boolean result = false;
        try {
            String Query = "UPDATE cruiseShipsTable SET fromLocation='" + cruiseShip.getFrom() + "',toLocation='"
                    + cruiseShip.getTo() + "',departureTime='" + cruiseShip.getDepartureTime() + "',arrivalTime='"
                    + cruiseShip.getArrivalTime() + "',totalSeats='" + cruiseShip.getTotalSeats() + "',cost='"
                    + cruiseShip.getCostPerPerson() + "',bookedSeats='" + cruiseShip.getBookedSeats()
                    + "' WHERE cruiseShipID='" + cruiseShip.getShipID() + "'";
            executeUpdate(Query);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void refreshBookingTable() {
        cancelWaitingBookings();
        changeConfirmBookingStatusFlag();
    }

    private boolean cancelWaitingBookings() {
        boolean result = false;
        try {
            long time = (new Util().getCurrentTimeInMinutes() + 20);
            String query = "UPDATE cruiseBookingJoin set statusFlag = 3 where statusFlag = 2 AND departureTime <="
                    + time;
            executeUpdate(query);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean changeConfirmBookingStatusFlag() {
        boolean result = false;
        try {
            String query = "UPDATE cruiseBookingJoin set statusFlag = 0 where statusFlag = 1 AND departureTime <="
                    + new Util().getCurrentTimeInMinutes();
            executeUpdate(query);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<CruiseBooking> listAllCruiseBooking(int userID) {
        ArrayList<CruiseBooking> list = new ArrayList<>();
        try {
            String Query = "SELECT * FROM cruiseBookingJoin WHERE userID='" + userID + "'";

            List<Map<String, Object>> resultList = executeQuery(Query);

            for (Map<String, Object> row : resultList) {
                CruiseBooking cruiseBooking = new CruiseBooking(convertObjectToInt(row.get("cruiseBookingID")),
                        convertObjectToInt(row.get("cruiseShipID")), convertObjectToInt(row.get("userID")),
                        convertObjectToInt(row.get("seats")), convertObjectToInt(row.get("cost")),
                        convertObjectToInt(row.get("statusFlag")));

                list.add(cruiseBooking);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<CargoBooking> listAllCargoBooking(int userID) {
        ArrayList<CargoBooking> list = new ArrayList<>();
        try {
            String Query = "SELECT * FROM cargoBookingJoin WHERE userID='" + userID + "'";

            List<Map<String, Object>> resultList = executeQuery(Query);

            for (Map<String, Object> row : resultList) {
                CargoBooking cargoBooking = new CargoBooking(
                        convertObjectToInt(resultList.get(0).get("cargoBookingID")),
                        convertObjectToInt(resultList.get(0).get("cargoShipID")),
                        convertObjectToInt(resultList.get(0).get("userID")),
                        convertObjectToInt(resultList.get(0).get("capacityBooked")),
                        convertObjectToInt(resultList.get(0).get("cost")),
                        convertObjectToInt(resultList.get(0).get("statusFlag")));
                list.add(cargoBooking);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public CruiseShip cruiseBookingFullStatus(int bookingID) {
        String from = "", to = "";
        Long departureTime = 0l, arrivalTime = 0l;
        int shipID = 0;
        try {
            String Query = "SELECT * FROM cruiseBookingJoin WHERE cruiseBookingID = '" + bookingID + "'";
            List<Map<String, Object>> resultList = executeQuery(Query);
            shipID = convertObjectToInt(resultList.get(0).get("cruiseShipID"));
            from = convertObjectToString(resultList.get(0).get("fromLocation"));
            to = convertObjectToString(resultList.get(0).get("toLocation"));
            departureTime = convertObjectToLong(resultList.get(0).get("departureTime"));
            arrivalTime = convertObjectToLong(resultList.get(0).get("arrivalTime"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CruiseShip(shipID, from, to, departureTime, arrivalTime, 0, 0, 0);
    }

    public CargoShip cargoBookingFullStatus(int bookingID) {
        String from = "", to = "";
        Long departureTime = 0l, arrivalTime = 0l;
        int shipID = 0;
        try {
            String Query = "SELECT * FROM cargoBookingJoin WHERE cruiseBookingID = '" + bookingID + "'";
            List<Map<String, Object>> resultList = executeQuery(Query);
            shipID = convertObjectToInt(resultList.get(0).get("cargoShipID"));
            from = convertObjectToString(resultList.get(0).get("fromLocation"));
            to = convertObjectToString(resultList.get(0).get("toLocation"));
            departureTime = convertObjectToLong(resultList.get(0).get("departureTime"));
            arrivalTime = convertObjectToLong(resultList.get(0).get("arrivalTime"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CargoShip(shipID, from, to, departureTime, arrivalTime, 0, 0, 0);
    }
    
    public ArrayList<CruiseShip> getAllCruiseShips(){
        ArrayList<CruiseShip> list = new ArrayList<>();
        try{
            String query = "SELECT * FROM cruiseShipsTable";
            List<Map<String , Object>> resuList = executeQuery(query);
            for(Map<String , Object> row : resuList){
                CruiseShip cruiseShip = new CruiseShip(
                    convertObjectToInt(row.get("cruiseShipID")),
                    convertObjectToString(row.get("fromLocation")),
                    convertObjectToString(row.get("toLocation")),
                    convertObjectToLong(row.get("departureTime")),
                    convertObjectToLong(row.get("arrivalTime")),
                    convertObjectToInt(row.get("totalSeats")),
                    convertObjectToInt(row.get("cost")),
                    convertObjectToInt(row.get("bookedSeats")
                ));
                list.add(cruiseShip);
            }
            

        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<CargoShip> getAllCargoShips(){
        ArrayList<CargoShip> list = new ArrayList<>();
        try{
            String query = "SELECT * FROM cargoShipsTable";
            List<Map<String , Object>> resuList = executeQuery(query);
            for(Map<String , Object> row : resuList){
                CargoShip cargoShip = new CargoShip(
                    convertObjectToInt(row.get("cargoShipID")),
                    convertObjectToString(row.get("fromLocation")),
                    convertObjectToString(row.get("toLocation")),
                    convertObjectToLong(row.get("departureTime")),
                    convertObjectToLong(row.get("arrivalTime")),
                    convertObjectToInt(row.get("capacity")),
                    convertObjectToInt(row.get("chargesPerTonne")),
                    convertObjectToInt(row.get("bookedCapacity"))
                );
                list.add(cargoShip);
            }
            

        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

}

/*
 * statusFlag flags 0 is confirmed past.
 * 1 is confirm acitve. 
 * 2 is waiting .
 * 3 is cancelled.
 */
