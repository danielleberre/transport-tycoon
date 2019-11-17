package tycoon;

/**
 * Target for shipping containers.
 * 
 * @author leberre
 *
 */
public interface TargetLocation extends Location {
	
	/**
	 * Handle the delivery of a container to this location.
	 * @param source the source of the container
	 * @param time the current time
	 * @return the time of arrival of the container
	 */
	int shipFrom(SourceLocation source, int time);
}
