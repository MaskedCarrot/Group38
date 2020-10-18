package ship;
import java.time.*;

abstract class Ship
{
	protected int shipID;
	protected String from;
	protected String to;
	protected LocalTime departureTime;
	protected LocalTime arrivalTime;

	public abstract void addShip();
	public abstract void searchShipsByDepartureTime();
	public abstract void searchShipsByArrivalTime();
	public abstract void searchShipsBySource();
	public abstract void searchShipsByDestination();
}
