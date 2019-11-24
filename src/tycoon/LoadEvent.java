package tycoon;

import java.util.Collection;

public class LoadEvent extends Event {

	private final Location location;
	private final Transport transport;
		
	public LoadEvent(Location location, int time, Transport transport, Collection<Cargo> cargos) {
		super(time,cargos);
		this.location = location;
		this.transport=transport;
	}
	
	@Override
	public String prefix() {
		return String.format("{\"event\": \"LOAD\", \"time\":%d, \"transport_id\":%d, \"kind\":\"%s\", \"location\":\"%s\", \"duration\":%d", getTime(),transport.getId(),transport,location,transport.getLoadDuration());
	}

}
