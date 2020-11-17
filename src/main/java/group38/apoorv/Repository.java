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
        boolean b = false;
        try{
            String query = "SELECT COUNT(userID) AS \"userCount\" FROM userTable WHERE email = '" + userEmail + "'";
            List<Map<String, Object>> resultList = executeQuery(query);
            int result = convertObjectToInt(resultList.get(0).get("userCount"));
            if (result == 0)
                b = true;
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

        try{
            if(!(statusFlag==1||statusFlag==2)){
                throw new AssertionError();
            }
            String query="INSERT INTO cruiseBookingTable(cruiseShipID,userID,cost,statusFlag) VALUES('"+shipID+"','"+userID+"','"+cost+"','"+statusFlag+"')";
            System.out.println(query);
            executeUpdate(query);
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean bookCargoShip(int shipID , int cost, int userID, int statusFlag){
        try{
            if(!(statusFlag==1||statusFlag==2)){
                throw new AssertionError();
            }
            String query="INSERT INTO cargoBookingTable(cargoShipID,userID,capacity,cost,statusFlag) VALUES('"+shipID+"','"+userID+"','0','"+cost+"','"+statusFlag+"')";
            executeUpdate(query);
            return false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public CruiseBooking cruiseBookingStatus(int bookingID)
    {
        int shipID,userID,cost,statusFlag;
        try{
            String Query = "SELECT * FROM cruiseBookingTable WHERE cruiseBookingID = '" + bookingID +"'";
            List<Map<String, Object>> resultList = executeQuery(Query);
            shipID = Integer.parseInt(resultList.get(0).get("cruiseShipID").toString());
            userID = Integer.parseInt(resultList.get(0).get("userID").toString());
            cost = Integer.parseInt(resultList.get(0).get("cost").toString());
            statusFlag = convertObjectToInt(resultList.get(0).get("statusFlag"));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return new CruiseBooking(bookingID, shipID, userID, 0, cost, statusFlag);
    }

    public CargoBooking cargoBookingStatus(int bookingID)
    {
        int shipID,userID,cost,capacity,statusFlag;
        try{
            String Query = "SELECT * FROM cruiseBookingTable WHERE cruiseBookingID = '" + bookingID +"'";
            List<Map<String, Object>> resultList = executeQuery(Query);
            shipID = Integer.parseInt(resultList.get(0).get("cargoShipID").toString());
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
            String query = "SELECT * FROM cargoShipsTable WHERE toLocation = \""+to+
                "\" and suserfromLocation = \""+from+"\" and departureTime >="+
                (new Util().getCurrentTimeInMinutes()+20)+" order by arrivalTime asc";
                
            List<Map<String, Object>> resultList = executeQuery(query);

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

       try{
            String query = "select * from cruiseShipsTable where toLocation = \""+to+
            "\" and fromLocation = \""+from+"\" and departureTime >="+
            (new Util().getCurrentTimeInMinutes()+20)+" order by arrivalTime asc";

            List<Map<String, Object>> resultList = executeQuery(query);

            for (Map<String, Object> row : resultList) {
                CruiseShip cruiseShip = new CruiseShip(
                        convertObjectToInt(row.get("cruiseShipID")),
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
       }catch(Exception e){
           e.printStackTrace();
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
        boolean result = false;
        try{   
            String Query="UPDATE cruiseShipsTable SET fromLocation='"+cruiseShip.getFrom()+"',toLocation='"+cruiseShip.getTo()+"',departureTime='"+cruiseShip.getDepartureTime()+"',arrivalTime='"+cruiseShip.getArrivalTime()
                +"',totalSeats='"+cruiseShip.getTotalSeats()+"',cost='"+cruiseShip.getCostPerPerson()+"',bookedSeats='"+cruiseShip.getBookedSeats()+"' WHERE cruiseShipID='"+cruiseShip.getShipID()+"'";
            executeUpdate(Query);
            result = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private void updateCruiseWaiting(int bookingID)
    {
        
    }

    public boolean cancelCruiseBoooking(int bookingID){
        try {
            String Query;
            //SELECT row with same shipID order by biD
            Query="UPDATE cruiseBookingTable SET statusFlag='3' WHERE cruiseBookingID"+bookingID+"')";
            executeUpdate(Query);   

            Query = "SELECT * FROM cruiseBookingJoin WHERE cruiseBookingID='"+bookingID+"'";
            
            List<Map<String, Object>> resultList = executeQuery(Query);

            int cruiseShipID=convertObjectToInt(resultList.get(0).get("cruiseShipID"));
            int bookedSeats=convertObjectToInt(resultList.get(0).get("bookedSeats"));
            int totalSeats=convertObjectToInt(resultList.get(0).get("totalSeats"));
            Query="SELECT * FROM cruiseBookingJoin WHERE statusFlag='2' AND cruiseShipID='"+cruiseShipID+"' ORDER BY cruiseBookingID";
            resultList = executeQuery(Query);
            
            for (Map<String, Object> row : resultList) {
                int seats=convertObjectToInt(row.get("seats")); 
                if(seats+bookedSeats<=totalSeats)
                {
                    updateCruiseWaiting(convertObjectToInt(row.get("cruiseBookingID")));
                    bookedSeats+=seats;
                }
            }
            Query="UPDATE cruiseBookingTable SET statusFlag='3' WHERE cruiseBookingID"+bookingID+"')";
            executeUpdate(Query);    
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean cancelCargoBoooking(int bookingID){
        try {    
            String Query="UPDATE cargoBookingTable SET statusFlag='"+3+"' WHERE cargoBookingID='"+bookingID+"')";
            executeUpdate(Query);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

    public int getAdminID(String emailID) {
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
        boolean result = false;
        try{
            String querry = "INSERT INTO userTable(phoneNumber , name , age , gender, password , email) VALUES("+user.getPhoneNumber()+",'"+user.getName()+"',"+user.getAge()+",'"+user.getGender()+"','"+user.getPassword()+"','"+user.getEmail()+"')";
            executeUpdate(querry);
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }
            return result;
    }

    public boolean refreshBookingTable(){
        confirmWaitingBookings();
        boolean result = false;
        try{
            String query = "UPDATE carogoShipBookingView set statusFlag = 3 where statusFlag = 2 where departureTime <="+new Util().getCurrentTimeInMinutes()+20;
            executeUpdate(query);
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }


    private boolean confirmWaitingBookings(){
        try{
            String query1 = "SELECT DISTINCT cargoShipID FROM cargoBookingTable";
            String query2 = "SELECT MAX(bookedCapacity) FROM cargoBookingTable INNER JOIN cargoShipTable on cargoBooknigTable.cargoShipID = cargoShipTable.cargoShipID where cargoBookingTable.cargoShipID =";
            String query3 = "SELECT capacity , chargesPerTonne FROM cargoShipTable WHERE cargoShipID =";
            String query4 = "SELECT cost , cargoBookingID FROM cargoBookingTable where statusFlag = 2 and cargoShipID =";
            String query5 = "UPDATE cargoBookingTable SET statusFlag = 1 WHERE statusFlag = 2 and bookingID =";
            List<Map<String, Object>> resultList1 = executeQuery(query1);
            for (Map<String, Object> row1 : resultList1) {
                int shipID = convertObjectToInt(row1.get("cargoShipID"));
                List<Map<String, Object>> resultList2 = executeQuery(query2+shipID);
                List<Map<String, Object>> resultList3 = executeQuery(query3+shipID+" LIMIT 1");
                List<Map<String, Object>> resultList4 = executeQuery(query4+shipID);
                int available = convertObjectToInt(resultList2.get(0).get("capacity")) - convertObjectToInt(resultList2.get(0).get("bookedCapacity"));
                int cost = convertObjectToInt(resultList3.get(0).get("chargesPerTonne"));
                for (Map<String, Object> row4 : resultList4) {
                    int required = convertObjectToInt(row4.get("cost"))/cost;
                    int bookingID = convertObjectToInt(row4.get("cargoBookingID"));
                    if(available - required >= 0){
                        available = available - required;
                        executeUpdate(query5+bookingID);
                    }
                }

            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        try{
            String query1 = "SELECT DISTINCT cruiseShipID FROM cruiseBookingTable";
            String query2 = "SELECT MAX(bookedCapacity) FROM cruiseBookingTable INNER JOIN cruiseShipTable on cruiseBooknigTable.cruiseShipID = cruiseShipTable.cruiseShipID where cruiseBookingTable.cruiseShipID =";
            String query3 = "SELECT capacity , cost FROM cruiseShipTable WHERE cruiseShipID =";
            String query4 = "SELECT cost , cruiseBookingID FROM cruiseBookingTable where statusFlag = 2 and cruiseShipID =";
            String query5 = "UPDATE cruiseBookingTable SET statusFlag = 1 WHERE statusFlag = 2 and bookingID =";
            List<Map<String, Object>> resultList1 = executeQuery(query1);
            for (Map<String, Object> row1 : resultList1) {
                int shipID = convertObjectToInt(row1.get("cargoShipID"));
                List<Map<String, Object>> resultList2 = executeQuery(query2+shipID);
                List<Map<String, Object>> resultList3 = executeQuery(query3+shipID+" LIMIT 1");
                List<Map<String, Object>> resultList4 = executeQuery(query4+shipID);
                int available = convertObjectToInt(resultList2.get(0).get("capacity")) - convertObjectToInt(resultList2.get(0).get("bookedCapacity"));
                int cost = convertObjectToInt(resultList3.get(0).get("cost"));
                for (Map<String, Object> row4 : resultList4) {
                    int required = convertObjectToInt(row4.get("cost"))/cost;
                    int bookingID = convertObjectToInt(row4.get("cruiseBookingID"));
                    if(available - required >= 0){
                        available = available - required;
                        executeUpdate(query5+bookingID);
                    }
                    else 
                        break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}


/*
statusFlag flags
0 is past booking 
1 is confirm 
2 is wainting
3 is cancelled 
*/
