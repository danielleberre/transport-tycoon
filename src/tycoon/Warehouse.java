package tycoon;

import java.util.Collection;
import java.util.List;

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
	public int deliver(Location target, int time, Transport transport, Collection<Cargo> cargos) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String toString() {
		return name;
	}
}

