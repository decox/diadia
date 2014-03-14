package it.uniroma3.dia.giocatore;

import static org.junit.Assert.*;
import it.uniroma3.dia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

public class BorsaTest {
	private Borsa borsa;
	private Borsa borsaLimitata;
	private Attrezzo attrezzoAggiunto;
	private Attrezzo attrezzo;
	private Attrezzo attrezzoInPiu;
	private Attrezzo attrezzoInesistente;
	
	@Before
	public void setUp() throws Exception {
		this.borsa = new Borsa();
		this.borsaLimitata = new Borsa(3);
		this.attrezzoAggiunto = new Attrezzo("aggiunto",2);
		this.attrezzo = new Attrezzo("attrezzo", 1);
		this.attrezzoInPiu = new Attrezzo("attrezzoInPiu",3);
		this.attrezzoInesistente = new Attrezzo("inesistente" , 4);
	}

	@Test
	public void testAddAttrezzo() {
		assertTrue(this.borsa.addAttrezzo(attrezzoAggiunto));
		assertTrue(this.borsa.hasAttrezzo(attrezzoAggiunto.getNome()));
	}

	@Test
	public void testHasAttrezzo() {
		assertFalse(this.borsa.hasAttrezzo(attrezzoAggiunto.getNome()));
		this.borsa.addAttrezzo(attrezzoAggiunto);
		assertTrue(this.borsa.hasAttrezzo(attrezzoAggiunto.getNome()));
	}

	@Test
	public void testRemoveAttrezzo_daBorsaVuota() {
		assertTrue(this.borsa.isEmpty());
		assertFalse(this.borsa.hasAttrezzo(attrezzoInesistente.getNome()));
		assertEquals(this.borsa.removeAttrezzo(attrezzoInesistente.getNome()),null);
	}

	@Test
	public void testRemoveAttrezzo_daBorsaConUnAttrezzo() {
		this.borsa.addAttrezzo(attrezzoAggiunto);
		assertTrue(this.borsa.hasAttrezzo(attrezzoAggiunto.getNome()));
		assertEquals(this.borsa.removeAttrezzo(attrezzoAggiunto.getNome()),attrezzoAggiunto);
	}

	@Test
	public void testRemoveAttrezzo_daBorsaConAttrezzoInesistente() {
		this.borsa.addAttrezzo(attrezzoAggiunto);
		assertFalse(this.borsa.hasAttrezzo(attrezzoInesistente.getNome()));
		assertEquals(this.borsa.removeAttrezzo(attrezzoInesistente.getNome()),null);
	}
	
	@Test
	public void testAddAttrezzo_suBorsaPiena() {
		this.borsaLimitata.addAttrezzo(attrezzoAggiunto);
		this.borsaLimitata.addAttrezzo(attrezzo);
		this.borsaLimitata.addAttrezzo(attrezzoInPiu);
		assertTrue(this.borsaLimitata.hasAttrezzo(attrezzoAggiunto.getNome()));
		assertTrue(this.borsaLimitata.hasAttrezzo(attrezzo.getNome()));
		assertFalse(this.borsaLimitata.hasAttrezzo(attrezzoInPiu.getNome()));
		
	}
	
	@Test
	public void testGetAttrezzo() {
		this.borsa.addAttrezzo(attrezzo);
		Attrezzo picchio = this.borsa.getAttrezzo(attrezzo.getNome());
		assertEquals(this.attrezzo,picchio);
	}
}
