package tycoon;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * The factory is the source of the containers.
 * 
 * It contains the topology of the network to reach the warehouses with the distances.
 * It is expected that there is a single path going from each warehouse to the factory.
 * 
 * @author leberre
 *
 */
public class Factory implements SourceLocation {

	/**
	 * Warehouse A, linked to a port.
	 */
	private final Warehouse A = new Warehouse(new Port(this, 1), 4);
	/**
	 * Warehouse B, linked to the factory.
	 */
	private final Warehouse B = new Warehouse(this, 5);
	/**
	 * The containers to ship, represented by their target warehouses.
	 */
	private final Deque<Warehouse> containers = new LinkedList<>();
	/**
	 * The first truck of the factory.
	 */
	private final Truck t1 = new Truck();
	/**
	 * The second truck of the factory.
	 */
	private final Truck t2 = new Truck();

	private int time = 0;

	/**
	 * Create a factory from a String of A and B representing the warehouses where each container should ship.
	 * 
	 * @param locations a String of A and B (e.g. "ABBA").
	 */
	public Factory(String locations) {
		this.containers.addAll(locations.chars().mapToObj(this::letterToWarehouse).collect(Collectors.toList()));
	}

	private Warehouse letterToWarehouse(int c) {
		if (c=='A') {
			return A;
		}
		if (c=='B') {
			return B;
		}
		throw new IllegalArgumentException("Only A and B letters are allowed!");
	}
	
	
	/**
	 * Ship one container.
	 * 
	 * It is expected that at least one container must ship.
	 * 
	 * @return the time of arrival of the container to its warehouse.
	 */
	private int ship() {
		assert !containers.isEmpty(); 
		return this.containers.removeFirst().shipFrom(this, this.time);
	}

	@Override
	public int distance() {
		return 0;
	}

	@Override
	public int deliver(TargetLocation location, int time) {
		// wait for the first available truck
		int wait1 = t1.nextAvailability(time);
		int wait2 = t2.nextAvailability(time);
		int firstShip;
		if (wait1 > wait2) {
			this.time = wait2;
			firstShip = t2.ship(location, wait2);
		} else if (wait1 < wait2) {
			this.time = wait1;
			firstShip = t1.ship(location, wait1);
		} else {
			this.time = wait1;
			firstShip = t1.ship(location, wait1);
			t2.ship(this, wait1);
		}
		return firstShip;
	}

	/**
	 * Ship all containers.
	 * 
	 * @return the time of arrival of the last container to its warehouse.
	 */
	public int shipAll() {
		int spent = 0;
		while (!containers.isEmpty()) {
			spent = Math.max(spent, ship());
		}
		return spent;
	}
}
