package tycoon;

/**
 * Represent a location that can be used as target to ship containers.
 * 
 * @author leberre
 *
 */
public abstract class LocationShippingSupport implements Location {

	private final int distance;
	private final Location previous;

	
	public LocationShippingSupport(Location previous, int distance) {
		this.previous = previous;
		this.distance = distance;
	}

	@Override
	public int distance() {
		return distance;
	}

	@Override
	public int shipFrom(Location location, int time, Transport transport, Cargo cargo) {
		int arrivalTime;
		if (location != previous) {
			arrivalTime =  shipFrom(previous,location.deliver(previous,time,transport,cargo),transport,cargo);
		} else {
		    arrivalTime = location.deliver(this,time,transport,cargo);
		}
		return arrivalTime;
	}
}
