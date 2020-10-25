import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Ships{

}

class User{

}

public class Repository extends Database {

    //select * from cargoShipDatabase where toLocation = "to" and fromLocation = "from oder by time asc"
    public ArrayList<Ships> listAllCargoShips(String to , String from){
        
        ArrayList<Ships> list = new ArrayList<>();
        String querry = "select * from CargoShipsDatabase where toLocation = \""+to+"\" and fromLocation = \""+from+"\" order by arrivalTime asc";
        List<Map<String, Object>> resultList = executeQuerry(querry);
        return list;

    }

    //select * form cruiseShipDatabase where to = "to" and from = "from oder by time asc"
    public ArrayList<Ships> listAllCruiseShips(String to , String from){
        
        ArrayList<Ships> list = new ArrayList<>();
        String querry = "select * from cruiseShipsDatabase where toLocation = \""+to+"\" and fromLocation = \""+from+"\" order by arrivalTime asc";
        List<Map<String, Object>> resultList = executeQuerry(querry);
        
        return list ;
    }


    //"select * from bookingTable where userId = "userID order by time asc"
    public ArrayList<User> listAllUserBookings(int userId){
       
        ArrayList<User> list = new ArrayList<>();
        String querry = "select * from bookingTable where userId = "+userId+" order by time asc";
        List<Map<String, Object>> resultList = executeQuerry(querry);
        
        return list;
    }


}
