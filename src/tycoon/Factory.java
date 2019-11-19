package tycoon;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * The factory is the source of the containers.
 * 
 * It contains the topology of the network to reach the warehouses with the
 * distances. It is expected that there is a single path going from each
 * warehouse to the factory.
 * 
 * @author leberre
 *
 */
public class Factory implements Location {

	/**
	 * The first truck of the factory.
	 */
	private final Truck t1 = new Truck();
	/**
	 * The second truck of the factory.
	 */
	private final Truck t2 = new Truck();

	/**
	 * Warehouse A, linked to a port.
	 */
	private final Warehouse A = new Warehouse("A", new Port(this, 1), 4);
	/**
	 * Warehouse B, linked to the factory.
	 */
	private final Warehouse B = new Warehouse("B", this, 5);
	/**
	 * The containers to ship, represented by their target warehouses.
	 */
	private final Deque<Cargo> containers = new LinkedList<>();
	private int time = 0;

	/**
	 * Create a factory from a String of A and B representing the warehouses where
	 * each container should ship.
	 * 
	 * @param locations a String of A and B (e.g. "ABBA").
	 */
	public Factory(String locations) {
		this.containers.addAll(locations.chars().mapToObj(this::letterToCargo).collect(Collectors.toList()));
	}

	private Cargo letterToCargo(int c) {
		if (c == 'A') {
			return new Cargo(this, A);
		}
		if (c == 'B') {
			return new Cargo(this, B);
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
		Cargo cargo = this.containers.removeFirst();
		// wait for the first available truck
		Transport transport;
		int wait1 = t1.nextAvailability(time);
		int wait2 = t2.nextAvailability(time);
		if (wait1 > wait2) {
			this.time = wait2;
			transport = t2;
		} else {
			this.time = wait1;
			transport = t1;
		}
		return cargo.getTarget().shipFrom(this, this.time, transport, cargo);
	}

	@Override
	public int deliver(Location location, int time, Transport transport, Cargo cargo) {
		EventManager.addEvent(new DepartureEvent(this, location, this.time, transport, cargo));
		int firstShip = transport.ship(location, time);
		EventManager.addEvent(new ArrivalEvent(location, firstShip, transport, cargo));
		if (cargo != null) {
			location.deliver(this, firstShip, transport, null);
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

		EventManager.events().forEach(System.out::println);
		return spent;
	}

	@Override
	public String toString() {
		return "FACTORY";
	}

	@Override
	public int shipFrom(Location source, int time, Transport transport, Cargo cargo) {
		return time+source.distance();
	}

	@Override
	public int distance() {
		return 0;
	}
}
