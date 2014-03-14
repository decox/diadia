package it.uniroma3.dia.personaggi;

import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

public class Mago extends Personaggio {
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo bel borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla da darti ...";

	private Attrezzo attrezzo;

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		String msg;

		if(this.attrezzo != null) {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		} else
			msg = MESSAGGIO_SCUSE;

		return msg;
	}

	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		
		int pesoDimezzato = attrezzo.getPeso()/2;
	
		attrezzo = new Attrezzo(attrezzo.getNome(), pesoDimezzato);

		return attrezzo;
	}


	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita){
		String msg = null;
		if(attrezzo != null){
			attrezzo = this.modificaAttrezzo(attrezzo);
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			msg = "Grazie per avermi donato il regalo";
		}
		return msg;

	}

}
