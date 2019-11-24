package tycoon;

/**
 * A ship is for the moment a simple transport.
 * 
 * It will change later (see second exercise).
 * 
 * @author leberre
 *
 */
public class Ship extends Transport {

	public Ship(int duration) {
		super(duration,4);
	}

	@Override
	public String toString() {
		return "SHIP";
	}
}
