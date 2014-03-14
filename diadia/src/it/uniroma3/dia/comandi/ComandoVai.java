package it.uniroma3.dia.comandi;
import it.uniroma3.dia.ambienti.Stanza;
import it.uniroma3.diadia.Partita;

public class ComandoVai implements Comando {
	private String direzione;
	private String messaggio;
	
	public String esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		
		if(this.direzione == null) {
			this.messaggio = "Dove vuoi andare? Devi specificare una Direzione!";
			return this.messaggio;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		
		if (prossimaStanza == null) {
			this.messaggio = "Direzione Inesistente.";
			return this.messaggio;
		}

		partita.setStanzaCorrente(prossimaStanza);
		this.messaggio = partita.getStanzaCorrente().getNome() + "\n";
		
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		this.messaggio += "Cfu rimanenti:" + partita.getGiocatore().getCfu() + "\n";
		
		return this.messaggio;
	}

	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

}
