package it.uniroma3.dia.comandi;

import static org.junit.Assert.*;
import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.dia.personaggi.Mago;
import it.uniroma3.dia.personaggi.Personaggio;
import it.uniroma3.diadia.Partita;

import org.junit.Before;
import org.junit.Test;

public class ComandoInteragisciTest {
	
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla da darti ...";

	
	private Personaggio personaggio;
	private Partita partita;
	private Partita partitaSenzaPersonaggio;
	private Comando comandoInteragisci;
	private Attrezzo attrezzo;
	
	@Before
	public void setUp() throws Exception {
		this.attrezzo = new Attrezzo("attrezzo", 2);
		this.personaggio = new Mago("merlino","",attrezzo);
		this.partita = new Partita();
		this.comandoInteragisci = new ComandoInteragisci();
		this.partita.getStanzaCorrente().setPersonaggio(personaggio);
		this.partitaSenzaPersonaggio = new Partita();
		this.partitaSenzaPersonaggio.getStanzaCorrente().setPersonaggio(null);
	}

	@Test
	public void testComandoInteragisci() {
		this.comandoInteragisci.esegui(partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
		
	}
	
	@Test
	public void testComandoInteragisci_diPersonaggioInteragito(){
		this.comandoInteragisci.esegui(partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
		assertEquals(this.comandoInteragisci.esegui(partita), MESSAGGIO_SCUSE);
		
	}
	
	@Test
	public void testComandoInteragisci_suStanzaSenzaPersonaggio(){
		assertEquals(this.comandoInteragisci.esegui(partitaSenzaPersonaggio), "Nessun personaggio nella stanza!");
		assertFalse(this.partitaSenzaPersonaggio.getStanzaCorrente().hasAttrezzo("attrezzo"));
		
	}
	
}
