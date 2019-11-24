package tycoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * This class has the responsibility to register events and to fire some
 * callback methods at some time point in the future.
 * 
 * @author leberre
 *
 */
public class EventManager {

	private static final List<Event> events = new ArrayList<>();

	private static final Map<Integer, List<Event>> runnables = new TreeMap<>();

	private static int currentTime;

	private static boolean verbose = true;

	public static void setVerbosity(boolean value) {
		verbose = value;
	}

	public static void addEvent(Event e) {
		events.add(e);
		List<Event> runnable = runnables.get(e.getTime());
		if (runnable == null) {
			runnable = new ArrayList<>();
			runnables.put(e.getTime(), runnable);
		}
		runnable.add(e);
	}

	public static void fireNextEvents() {
		if (!runnables.isEmpty()) {
			Entry<Integer, List<Event>> entry = runnables.entrySet().iterator().next();
			currentTime = entry.getKey();
			List<Event> events = entry.getValue();
			Collections.sort(events);
			for (Event e : events) {
				if (verbose) {
					System.out.println(e);
				}
				e.getCallback().run();
			}
			runnables.remove(currentTime);
		}
	}

	public static int getTime() {
		return currentTime;
	}

	public static Stream<Event> events() {
		Collections.sort(events, (e1, e2) -> e1.getTime() - e2.getTime());
		return events.stream();
	}

	public static boolean isEmpty() {
		return runnables.isEmpty();
	}
}
