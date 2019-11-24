package tycoon;

/**
 * A Truck is for the moment a simple transport.
 * 
 * It may change later on.
 * 
 * @author leberre
 *
 */
public class Truck extends Transport {

	public Truck() {
		super(0,1);
	}

	@Override
	public String toString() {
		return "TRUCK";
	}
}
