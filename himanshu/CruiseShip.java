package himanshu;

import java.time.*;

public class CruiseShip extends Ship
{
	private int totalSeats;
	private int costPerPerson;
	private int bookedSeats;

	public void addShip()
	{
		getCruiseShipInput();
		Database DB = new Database();
		DB.insertCruiseShip(shipID, from, to, departureTime,
			arrivalTime, totalSeats, costPerPerson, bookedSeats);
	}

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
}
