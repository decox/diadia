package it.uniroma3.dia.comandi;

import static org.junit.Assert.*;
import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

import org.junit.Before;
import org.junit.Test;

public class ComandoPosaTest {
	private Partita partitaUnAttrezzoInBorsa;
	private Partita partitaBorsaVuota;
	private Attrezzo attrezzo;
	private Attrezzo attrezzoNonInBorsa;
	private Comando comandoPosa;
	
	@Before
	public void setUp() throws Exception {
		this.partitaUnAttrezzoInBorsa = new Partita();
		this.partitaBorsaVuota = new Partita();
		this.attrezzo = new Attrezzo("test", 10);
		this.comandoPosa = new ComandoPosa();
		this.attrezzoNonInBorsa = new Attrezzo("attrezzoNonInBorsa", 2);
		
		this.attrezzoNonInBorsa.setNome("attrezzoNonInBorsa");
		this.partitaUnAttrezzoInBorsa.getGiocatore().getBorsa().addAttrezzo(attrezzo);
	}

	@Test
	public void testComandoPosa_daBorsaConUnAttrezzo() {
		this.comandoPosa.setParametro("test");
		this.comandoPosa.esegui(partitaUnAttrezzoInBorsa);
		
		assertFalse(this.partitaUnAttrezzoInBorsa.getGiocatore().getBorsa().hasAttrezzo("test"));
		assertTrue(this.partitaUnAttrezzoInBorsa.getStanzaCorrente().hasAttrezzo("test"));
	}
	
	@Test
	public void testComandoPosa_daBorsaVuota() {
		this.comandoPosa.setParametro("test");
		this.comandoPosa.esegui(partitaBorsaVuota);
		
		assertFalse(this.partitaBorsaVuota.getGiocatore().getBorsa().hasAttrezzo("test"));
		assertFalse(this.partitaBorsaVuota.getStanzaCorrente().hasAttrezzo("test"));
	}
	
	@Test
	public void testComandoPosa_daBorsaPiena() {
		this.comandoPosa.setParametro("test");
		this.comandoPosa.esegui(partitaBorsaVuota);
		
		assertFalse(this.partitaBorsaVuota.getGiocatore().getBorsa().hasAttrezzo("test"));
		assertFalse(this.partitaBorsaVuota.getStanzaCorrente().hasAttrezzo("test"));
	}
	
	@Test
	public void testComandoPosa_attrezzoNonInBorsa() {
		assertNull(this.partitaBorsaVuota.getGiocatore().getBorsa().getAttrezzo("attrezzoNonInBorsa"));
		assertFalse(this.partitaBorsaVuota.getGiocatore().getBorsa().hasAttrezzo("attrezzoNonInBorsa"));

		this.comandoPosa.setParametro("attrezzoNonInBorsa");
		this.comandoPosa.esegui(partitaBorsaVuota);
		
		assertFalse(this.partitaBorsaVuota.getGiocatore().getBorsa().hasAttrezzo("attrezzoNonInBorsa"));
		assertFalse(this.partitaBorsaVuota.getStanzaCorrente().hasAttrezzo("attrezzoNonInBorsa"));

	}
}
