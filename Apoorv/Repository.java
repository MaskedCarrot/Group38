package Apoorv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Ritik.user;
import himanshu.CargoShip;
import himanshu.CruiseShip;

public class Repository extends Database {

    //select * from cargoShipDatabase where toLocation = "to" and fromLocation = "from oder by time asc"
    public ArrayList<CargoShip> listAllCargoShips(String to , String from){
        
        ArrayList<CargoShip> list = new ArrayList<>();
        String querry = "select * from CargoShipsDatabase where toLocation = \""+to+"\" and fromLocation = \""+from+"\" order by arrivalTime asc";
        List<Map<String, Object>> resultList = executeQuerry(querry);
        return list;

    }

    //select * form cruiseShipDatabase where to = "to" and from = "from oder by time asc"
    public ArrayList<CruiseShip> listAllCruiseShips(String to , String from){
        
        ArrayList<CruiseShip> list = new ArrayList<>();
        String querry = "select * from cruiseShipsDatabase where toLocation = \""+to+"\" and fromLocation = \""+from+"\" order by arrivalTime asc";
        List<Map<String, Object>> resultList = executeQuerry(querry);
        
        return list ;
    }


    //"select * from bookingTable where userId = "userID order by time asc"
    public ArrayList<user> listAllUserBookings(int userId){
       
        ArrayList<user> list = new ArrayList<>();
        String querry = "select * from bookingTable where userId = "+userId+" order by time asc";
        List<Map<String, Object>> resultList = executeQuerry(querry);
        
        return list;
    }


}
