package it.uniroma3.dia.comandi;

import static org.junit.Assert.*;
import it.uniroma3.dia.ambienti.Stanza;
import it.uniroma3.diadia.Partita;

import org.junit.Before;
import org.junit.Test;

public class ComandoGuardaTest {
	private Partita partita;
	private Comando comandoGuarda;
	private String direzioneEst;
	private String direzioneOvest;
	private Stanza stanzaIsolata;

	@Before
	public void setUp() throws Exception {
		this.partita = new Partita();
		this.comandoGuarda = new ComandoGuarda();
		this.direzioneEst = "est";
		this.direzioneOvest = "ovest";
		this.stanzaIsolata = new Stanza("stanzaIsolata");
	}

	@Test
	public void testComandoGuarda_suStanzeEsistenti() {
		this.comandoGuarda.setParametro(direzioneEst);
		assertEquals(this.comandoGuarda.esegui(partita),
				this.partita.getStanzaCorrente().getStanzaAdiacente(direzioneEst).toString());
		this.comandoGuarda.setParametro(direzioneOvest);
		assertEquals(this.comandoGuarda.esegui(partita),
				this.partita.getStanzaCorrente().getStanzaAdiacente(direzioneOvest).toString());


	}

	@Test
	public void testComandoGuarda_suStanzaInesistente() {
		this.partita.setStanzaCorrente(stanzaIsolata);
		this.comandoGuarda.setParametro(direzioneOvest);
		assertEquals(this.comandoGuarda.esegui(partita), "Nessuna stanza nella direzione specificata!");

	}

	@Test
	public void testComandaGuarda_suStanzaCorrente() {
		String s = new String();
		s = this.partita.getStanzaCorrente().toString();
		s += this.partita.getGiocatore().getBorsa().toString();
		assertEquals(this.comandoGuarda.esegui(partita), s);
	}


}
