package tycoon;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
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
	private Warehouse A;
	/**
	 * Warehouse B, linked to the factory.
	 */
	private Warehouse B;
	/**
	 * The containers to ship, represented by their target warehouses.
	 */
	private final Deque<Cargo> containers = new LinkedList<>();
	private int time = 0;

	public Factory() {
	}

	public void init(Warehouse a, Warehouse b) {
		this.A = a;
		this.B = b;
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
		return cargo.getTarget().shipFrom(this, this.time, transport, List.of(cargo));
	}

	@Override
	public int deliver(Location location, int time, Transport transport, Collection<Cargo> cargos) {
		EventManager.addEvent(new LoadEvent(this, time, transport, cargos,()-> {}));
		EventManager.addEvent(new DepartureEvent(this, location, time, transport, cargos,()-> location.onArrival(time+location.distance(), transport, cargos)));
		int shipTime = transport.ship(location, time,cargos);
		EventManager.addEvent(new DepartureEvent(location, this, shipTime, transport, List.of(),()-> transport.goBack(this, shipTime+location.distance())));
		return shipTime;
	}

	/**
	 * Ship all containers represented by the a String of A and B corresponding to  the warehouses where
	 * each container should ship.
	 * 
	 * @param locations a String of A and B (e.g. "ABBA")
	 * @return the time of arrival of the last container to its warehouse.
	 */
	public int shipAll(String locations) {
		this.containers.addAll(locations.chars().mapToObj(this::letterToCargo).collect(Collectors.toList()));
		int spent = 0;
		while (!containers.isEmpty()) {
			spent = Math.max(spent, ship());
			EventManager.fireNextEvents();
		}
		while(!EventManager.isEmpty()) {
			EventManager.fireNextEvents();
		}
		return spent;
	}

	@Override
	public String toString() {
		return "FACTORY";
	}

	@Override
	public int shipFrom(Location source, int time, Transport transport, Collection<Cargo> cargos) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int distance() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onArrival(int time, Transport transport, Collection<Cargo> cargos) {
		EventManager.addEvent(new ArrivalEvent(this, time, transport, cargos,()-> {}));
	}
}
