package testGame;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import terrenoDiGioco.Stanza;

public class TestStanza {

	Stanza stanza1;
	
	@Before
	public void setUp(){
		stanza1 = new Stanza(1);
	}

	@Test
	public void testStanza_nonNulla() {
		assertNotNull(stanza1);
	}
	
	@Test
	public void testStanza_collegamentiNonNulli() {
		assertNotNull(stanza1.getCollegamenti());
	}
	
	@Test
	public void testStanza_verificaCollegamenti() {
		assertTrue(stanza1.getCollegamenti().get("nord").equals(stanza1));
		assertTrue(stanza1.getCollegamenti().get("est").equals(stanza1));
		assertTrue(stanza1.getCollegamenti().get("sud").equals(stanza1));
		assertTrue(stanza1.getCollegamenti().get("ovest").equals(stanza1));
	}

}
