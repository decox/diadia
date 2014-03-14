package it.uniroma3.diadia;

import it.uniroma3.dia.ambienti.Labirinto;
import it.uniroma3.dia.ambienti.Stanza;
import it.uniroma3.dia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  Paolo Merialdo, Valter Crescenzi (da un'idea di Michael Kolling and David J. Barnes)
 * @see Stanza
 * @version 0.1
 */

public class Partita {
	private Giocatore giocatore;

	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;

	private boolean finita;

	public Partita(){
		this.finita = false;
		this.labirinto = new Labirinto();
		this.stanzaCorrente = labirinto.getStanzaIniziale();
		this.stanzaVincente = labirinto.getStanzaFinale();
		this.giocatore = new Giocatore();
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || !giocatore.isVivo();
	}
	
	/**
	 * Restituisce vero se il giocatore e' ancora vivo.
	 * @return Vero se il giocatore e' vivo.
	 */
	public boolean giocatoreIsVivo() {
		return giocatore.isVivo();
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
}
