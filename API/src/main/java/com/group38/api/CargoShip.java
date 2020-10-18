package ship;
import java.time.*;

class CargoShip extends Ship
{
	private int capacity;
	private int chargesPerTonne;
	private int bookedCapacity;

	public void addShip()
	{
		getCargoShipInput();
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
}
