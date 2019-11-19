package tycoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class EventManager {

	private static final List<Event> events = new ArrayList<>();

	public static void addEvent(Event e) {
		events.add(e);
	}
	
	public static Stream<Event> events() {
		Collections.sort(events,(e1,e2)->e1.getTime()-e2.getTime());
		return events.stream();
	}
}
