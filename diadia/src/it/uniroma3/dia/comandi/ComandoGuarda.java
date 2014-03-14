package it.uniroma3.dia.comandi;
import it.uniroma3.dia.ambienti.Stanza;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	private String direzione;
	private String messaggio;

	@Override
	public String esegui(Partita partita) {
		if(this.direzione == null) {
			this.messaggio = partita.getStanzaCorrente().toString();
			this.messaggio += partita.getGiocatore().getBorsa().toString();
		}
		else {
			Stanza stanza = partita.getStanzaCorrente().getStanzaAdiacente(this.direzione);
			
			if(stanza == null)
				this.messaggio = "Nessuna stanza nella direzione specificata!";
			else
				this.messaggio = stanza.toString();
		}

		return this.messaggio;
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

}
