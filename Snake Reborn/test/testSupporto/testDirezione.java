package testSupporto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import supporto.Direzione;

public class testDirezione {
	
	Direzione direzione;
	
	@Before
	public void setUp(){
		direzione = new Direzione(1,0); // --->
	}

	@Test
	public void testRuotaDX() {
		direzione.ruotaDX();
		assertEquals(0,direzione.getX());
		assertEquals(1,direzione.getY());
	}

	@Test
	public void testRuotaSX() {
		direzione.ruotaSX();
		assertEquals(0,direzione.getX());
		assertEquals(-1,direzione.getY());
	}

	@Test
	public void testInverti() {
		direzione.Inverti();
		assertEquals(-1,direzione.getX());
		assertEquals(0,direzione.getY());
	}

}
