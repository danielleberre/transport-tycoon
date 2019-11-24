package tycoon;

import java.util.Collection;

public abstract class Event {

	private final int time;
	
	private Collection<Cargo> cargos;
	
	public Event(int time, Collection<Cargo> cargos) {
		this.time = time;
		this.cargos = cargos;
	}
	
	public int getTime() {
		return time;
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
			stb.deleteCharAt(stb.length()-1);
			stb.append("]}");
		}
		return stb.toString();
	}
}
