package it.uniroma3.diadia;


import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class PartitaTest {
	private Partita partitaGenerica;

	@Before
	public void setUp() throws Exception {
		partitaGenerica = new Partita();
	}

	@Test
	public void testGiocatoreVivo_daGiocatoreVivo() {
		assertTrue(this.partitaGenerica.giocatoreIsVivo());
	}
	
	@Test
	public void testGiocatoreVivo_daGiocatoreMorto() {
		this.partitaGenerica.getGiocatore().setCfu(0);
		assertFalse(this.partitaGenerica.giocatoreIsVivo());
	}

	@Test
	public void testVinta_daPartitaNonVinta() {
		assertFalse(this.partitaGenerica.vinta());
	}

	@Test
	public void testIsFinita_conCfuFiniti() {
		assertFalse(this.partitaGenerica.isFinita());
		this.partitaGenerica.getGiocatore().setCfu(0);
		assertTrue(this.partitaGenerica.isFinita());
	}
	
	@Test
	public void testIsFinita_conUscitaGiocatore() {
		assertFalse(this.partitaGenerica.isFinita());
		this.partitaGenerica.setFinita();
		assertTrue(this.partitaGenerica.isFinita());
	}
	
	@Test
	public void testIsFinita_conGiocatoreInGioco() {
		assertFalse(this.partitaGenerica.isFinita());
	}


}
