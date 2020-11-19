package group38.himanshu;

import java.util.Scanner;
import group38.apoorv.*;
import java.util.ArrayList;

// import java.time.*;

public class CargoShip extends Ship {
	private int capacity;
	private int chargesPerTonne;
	private int bookedCapacity;

	public CargoShip() {
		super();
		capacity = -1;
		chargesPerTonne = -1;
		bookedCapacity = -1;
	}

	public CargoShip(int shipID, String from, String to, Long departureTime, Long arrivalTime, int capacity,
			int chargesPerTonne, int bookedCapacity) {
		super(shipID, from, to, departureTime, arrivalTime);
		this.capacity = capacity;
		this.chargesPerTonne = chargesPerTonne;
		this.bookedCapacity = bookedCapacity;
	}

	public void addShip() {
		System.out.print("Enter source: ");
		Scanner sc = new Scanner(System.in);
		from = sc.nextLine();
		System.out.print("Enter destination: ");
		to = sc.nextLine();

		System.out.print("Enter departure time in HH:MM format: ");
		String time = sc.nextLine();
		int hh = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
		int mm = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
		Util u = new Util();
		departureTime = u.convertTimeToMinutes(hh, mm);

		System.out.print("Enter arrival time in HH:MM format: ");
		time = sc.nextLine();
		hh = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
		mm = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
		arrivalTime = u.convertTimeToMinutes(hh, mm);

		System.out.print("Enter capacity of the ship (in tonnes): ");
		capacity = sc.nextInt();
		System.out.print("Enter charges per tonne: ");
		chargesPerTonne = sc.nextInt();
		bookedCapacity = 0;

		Repository repository = new Repository();
		if (repository.addCargoShip(this))
			System.out.println("Ship added successfully.");
		else
			System.out.println("Could not add ship.");
	}

	public void removeShip() {
		System.out.print("Enter ship ID: ");
		Scanner sc = new Scanner(System.in);
		shipID = sc.nextInt();
		Repository repository = new Repository();
		if (repository.removeCargoShip(shipID))
			System.out.println("Ship removed successfully.");
		else
			System.out.println("Could not remove ship.");
	}

	public void updateShip() {
		System.out.print("Enter ship ID: ");
		Scanner sc = new Scanner(System.in);
		shipID = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter new source: ");
		from = sc.nextLine();
		System.out.print("Enter new destination: ");
		to = sc.nextLine();

		System.out.print("Enter new departure time in HH:MM format: ");
		String time = sc.nextLine();
		int hh = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
		int mm = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
		Util u = new Util();
		departureTime = u.convertTimeToMinutes(hh, mm);

		System.out.print("Enter new arrival time in HH:MM format: ");
		time = sc.nextLine();
		hh = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
		mm = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
		arrivalTime = u.convertTimeToMinutes(hh, mm);

		System.out.print("Enter new capacity of the ship (in tonnes): ");
		capacity = sc.nextInt();
		System.out.print("Enter new charges per tonne: ");
		chargesPerTonne = sc.nextInt();
		bookedCapacity = 0;

		Repository repository = new Repository();
		if (repository.updateCargoShip(this))
			System.out.println("Ship updated successfully.");
		else
			System.out.println("Could not update ship.");
	}

	public int getCapacity() {
		return capacity;
	}

	public int getChargesPerTonne() {
		return chargesPerTonne;
	}

	public int getBookedCapacity() {
		return bookedCapacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setChargesPerTonne(int chargesPerTonne) {
		this.chargesPerTonne = chargesPerTonne;
	}

	public void setBookedCapacity(int bookedCapacity) {
		this.bookedCapacity = bookedCapacity;
	}

	public ArrayList<CargoShip> listShips() {
		System.out.print("Enter the starting place: ");
		Scanner sc = new Scanner(System.in);
		String from = sc.nextLine();
		from.toLowerCase();
		System.out.print("Enter the destination: ");
		String to = sc.nextLine();
		to.toLowerCase();

		ArrayList<CargoShip> ship = new Repository().listAllCargoShips(from, to);
		if (ship.isEmpty()) {
			System.out.println("No ships found.");
			return ship;
		}

		for (int i = 0; i < ship.size(); ++i) {
			System.out.println((i + 1) + ". Source: " + ship.get(i).getFrom());
			System.out.println("Destination: " + ship.get(i).getTo());
			System.out.println("Departure time: " + ship.get(i).getDepartureTime());
			System.out.println("Arrival time: " + ship.get(i).getArrivalTime());
			System.out.println("Charges per tonne: " + ship.get(i).getChargesPerTonne());
		}

		return ship;
	}
}
