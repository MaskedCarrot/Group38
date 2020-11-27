package group38.apoorv;

import java.util.ArrayList;
import group38.himanshu.CargoShip;
import group38.himanshu.CruiseShip;
import group38.ritik.User;
import group38.aniket.CargoBooking;
import group38.aniket.CruiseBooking;

interface Dao {

    /** Functions for user in [Repository] */

    boolean checkUserCredentials(String userEmail, String password);

    public boolean addUser(User user);

    int getUserID(String emailID);

    public boolean isUniqueEmail(String userEmail);

    public User displayUserDetails(int userID);

    ArrayList<CargoShip> listAllCargoShips(String to, String from);

    ArrayList<CruiseShip> listAllCruiseShips(String to, String from);

    int bookCruiseShip(int shipID, int cost, int userID, int statusFlag);

    int bookCargoShip(int shipID, int cost, int userID, int statusFlag);

    public CruiseBooking cruiseBookingStatus(int bookingID);

    public CargoBooking cargoBookingStatus(int bookingID);

    public boolean cancelCruiseBoooking(int bookingID);

    public boolean cancelCargoBoooking(int bookingID);

    boolean addCargoShip(CargoShip cargoShip);

    boolean addCruiseShip(CruiseShip cruiseShip);

    public ArrayList<CruiseBooking> listAllCruiseBooking(int userID);

    public ArrayList<CargoBooking> listAllCargoBooking(int userID);

    public CruiseShip cruiseBookingFullStatus(int bookingID);

    public CargoShip cargoBookingFullStatus(int bookingID);

    /** Functions for admin in [Repository] */

    boolean checkAdminCredentials(String adminEmail, String password);

    public int getAdminID(String emailID);

    boolean removeCargoShip(int shipID);

    boolean removeCruiseShip(int shipID);

    boolean updateCargoShip(CargoShip cargoShip);

    boolean updateCruiseShip(CruiseShip cruiseShip);

    public ArrayList<CruiseShip> getAllCruiseShips();

    public ArrayList<CargoShip> getAllCargoShips();

    /** Functions for user/admin in [Repository] */

    void refreshBookingTable();

}
