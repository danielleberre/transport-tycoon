package tycoon;

public abstract class Event {

	private final int time;
	
	public Event(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}
}
