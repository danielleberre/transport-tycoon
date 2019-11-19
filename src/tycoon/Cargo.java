package tycoon;

public class Cargo {

	private static int nbObjects=0;
	private final int id = nbObjects++;
	
	private final Location source;
	private final Location target;
	
	public Cargo(Location source, Location target) {
		this.source = source;
		this.target = target;
	}
	
	public int getId() {
		return id;
	}
	
	public Location getTarget() {
		return target;
	}
	
	@Override
	public String toString() {
		return String.format("{\"cargo_id\": %d, \"destination\":\"%s\", \"origin\":\"%s\"}",id, target,source);
	}
}
