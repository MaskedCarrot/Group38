package apoorv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Ritik.user;
import himanshu.CargoShip;
import himanshu.CruiseShip;
import Aniket.CruiseBooking;
import Aniket.CargoBooking;

public class Repository extends Database implements Dao {



    public Boolean checkUserCredentials(int userId , String password){
        String Query = "SELECT COUNT(userID) as \"userCount\" from userTable WHERE userID = "+userId+" and password = \""+password+"\"";
        List<Map<String, Object>> resultList = executeQuery(Query);
        Boolean b = false;
        try{
            int result = Integer.parseInt(resultList.get(0).get("userCount").toString());
            if(result == 1 )
                b = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return b;
    }


    public Boolean checkAdminCreadentials(int adminId  , String password){
        String Query = "SELECT COUNT(adminId) as \"adminCount\" from adminTable WHERE adminId = "+adminId+" and password = \""+password+"\"";
        List<Map<String, Object>> resultList = executeQuery(Query);
        Boolean b = false;
        try{
            int result = Integer.parseInt(resultList.get(0).get("adminCount").toString());
            if(result == 1 )
                b = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return b;
    
    }


    public Boolean bookCruiseShip(int shipId , int cost , int userID , Boolean isWaiting){
        CruiseBooking booking = new CruiseBooking();
        booking.shipID = shipId;
        booking.userID  = userID;
        booking.cost = cost;
        booking.isWaiting = isWaiting;//removed?????

        // System.out.println("Enter number of tonnes to be Booked: ");
        // int space=sc.nextInt();
        //CALCULATE COST

        String Query="SELECT COUNT(cruiseBookingID) AS Seats_Booked FROM cruiseBookingTable WHERE cruiseShipID='"+shipId+"'";
        List<Map<String, Object>> resultList = executeQuery(Query);
        try{
            int result = Integer.parseInt(resultList.get(0).get("Seats_Booked").toString());
            Query="SELECT totalSeats FROM cruiseShipTable WHERE cruiseShipID='"+shipId+"'";
            resultList = executeQuery(Query);
            int result2 = Integer.parseInt(resultList.get(0).get("totalSeats").toString());
            if(result<result2)
            {
                // isWaiting=false;
            }
            else
            {
                // isWaiting=true;
            }
            Query="INSERT INTO cruiseBookingTable(cruiseShipID,userID,seats,cost) VALUES(";
        }catch(Exception e){
            
        }
        return false ;
        
    }
    

    public Boolean bookCragoShip(int shipId , double tonne , int userId){
        return false ;
    }
    
    
    public ArrayList<CargoShip> listAllCargoShips(String from , String to){        
        ArrayList<CargoShip> list = new ArrayList<>();
        String Query = "select * from cargoShipsTable where toLocation = \""+to+"\" and fromLocation = \""+from+"\" order by arrivalTime asc";
        List<Map<String, Object>> resultList = executeQuery(Query);

        for(int i =0 ;i<resultList.size() ;i++){
            CargoShip cargoShip = new CargoShip();
            Map<String, Object> row = resultList.get(i);

            cargoShip.shipId = convertObjectToInt(row.get("cargoShipID"));
            cargoShip.from = convertObjectToString(row.get("fromLocation"));
            cargoShip.to = convertObjectToString(row.get("toLocation"));
            cargoShip.arrivalTime = convertObjectToLong(row.get("arrivalTime")) ;
            cargoShip.departureTime = convertObjectToLong(row.get("departureTime"));
            cargoShip.bookedCapacity = convertObjectToInt(row.get("bookedCapacity"));
            cargoShip.capacity  = convertObjectToInt(row.get("capacity"));
            cargoShip.chargesPerTonne = convertObjectToInt(row.get("chargesPerTonne"));
            
            list.add(cargoShip);
        }
        return list;
        
    }
    
    public ArrayList<CruiseShip> listAllCruiseShips(String from , String to){
                
        ArrayList<CruiseShip> list = new ArrayList<>();
        String Query = "select * from cruiseShipsDatabase where toLocation = \""+to+"\" and fromLocation = \""+from+"\" order by arrivalTime asc";
        List<Map<String, Object>> resultList = executeQuery(Query);


        for(int i =0 ;i<resultList.size() ;i++){
            CruiseShip cruiseShip = new CruiseShip();
            Map<String, Object> row = resultList.get(i);
    
            cruiseShip.shipId = convertObjectToInt(row.get("cargoShipID"));
            cruiseShip.from = convertObjectToString(row.get("fromLocation"));
            cruiseShip.to = convertObjectToString(row.get("toLocation"));
            cruiseShip.arrivalTime = convertObjectToLong(row.get("arrivalTime")) ;
            cruiseShip.departureTime = convertObjectToLong(row.get("departureTime"));
            cruiseShip.bookedSeats = convertObjectToInt(row.get("bookedSeats"));
            cruiseShip.costPerPerson = convertObjectToInt(row.get("cost"));
            cruiseShip.totalSeats = convertObjectToInt(row.get("totalSeats"));
            
            list.add(cruiseShip);
        }
        return list;        
    }


    public Boolean addCargoShip(CargoShip cargoShip){
        return false ;
        
    }
    public Boolean addCruissShip(CruiseShip cruiseShip){
        
        return false ;
    }
    
    public Boolean removeCargoShip(int shipId){
        return false ;
    }
    public Boolean removeCruiseShip(int shipId){
        return false ;
    }

        
    public Boolean updateCargoShip(CargoShip cargoShip){
        return false ;
    }
    public Boolean updateCruiseShip(CruiseShip cruiseShip){
        return false ;
    }


}
