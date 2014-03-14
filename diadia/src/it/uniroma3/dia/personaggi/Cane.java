package it.uniroma3.dia.personaggi;

import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

public class Cane extends Personaggio {	

	private static final String MESSAGGIO_INTERAGISCI = "Grrrrrrrrrr!!";
	private String nomeCiboPreferito;
	private Attrezzo attrezzoDonato;

	public Cane(String nome, String presentazione , String nomeCiboPreferito, Attrezzo attrezzoDonato) {
		super(nome, presentazione);
		this.nomeCiboPreferito = nomeCiboPreferito;
		this.attrezzoDonato = attrezzoDonato;
	}

	@Override
	public String agisci(Partita partita) {

		int cfu = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfu -1);

		return MESSAGGIO_INTERAGISCI;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg = null;
		if(this.nomeCiboPreferito != null)
			if(attrezzo.getNome().equals(this.nomeCiboPreferito)){
				partita.getStanzaCorrente().addAttrezzo(attrezzoDonato);
				msg = "bau-bau";
			} else{
				partita.getStanzaCorrente().addAttrezzo(attrezzo);
				msg = "grrrrrr";
			}

		return msg;
	}

}

