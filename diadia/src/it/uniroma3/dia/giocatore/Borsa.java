package it.uniroma3.dia.giocatore;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.dia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<String, Attrezzo>();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;

		return this.attrezzi.put(attrezzo.getNome(), attrezzo) == null;
	}

	public int getPesoMax() {
		return this.pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
		int pesoTotale = 0;

		for(Attrezzo a : this.attrezzi.values())
			pesoTotale += a.getPeso();

		return pesoTotale;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}


	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}

	public String toString() {
		String s = new String();
		if (!this.isEmpty()) {
			if(!s.equals(null))
				s += "\nContenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ";
			for(Attrezzo attrezzo : this.attrezzi.values()){
				if(!attrezzo.toString().equals(null))
					s += attrezzo.toString()+" ";
			}
		}
		else 
			s += "\nBorsa vuota";
		return s;
	}

}