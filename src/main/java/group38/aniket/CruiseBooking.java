package group38.aniket;

import java.util.ArrayList;
import java.util.Scanner;
import group38.apoorv.*;
import group38.himanshu.*;

public class CruiseBooking extends Booking {
	private int cost;
	private int seats;

	public CruiseBooking() {
		super();
		seats = -1;
		cost = -1;
	}

	public CruiseBooking(int bookingID, int shipID, int userID, int seats, int cost, int statusFlag) {
		super(bookingID, shipID, userID, statusFlag);
		this.seats = seats;
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public void book(int userID) {
		ArrayList<CruiseShip> ship = new CruiseShip().listShips();
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
		cost = seats * ship.get(choice).getCostPerPerson();

		// -1: late booking
		// -2: failed booking
		/*
		 * statusFlag flags 0 is confirmed past 1 is confirm acitve 2 is wainting 3 is
		 * cancelled
		 */
		if (ship.get(choice).getBookedSeats() + seats > ship.get(choice).getTotalSeats()) {
			bookingID = new Repository().bookCruiseShip(ship.get(choice).getShipID(), cost, userID, 2);
			if (bookingID == -2)
				System.out.println("Booking failed.");
			else if (bookingID == -1)
				System.out.println("Sorry, booking already closed.");
			else
				System.out.println("Your booking is in waiting. Booking ID: " + bookingID);
		} else {
			bookingID = new Repository().bookCruiseShip(ship.get(choice).getShipID(), cost, userID, 1);
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
		System.out.print("Enter booking ID: ");
		int bookingID = sc.nextInt();
		Repository repository = new Repository();
		CruiseBooking cruiseBooking = repository.cruiseBookingStatus(bookingID);

		if (cruiseBooking == null) {
			System.out.println(
					"An error has occured. Either the bookingID does not exits or there was an error communicating with the database.");
			return;
		}

		System.out.println("Booking Details");
		System.out.println("Booking ID: " + cruiseBooking.getBookingID());
		System.out.println("Ship ID: " + cruiseBooking.getShipID());
		System.out.println("User ID: " + cruiseBooking.getUserID());
		System.out.println("Seats booked: " + cruiseBooking.getSeats());
		System.out.println("Cost: " + cruiseBooking.getCost());

		/*
		 * statusFlag flags 0 is confirmed past 1 is confirm acitve 2 is wainting 3 is
		 * cancelled
		 */
		if (cruiseBooking.getStatusFlag() == 0)
			System.out.println("Past confirmed booking");
		else if (cruiseBooking.getStatusFlag() == 1)
			System.out.println("Confirmed booking");
		else if (cruiseBooking.getStatusFlag() == 2)
			System.out.println("Waiting");
		else // statusFlag 3
			System.out.println("Cancelled booking");

		System.out.print("Enter 1 to view more details, 0 to return: ");
		int choice = sc.nextInt();
		while (choice != 0 && choice != 1) {
			System.out.println("Please enter a correct choice.");
			System.out.print("Enter 1 to view more details, 0 to return: ");
			choice = sc.nextInt();
		}
		if (choice == 0)
			return;

		CruiseShip cruiseShip = repository.cruiseBookingFullStatus(bookingID);
		System.out.println("Booking Details");
		System.out.println("Booking ID: " + cruiseBooking.getBookingID());
		System.out.println("User ID: " + cruiseBooking.getUserID());
		System.out.println("Ship ID: " + cruiseBooking.getShipID());
		System.out.println("Source: " + cruiseShip.getFrom());
		System.out.println("Destination: " + cruiseShip.getTo());

		Util u = new Util();
		String departureTime = u.convertMinutesToTime(cruiseShip.getDepartureTime());
		System.out.println("Departure time: " + departureTime);
		String arrivalTime = u.convertMinutesToTime(cruiseShip.getArrivalTime());
		System.out.println("Arrival time: " + arrivalTime);
		System.out.println("Seats booked: " + cruiseBooking.getSeats());
		System.out.println("Cost: " + cruiseBooking.getCost());
	}

	public void cancelBooking() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Booking ID for Cancellation: ");
		int bookingID = sc.nextInt();
		Repository rep = new Repository();
		if (rep.cancelCruiseBoooking(bookingID)) {
			System.out.println("Booking Cancelled");
		} else {
			System.out.println("Booking Not Cancelled");
		}
	}

	public void listBookings(int userID) {
		ArrayList<CruiseBooking> list = new Repository().listAllCruiseBooking(userID);
		for (CruiseBooking cruiseBooking : list) {
			System.out.println("BookingID: " + cruiseBooking.getBookingID());
			System.out.println("Cost: " + cruiseBooking.getCost());
			System.out.println("Seats: " + cruiseBooking.getSeats());
			System.out.println("ShipID: " + cruiseBooking.getShipID());
			System.out.println("UserID: " + cruiseBooking.getUserID());
			switch (cruiseBooking.getStatusFlag()) {
				case 0: {
					System.out.println("Status: Past confirmed booking");
					break;
				}
				case 1: {
		 			System.out.println("Status: Confirmed booking");
					break;
				}
				case 2: {
					System.out.println("Status: Waiting");
					break;
				}
				case 3: {
					System.out.println("Status: Cancelled booking");
				}

			}

		}

	}

}
