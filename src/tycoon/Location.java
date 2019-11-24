package tycoon;

import java.util.Collection;

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
	int shipFrom(Location source, int time, Transport transport, Collection<Cargo> cargos);
	
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
	int deliver(Location target, int time, Transport transport, Collection<Cargo> cargos);
	
	
	/**
	 * Callback method called when a Cargo arrive at this location. 
	 * 
	 * @param time the arrival time
	 * @param transport the transport conveying the cargo	
	 * @param cargo the cargo itself
	 */
	void onArrival(int time, Transport transport, Collection<Cargo> cargos);
}
