package tycoon;

import java.util.Collection;

public class ArrivalEvent extends Event {

	private final Location target;
	private final Transport transport;
		
	public ArrivalEvent(Location target, int time, Transport transport, Collection<Cargo> cargos) {
		super(time,cargos);
		this.target = target;
		this.transport=transport;
	}
	
	@Override
	public String prefix() {
		return String.format("{\"event\": \"ARRIVE\", \"time\":%d, \"transport_id\":%d, \"kind\":\"%s\", \"location\":\"%s\"", getTime(),transport.getId(),transport,target);
	}

}
