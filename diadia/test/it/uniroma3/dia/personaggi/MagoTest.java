package it.uniroma3.dia.personaggi;

import static org.junit.Assert.*;
import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

import org.junit.Before;
import org.junit.Test;

public class MagoTest {

	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla da darti ...";
	private Personaggio mago;
	private Attrezzo attrezzoDono;
	private Partita partita;
	private Attrezzo attrezzoInRegalo;
	
	@Before
	public void setUp() throws Exception {
		this.attrezzoDono = new Attrezzo("bastone", 2);
		this.mago = new Mago("merlino", "", attrezzoDono);
		this.partita = new Partita();
		this.partita.getStanzaCorrente().setPersonaggio(mago);
		this.attrezzoInRegalo = new Attrezzo("regalo", 4);
	}

	@Test
	public void testAgisci() {
		mago.agisci(partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(attrezzoDono.getNome()));
	}
	
	@Test
	public void testAgisciDueVolte() {
		mago.agisci(partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(attrezzoDono.getNome()));
		assertEquals(mago.agisci(partita), MESSAGGIO_SCUSE);
		
	}
	
	@Test
	public void testRiceviRegalo() {
		assertTrue(partita.getGiocatore().getBorsa().addAttrezzo(attrezzoInRegalo));
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzoInRegalo.getNome()));
		mago.riceviRegalo(attrezzoInRegalo, partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(attrezzoInRegalo.getNome()));
		assertEquals(this.partita.getStanzaCorrente().getAttrezzo(attrezzoInRegalo.getNome()).getPeso(), 2);
	}
	

}
