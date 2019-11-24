package tycoon;

import java.util.Collection;

public class DepartureEvent extends Event {

	private final Location source;
	private final Location target;
	private final Transport transport;
	
	public DepartureEvent(Location source, Location target, int time, Transport transport, Collection<Cargo> cargos) {
		super(time,cargos);
		this.source = source;
		this.target = target;
		this.transport=transport;
	}
	
	@Override
	public String prefix() {
		return String.format("{\"event\": \"DEPART\", \"time\":%d, \"transport_id\":%d, \"kind\":\"%s\", \"location\":\"%s\", \"destination\":\"%s\"", getTime(),transport.getId(),transport,source,target);
	}

}
