package tycoon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FactoryTest {

	@Test
	void testProvidedExamples() {
		Factory factory = new Factory("A");
		assertEquals(5, factory.timeToShip());
		factory = new Factory("AB");
		assertEquals(5, factory.timeToShip());
		factory = new Factory("BB");
		assertEquals(5, factory.timeToShip());
		factory = new Factory("ABB");
		assertEquals(7, factory.timeToShip());
	}

	@Test
	void testOwnExamplesB() {
		Factory factory = new Factory("B");
		assertEquals(5, factory.timeToShip());
	}

	@Test
	void testOwnExamplesABA() {
		Factory factory = new Factory("ABA");
		assertEquals(13, factory.timeToShip());
	}

	@Test
	void testOwnExamplesABAB() {
		Factory factory = new Factory("ABAB");
		assertEquals(13, factory.timeToShip());
	}
	
	@Test
	void testOwnExamplesEmpty() {
		Factory factory = new Factory("");
		assertEquals(0, factory.timeToShip());
		assertEquals(0,factory.ship());
	}

	@Test
	void testOwnExamplesABBA() {
		Factory factory = new Factory("ABBA");
		assertEquals(15, factory.timeToShip());
	}
	
	@Test
	void testOwnExamplesBrokenString() {
		assertThrows(IllegalArgumentException.class, () -> new Factory("ABC"));
	}

	@Test
	void testUnkownAABABBAB() {
		Factory factory = new Factory("AABABBAB");
		assertEquals(29, factory.timeToShip());
	}
	
	@Test
	void testUnkownABBBABAAABBB() {
		Factory factory = new Factory("ABBBABAAABBB");
		assertEquals(41, factory.timeToShip());
	}
}
