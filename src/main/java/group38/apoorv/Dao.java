package apoorv;

import java.util.ArrayList;
import himanshu.CargoShip;
import himanshu.CruiseShip;

interface Dao  {

    boolean checkUserCredentials(String userEmail , String password);
    boolean checkAdminCredentials(String adminEmail  , String password);

    boolean bookCruiseShip(int shipId , int cost ,int userId , int status_flag);
    boolean bookCargoShip(int shipId , double cost , int userId, int status_flag);
    
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
