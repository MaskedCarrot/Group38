package apoorv;

import java.util.ArrayList;
import himanshu.CargoShip;
import himanshu.CruiseShip;

interface Dao  {

    Boolean checkUserCredentials(int userId , String password);
    Boolean checkAdminCreadentials(String adminEmail  , String password);

    Boolean bookCruiseShip(int shipId , int cost ,int userId , Boolean isWaiting);
    Boolean bookCragoShip(int shipId , double cost , int userId);
    
    ArrayList<CargoShip> listAllCargoShips(String to , String from);
    ArrayList<CruiseShip> listAllCruiseShips(String to , String from);

    Boolean addCargoShip(CargoShip cargoShip);
    Boolean addCruiseShip(CruiseShip cruiseShip);
    
    Boolean removeCargoShip(int shipId);
    Boolean removeCruiseShip(int shipId);

        
    Boolean updateCargoShip(CargoShip cargoShip);
    Boolean updateCruiseShip(CruiseShip cruiseShip);

}
