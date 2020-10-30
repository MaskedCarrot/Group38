package Aniket;

public class CargoBooking extends Booking
{
	public int capacity;
	public int cost;
	/*
	public void Book()
	{
		System.out.println("Enter Ship ID of Ship in which Booking is to be made: ");
		int S_id=getIDInput();
		System.out.println("Enter your User ID: ");
		int U_id=getIDInput();
		int B_id=generateBookingID();
		System.out.println("Enter number of tonnes to be Booked: ");
		int space=getCapacityInput();
		capacity=space;
		//calculate cost

		CargoBookingDatabase DB=new CargoBookingDatabase();
		DB.insertCargoBooking(B_id,S_id,U_id,space,cost);
	}
	public void getBookingStatus()
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