package tycoon;

public class DepartureEvent extends Event {

	private final Location source;
	private final Location target;
	private final Transport transport;
	
	private final Cargo cargo;
	
	public DepartureEvent(Location source, Location target, int time, Transport transport, Cargo cargo) {
		super(time);
		this.source = source;
		this.target = target;
		this.transport=transport;
		this.cargo = cargo;
	}
	
	@Override
	public String toString() {
		String start = String.format("{\"event\": \"DEPART\", \"time\":%d, \"transport_id\":%d, \"kind\":\"%s\", \"location\":\"%s\", \"destination\":\"%s\"", getTime(),transport.getId(),transport,source,target);
		String end;
		if (cargo == null) {
			end = "}";
		} else {
			end = String.format(", \"cargo\":[%s]}", cargo);
		}
		return start+end;
	}

}
