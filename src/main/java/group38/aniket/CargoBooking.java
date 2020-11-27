package group38.aniket;

import java.util.ArrayList;
import java.util.Scanner;
import group38.himanshu.CargoShip;
import group38.apoorv.*;

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
			bookingID = new Repository().bookCargoShip(ship.get(choice).getShipID(), cost, userID, 1);
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
		CargoBooking cargoBooking = repository.cargoBookingStatus(bookingID);

		if (cargoBooking == null) {
			System.out.println(
					"An error has occured. Either the bookingID does not exits or there was an error communicating with the database.");
			return;
		}

		System.out.println("Booking Details");
		System.out.println("Booking ID: " + cargoBooking.getBookingID());
		System.out.println("Ship ID: " + cargoBooking.getShipID());
		System.out.println("User ID: " + cargoBooking.getUserID());
		System.out.println("Capacity booked: " + cargoBooking.getCapacity());
		System.out.println("Cost: " + cargoBooking.getCost());

		/*
		 * statusFlag flags 0 is confirmed past 1 is confirm acitve 2 is wainting 3 is
		 * cancelled
		 */
		if (cargoBooking.getStatusFlag() == 0)
			System.out.println("Past confirmed booking");
		else if (cargoBooking.getStatusFlag() == 1)
			System.out.println("Confirmed booking");
		else if (cargoBooking.getStatusFlag() == 2)
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

		CargoShip cargoShip = repository.cargoBookingFullStatus(bookingID);
		System.out.println("Booking Details");
		System.out.println("Booking ID: " + cargoBooking.getBookingID());
		System.out.println("User ID: " + cargoBooking.getUserID());
		System.out.println("Ship ID: " + cargoBooking.getShipID());
		System.out.println("Source: " + cargoShip.getFrom());
		System.out.println("Destination: " + cargoShip.getTo());

		Util u = new Util();
		String departureTime = u.convertMinutesToTime(cargoShip.getDepartureTime());
		System.out.println("Departure time: " + departureTime);
		String arrivalTime = u.convertMinutesToTime(cargoShip.getArrivalTime());
		System.out.println("Arrival time: " + arrivalTime);
		System.out.println("Capacity booked: " + cargoBooking.getCapacity());
		System.out.println("Cost: " + cargoBooking.getCost());
	}

	public void cancelBooking() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Booking ID for Cancellation: ");
		int bookingID = sc.nextInt();
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
