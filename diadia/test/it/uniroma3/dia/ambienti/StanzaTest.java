/**
 * 
 */
package it.uniroma3.dia.ambienti;
import static org.junit.Assert.*;
import it.uniroma3.dia.ambienti.Stanza;
import it.uniroma3.dia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

/**
 * @author luigi
 *
 */
public class StanzaTest {
	private Stanza atrio;
	private Stanza aulaN10;
	private Stanza aulaN11;
	private Attrezzo osso;
	private Attrezzo spada;
	private Attrezzo scudo;

	@Before
	public void setUp() {
		this.atrio = new Stanza("Atrio");
		this.aulaN11 = new Stanza("N11");
		this.aulaN10 = new Stanza("N10");
		
		this.osso = new Attrezzo("osso", 3);
		this.spada = new Attrezzo("spada", 4);
		this.scudo = new Attrezzo("scudo", 5);
	}

	@Test
	public void testRemoveAttrezzo_daStanzaVuota() {
		assertEquals(0, this.atrio.getNumeroAttrezzi());
		assertFalse(this.atrio.removeAttrezzo(this.osso));
		assertFalse(this.atrio.hasAttrezzo(this.osso.getNome()));
		assertEquals(0, this.atrio.getNumeroAttrezzi());
	}

	@Test
	public void testRemoveAttrezzo_daStanzaConUnAttrezzo() {
		assertEquals(0, this.atrio.getNumeroAttrezzi());
		assertTrue(this.atrio.addAttrezzo(this.osso));
		assertTrue(this.atrio.hasAttrezzo(this.osso.getNome()));
		assertEquals(1, this.atrio.getNumeroAttrezzi());
		
		assertTrue(this.atrio.removeAttrezzo(this.osso));
		assertFalse(this.atrio.hasAttrezzo(this.osso.getNome()));
		assertEquals(0, this.atrio.getNumeroAttrezzi());
	}

	@Test
	public void testRemoveAttrezzo_daStanzaConAttrezzoInesistente() {
		assertEquals(0, this.atrio.getNumeroAttrezzi());
		assertTrue(this.atrio.addAttrezzo(this.osso));
		assertTrue(this.atrio.hasAttrezzo(this.osso.getNome()));
		assertEquals(1, this.atrio.getNumeroAttrezzi());
		
		assertFalse(this.atrio.hasAttrezzo(this.spada.getNome()));
		assertFalse(this.atrio.removeAttrezzo(this.spada));
		assertTrue(this.atrio.hasAttrezzo(this.osso.getNome()));
		assertFalse(this.atrio.hasAttrezzo(this.spada.getNome()));
		assertEquals(1, this.atrio.getNumeroAttrezzi());
	}
	
	@Test
	public void testAggiungiAttrezzo_daStanzaVuota() {
		assertEquals(0, this.atrio.getNumeroAttrezzi());
		assertTrue(this.atrio.addAttrezzo(this.scudo));
		assertTrue(this.atrio.hasAttrezzo(this.scudo.getNome()));
		assertEquals(1, this.atrio.getNumeroAttrezzi());
	}
	
	@Test
	public void testAggiungiAttrezzo_daStanzaConAttrezzo() {
		assertEquals(0, this.atrio.getNumeroAttrezzi());
		assertTrue(this.atrio.addAttrezzo(this.osso));
		assertTrue(this.atrio.addAttrezzo(this.scudo));
		assertTrue(this.atrio.hasAttrezzo(this.osso.getNome()));
		assertTrue(this.atrio.hasAttrezzo(this.scudo.getNome()));
		assertEquals(2, this.atrio.getNumeroAttrezzi());
	}
	
	public void impostaStanzaAdiacente_daStanzaSenzaDirezioniIniziali() {
		assertNull(this.atrio.getStanzaAdiacente("nord"));
		this.atrio.impostaStanzaAdiacente("nord", this.aulaN10);
		assertSame(this.atrio.getStanzaAdiacente("nord"), this.aulaN10);
		
		assertNull(this.atrio.getStanzaAdiacente("est"));
		this.atrio.impostaStanzaAdiacente("est", this.aulaN11);
		assertSame(this.atrio.getStanzaAdiacente("est"), this.aulaN11);
	}
	
	public void impostaStanzaAdiacente_daStanzaConDirezioneImpostata() {
		assertNull(this.atrio.getStanzaAdiacente("nord"));
		this.atrio.impostaStanzaAdiacente("nord", this.aulaN11);
		assertSame(this.atrio.getStanzaAdiacente("nord"), this.aulaN11);
		
		this.atrio.impostaStanzaAdiacente("nord", this.aulaN10);
		assertSame(this.atrio.getStanzaAdiacente("nord"), this.aulaN10);
	}
	
	
}