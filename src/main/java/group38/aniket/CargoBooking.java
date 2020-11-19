package group38.aniket;

import java.util.ArrayList;
import java.util.Scanner;
import group38.himanshu.CargoShip;
import group38.apoorv.Repository;

public class CargoBooking extends Booking {
	private int cost;
	private int capacity;

	public CargoBooking() {
		super();
		capacity = -1;
		cost = -1;
	}

	public CargoBooking(int bookingID, int shipID, int userID, int capacity, int statusFlag, int cost) {
		super(bookingID, shipID, userID, statusFlag);
		this.capacity = capacity;
		this.cost = cost;
	}

	public int getCost() {
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
		capacity = sc.nextInt();
		cost = capacity * ship.get(choice).getChargesPerTonne();

		if (ship.get(choice).getBookedCapacity() + capacity > ship.get(choice).getCapacity()) {
			bookingID = new Repository().bookCargoShip(ship.get(choice).getShipID(), cost, userID, 2);
			if (bookingID == -2)
				System.out.println("Booking failed.");
			else if (bookingID == -1)
				System.out.println("Sorry, booking already closed.");
			else
				System.out.println("Your booking is in waiting. Booking ID: " + bookingID);
		} else {
			new Repository().bookCargoShip(ship.get(choice).getShipID(), cost, userID, 1);
			if (bookingID == -2)
				System.out.println("Booking Falied.");
			else if (bookingID == -1)
				System.out.println("Sorry, booking already closed.");
			else
				System.out.println("Booking confirmed. Booking ID: " + bookingID);
		}
	}

	public void getBookingStatus() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Booking ID: ");
		int bookingID = sc.nextInt();
		CargoBooking cargobooking = new Repository().cargoBookingStatus(bookingID);

		if (cargobooking == null) {
			System.out.println(
					"An error has occured. Either the bookingID does not exits or there was an error communicating with the database.");
			return;
		}

		System.out.println("Booking Details: ");
		System.out.println("Booking ID: " + cargobooking.getBookingID());
		System.out.println("Ship ID: " + cargobooking.getShipID());
		System.out.println("userID: " + cargobooking.getUserID());
		System.out.println("Cost: " + cargobooking.getCost());
		// System.out.println("\n");//PRINT BOOKING ID
		if (cargobooking.getStatusFlag() == 1) {
			System.out.println("Confirmed");
		} else if (cargobooking.getStatusFlag() == 2) {
			System.out.println("Waiting");
		}
	}

	public void cancelBooking() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Booking ID for Cancellation: ");
		int bookingID = sc.nextInt();
		//
		Repository rep = new Repository();
		if (rep.cancelCargoBoooking(bookingID)) {
			System.out.println("Booking Cancelled");
		} else {
			System.out.println("Booking Not Cancelled");
		}

	}

	public void listBookings(int userID) {
		ArrayList<CargoBooking> list = new Repository().listAllCargoBooking(userID);
		for (CargoBooking cargoBooking : list) {
			System.out.println("BookingID: " + cargoBooking.getBookingID());
			System.out.println("Cost: " + cargoBooking.getCost());
			System.out.println("Capacity: " + cargoBooking.getCapacity());
			System.out.println("ShipID: " + cargoBooking.getShipID());
			System.out.println("UserID: " + cargoBooking.getUserID());
			switch (cargoBooking.getStatusFlag()) {
				case 0: {
					System.out.println("Status: Past");
					break;
				}
				case 1: {
					System.out.println("Status: Active ");
					break;
				}
				case 2: {
					System.out.println("Status: Waiting");
					break;
				}
				case 3: {
					System.out.println("Status: Cancelled");
				}

			}
		}
	}
}
