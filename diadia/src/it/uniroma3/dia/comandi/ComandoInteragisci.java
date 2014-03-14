package it.uniroma3.dia.comandi;

import it.uniroma3.dia.personaggi.Personaggio;
import it.uniroma3.diadia.Partita;

public class ComandoInteragisci implements Comando {
	private String messaggio;
	
	@Override
	public String esegui(Partita partita) {
		Personaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio != null) 
			this.messaggio = personaggio.agisci(partita);
		else 
			this.messaggio = "Nessun personaggio nella stanza!";
		
		return this.messaggio;
	}
	
	public String getErrore() {
		return null;
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
	
	@Override
	public void setParametro(String parametro) {
		//Non utilizzato
	}

}
