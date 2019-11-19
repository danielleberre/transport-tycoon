package tycoon;

/**
 * A transport moves a single container from a Location to another one.
 * 
 * @author leberre
 *
 */
public abstract class Transport {
	private int nextAvailability = 0;

	private static int nbObjects = 0;
	
	private final int id = nbObjects++;
	
	public int getId() {
		return id;
	}
	
	/**
	 * Compute when the transport will be available for shipping the next container.
	 * @param time the current time
	 * @return the time when the transport will be ready for handling a container.
	 */
	public int nextAvailability(int time) {
		if (nextAvailability<time) {
			return time;
		}
		return nextAvailability;
	}

	/**
	 * Ship a container to a given Location.
	 * @param location the target Location
	 * @param time the current time
	 * @return the time of arrival of the container
	 */
	public int ship(Location location, int time) {
		this.nextAvailability = time + 2 * location.distance();
		return time + location.distance();
	}
}
