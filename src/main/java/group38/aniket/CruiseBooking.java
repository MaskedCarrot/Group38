package group38.aniket;
import java.util.ArrayList;
import java.util.Scanner;
import group38.apoorv.*;
import group38.himanshu.*;

public class CruiseBooking extends Booking
{
	private int seats;
	private int cost;

	public CruiseBooking() {
		super();
		seats = -1;
		cost = -1;
	}

	public CruiseBooking(int bookingID , int shipID , int userID ,int seats,int cost,int statusFlag) {
		super(bookingID , shipID , userID , statusFlag);
		this.seats=seats;
		this.cost=cost;
	}
	
	public void book(int userID) {
		ArrayList<CruiseShip> ship = new ArrayList<>();
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

		System.out.print("Enter number of seats to be booked: ");
		seats = sc.nextInt();
		cost = seats * ship[choice].getCostPerPerson();

		if (ship[choice].getBookedSeats() + seats > ship[choice].getTotalSeats()) {
			new Repository().bookCruiseShip(ship[choice].getShipID(), cost, userID, 2);
			System.out.println("Your booking is in waiting.");
		} else {
			new Repository().bookCruiseShip(ship[choice].getShipID(), cost, userID, 1);
			System.out.println("Booking confirmed.");
		}
	}

	public void getBookingStatus() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Booking ID: ");
		int bookingID=sc.nextInt();
		CruiseBooking cruisebooking=new Repository().cruiseBookingStatus(bookingID);
		System.out.println("Booking Details: ");
		System.out.println("Booking ID: "+cruisebooking.getBookingID());
		System.out.println("Ship ID: "+cruisebooking.getShipID());
		System.out.println("userID: "+cruisebooking.getUserID());
		System.out.println("Seats: "+cruisebooking.getSeats());
		System.out.println("Cost: "+cruisebooking.getCost());
		System.out.println("\n");
		if(cruisebooking.statusFlag == 1){
			System.out.println("Confirmed");
		}
		else if (cruisebooking.statusFlag == 2){
			System.out.println("Waiting");
		}
	}

	public void cancelBooking(){
		System.out.println("Enter Booking ID for Cancellation: ");
		int bookingID=getIDInput();
		// 
		Repository rep=new Repository();
		if(rep.cancelCruiseBoooking(bookingID))
		{
			System.out.println("Booking Cancelled");
		}
		else
		{
			System.out.println("Booking Not Cancelled");
		}

	}
	
}

	// public void Book()
	// {
	// 	System.out.println("Enter Ship ID of Ship in which Booking is to be made: ");
	// 	int S_id=getIDInput();
	// 	System.out.println("Enter your User ID: ");
	// 	int U_id=getIDInput();
	// 	int B_id=generateBookingID();
		// System.out.println("Enter number of seats to be Booked: ");
		// seats=getCapacityInput();
	// 	//calculate cost

	// 	CruiseBookingDatabase DB=new CruiseBookingDatabase();
	// 	DB.insertCruiseBooking(B_id,S_id,U_id,seats,cost);
	// }