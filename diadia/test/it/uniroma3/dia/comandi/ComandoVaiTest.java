package it.uniroma3.dia.comandi;

import static org.junit.Assert.*;
import it.uniroma3.diadia.Partita;

import org.junit.Before;
import org.junit.Test;

public class ComandoVaiTest {
	private Partita partitaDiProva;
	private Comando comandoVai;

	@Before
	public void setUp() throws Exception {
		this.partitaDiProva = new Partita();
		this.comandoVai = new ComandoVai();
	}

	@Test
	public void testEsegui_direzioneCorretta() {
		comandoVai.setParametro("est");
		comandoVai.esegui(this.partitaDiProva);
		assertEquals(this.partitaDiProva.getStanzaCorrente().getNome(), "Aula N11"); //verifico aggiornamento stanza
		assertEquals(this.partitaDiProva.getGiocatore().getCfu(), 19); //verifico che scala i cfu
	}

	@Test
	public void testEsegui_direzioneVuota() {
		comandoVai.setParametro("");
		comandoVai.esegui(partitaDiProva);
		assertEquals(this.partitaDiProva.getStanzaCorrente().getNome(), "Atrio"); //verifico che non cambia stanza
		assertEquals(this.partitaDiProva.getGiocatore().getCfu(), 20); //verifico che non scala i cfu
	}
	
	@Test
	public void testEsegui_direzioneSbagliata() {
		comandoVai.setParametro("errore");
		comandoVai.esegui(partitaDiProva);
		assertEquals(this.partitaDiProva.getStanzaCorrente().getNome(), "Atrio"); //verifico che non cambia stanza
		assertEquals(this.partitaDiProva.getGiocatore().getCfu(), 20); //verifico che non scala i cfu
	}

	@Test
	public void testEsegui_direzioneInesistente() {
		comandoVai.setParametro("sud");
		comandoVai.esegui(partitaDiProva);
		
		assertEquals(this.partitaDiProva.getStanzaCorrente().getNome(), "Aula N10");
		assertEquals(this.partitaDiProva.getGiocatore().getCfu(), 19);
		
		//Lo eseguo di nuovo, ma questa volta a sud non c'è nessuna stanza
		comandoVai.esegui(partitaDiProva);

		assertEquals(this.partitaDiProva.getStanzaCorrente().getNome(), "Aula N10");
		assertEquals(this.partitaDiProva.getGiocatore().getCfu(), 19);
	}
}