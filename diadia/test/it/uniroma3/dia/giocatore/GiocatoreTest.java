package it.uniroma3.dia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {
	private Giocatore giocatoreVivo;
	private Giocatore giocatoreMorto;
	
	@Before
	public void setUp() throws Exception {
	this.giocatoreVivo = new Giocatore();
	this.giocatoreMorto = new Giocatore(0);
	}
	
	@Test
	public void testIsVivo_suGiocatoreMorto(){
		assertFalse(this.giocatoreMorto.isVivo());
	}
	
	@Test
	public void testIsVivo_suGiocatoreVivo() {
		assertTrue(this.giocatoreVivo.isVivo());
	}

	@Test
	public void testGetCfu() {
		assertEquals(this.giocatoreMorto.getCfu(), 0);
	}

	@Test
	public void testSetCfu() {
		this.giocatoreVivo.setCfu(10);
		assertEquals(this.giocatoreVivo.getCfu(), 10);
	}


}
