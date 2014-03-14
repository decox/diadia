package it.uniroma3.dia.ambienti;

import static org.junit.Assert.*;
import it.uniroma3.dia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

public class StanzaMagicaTest {
	private StanzaMagica stanzaSogliaDefault;
	private StanzaMagica stanzaSogliaImpostata;
	private Attrezzo primoAttrezzo;
	private Attrezzo secondoAttrezzo;
	private Attrezzo terzoAttrezzo;
	private Attrezzo quartoAttrezzo;

	@Before
	public void setUp() throws Exception {
		this.stanzaSogliaDefault = new StanzaMagica("StanzaSogliaDefault");
		this.stanzaSogliaImpostata = new StanzaMagica("StanzaSogliaImpostata", 1);
		this.primoAttrezzo = new Attrezzo("PrimoAttrezzo", 1);
		this.secondoAttrezzo = new Attrezzo("SecondoAttrezzo", 1);
		this.terzoAttrezzo = new Attrezzo("TerzoAttrezzo", 1);
		this.quartoAttrezzo = new Attrezzo("QuartoAttrezzo", 1);

	}

	@Test
	public void testStanzaSogliaDefault_con3attrezzi() {
		assertTrue(this.stanzaSogliaDefault.addAttrezzo(primoAttrezzo));
		assertTrue(this.stanzaSogliaDefault.addAttrezzo(secondoAttrezzo));
		assertTrue(this.stanzaSogliaDefault.addAttrezzo(terzoAttrezzo));
		
		assertTrue(this.stanzaSogliaDefault.hasAttrezzo(primoAttrezzo.getNome()));
		assertTrue(this.stanzaSogliaDefault.hasAttrezzo(secondoAttrezzo.getNome()));
		assertTrue(this.stanzaSogliaDefault.hasAttrezzo(terzoAttrezzo.getNome()));
		
		assertEquals(this.stanzaSogliaDefault.getAttrezzo(primoAttrezzo.getNome()).getPeso(), 1);
		assertEquals(this.stanzaSogliaDefault.getAttrezzo(secondoAttrezzo.getNome()).getPeso(), 1);
		assertEquals(this.stanzaSogliaDefault.getAttrezzo(terzoAttrezzo.getNome()).getPeso(), 1);

	}
	
	@Test
	public void testStanzaSogliaDefault_con4attrezzi() {
		assertTrue(this.stanzaSogliaDefault.addAttrezzo(primoAttrezzo));
		assertTrue(this.stanzaSogliaDefault.addAttrezzo(secondoAttrezzo));
		assertTrue(this.stanzaSogliaDefault.addAttrezzo(terzoAttrezzo));
		assertTrue(this.stanzaSogliaDefault.addAttrezzo(quartoAttrezzo));

		assertTrue(this.stanzaSogliaDefault.hasAttrezzo(terzoAttrezzo.getNome()));
		assertFalse(this.stanzaSogliaDefault.hasAttrezzo(quartoAttrezzo.getNome()));
		assertTrue(this.stanzaSogliaDefault.hasAttrezzo("ozzerttAotrauQ"));
		assertEquals(this.stanzaSogliaDefault.getAttrezzo(terzoAttrezzo.getNome()).getPeso(), 1);
		assertEquals(this.stanzaSogliaDefault.getAttrezzo("ozzerttAotrauQ").getPeso(), 2);
	}
	
	@Test
	public void testStanzaSogliaImpostata_con1attrezzo() {
		assertTrue(this.stanzaSogliaImpostata.addAttrezzo(primoAttrezzo));
		
		assertTrue(this.stanzaSogliaImpostata.hasAttrezzo(primoAttrezzo.getNome()));
		
		assertEquals(this.stanzaSogliaImpostata.getAttrezzo(primoAttrezzo.getNome()).getPeso(), 1);
		
	}
	
	@Test
	public void testStanzaSogliaImpostata_con2attrezzi() {
		assertTrue(this.stanzaSogliaImpostata.addAttrezzo(primoAttrezzo));
		assertTrue(this.stanzaSogliaImpostata.addAttrezzo(secondoAttrezzo));
		
		assertTrue(this.stanzaSogliaImpostata.hasAttrezzo(primoAttrezzo.getNome()));
		assertFalse(this.stanzaSogliaImpostata.hasAttrezzo(secondoAttrezzo.getNome()));
		assertTrue(this.stanzaSogliaImpostata.hasAttrezzo("ozzerttAodnoceS"));
		
		assertEquals(this.stanzaSogliaImpostata.getAttrezzo(primoAttrezzo.getNome()).getPeso(), 1);
		assertEquals(this.stanzaSogliaImpostata.getAttrezzo("ozzerttAodnoceS").getPeso(), 2);
	}
	
	@Test
	public void testStanzaSogliaImpostata_con3attrezzi() {
		assertTrue(this.stanzaSogliaImpostata.addAttrezzo(primoAttrezzo));
		assertTrue(this.stanzaSogliaImpostata.addAttrezzo(secondoAttrezzo));
		assertTrue(this.stanzaSogliaImpostata.addAttrezzo(terzoAttrezzo));
		
		
		assertTrue(this.stanzaSogliaImpostata.hasAttrezzo(primoAttrezzo.getNome()));
		assertFalse(this.stanzaSogliaImpostata.hasAttrezzo(secondoAttrezzo.getNome()));
		assertFalse(this.stanzaSogliaImpostata.hasAttrezzo(terzoAttrezzo.getNome()));
		assertTrue(this.stanzaSogliaImpostata.hasAttrezzo("ozzerttAodnoceS"));
		assertTrue(this.stanzaSogliaImpostata.hasAttrezzo("ozzerttAozreT"));
		
		assertEquals(this.stanzaSogliaImpostata.getAttrezzo(primoAttrezzo.getNome()).getPeso(), 1);
		assertEquals(this.stanzaSogliaImpostata.getAttrezzo("ozzerttAodnoceS").getPeso(), 2);
		assertEquals(this.stanzaSogliaImpostata.getAttrezzo("ozzerttAozreT").getPeso(), 2);
	}
}