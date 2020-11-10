package himanshu;

public abstract class Ship
{
	private int shipId;
	private String from;
	private String to;
	private Long departureTime;
	private Long arrivalTime;

	Ship() {
		shipID = -1;
		from = "";
		to = "";
		departureTime = -1;
		arrivalTime = -1;
	}

	Ship(int shipID, String from, String to, Long departureTime, Long arrivalTime) {
		this.shipID = shipID;
		this.from = from;
		this.to = to;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	public int getShipId() {
		return shipId;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public Long getDepartureTime() {
		return departureTime;
	}

	public Long getArrivalTime() {
		return arrivalTime;
	}

	public abstract void addShip();
	// public abstract void searchShipsByDepartureTime();
	// public abstract void searchShipsByArrivalTime();
	// public abstract void searchShipsBySource();
	// public abstract void searchShipsByDestination();
}
