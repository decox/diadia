package it.uniroma3.dia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	private Labirinto labirinto;
	
	@Before
	public void setUp() throws Exception {
		this.labirinto = new Labirinto();
		
	}

	@Test
	public void testGetStanzaIniziale() {
		assertEquals(this.labirinto.getStanzaIniziale().getNome(), "Atrio");
	}

	@Test
	public void testGetStanzaFinale() {
		assertEquals(this.labirinto.getStanzaFinale().getNome(), "Biblioteca");
	}

	@Test
	public void testGetMessaggioBenvenuto() {
		assertEquals(this.labirinto.getMessaggioBenvenuto() , "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
					"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
					"I locali sono popolati da strani personaggi, " +
					"alcuni amici, altri... chissa!\n"+
					"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
					"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
					"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
					"Per conoscere le istruzioni usa il comando 'aiuto'.");
	}
	
	public void testInit(){
		assertEquals(this.labirinto.getStanzaIniziale().getStanzaAdiacente("sud").getNome(), "Aula N10");
		assertEquals(this.labirinto.getStanzaIniziale().getStanzaAdiacente("nord").getNome(), "Biblioteca");
		assertEquals(this.labirinto.getStanzaIniziale().getStanzaAdiacente("est").getNome(), "Aula N11");
		assertEquals(this.labirinto.getStanzaIniziale().getStanzaAdiacente("ovest"), "Laboratorio Campus");
		assertEquals(this.labirinto.getStanzaFinale().getStanzaAdiacente("sud").getNome(), "Atrio");
		
	}
}
