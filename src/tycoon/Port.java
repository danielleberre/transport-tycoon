package tycoon;

/**
 * A port is both a target and a source for containers.
 * 
 * @author leberre
 *
 */
public class Port extends LocationShippingSupport implements SourceLocation {
	private final Ship ship = new Ship();

	/**
	 * Create a new port.
	 * @param previous the previous Location to reach this Port.
	 * @param distance the distance between this Port and the previous Location.
	 */
	public Port(SourceLocation previous, int distance) {
		super(previous,distance);
	}

	@Override
	public int deliver(TargetLocation location, int time) {
		return  ship.ship(location,ship.nextAvailability(time));
	}
}
