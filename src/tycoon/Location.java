package tycoon;

/**
 * Target for shipping containers.
 * 
 * @author leberre
 *
 */
public interface Location {
	
	/**
	 * Handle the delivery of a container to this location.
	 * @param source the source of the container
	 * @param time the current time
	 * @return the time of arrival of the container
	 */
	int shipFrom(Location source, int time, Transport transport, Cargo cargo);
	
	/**
	 * Provide the distance to it's previous location.
	 * @return
	 */
	int distance();
	
	/**
	 * Deliver a container to a given target at a given time.
	 * @param target the target location
	 * @param time the current time
	 * @return the time of arrival of the container
	 */
	int deliver(Location target, int time, Transport transport, Cargo cargo);
}
