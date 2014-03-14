package it.uniroma3.dia.attrezzi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AttrezzoTest {
	private Attrezzo attrezzo;


	@Before
	public void setUp() throws Exception {
		this.attrezzo = new Attrezzo("martello", 10);
	}

	@Test
	public void testGetNome() {
		assertEquals(this.attrezzo.getNome(), "martello");
	}
	
	@Test
	public void testGetPeso() {
		assertEquals(this.attrezzo.getPeso(),10);
	}

}
