package it.uniroma3.dia.comandi;

import static org.junit.Assert.*;
import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.dia.personaggi.Mago;
import it.uniroma3.dia.personaggi.Personaggio;
import it.uniroma3.diadia.Partita;

import org.junit.Before;
import org.junit.Test;

public class ComandoRegalaTest {
	private Personaggio personaggio;
	private Partita partita;
	private Partita partitaSenzaPersonaggio;
	private Comando comandoRegala;
	private Attrezzo attrezzo;
	private Attrezzo attrezzoSecondoRegalo;
	
	
	@Before
	public void setUp() throws Exception {
		this.personaggio = new Mago("mago","",attrezzo);
		this.partita = new Partita();
		this.comandoRegala = new ComandoRegala();
		this.attrezzo = new Attrezzo("Attrezzo", 2);	
		this.attrezzoSecondoRegalo = new Attrezzo("attrezzo2regalo", 1);
		this.partita.getStanzaCorrente().setPersonaggio(personaggio);
		this.partitaSenzaPersonaggio = new Partita();
		this.partitaSenzaPersonaggio.getStanzaCorrente().setPersonaggio(null);
	}

	@Test
	public void testComandoRegala_nessunPersonaggio() {
		assertNull(this.partitaSenzaPersonaggio.getStanzaCorrente().getPersonaggio());
		this.comandoRegala.setParametro("Attrezzo");
		assertEquals(this.comandoRegala.esegui(partitaSenzaPersonaggio),"Nessun personaggio nella stanza!");
		
	}
	
	@Test
	public void testComandoRegala_personaggioEsistente(){
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("Attrezzo"));
		this.comandoRegala.setParametro("Attrezzo");
		this.comandoRegala.esegui(partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("Attrezzo"));
		assertEquals(this.partita.getStanzaCorrente().getAttrezzo("Attrezzo").getPeso(), 1);
	}
	
	@Test
	public void testComandoRegala_attrezzoNonInBorsa() {
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("Attrezzo"));
		this.comandoRegala.setParametro("Attrezzo");
		this.comandoRegala.esegui(partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("Attrezzo"));
		
	}
	
	@Test
	public void testComandoRegala_regalaDueVolteConsecutive() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("Attrezzo"));
		this.comandoRegala.setParametro("Attrezzo");
		this.comandoRegala.esegui(partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("Attrezzo"));
		assertEquals(this.partita.getStanzaCorrente().getAttrezzo("Attrezzo").getPeso(), 1);
		
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoSecondoRegalo);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo2regalo"));
		this.comandoRegala.setParametro("attrezzo2regalo");
		this.comandoRegala.esegui(partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("attrezzo2regalo"));
		assertEquals(this.partita.getStanzaCorrente().getAttrezzo("attrezzo2regalo").getPeso(), 0);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("Attrezzo"));
		assertEquals(this.partita.getStanzaCorrente().getAttrezzo("Attrezzo").getPeso(), 1);
		
	}
	
	
}
