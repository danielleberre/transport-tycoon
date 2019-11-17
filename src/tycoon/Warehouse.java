package tycoon;

/**
 * Warehouse are target for containers.
 * 
 * @author leberre
 *
 */
public class Warehouse extends LocationShippingSupport {

	/**
	 * Create a warehouse.
	 * 
	 * @param previous the previous Location to reach this warehouse.
	 * @param distance the distance between the warehouse and the previous location.
	 */
	public Warehouse(SourceLocation previous, int distance) {
		super(previous, distance);
	}
}
