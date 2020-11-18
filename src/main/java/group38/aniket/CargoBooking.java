package group38.aniket;
import java.util.ArrayList;
import java.util.Scanner;
import group38.himanshu.CargoShip;
import group38.himanshu.CruiseShip;
import group38.apoorv.Repository;

public class CargoBooking extends Booking
{
	private int cost;
	private int capacity;

	public CargoBooking()
	{
		super();
		capacity=-1;
		cost=-1;
	}

	public CargoBooking(int bookingID , int shipID , int userID  , int capacity, int statusFlag , int cost){
		super(bookingID , shipID , userID , statusFlag);
		this.capacity  = capacity;
		this.cost = cost;
	}
	
    public int getCost(){
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
	}
	

	public void book(int userID) {
		ArrayList<CargoShip> ship = new CargoShip().listShips();
		if (ship.isEmpty())
			return;
		
		System.out.print("Choose a ship (1 - " + ship.size() + "): ");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		while (choice < 1 || choice > ship.size()) {
			System.out.print("Please enter a valid choice (1 - " + ship.size() + "): ");
			choice = sc.nextInt();
		}
		--choice;

		System.out.print("Enter total capacity booked: ");
		int seats = sc.nextInt();
		cost = seats * ship.get(choice).getChargesPerTonne();

		if (ship.get(choice).getBookedCapacity() + seats > ship.get(choice).getCapacity()) {
			new Repository().bookCargoShip(ship.get(choice).getShipID(), cost, userID, 2);
			System.out.println("Your booking is in waiting.");
		} else {
			new Repository().bookCargoShip(ship.get(choice).getShipID(), cost, userID, 1);
			System.out.println("Booking confirmed.");
		}
	}
	public void getBookingStatus() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Booking ID: ");
		int bookingID=sc.nextInt();
		CargoBooking cargobooking=new Repository().cargoBookingStatus(bookingID);
		System.out.println("Booking Details: ");
		System.out.println("Booking ID: "+cargobooking.getBookingID());
		System.out.println("Ship ID: "+cargobooking.getShipID());
		System.out.println("userID: "+cargobooking.getUserID());
		System.out.println("Cost: "+cargobooking.getCost());
		// System.out.println("\n");//PRINT BOOKING ID
		if(cargobooking.getStatusFlag() == 1){
			System.out.println("Confirmed");
		}
		else if (cargobooking.getStatusFlag() == 2){
			System.out.println("Waiting");
		}
	}
	public void cancelBooking(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Booking ID for Cancellation: ");
		int bookingID=sc.nextInt();
		// 
		Repository rep=new Repository();
		if(rep.cancelCargoBoooking(bookingID))
		{
			System.out.println("Booking Cancelled");
		}
		else
		{
			System.out.println("Booking Not Cancelled");
		}

	}
	// public void Book()
	// {
	// 	System.out.println("Enter Ship ID of Ship in which Booking is to be made: ");
	// 	int S_id=getIDInput();
	// 	System.out.println("Enter your User ID: ");
	// 	int U_id=getIDInput();
	// 	int B_id=generateBookingID();
	// 	System.out.println("Enter number of tonnes to be Booked: ");
	// 	int space=getCapacityInput();
	// 	capacity=space;
	// 	//calculate cost

	// 	CargoBookingDatabase DB=new CargoBookingDatabase();
	// 	DB.insertCargoBooking(B_id,S_id,U_id,space,cost);
	// }
	// public void Book_After_Search(int shipID,int userID)
	// {
		
	// }
	/*public void getBookingStatus()
	{
		System.out.println("Enter Booking ID: ");
		int ID=getIDInput();
		CargoBookingDatabase DB=new CargoBookingDatabase();
		DB.getUserCargoBooking(ID);
	}
	public void cancelBooking()
	{
		System.out.println("Enter Booking ID for Cancellation: ");
		int ID=getIDInput();
		CargoBookingDatabase DB=new CargoBookingDatabase();
		DB.deleteCargoBooking(ID);
	}
*/
}
