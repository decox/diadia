package it.uniroma3.dia.comandi;

import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

public class ComandoPosa implements Comando {
	private String nomeAttrezzo;
	private Attrezzo attrezzoDaPosare;
	private String messaggio;
	
	@Override
	public String esegui(Partita partita) {
		this.attrezzoDaPosare = partita.getGiocatore().getBorsa().removeAttrezzo(this.nomeAttrezzo);
		if(this.attrezzoDaPosare == null) {
			this.messaggio = "Attrezzo selezionato non presente nella borsa!";
			this.messaggio += "Ecco gli attrezzi nella borsa:";
			this.messaggio += partita.getGiocatore().getBorsa().toString();
			return this.messaggio;
		}
		
		partita.getStanzaCorrente().addAttrezzo(this.attrezzoDaPosare);
		this.messaggio = "Attrezzo posato nella stanza.";
		
		return this.messaggio;
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

}
