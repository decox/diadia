package it.uniroma3.dia.personaggi;

import static org.junit.Assert.*;
import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

import org.junit.Before;
import org.junit.Test;

public class CaneTest {

	private static final String MESSAGGIO_INTERAGISCI = "Grrrrrrrrrr!!";

	private Personaggio cane;
	private Attrezzo attrezzoDonato;
	private Attrezzo attrezzoIndesiderato;
	private Partita partita;
	private Attrezzo attrezzoInRegalo;
	
	@Before
	public void setUp() throws Exception {
		this.attrezzoDonato = new Attrezzo("chiaveStanza", 0);
		this.cane = new Cane("Pluto", "bau" ,"osso" , attrezzoDonato);
		this.partita = new Partita();
		this.partita.getStanzaCorrente().setPersonaggio(this.cane);
		this.attrezzoInRegalo = new Attrezzo("osso", 4);
		this.attrezzoIndesiderato = new Attrezzo("indesiderato", 2);
	}

	@Test
	public void testRiceviRegalo() {
		assertEquals(cane.riceviRegalo(attrezzoInRegalo, partita), "bau-bau");
		
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(attrezzoDonato.getNome()));
		
	}
	
	@Test
	public void testRiveviRegalo_attrezzoIndesiderato(){
		assertEquals(cane.riceviRegalo(attrezzoIndesiderato, partita), "grrrrrr");
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(attrezzoDonato.getNome()));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(attrezzoIndesiderato.getNome()));
	}
	
	@Test
	public void testAgisci() {
		assertEquals(cane.agisci(partita), MESSAGGIO_INTERAGISCI );
		assertEquals(partita.getGiocatore().getCfu(), 19);
		
	}
	
	@Test
	public void testAgisci_dueVolte() {
		assertEquals(cane.agisci(partita), MESSAGGIO_INTERAGISCI );
		assertEquals(partita.getGiocatore().getCfu(), 19);
		assertEquals(cane.agisci(partita), MESSAGGIO_INTERAGISCI );
		assertEquals(partita.getGiocatore().getCfu(), 18);
	}
	
}
