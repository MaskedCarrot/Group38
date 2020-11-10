package himanshu;

// import java.time.*;

public class CargoShip extends Ship
{
	private int capacity;
	private int chargesPerTonne;
	private int bookedCapacity;

	CargoShip() {
		super();
		capacity = -1;
		chargesPerTonne = -1;
		bookedCapacity = -1;
	}
	
	CargoShip(int shipID, String from, String to, Long departureTime, Long arrivalTime, int capacity, int chargesPerTonne, int bookedCapacity) {
		super(shipID, from, to, departureTime, arrivalTime);
		this.capacity = capacity;
		this.chargesPerTonne = chargesPerTonne;
		this.bookedCapacity = bookedCapacity;
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
