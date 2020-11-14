package himanshu;
import java.util.Scanner;
import apoorv.*;

// import java.time.*;

public class CargoShip extends Ship
{
	private int capacity;
	private int chargesPerTonne;
	private int bookedCapacity;

	public CargoShip() {
		super();
		capacity = -1;
		chargesPerTonne = -1;
		bookedCapacity = -1;
	}
	
	public CargoShip(int shipID, String from, String to, Long departureTime, Long arrivalTime, int capacity, int chargesPerTonne, int bookedCapacity) {
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
	/*
	public void addShip()
	{
		//getCargoShipInput(); 
		Database DB = new Database();
		DB.insertCargoShip(shipID, from, to, departureTime,
			arrivalTime, capacity, chargesPerTonne, bookedCapacity);
	}

	public void searchShipsByDepartureTime()
	{
		System.out.println("Enter departure time: ");
		LocalTime time = getTimeInput();
		Database DB = new Database();
		DB.searchCargoShipsByDepartureTime(time);
	}

	public void searchShipsByArrivalTime()
	{
		System.out.println("Enter arrival time: ");
		LocalTime time = getTimeInput();
		Database DB = new Database();
		DB.searchCargoShipsByArrivalTime(time);
	}

	public void searchShipsBySource()
	{
		System.out.println("Enter source: ");
		String location = getLocationInput();
		Database DB = new Database();
		DB.searchCargoShipsBySource(location);
	}

	public void searchShipsByDestination()
	{
		System.out.println("Enter destination: ");
		String location = getLocationInput();
		Database DB = new Database();
		DB.searchCargoShipsByDestination(location);
	}
	*/
}
