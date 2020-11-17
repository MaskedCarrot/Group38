package group38.himanshu;
import group38.apoorv.*;
import java.util.Scanner;
import java.util.ArrayList;

public class CruiseShip extends Ship
{
	private int totalSeats;
	private int costPerPerson;
	private int bookedSeats;

	public CruiseShip() {
		super();
		totalSeats = -1;
		costPerPerson = -1;
		bookedSeats = -1;
	}
	
	public CruiseShip(int shipID, String from, String to, Long departureTime, Long arrivalTime, int totalSeats, int costPerPerson, int bookedSeats) {
		super(shipID, from, to, departureTime, arrivalTime);
		this.totalSeats = totalSeats;
		this.costPerPerson = costPerPerson;
		this.bookedSeats = bookedSeats;
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

		System.out.print("Enter total seats: ");
		totalSeats = sc.nextInt();
		System.out.print("Enter cost per person: ");
		costPerPerson = sc.nextInt();
		bookedSeats = 0;

		Repository repository = new Repository();
		if (repository.addCruiseShip(this))
			System.out.println("Ship added successfully.");
		else
			System.out.println("Could not add ship.");
	}

	public void removeShip() {
		System.out.print("Enter ship ID: ");
		Scanner sc = new Scanner(System.in);
		shipID = sc.nextInt();
		Repository repository = new Repository();
		if (repository.removeCruiseShip(shipID))
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

		System.out.print("Enter new total seats: ");
		totalSeats = sc.nextInt();
		System.out.print("Enter new cost per person: ");
		costPerPerson = sc.nextInt();
		bookedSeats = 0;

		Repository repository = new Repository();
		if (repository.updateCruiseShip(this))
			System.out.println("Ship updated successfully.");
		else
			System.out.println("Could not update ship.");
	}

	public ArrayList<CruiseShip> listShips() {
		System.out.print("Enter the starting place: ");
		Scanner sc = new Scanner(System.in);
		String from = sc.nextLine();
		System.out.print("Enter the destination: ");
		String to = sc.nextLine();

		Arraylist<CruiseShip> ship = new Repository().listAllCruiseShips(from, to);
		if (ship.isEmpty()) {
			System.out.println("No ships found.");
			return ship;
		}

		for (int i = 0; i < ship.size(); ++i) {
			System.out.println((i + 1) + ". Source: " + ship[i].getFrom());
			System.out.println("Destination: " + ship[i].getTo());
			System.out.println("Departure time: " + ship[i].getDepartureTime());
			System.out.println("Arrival time: " + ship[i].getArrivalTime());
			System.out.println("Cost per person: " + ship[i].getCostPerPerson());
		}

		return ship;
	}

	public int getTotalSeats() {
		return totalSeats;
	}
	
	public int getCostPerPerson() {
		return costPerPerson;
	}
	
	public int getBookedSeats() {
		return bookedSeats;
	}
/*
	public void searchShipsByDepartureTime()
	{
		System.out.println("Enter departure time: ");
		LocalTime time = getTimeInput();
		Database DB = new Database();
		DB.searchCruiseShipsByDepartureTime(time);
	}

	public void searchShipsByArrivalTime()
	{
		System.out.println("Enter arrival time: ");
		LocalTime time = getTimeInput();
		Database DB = new Database();
		DB.searchCruiseShipsByArrivalTime(time);
	}

	public void searchShipsBySource()
	{
		System.out.println("Enter source: ");
		String location = getLocationInput();
		Database DB = new Database();
		DB.searchCruiseShipsBySource(location);
	}

	public void searchShipsByDestination()
	{
		System.out.println("Enter destination: ");
		String location = getLocationInput();
		Database DB = new Database();
		DB.searchCruiseShipsByDestination(location);
	}
	*/
}
