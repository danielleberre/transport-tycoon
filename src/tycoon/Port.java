package tycoon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * A port is both a target and a source for containers.
 * 
 * @author leberre
 *
 */
public class Port extends LocationShippingSupport {
	private final Ship ship;

	private final Deque<Cargo> containers = new LinkedList<>();

	private int lastShip = -1;

	/**
	 * Create a new port.
	 * 
	 * @param previous the previous Location to reach this Port.
	 * @param distance the distance between this Port and the previous Location.
	 * @param duration the duration of load/unload step for the ship
	 */
	public Port(Location previous, int distance, int duration) {
		super(previous, distance);
		ship = new Ship(duration);
	}

	@Override
	public int deliver(Location location, int time, Transport transport, Collection<Cargo> cargos) {
		assert !cargos.isEmpty();

		int shipTime;
		if (lastShip >= time) {
			shipTime = lastShip;
		} else {
			shipTime = ship.nextAvailability(time);
		}
		containers.addAll(cargos);
		// ship is departing now
		if (containers.size() <= ship.getCapacity()) {
			shipToDestination(location, shipTime);
		} else {
			throw new RuntimeException("Houston, we have a problem!");
		}
		return shipTime + location.distance() + 2 * ship.getLoadDuration();

	}

	private void shipToDestination(Location location, int shipTime) {
		if (lastShip < shipTime) {
			lastShip = shipTime;
			Collection<Cargo> cargos = new ArrayList<>();
			cargos.addAll(containers);
			EventManager.addEvent(new LoadEvent(this, shipTime, ship, cargos, () -> {
			}));
			EventManager.addEvent(new DepartureEvent(this, location, shipTime + ship.getLoadDuration(), ship,
					cargos, () -> location.onArrival(shipTime + ship.getLoadDuration() + location.distance(), ship, cargos)));
			int arrivalTime = ship.ship(location, shipTime + ship.getLoadDuration(), cargos);
			EventManager.addEvent(new DepartureEvent(location, this, arrivalTime + ship.getLoadDuration(), ship,
					List.of(), () -> ship.goBack(this, arrivalTime + ship.getLoadDuration() + location.distance())));
		}
		containers.clear();

	}

	@Override
	public void onArrival(int time, Transport transport, Collection<Cargo> cargos) {
		super.onArrival(time, transport, cargos);
		if (transport == ship && !containers.isEmpty()) {
			shipToDestination(containers.getFirst().getTarget(), time);

		}
	}

	@Override
	public String toString() {
		return "PORT";
	}
}
