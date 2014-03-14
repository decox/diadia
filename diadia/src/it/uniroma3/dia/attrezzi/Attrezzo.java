package it.uniroma3.dia.attrezzi;

import it.uniroma3.dia.ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  Paolo Merialdo
 * @see Stanza
 * @version 0.1
 *
 */
public class Attrezzo implements Comparable<Attrezzo> {
	private String nome;
	private int peso;

	/**
	 * Crea un attrezzo
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}
	
	public void setPeso(int peso){
		this.peso = peso;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	@Override
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode() + this.peso;
	}
	
	@Override
	public boolean equals(Object o) {
		Attrezzo attrezzo = (Attrezzo) o;
		return this.nome.equals(attrezzo.getNome())
				&& this.peso == attrezzo.peso;
	}
	
	@Override
	public int compareTo(Attrezzo attrezzo) {
		return this.getNome().compareTo(attrezzo.getNome());
	}

}