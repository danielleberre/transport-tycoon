package tycoon;

/**
 * Warehouse are target for containers.
 * 
 * @author leberre
 *
 */
public class Warehouse extends LocationShippingSupport {

	private final String name;
	/**
	 * Create a warehouse.
	 * 
	 * @param previous the previous Location to reach this warehouse.
	 * @param distance the distance between the warehouse and the previous location.
	 */
	public Warehouse(String name, Location previous, int distance) {
		super(previous, distance);
		this.name = name;
	}
	
	@Override
	public int deliver(Location target, int time, Transport transport, Cargo cargo) {
		EventManager.addEvent(new DepartureEvent(this, target, time, transport, null));
		int arrivalTime = time+distance();
		EventManager.addEvent(new ArrivalEvent(target, arrivalTime, transport, null));
		return arrivalTime;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

