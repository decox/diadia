package it.uniroma3.dia.comandi;

import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.dia.giocatore.Borsa;
import it.uniroma3.dia.personaggi.Personaggio;
import it.uniroma3.diadia.Partita;

public class ComandoRegala implements Comando {
	private String nomeAttrezzo;
	private String messaggio;

	@Override
	public String esegui(Partita partita) {
		Personaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		Borsa borsaGiocatore = partita.getGiocatore().getBorsa();
		Attrezzo attrezzoBorsa;
		
		if(personaggio == null) {
			this.messaggio = "Nessun personaggio nella stanza!";
			return this.messaggio;
		}
		
		if(!borsaGiocatore.hasAttrezzo(nomeAttrezzo)) {
			this.messaggio = "L'attrezzo selezionato non e' presente nella borsa!";	
			return this.messaggio;
		}
		
		attrezzoBorsa = borsaGiocatore.getAttrezzo(nomeAttrezzo);
		borsaGiocatore.removeAttrezzo(nomeAttrezzo);
		
		this.messaggio = personaggio.riceviRegalo(attrezzoBorsa, partita);
		
		return this.messaggio;
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

}
