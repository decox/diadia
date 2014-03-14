package it.uniroma3.dia.ambienti;

import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.dia.personaggi.Cane;
import it.uniroma3.dia.personaggi.Mago;
import it.uniroma3.dia.personaggi.Personaggio;
import it.uniroma3.dia.personaggi.Strega;

/**
 * @author 
 *
 */
public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaFinale;
	private static final String MESSAGGIO_BENVENUTO = 
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
					"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
					"I locali sono popolati da strani personaggi, " +
					"alcuni amici, altri... chissa!\n"+
					"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
					"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
					"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
					"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	
	public Labirinto(String nomefile){
		CaricatoreLabirinto c = 
				new CaricatoreLabirinto(nomefile);
			c.carica();
			this.stanzaIniziale = c.getStanzaIniziale();
			this.stanzaFinale = c.getStanzaVincente();
	}
	
	public Labirinto() {
		init();
	}
	
	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	private void init() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo attrezzoCane = new Attrezzo("chiaveStanzaBloccata", 0);

		
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		
		/* Personaggi */
		Attrezzo scettro = new Attrezzo("scettro", 1);
		Personaggio merlino = new Mago("Merlino", "Io posso far comparire oggetti dal nulla! ", scettro);
		atrio.setPersonaggio(merlino);
		
		Personaggio morgana = new Strega("Morgana", "Se sarai educato, nella stanza con più attrezzi verrai portato!");
		aulaN11.setPersonaggio(morgana);
		
		Personaggio pluto = new Cane("Pluto", "Wof! Wof!", "osso", attrezzoCane);
		aulaN10.setPersonaggio(pluto);
		
		// il gioco comincia nell'atrio
		stanzaIniziale = atrio;  
		stanzaFinale = biblioteca;
	}
	
	/**
	 * Ritorna la stanza dove il giocatore inizia la partita.
	 * @return La stanza iniziale.
	 */
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	
	/**
	 * Ritorna la stanza dove il giocatore vince la partita.
	 * @return La stanza finale.
	 */
	public Stanza getStanzaFinale() {
		return stanzaFinale;
	}
	
	/**
	 * Ritorna il messaggio di benvenuto iniziale.
	 * @return Una stringa contenente il messaggio iniziale.
	 */
	public String getMessaggioBenvenuto() {
		return MESSAGGIO_BENVENUTO;
	}

}
