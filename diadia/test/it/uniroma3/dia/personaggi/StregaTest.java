package it.uniroma3.dia.personaggi;

import static org.junit.Assert.*;
import it.uniroma3.dia.ambienti.Stanza;
import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

import org.junit.Before;
import org.junit.Test;

public class StregaTest {
	private Partita partitaDiProva;
	private Strega strega;
	private String presentazione;
	private Stanza stanzaDiPartenza;
	private Stanza stanzaConPiuAttrezzi;
	private Stanza stanzaConMenoAttrezzi;
	private Attrezzo attrezzoGenerico1;
	private Attrezzo attrezzoGenerico2;
	private Attrezzo attrezzoGenerico3;
	
	@Before
	public void setUp() throws Exception {
		this.presentazione = "Se sarai educato, nella stanza con più attrezzi verrai portato!";
		this.strega = new Strega("strega", this.presentazione);
		
		this.stanzaDiPartenza = new Stanza("stanzaDiPartenza");
		this.stanzaConPiuAttrezzi = new Stanza("stanzaConPiuAttrezzi");
		this.stanzaConMenoAttrezzi = new Stanza("stanzaConMenoAttrezzi");
		
		this.attrezzoGenerico1 = new Attrezzo("generico1", 1);
		this.attrezzoGenerico2 = new Attrezzo("generico2", 2);
		this.attrezzoGenerico3 = new Attrezzo("generico3", 3);
		
		this.stanzaConMenoAttrezzi.addAttrezzo(this.attrezzoGenerico1);
		this.stanzaConPiuAttrezzi.addAttrezzo(this.attrezzoGenerico2);
		this.stanzaConPiuAttrezzi.addAttrezzo(this.attrezzoGenerico3);
		
		this.stanzaDiPartenza.impostaStanzaAdiacente("nord", this.stanzaConPiuAttrezzi);
		this.stanzaConPiuAttrezzi.impostaStanzaAdiacente("sud", this.stanzaDiPartenza);
		
		this.stanzaDiPartenza.impostaStanzaAdiacente("est", this.stanzaConMenoAttrezzi);
		this.stanzaConMenoAttrezzi.impostaStanzaAdiacente("ovest", this.stanzaDiPartenza);
		
		this.partitaDiProva = new Partita();
		this.partitaDiProva.setStanzaCorrente(this.stanzaDiPartenza);
	}

	@Test
	public void testAgisci_giocatoreHaSalutato() {
		assertEquals(this.partitaDiProva.getStanzaCorrente().getStanzaAdiacente("nord"), 
				this.stanzaConPiuAttrezzi);
		assertEquals(this.partitaDiProva.getStanzaCorrente().getStanzaAdiacente("est"), 
				this.stanzaConMenoAttrezzi);
		assertEquals(this.stanzaDiPartenza, this.partitaDiProva.getStanzaCorrente());
		
		assertNotNull( this.strega.saluta() );
		assertNotNull( this.strega.agisci(this.partitaDiProva) );
		
		assertEquals(this.stanzaConPiuAttrezzi, this.partitaDiProva.getStanzaCorrente());
		assertEquals(this.partitaDiProva.getStanzaCorrente().getStanzaAdiacente("sud"), 
				this.stanzaDiPartenza);
	}
	
	@Test
	public void testAgisci_giocatoreNonHaSalutato() {
		assertEquals(this.partitaDiProva.getStanzaCorrente().getStanzaAdiacente("nord"), 
				this.stanzaConPiuAttrezzi);
		assertEquals(this.partitaDiProva.getStanzaCorrente().getStanzaAdiacente("est"), 
				this.stanzaConMenoAttrezzi);
		assertEquals(this.stanzaDiPartenza, this.partitaDiProva.getStanzaCorrente());
		
		assertNotNull( this.strega.agisci(this.partitaDiProva) );
		
		assertEquals(this.stanzaConMenoAttrezzi, this.partitaDiProva.getStanzaCorrente());
		assertEquals(this.partitaDiProva.getStanzaCorrente().getStanzaAdiacente("ovest"), 
				this.stanzaDiPartenza);
	}
	
}
