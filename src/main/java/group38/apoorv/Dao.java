package apoorv;

import java.util.ArrayList;
import himanshu.CargoShip;
import himanshu.CruiseShip;

interface Dao  {

    boolean checkUserCredentials(String userEmail , String password);
    boolean checkAdminCredentials(String adminEmail  , String password);

    boolean bookCruiseShip(int shipId , int cost ,int userId , boolean isWaiting);
    boolean bookCragoShip(int shipId , double cost , int userId);
    
    ArrayList<CargoShip> listAllCargoShips(String to , String from);
    ArrayList<CruiseShip> listAllCruiseShips(String to , String from);

    boolean addCargoShip(CargoShip cargoShip);
    boolean addCruiseShip(CruiseShip cruiseShip);
    
    boolean removeCargoShip(int shipId);
    boolean removeCruiseShip(int shipId);

        
    boolean updateCargoShip(CargoShip cargoShip);
    boolean updateCruiseShip(CruiseShip cruiseShip);

    int getUserID(String emailID);

}
