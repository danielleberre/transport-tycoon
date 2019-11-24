package tycoon;

import java.util.Collection;

public abstract class Event implements Comparable<Event> {

	private final int time;

	private Collection<Cargo> cargos;

	private Runnable callback;

	private int preference;

	public Event(int preference, int time, Collection<Cargo> cargos, Runnable callback) {
		this.preference = preference;
		this.time = time;
		this.cargos = cargos;
		this.callback = callback;
	}

	@Override
	public int compareTo(Event o) {
		if (time == o.time) {
			return preference - o.preference;
		}
		return time - o.time;
	}

	public int getTime() {
		return time;
	}

	public Runnable getCallback() {
		return callback;
	}

	protected abstract String prefix();

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append(prefix());
		if (cargos.isEmpty()) {
			stb.append("}");
		} else {
			stb.append(", \"cargo\":[");
			for (Cargo cargo : cargos) {
				stb.append(cargo);
				stb.append(",");
			}
			stb.deleteCharAt(stb.length() - 1);
			stb.append("]}");
		}
		return stb.toString();
	}
}
