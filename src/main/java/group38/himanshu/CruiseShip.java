package himanshu;
import apoorv.*;
import java.util.Scanner;

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
		totalSeats = this.totalSeats;
		costPerPerson = this.costPerPerson;
		bookedSeats = this.bookedSeats;
	}

	public void addShip() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter source: ");
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
		boolean success = repository.addCruiseShip(this);
		if (success)
			System.out.println("Ship added successfully.");
		else
			System.out.println("Could not add ship.");
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
