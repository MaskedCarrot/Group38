package group38.Aniket;

import java.util.Scanner;
import group38.himanshu.CruiseShip;
import  group38.apoorv.*;
public class CruiseBooking extends Booking
{
	public int seats;
	public int cost;
	public CruiseBooking(int bookingID , int shipID , int userID ,int seats,int cost,int statusFlag)
	{
		super(bookingID , shipID , userID , statusFlag);
		this.seats=seats;
		this.cost=cost;
	}
	public void Book_After_Search(int shipID,int userID,int costPerSeat,int bookedSeats,int totalSeats)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of seats to be Booked: ");
		seats = sc.nextInt();
		cost = seats * costPerSeat;
		Repository rep=new Repository();
		if(bookedSeats+seats>totalSeats)
		{
			rep.bookCruiseShip(shipID, cost, userID, 2);
		}
		else
		{
			rep.bookCruiseShip(shipID, cost, userID, 1);
		}
	}	
	public void getBookingStatus()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Booking ID: ");
		int bookingID=sc.nextInt();
		CruiseBooking cruisebooking=new Repository().CruiseBookingStatus(bookingID);
		System.out.println("Booking Details: ");
		System.out.println("Booking ID: "+cruisebooking.bookingID);
		System.out.println("Ship ID: "+cruisebooking.shipID);
		System.out.println("userID: "+cruisebooking.userID);
		System.out.println("Seats: "+cruisebooking.seats);
		System.out.println("Cost: "+cruisebooking.cost);
		switch(cruisebooking.statusFlag){
			case 1 :{
				System.out.println("Confirmed");
				break;
			}
			case 2:{
				System.out.println("Waiting");
				break;
			}
		}
	}
	// public void cancelBooking()
	// {
	// 	System.out.println("Enter Booking ID for Cancellation: ");
	// 	int ID=getIDInput();
	// 	CruiseBookingDatabase DB=new CruiseBookingDatabase();
	// 	.deleteCruiseBooking(ID);
	// }


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