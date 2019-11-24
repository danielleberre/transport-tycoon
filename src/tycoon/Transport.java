package tycoon;

import java.util.Collection;
import java.util.List;

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
	
	private final int loadDuration;
	
	private final int capacity;
	
	private int currentLoad=0;
	
	protected Transport(int loadDuration, int capacity) {
		this.loadDuration = loadDuration;
		this.capacity = capacity;
	}
	
	public int getId() {
		return id;
	}
	
	/**
	 * Compute when the transport will be available for shipping the next container.
	 * @param time the current time
	 * @return the time when the transport will be ready for handling a container.
	 */
	public int nextAvailability(int time) {
		if (nextAvailability<=time) {
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
	public int ship(Location location, int time, Collection<Cargo> cargos) {
		assert cargos.size() <= capacity;
		this.nextAvailability = time + 2 * (location.distance()+loadDuration);
		int arrivalTime = time + location.distance();
		location.onArrival(arrivalTime, this, cargos);
		return arrivalTime;
	}
	
	public void goBack(Location location, int arrivalTime) {
		currentLoad=0;
		location.onArrival(arrivalTime, this, List.of());
	}

	public int getLoadDuration() {
		return loadDuration;
	}
	
	public int getCapacity() {
		return capacity;
	}
}
