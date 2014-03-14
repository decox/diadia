package it.uniroma3.dia.personaggi;

import static org.junit.Assert.*;
import it.uniroma3.diadia.Partita;

import org.junit.Before;
import org.junit.Test;

public class PersonaggioMockTest {
	private Personaggio personaggio;
	private Partita partita;
	private String presentazione = "bella";
	
	@Before
	public void setUp() throws Exception {
	this.partita = new Partita();
	this.personaggio = new PersonaggioMock("pintus", this.presentazione);
	this.partita.getStanzaCorrente().setPersonaggio(personaggio);
	}

	@Test
	public void testHaSalutato() {
		assertFalse(this.personaggio.haSalutato());
		this.personaggio.saluta();
		assertTrue(this.personaggio.haSalutato());
	}
	
	@Test
	public void testSaluta(){
		assertFalse(this.personaggio.haSalutato());
		assertTrue(this.personaggio.saluta().contains(this.presentazione));
		assertTrue(this.personaggio.haSalutato());
		assertFalse(this.personaggio.saluta().contains(this.presentazione));
		
	}
}
