package tycoon;

/**
 * A port is both a target and a source for containers.
 * 
 * @author leberre
 *
 */
public class Port extends LocationShippingSupport {
	private final Ship ship = new Ship();

	/**
	 * Create a new port.
	 * 
	 * @param previous the previous Location to reach this Port.
	 * @param distance the distance between this Port and the previous Location.
	 */
	public Port(Location previous, int distance) {
		super(previous, distance);
	}

	@Override
	public int deliver(Location location, int time, Transport transport, Cargo cargo) {
		if (cargo == null) {
			EventManager.addEvent(new DepartureEvent(this, location, time, transport, null));
			int arrivalTime = time+distance();
			EventManager.addEvent(new ArrivalEvent(location, arrivalTime, transport, null));
			return arrivalTime;
		} else {
			int shipTime = ship.nextAvailability(time);
			EventManager.addEvent(new DepartureEvent(this, location, shipTime, ship, cargo));
			int arrivalTime = ship.ship(location, shipTime);
			EventManager.addEvent(new ArrivalEvent(location, arrivalTime, ship, cargo));
			location.deliver(this, arrivalTime, ship, null);
			return arrivalTime;
		}

	}

	@Override
	public String toString() {
		return "PORT";
	}
}
