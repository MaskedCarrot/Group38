package Aniket;

abstract class Booking
{
	public int bookingID;
	public int shipID;
	public int userID;
	public int statusFlag;

	public Booking()
	{
		bookingID = -1;
		shipID = -1;
		userID = -1;
		statusFlag = -1;
	}
	
	public Booking(int bookingID , int shipID , int userID , int statusFlag){
		this.bookingID = bookingID;
		this.shipID  = shipID;
		this.userID  =userID;
		this.statusFlag = statusFlag;

	}

	/*
	public abstract void Book();
	public abstract void getBookingStatus();
	public abstract void cancelBooking();
	*/
}
