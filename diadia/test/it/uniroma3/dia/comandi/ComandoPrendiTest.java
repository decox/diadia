package it.uniroma3.dia.comandi;

import static org.junit.Assert.*;
import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

import org.junit.Before;
import org.junit.Test;

public class ComandoPrendiTest {
	private Partita partitaDiProva;
	private Comando comandoPrendi;
	private Attrezzo attrezzo;
	private Attrezzo attrezzoPesante;
	
	@Before
	public void setUp() throws Exception {
	this.partitaDiProva = new Partita();
	this.comandoPrendi = new ComandoPrendi();
	this.attrezzo = new Attrezzo("attrezzo", 1);
	this.attrezzoPesante = new Attrezzo("pesante", 50);
	}

	@Test
	public void testPrendiAttrezzo() {
		this.partitaDiProva.getStanzaCorrente().addAttrezzo(attrezzo);
		comandoPrendi.setParametro("attrezzo");
		comandoPrendi.esegui(partitaDiProva);
		assertTrue(this.partitaDiProva.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
	}

	@Test
	public void testPrendiAttrezzo_inesistente() {
		assertFalse(this.partitaDiProva.getStanzaCorrente().hasAttrezzo("attrezzo"));
		comandoPrendi.setParametro("attrezzo");
		comandoPrendi.esegui(partitaDiProva);
		assertFalse(this.partitaDiProva.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
	}
	
	@Test
	public void testPrendiAttrezzoSbagliato() {
		this.partitaDiProva.getStanzaCorrente().addAttrezzo(attrezzo);
		assertFalse(this.partitaDiProva.getStanzaCorrente().hasAttrezzo("inesistente"));
		assertTrue(this.partitaDiProva.getStanzaCorrente().hasAttrezzo("attrezzo"));
		comandoPrendi.setParametro("inesistente");
		comandoPrendi.esegui(this.partitaDiProva);
		assertTrue(this.partitaDiProva.getStanzaCorrente().hasAttrezzo("attrezzo"));
		
	}

	@Test
	public void testPrendiAttrezzo_troppoPesante(){
		this.partitaDiProva.getStanzaCorrente().addAttrezzo(attrezzoPesante);
		assertTrue(this.partitaDiProva.getStanzaCorrente().hasAttrezzo("pesante"));
		comandoPrendi.setParametro("pesante");
		comandoPrendi.esegui(this.partitaDiProva);
		assertFalse(this.partitaDiProva.getGiocatore().getBorsa().hasAttrezzo("pesante"));
		assertTrue(this.partitaDiProva.getStanzaCorrente().hasAttrezzo("pesante"));
	}
	
	@Test
	public void testPrendiAtterzzo_dueVolte(){
		this.partitaDiProva.getStanzaCorrente().addAttrezzo(attrezzo);
		assertTrue(this.partitaDiProva.getStanzaCorrente().hasAttrezzo("attrezzo"));
		comandoPrendi.setParametro("attrezzo");
		comandoPrendi.esegui(this.partitaDiProva);
		assertTrue(this.partitaDiProva.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
		
		assertFalse(this.partitaDiProva.getStanzaCorrente().hasAttrezzo("attrezzo"));
		comandoPrendi.setParametro("attrezzo");
		comandoPrendi.esegui(this.partitaDiProva);
		assertTrue(this.partitaDiProva.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
	}
}
