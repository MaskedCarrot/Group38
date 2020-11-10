package himanshu;

public class CruiseShip extends Ship
{
	private int totalSeats;
	private int costPerPerson;
	private int bookedSeats;

	CruiseShip() {
		super();
		totalSeats = -1;
		costPerPerson = -1;
		bookedSeats = -1;
	}
	
	CruiseShip(int shipID, String from, String to, Long departureTime, Long arrivalTime, int totalSeats, int costPerPerson, int bookedSeats) {
		super(shipID, from, to, departureTime, arrivalTime);
		totalSeats = this.totalSeats;
		costPerPerson = this.costPerPerson;
		bookedSeats = this.bookedSeats;
	}

	public void addShip() {
		System.out.println()
		Database DB = new Database();
		DB.insertCruiseShip(shipID, from, to, departureTime,
			arrivalTime, totalSeats, costPerPerson, bookedSeats);
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
