package tycoon;

public class Main {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Main <LOCATIONS>");
			return;
		}
		String locations = args[0];
		Factory factory = new Factory(locations);
		int spent = factory.shipAll();
		System.out.println(spent);
	}
}
