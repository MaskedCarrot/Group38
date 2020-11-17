package group38.apoorv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import group38.himanshu.CargoShip;
import group38.himanshu.CruiseShip;
import group38.ritik.User;
import group38.aniket.CargoBooking;
import group38.aniket.CruiseBooking;


public class Repository extends Database implements Dao {


    public boolean checkUserCredentials(String userEmail , String password){
        boolean b = false;
        try{
            String query = "SELECT COUNT(userID) AS \"userCount\" FROM userTable WHERE email = '" + userEmail + "' AND password = '"+password+"'";
            List<Map<String, Object>> resultList = executeQuery(query);
            int result = convertObjectToInt(resultList.get(0).get("userCount"));
            if(result == 1 )
                b = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return b;
    }

    public User displayUserDetails(int userID) {
        User user;
        try{
            String query = "SELECT * FROM userTable WHERE userID = '" + userID +"'";
            List<Map<String, Object>> resultList = executeQuery(query);
            user = new User(
                userID,
                convertObjectToLong(resultList.get(0).get("phoneNumber")),
                convertObjectToString(resultList.get(0).get("name")),
                convertObjectToInt(resultList.get(0).get("age")),
                convertObjectToString(resultList.get(0).get("gender")).charAt(0),
                convertObjectToString(resultList.get(0).get("email")),
                ""
            );
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        
        return user;        
    }

    public boolean isUniqueEmail(String userEmail) {
        boolean b = true;
        try{
            String query = "SELECT COUNT(userID) AS \"userCount\" FROM userTable WHERE email = '" + userEmail + "'";
            List<Map<String, Object>> resultList = executeQuery(query);
            int result = convertObjectToInt(resultList.get(0).get("userCount"));
            if(result == 1 )
                b = false;
        }catch(Exception e){
            e.printStackTrace();
        }
        return b;
    }

    public boolean checkAdminCredentials(String adminEmail, String password){
        boolean b = false;
        try{
            String query = "SELECT COUNT(adminID) as \"adminCount\" from adminTable WHERE email = '"+adminEmail+"' and password = '"+password+"'";
            List<Map<String, Object>> resultList = executeQuery(query);
            int result = convertObjectToInt(resultList.get(0).get("adminCount"));
            if(result == 1 )
                b = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return b;
    
    }

    public boolean bookCruiseShip(int shipID , int cost , int userID , int statusFlag){
        
        boolean b = false;
        try{
            if(!(statusFlag==1||statusFlag==2)){
                throw new AssertionError();
            }
            String query="INSERT INTO cruiseBookingTable(cruiseShipID,userID,seats,cost,statusFlag) VALUES('"+shipID+"','"+userID+"','0','"+cost+"','"+statusFlag+"'";
            executeUpdate(query);
            b = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return b;
    }
    
    public boolean bookCargoShip(int shipID , int cost, int userID, int statusFlag){
        boolean b = false;
        try{
            if(!(statusFlag==1||statusFlag==2)){
                throw new AssertionError();
            }
            String query="INSERT INTO cargoBookingTable(cargoShipID,userID,capacity,cost,statusFlag) VALUES('"+shipID+"','"+userID+"','0','"+cost+"','"+statusFlag+"'";
            executeUpdate(query);
            b = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return b;
    }
    
    public CruiseBooking cruiseBookingStatus(int bookingID)
    {
        int shipID,userID,seats,cost,statusFlag;
        try{
            String Query = "SELECT * FROM userTcruiseBookingTable WHERE cruiseBookingID = '" + bookingID +"'";
            List<Map<String, Object>> resultList = executeQuery(Query);
            shipID = Integer.parseInt(resultList.get(0).get("shipID").toString());
            userID = Integer.parseInt(resultList.get(0).get("userID").toString());
            seats = Integer.parseInt(resultList.get(0).get("seats").toString());
            cost = Integer.parseInt(resultList.get(0).get("cost").toString());
            statusFlag = convertObjectToInt(resultList.get(0).get("statusFlag"));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return new CruiseBooking(bookingID, shipID, userID, seats, cost, statusFlag);
    }

    public CargoBooking cargoBookingStatus(int bookingID)
    {
        int shipID,userID,cost,capacity,statusFlag;
        try{
            String Query = "SELECT * FROM userTcruiseBookingTable WHERE cruiseBookingID = '" + bookingID +"'";
            List<Map<String, Object>> resultList = executeQuery(Query);
            shipID = Integer.parseInt(resultList.get(0).get("shipID").toString());
            userID = Integer.parseInt(resultList.get(0).get("userID").toString());
            capacity = Integer.parseInt(resultList.get(0).get("capacity").toString());
            cost = Integer.parseInt(resultList.get(0).get("cost").toString());
            statusFlag = convertObjectToInt(resultList.get(0).get("statusFlag"));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return new CargoBooking(bookingID, shipID, userID, statusFlag ,  capacity, cost);
    }

    public ArrayList<CargoShip> listAllCargoShips(String from , String to){
        ArrayList<CargoShip> list = new ArrayList<>();
        try { 
            String Query = "SELECT * FROM cargoShipsTable WHERE toLocation = \""+to+"\" AND fromLocation = \""+from+"\" ORDER BY arrivalTime ASC";
            List<Map<String, Object>> resultList = executeQuery(Query);

            for (Map<String, Object> row : resultList) {
                CargoShip cargoShip = new CargoShip(
                        convertObjectToInt(row.get("cargoShipID")),
                        convertObjectToString(row.get("fromLocation")),
                        convertObjectToString(row.get("toLocation")),
                        convertObjectToLong(row.get("departureTime")),
                        convertObjectToLong(row.get("arrivalTime")),
                        convertObjectToInt(row.get("chargesPerTonne")),
                        convertObjectToInt(row.get("capacity")),
                        convertObjectToInt(row.get("bookedCapacity")));
                list.add(cargoShip);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
        
    }

    public ArrayList<CruiseShip> listAllCruiseShips(String from , String to){
                
        ArrayList<CruiseShip> list = new ArrayList<>();
        String Query = "select * from cruiseShipsDatabase where toLocation = \""+to+"\" and fromLocation = \""+from+"\" order by arrivalTime asc";
        List<Map<String, Object>> resultList = executeQuery(Query);


        for (Map<String, Object> row : resultList) {
            CruiseShip cruiseShip = new CruiseShip(
                    convertObjectToInt(row.get("CruiseShipID")),
                    convertObjectToString(row.get("fromLocation")),
                    convertObjectToString(row.get("toLocation")),
                    convertObjectToLong(row.get("departureTime")),
                    convertObjectToLong(row.get("arrivalTime")),
                    convertObjectToInt(row.get("totalSeats")),
                    convertObjectToInt(row.get("cost")),
                    convertObjectToInt(row.get("bookedSeats"))
            );

            list.add(cruiseShip);
        }
        return list;        
    }

    public boolean addCargoShip(CargoShip cargoShip){
        int capacity,chargesPerTonne,bookedCapacity;
        String from,to;
        Long departureTime,arrivalTime;
        capacity=cargoShip.getCapacity();
        chargesPerTonne=cargoShip.getChargesPerTonne();
        bookedCapacity=cargoShip.getBookedCapacity();
        from=cargoShip.getFrom();
        to=cargoShip.getTo();
        departureTime=cargoShip.getDepartureTime();
        arrivalTime=cargoShip.getArrivalTime();
        try{
            String Query="INSERT INTO cargoShipsTable(fromLocation,toLocation,departureTime,arrivalTime,capacity,chargesPerTonne,bookedCapacity) VALUES('"+from+
            "','"+to+"','"+departureTime+"','"+arrivalTime+"','"+capacity+"','"+chargesPerTonne+"','"+bookedCapacity+"')";
            executeUpdate(Query);
        }
        catch(Exception e){
            e.printStackTrace();
            return false ;
        }
        return true;
    }
    public boolean addCruiseShip(CruiseShip cruiseShip){
        int totalSeats,costPerPerson,bookedSeats;
        String from,to;
        Long departureTime,arrivalTime;
        totalSeats=cruiseShip.getTotalSeats();
        costPerPerson=cruiseShip.getCostPerPerson();
        bookedSeats=cruiseShip.getBookedSeats();
        from=cruiseShip.getFrom();
        to=cruiseShip.getTo();
        departureTime=cruiseShip.getDepartureTime();
        arrivalTime=cruiseShip.getArrivalTime();
        try{
            String Query="INSERT INTO cruiseShipsTable(fromLocation,toLocation,departureTime,arrivalTime,totalSeats,cost,bookedSeats) VALUES('"+from+
            "','"+to+"','"+departureTime+"','"+arrivalTime+"','"+totalSeats+"','"+costPerPerson+"','"+bookedSeats+"')";
            executeUpdate(Query);
        }
        catch(Exception e){
            e.printStackTrace();
            return false ;            
        }
        return true;
    }
    
    public boolean removeCargoShip(int shipID){
        try{
            String Query="DELETE FROM cargoShipsTable WHERE cargoShipID='"+shipID+"'";
            executeUpdate(Query);
        }
        catch(Exception e){
            e.printStackTrace();
            return false ;
        }
        return true;
    }

    public boolean removeCruiseShip(int shipID){
        try{
            String Query="DELETE FROM cruiseShipsTable WHERE cruiseShipID='"+shipID+"'";
            executeUpdate(Query);
        }
        catch(Exception e){
            e.printStackTrace();
            return false ;
        }
        return true;
    }
        
    public boolean updateCargoShip(CargoShip cargoShip){
        try{
            String Query="UPDATE cargoShipsTable SET fromLocation='"+cargoShip.getFrom()+"',toLocation='"+cargoShip.getTo()+"',departureTime='"+cargoShip.getDepartureTime()
            +"',arrivalTime='"+cargoShip.getArrivalTime()+"',capacity='"+cargoShip.getCapacity()+"',chargesPerTonne='"+cargoShip.getChargesPerTonne()+"',bookedCapacity='"+cargoShip.getBookedCapacity()+"' WHERE cargoShipID='"+cargoShip.getShipID()+"'";
            executeUpdate(Query);
        }
        catch(Exception e){
            e.printStackTrace();
            return false ;
        }
        return true;
    }

    public boolean updateCruiseShip(CruiseShip cruiseShip){
        try{   
            String Query="UPDATE cruiseShipsTable SET fromLocation='"+cruiseShip.getFrom()+"',toLocation='"+cruiseShip.getTo()+"',departureTime='"+cruiseShip.getDepartureTime()+"',arrivalTime='"+cruiseShip.getArrivalTime()
            +"',totalSeats='"+cruiseShip.getTotalSeats()+"',cost='"+cruiseShip.getCostPerPerson()+"',bookedSeats='"+cruiseShip.getBookedSeats()+"' WHERE cruiseShipID='"+cruiseShip.getShipID()+"'";
            executeUpdate(Query);
        }
        catch(Exception e){
            e.printStackTrace();
            return false ;
        }
        return true;
    }

    public boolean cancelCruiseBoooking(int bookingID){
        String Query="UPDATE cruiseBookingTable SET statusFlag='"+3+"' WHERE cruiseShipID='"+bookingID+"'";
        //String nextQuerry = "SELECT seats , cruiseShipID FROM cruiseBookingTable WHERE cruiseShipID='"+bookingID+"'";
        executeUpdate(Query);
        return true;
    }

    public boolean cancelCargoBoooking(int bookingID){
        String Query="UPDATE cargoBookingTable SET statusFlag='"+3+"' WHERE cargoShipID='"+bookingID+"'";
        executeUpdate(Query);
        return true;
    }
    
    public int getUserID(String emailID)
    {
        String Query="SELECT userID FROM userTable WHERE email='"+emailID+"'";
        int result=0;
        try{
        List<Map<String, Object>> resultList = executeQuery(Query);
        result = Integer.parseInt(resultList.get(0).get("userID").toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int getAdminID(String emailID)
    {
        String Query="SELECT adminID FROM adminTable WHERE email='"+emailID+"'";
        int result=0;
        try{
        List<Map<String, Object>> resultList = executeQuery(Query);
        result = Integer.parseInt(resultList.get(0).get("adminID").toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }


    public boolean addUser(User user){
        String querry = "INSERT INTO userTable(phoneNumber , name , age , gender, password , email) VALUES("+user.getPhoneNumber()+",'"+user.getName()+"',"+user.getAge()+",'"+user.getGender()+"','"+user.getPassword()+"','"+user.getEmail()+"')";
        boolean result = false;
        try{
            executeUpdate(querry);
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }
            return result;
    }

    /*
    public boolean handleBookings(){
        String querry = ""
        return false;
    }
    */

    private boolean cancelLeftBookings(){
        String querry = "UPDATE cruiseBookingTable SET statusFlag = 3 WHERE statusFlag = 2";
        String querry2 = "UPDATE cargoBookingTable  SET statusFlag = 3 WHERE statusFlag = 2";
        boolean result = false;
        try{
            executeUpdate(querry);
            executeUpdate(querry2);
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    




}


/*
statusFlag flags
0 is past booking 
1 is confirm 
2 is wainting
3 is cancelled 
*/
