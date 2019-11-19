package tycoon;

public class ArrivalEvent extends Event {

	private final Location target;
	private final Transport transport;
	
	private final Cargo cargo;
	
	public ArrivalEvent(Location target, int time, Transport transport, Cargo cargo) {
		super(time);
		this.target = target;
		this.transport=transport;
		this.cargo = cargo;
	}
	
	@Override
	public String toString() {
		String start = String.format("{\"event\": \"ARRIVE\", \"time\":%d, \"transport_id\":%d, \"kind\":\"%s\", \"location\":\"%s\"", getTime(),transport.getId(),transport,target);
		String end;
		if (cargo == null) {
			end = "}";
		} else {
			end = String.format(", \"cargo\":[%s]}", cargo);
		}
		return start+end;
	}

}
