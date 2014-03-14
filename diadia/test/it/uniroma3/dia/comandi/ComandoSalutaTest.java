package it.uniroma3.dia.comandi;

import static org.junit.Assert.*;
import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.dia.personaggi.Mago;
import it.uniroma3.dia.personaggi.Personaggio;
import it.uniroma3.diadia.Partita;

import org.junit.Before;
import org.junit.Test;

public class ComandoSalutaTest {
	private Personaggio personaggio;
	private Partita partita;
	private Partita partitaSenzaPersonaggio;
	private Comando comandoSaluta;
	private Attrezzo attrezzo;
	
	@Before
	public void setUp() throws Exception {
		this.personaggio = new Mago("mago","",attrezzo);
		this.partita = new Partita();
		this.comandoSaluta = new ComandoSaluta();
		this.attrezzo = new Attrezzo("Atterzzo", 1);	
		this.partita.getStanzaCorrente().setPersonaggio(personaggio);
		this.partitaSenzaPersonaggio = new Partita();
		this.partitaSenzaPersonaggio.getStanzaCorrente().setPersonaggio(null);
	}

	@Test
	public void testComandoSaluta() {
		this.comandoSaluta.esegui(partita);
		assertTrue(this.personaggio.haSalutato());
	}
	
	@Test
	public void testComandoSaluta_diPersonaggioSalutato() {
		this.comandoSaluta.esegui(partita);
		assertTrue(this.personaggio.haSalutato());
		assertEquals(this.comandoSaluta.esegui(partita), "Ciao, io sono mago. Ci siamo gia' presentati.");
	
	}

	@Test
	public void testComandoSaluta_suStanzaSenzaPersonaggio() {
		assertEquals(this.comandoSaluta.esegui(partitaSenzaPersonaggio), "Nessun personaggio nella stanza!");
	}
}
