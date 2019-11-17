package tycoon;

/**
 * A Location represents the various places where the containers can be (Factory, Warehouse, Port).
 * 
 * @author leberre
 *
 */
public interface Location {

	/**
	 * Provide the distance to it's previous location.
	 * @return
	 */
	int distance();
	
}
