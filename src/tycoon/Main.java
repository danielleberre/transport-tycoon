package tycoon;

public class Main {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Main <LOCATIONS>");
			return;
		}
		String locations = args[0];
		Factory factory = new Factory();
		Warehouse a = new Warehouse("A", new Port(factory, 1,1), 4);
		Warehouse b = new Warehouse("B", factory, 5);
		factory.init(a, b);
		int spent = factory.shipAll(locations);
		System.out.println(spent);
	}
}
