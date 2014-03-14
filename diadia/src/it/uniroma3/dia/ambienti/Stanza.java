package it.uniroma3.dia.ambienti;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.dia.personaggi.Personaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * @author Paolo Merialdo (a partire da un'idea di Michael Kolling e David J. Barnes)
 * @see Attrezzo
 * @version 0.1
 */

public class Stanza {
	private String nome;
	private Map<String, Attrezzo> attrezzi;
	private Map<String, Stanza> stanzeAdiacenti;
	private Personaggio personaggio;
	
	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<String, Stanza>();
		this.attrezzi = new HashMap<>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanzaAdiacente stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanzaAdiacente) {
		this.stanzeAdiacenti.put(direzione, stanzaAdiacente);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione La direzione rispetto alla stanza di partenza.
	 * @return La stanza corrispondente alla direzione.
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Attrezzo[] getAttrezzi() {
		return this.attrezzi.values().toArray(new Attrezzo[this.attrezzi.size()]);
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.put(attrezzo.getNome(), attrezzo) == null;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		String s = new String();
		s += this.nome;

		s += "\nUscite: ";
		for (String direzione : this.stanzeAdiacenti.keySet())
			s += " " + direzione;

		s += "\nAttrezzi nella stanza: ";
		for (Attrezzo attrezzo: this.attrezzi.values())
			s += " " + attrezzo;
		
		if(getPersonaggio() != null)
		s += "\nNella stanza c'e' " + getPersonaggio();
		
		return s;
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo.getNome()) != null;
	}
	
	/**
	 * Ritorna il numero degli attrezzi presenti nella stanza.
	 * @return Il numero degli attrezzi
	 */
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}
	
	/**
	 * Restituisce le direzioni che e' possibile raggiungere da questa 
	 * stanza.
	 * @return String[] se ci sono delle stanza adiacenti, null altrimenti.
	 */
	public Set<String> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
	}
	
	/**
	 * Imposta il personaggio presente nella stanza.
	 * @param personaggio Il personaggio da assegnare alla stanza
	 */
	public void setPersonaggio(Personaggio personaggio) {
		this.personaggio = personaggio;
	}
	
	/**
	 * Ritorna il personaggio presente nella stanza.
	 * @return Il personaggio della stanza se presente, altrimenti null.
	 */
	public Personaggio getPersonaggio() {
		return this.personaggio;
	}
	
	@Override
	public boolean equals(Object o) {
		return this.nome.equals( ((Stanza) o).getNome() );
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}

	public void impostaUscita(String nomeUscita, Stanza sd) {
		this.impostaStanzaAdiacente(nomeUscita, sd);
	}

}