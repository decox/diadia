/**
 * 
 */
package it.uniroma3.dia.personaggi;

import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

/**
 * @author luigi
 * @version 0.1
 */
public abstract class Personaggio {
	private String nome;
	private String presentazione;
	private boolean haSalutato;
	

	public Personaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
		
	}

	public String getNome() {
		return this.nome;
	}
	
	
	
	public boolean haSalutato() {
		return this.haSalutato;
	}

	public String saluta() {
		String risposta = "Ciao, io sono " + this.getNome() + ". "; 

		if (!haSalutato)
			risposta += this.presentazione;
		else
			risposta += "Ci siamo gia' presentati.";
		this.haSalutato = true;

		return risposta;
	}

	abstract public String agisci(Partita partita);
	
	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);
		
	@Override
	public String toString() {
		return this.getNome();
	}
}
