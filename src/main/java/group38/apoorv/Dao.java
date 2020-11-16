package group38.apoorv;

import java.util.ArrayList;
import group38.himanshu.CargoShip;
import group38.himanshu.CruiseShip;

interface Dao  {

    boolean checkUserCredentials(String userEmail , String password);
    boolean checkAdminCredentials(String adminEmail  , String password);

    boolean bookCruiseShip(int shipID , int cost ,int userID , int status_flag);
    boolean bookCargoShip(int shipID , int cost , int userID, int status_flag);
    
    ArrayList<CargoShip> listAllCargoShips(String to , String from);
    ArrayList<CruiseShip> listAllCruiseShips(String to , String from);

    boolean addCargoShip(CargoShip cargoShip);
    boolean addCruiseShip(CruiseShip cruiseShip);
    
    boolean removeCargoShip(int shipID);
    boolean removeCruiseShip(int shipID);

        
    boolean updateCargoShip(CargoShip cargoShip);
    boolean updateCruiseShip(CruiseShip cruiseShip);

    int getUserID(String emailID);

}
