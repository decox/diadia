package it.uniroma3.dia.ambienti;

import static org.junit.Assert.*;
import it.uniroma3.dia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

public class StanzaBloccataTest {
	private StanzaBloccata stanzaBloccata;
	private Stanza stanzaNellaDirezioneBloccata;
	private Stanza stanzaNellaDirezioneLibera;
	private Attrezzo attrezzoChiaveStanza;
	private Attrezzo attrezzoGenerico;
	private String direzioneBloccata;
	private String direzioneLibera;
	
	@Before
	public void setUp() throws Exception {
		this.attrezzoChiaveStanza = new Attrezzo("chiave direzione bloccata", 1);
		this.attrezzoGenerico = new Attrezzo("generico", 5);
		
		this.direzioneBloccata = "nord";
		this.direzioneLibera = "est";
		
		this.stanzaNellaDirezioneBloccata = new Stanza("stanza bloccata");
		this.stanzaNellaDirezioneLibera = new Stanza("stanza libera");
		
		this.stanzaBloccata = new StanzaBloccata("bloccata", 
				this.direzioneBloccata, 
				this.attrezzoChiaveStanza.getNome());
		
		this.stanzaBloccata.impostaStanzaAdiacente(
				this.direzioneBloccata, 
				this.stanzaNellaDirezioneBloccata);
		
		this.stanzaBloccata.impostaStanzaAdiacente(
				this.direzioneLibera, 
				this.stanzaNellaDirezioneLibera);
	}

	@Test
	public void testGetStanzaAdiacente_senzaAttrezziNellaStanza() {
		assertEquals(0, this.stanzaBloccata.getNumeroAttrezzi());
		assertFalse(this.stanzaBloccata.hasAttrezzo(this.attrezzoChiaveStanza.getNome()));
		assertEquals(this.stanzaBloccata, this.stanzaBloccata.getStanzaAdiacente(this.direzioneBloccata));
		assertEquals(this.stanzaNellaDirezioneLibera, this.stanzaBloccata.getStanzaAdiacente(this.direzioneLibera));
	}
	
	@Test
	public void testGetStanzaAdiacente_conAttrezziGenericiNellaStanza() {
		assertEquals(0, this.stanzaBloccata.getNumeroAttrezzi());
		assertTrue(this.stanzaBloccata.addAttrezzo(this.attrezzoGenerico));
		assertTrue(this.stanzaBloccata.hasAttrezzo(this.attrezzoGenerico.getNome()));
		assertFalse(this.stanzaBloccata.hasAttrezzo(this.attrezzoChiaveStanza.getNome()));
		
		assertEquals(this.stanzaBloccata, this.stanzaBloccata.getStanzaAdiacente(this.direzioneBloccata));
		assertEquals(this.stanzaNellaDirezioneLibera, this.stanzaBloccata.getStanzaAdiacente(this.direzioneLibera));
	}
	
	@Test
	public void testGetStanzaAdiacente_conChiaveNellaStanza() {
		assertEquals(0, this.stanzaBloccata.getNumeroAttrezzi());
		assertTrue(this.stanzaBloccata.addAttrezzo(this.attrezzoChiaveStanza));
		assertTrue(this.stanzaBloccata.hasAttrezzo(this.attrezzoChiaveStanza.getNome()));
		assertFalse(this.stanzaBloccata.hasAttrezzo(this.attrezzoGenerico.getNome()));
		
		assertEquals(this.stanzaNellaDirezioneBloccata, this.stanzaBloccata.getStanzaAdiacente(this.direzioneBloccata));
		assertEquals(this.stanzaNellaDirezioneLibera, this.stanzaBloccata.getStanzaAdiacente(this.direzioneLibera));
	}
	
	@Test
	public void testGetStanzaAdiacente_conChiaveEdAttrezziNellaStanza() {
		assertEquals(0, this.stanzaBloccata.getNumeroAttrezzi());
		assertTrue(this.stanzaBloccata.addAttrezzo(this.attrezzoChiaveStanza));
		assertTrue(this.stanzaBloccata.addAttrezzo(this.attrezzoGenerico));
		assertTrue(this.stanzaBloccata.hasAttrezzo(this.attrezzoChiaveStanza.getNome()));
		assertTrue(this.stanzaBloccata.hasAttrezzo(this.attrezzoGenerico.getNome()));
		
		assertEquals(this.stanzaNellaDirezioneBloccata, this.stanzaBloccata.getStanzaAdiacente(this.direzioneBloccata));
		assertEquals(this.stanzaNellaDirezioneLibera, this.stanzaBloccata.getStanzaAdiacente(this.direzioneLibera));
	}

}
