package tycoon;

import java.util.Collection;

public class UnloadEvent extends Event {

	private final Location target;
	private final Transport transport;
		
	public UnloadEvent(Location target, int time, Transport transport, Collection<Cargo> cargos,Runnable callback) {
		super(2,time,cargos,callback);
		this.target = target;
		this.transport=transport;
	}
	
	@Override
	public String prefix() {
		return String.format("{\"event\": \"UNLOAD\", \"time\":%d, \"transport_id\":%d, \"kind\":\"%s\", \"location\":\"%s\", \"duration\":%d", getTime(),transport.getId(),transport,target,transport.getLoadDuration());
	}

}
