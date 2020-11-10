package apoorv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import himanshu.CargoShip;
import himanshu.CruiseShip;
import Aniket.CruiseBooking;
import Aniket.CargoBooking;



public class Repository extends Database implements Dao {



    public boolean checkUserCredentials(String userEmail , String password){
        boolean b = false;
        try{
            String Query = "SELECT COUNT(userID) AS \"userCount\" FROM userTable WHERE email = '" + userEmail + "' AND password = '"+password+"'";
            List<Map<String, Object>> resultList = executeQuery(Query);
            int result = Integer.parseInt(resultList.get(0).get("userCount").toString());
            if(result == 1 )
                b = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return b;
    }


    public boolean checkAdminCredentials(String adminEmail, String password){
        boolean b = false;
        try{
            String Query = "SELECT COUNT(adminID) as \"adminCount\" from adminTable WHERE email = '"+adminEmail+"' and password = '"+password+"'";
            List<Map<String, Object>> resultList = executeQuery(Query);
            int result = Integer.parseInt(resultList.get(0).get("adminCount").toString());
            if(result == 1 )
                b = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return b;
    
    }


    public boolean bookCruiseShip(int shipID , int cost , int userID , int status_flag){
        // CruiseBooking booking = new CruiseBooking();
        // booking.shipID = shipId;
        // booking.userId  = userId;
        // booking.cost = cost;
        // booking.isWaiting = isWaiting;//removed?????

        // System.out.println("Enter number of tonnes to be Booked: ");
        // int space=sc.nextInt();
        //CALCULATE COST

        // String Query="SELECT COUNT(cruiseBookingID) AS Seats_Booked FROM cruiseBookingTable WHERE cruiseShipID='"+shipId+"'";
        // List<Map<String, Object>> resultList = executeQuery(Query);
        // try{
        //     int result = Integer.parseInt(resultList.get(0).get("Seats_Booked").toString());
        //     Query="SELECT totalSeats FROM cruiseShipTable WHERE cruiseShipID='"+shipId+"'";
        //     resultList = executeQuery(Query);
        //     int result2 = Integer.parseInt(resultList.get(0).get("totalSeats").toString());
        //     if(result<result2)
        //     {
        //         // isWaiting=false;
        //     }
        //     else
        //     {
        //         // isWaiting=true;
        //     }
        //     Query="INSERT INTO cruiseBookingTable(cruiseShipID,userID,seats,cost) VALUES(";
        // }catch(Exception e){
        //     e.printStackTrace();
        // }
        return false ;
        
    }
    

    public boolean bookCargoShip(int shipID , int cost, int userID, int status_flag){
        // CargoBooking booking=new CargoBooking();
        // booking.shipID = shipId;
        // booking.userId  = userId;
        // booking.cost = cost;
        // booking.isWaiting = isWaiting;//removed?????
        return false ;
    }

   
    
    
    public ArrayList<CargoShip> listAllCargoShips(String from , String to){        
        ArrayList<CargoShip> list = new ArrayList<>();
        try { 
            String Query = "SELECT * FROM cargoShipsTable WHERE toLocation = \""+to+"\" AND fromLocation = \""+from+"\" ORDER BY arrivalTime ASC";
            List<Map<String, Object>> resultList = executeQuery(Query);

            for(int i =0 ;i<resultList.size() ;i++){
                Map<String, Object> row = resultList.get(i);
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


        for(int i =0 ;i<resultList.size() ;i++){
            Map<String, Object> row = resultList.get(i);
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
    

    /*

    public void cleanUpBookings(){

        long currnetTIme = Util.getCurrentTimeInMilli()

        String query = "UPDATE cruiseBookingTable SET statusFlag = "+3+"WHERE statusFlag = "2 +"and "; 
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}


/*
statusFlag flags
0 is past booking 
1 is confirm 
2 is wainting
3 is cancelled 
*/
