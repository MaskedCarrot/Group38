package group38.aniket;

abstract class Booking
{
	private int bookingID;
	private int shipID;
	private int userID;
	private int statusFlag;

	public Booking() {
		bookingID = -1;
		shipID = -1;
		userID = -1;
		statusFlag = -1;
	}
	
	public Booking(int bookingID, int shipID, int userID, int statusFlag) {
		this.bookingID = bookingID;
		this.shipID = shipID;
		this.userID = userID;
		this.statusFlag = statusFlag;
	}


	public int getBookingID() {
        return bookingID;
    }

    public int getShipID() {
        return shipID;
    }

    public int getUserID() {
        return userID;
    }

    public int getStatusFlag() {
        return statusFlag;
    }


/*
statusFlag flags
0 is past booking 
1 is confirm 
2 is wainting
3 is cancelled 
*/


	/*
	public abstract void Book();
	public abstract void getBookingStatus();
	public abstract void cancelBooking();
	*/
}
