package group38.aniket;

abstract class Booking {

    protected int bookingID;
    protected int shipID;
    protected int userID;
    protected int statusFlag;

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

    public abstract void book(int userID);
    public abstract void getBookingStatus();
    public abstract void cancelBooking();
    public abstract void listBookings(int userID);

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

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setShipID(int shipID) {
        this.shipID = shipID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setStatusFlag(int statusFlag) {
        this.statusFlag = statusFlag;
    }
}
