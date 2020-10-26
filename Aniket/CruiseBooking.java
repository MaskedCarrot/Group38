package Aniket;

class CruiseBooking extends Booking
{
	private int seats;
	private int cost;
	public void Book()
	{
		System.out.println("Enter Ship ID of Ship in which Booking is to be made: ");
		int S_id=getIDInput();
		System.out.println("Enter your User ID: ");
		int U_id=getIDInput();
		int B_id=generateBookingID();
		System.out.println("Enter number of seats to be Booked: ");
		seats=getCapacityInput();
		//calculate cost

		CruiseBookingDatabase DB=new CruiseBookingDatabase();
		DB.insertCruiseBooking(B_id,S_id,U_id,seats,cost);
	}
	public void getBookingStatus()
	{
		System.out.println("Enter Booking ID: ");
		int ID=getIDInput();
		CruiseBookingDatabase DB=new CruiseBookingDatabase();
		DB.getUserCruiseBooking(ID);
	}
	public void cancelBooking()
	{
		System.out.println("Enter Booking ID for Cancellation: ");
		int ID=getIDInput();
		CruiseBookingDatabase DB=new CruiseBookingDatabase();
		DB.deleteCruiseBooking(ID);
	}
}