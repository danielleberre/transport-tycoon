package tycoon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FactoryTest {
	private Factory factory;	
	private Warehouse a;
	private Warehouse b;
	
	@BeforeEach
	public void setup() {
		factory = new Factory();
		a = new Warehouse("A", new Port(factory, 1,0), 4);
		b = new Warehouse("B", factory, 5);
		factory.init(a, b);
	}

	@Test
	void testProvidedExamplesA() {
		assertEquals(5, factory.shipAll("A"));
	}
	
	@Test
	void testProvidedExamplesAB() {
		assertEquals(5, factory.shipAll("AB"));
	}
	
	@Test
	void testProvidedExamplesBB() {
		assertEquals(5, factory.shipAll("BB"));
	}
	
	@Test
	void testProvidedExamplesABB() {
		assertEquals(7, factory.shipAll("ABB"));
	}

	@Test
	void testOwnExamplesB() {
		assertEquals(5, factory.shipAll("B"));
	}

	@Test
	void testOwnExamplesABA() {
		assertEquals(13, factory.shipAll("ABA"));
	}

	@Test
	void testOwnExamplesABAB() {
		assertEquals(13, factory.shipAll("ABAB"));
	}
	
	@Test
	void testOwnExamplesEmpty() {
		assertEquals(0, factory.shipAll(""));
	}

	@Test
	void testOwnExamplesABBA() {
		assertEquals(15, factory.shipAll("ABBA"));
	}
	
	@Test
	void testOwnExamplesBrokenString() {
		assertThrows(IllegalArgumentException.class, () -> factory.shipAll("ABC"));
	}

	@Test
	void testUnkownAABABBAB() {
		assertEquals(29, factory.shipAll("AABABBAB"));
	}
	
	@Test
	void testUnkownABBBABAAABBB() {
		assertEquals(41, factory.shipAll("ABBBABAAABBB"));
	}
}
