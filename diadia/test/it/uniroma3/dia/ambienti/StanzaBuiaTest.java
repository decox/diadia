package it.uniroma3.dia.ambienti;

import static org.junit.Assert.*;
import it.uniroma3.dia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

public class StanzaBuiaTest {
	private Stanza stanzaBuia;
	private	Attrezzo attrezzoIlluminante;
	private Attrezzo attrezzoSbagliato;


	@Before
	public void setUp() throws Exception {
		this.stanzaBuia = new StanzaBuia("stanza buia", "Lanterna");
		this.attrezzoIlluminante = new Attrezzo("Lanterna", 1);
		this.attrezzoSbagliato = new Attrezzo("Sbagliato", 2);

	}

	@Test
	public void testStanzaBuia_conAttrezzoIlluminante(){
		assertTrue(this.stanzaBuia.addAttrezzo(attrezzoIlluminante));
		assertEquals(this.stanzaBuia.getDescrizione(), this.stanzaBuia.toString());
	}

	@Test
	public void testStanzaBuia_conAttrezzoSbagliato() {
		assertTrue(this.stanzaBuia.addAttrezzo(attrezzoSbagliato));
		assertNotSame(this.stanzaBuia.getDescrizione(), this.stanzaBuia.toString());
		assertEquals(this.stanzaBuia.getDescrizione(), "Qui c'e' un buio pesto! Servirebbe un lume nella stanza!");
	}

	@Test
	public void testStanzaBuia_conStanzaVuota(){
		assertFalse(this.stanzaBuia.hasAttrezzo(attrezzoIlluminante.getNome()));
		assertFalse(this.stanzaBuia.hasAttrezzo(attrezzoSbagliato.getNome()));
		assertNotSame(this.stanzaBuia.getDescrizione(), this.stanzaBuia.toString());
		assertEquals(this.stanzaBuia.getDescrizione(), "Qui c'e' un buio pesto! Servirebbe un lume nella stanza!");

	}

	@Test 
	public void testStanzaBuia_conPiuAttrezzi(){
		assertTrue(this.stanzaBuia.addAttrezzo(attrezzoIlluminante));
		assertTrue(this.stanzaBuia.addAttrezzo(attrezzoSbagliato));
		assertEquals(this.stanzaBuia.getDescrizione(), this.stanzaBuia.toString());

	}
}