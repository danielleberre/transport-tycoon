package tycoon;

/**
 * Source for shipping containers.
 * 
 * @author leberre
 *
 */
public interface SourceLocation {

	/**
	 * Deliver a container to a given target at a given time.
	 * @param target the target location
	 * @param time the current time
	 * @return the time of arrival of the container
	 */
	int deliver(TargetLocation target, int time);
}
