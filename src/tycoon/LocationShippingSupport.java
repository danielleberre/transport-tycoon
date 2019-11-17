package tycoon;

/**
 * Represent a location that can be used as target to ship containers.
 * 
 * @author leberre
 *
 */
public abstract class LocationShippingSupport implements Location, TargetLocation {

	private final int distance;
	private final SourceLocation previous;

	
	public LocationShippingSupport(SourceLocation previous, int distance) {
		this.previous = previous;
		this.distance = distance;
	}

	@Override
	public int distance() {
		return distance;
	}

	@Override
	public int shipFrom(SourceLocation location, int time) {
		if (location != previous) {
			// TODO fix the cast
			return  shipFrom(previous,location.deliver((TargetLocation)previous,time));
		}
		return location.deliver(this,time);
	}

}
