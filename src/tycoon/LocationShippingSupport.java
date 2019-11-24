package tycoon;

import java.util.Collection;
import java.util.List;

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
	public int shipFrom(Location location, int time, Transport transport, Collection<Cargo> cargos) {
		int arrivalTime;
		if (location != previous) {
			arrivalTime = shipFrom(previous, location.deliver(previous, time, transport, cargos), transport, cargos);
		} else {
			arrivalTime = previous.deliver(this, time, transport, cargos);
		}
		return arrivalTime;
	}

	@Override
	public void onArrival(int time, Transport transport, Collection<Cargo> cargos) {
		EventManager.addEvent(new ArrivalEvent(this, time, transport, cargos,()-> {}));
		if (!cargos.isEmpty()) {
			EventManager.addEvent(new UnloadEvent(this, time, transport, cargos,()-> {}));
		}
	}
}
