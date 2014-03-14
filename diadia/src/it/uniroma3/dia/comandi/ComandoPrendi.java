package it.uniroma3.dia.comandi;
import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

public class ComandoPrendi implements Comando {
	private String nomeAttrezzo;
	private Attrezzo attrezzoPreso;
	private String messaggio;
	
	@Override
	public String esegui(Partita partita) {
		this.attrezzoPreso = partita.getStanzaCorrente().getAttrezzo(this.nomeAttrezzo);
		
		if(this.attrezzoPreso == null) {
			this.messaggio = "Attrezzo selezionato non presente nella stanza!\n";
			this.messaggio += "Usa il comando 'guarda' per vedere gli oggetti presenti.";
			return this.messaggio;
		}
		
		if(partita.getGiocatore().getBorsa().addAttrezzo(attrezzoPreso))
			partita.getStanzaCorrente().removeAttrezzo(attrezzoPreso);
		else
			this.messaggio = "Non puoi prendere attrezzi! Hai la borsa piena!\n";
		
		this.messaggio += partita.getGiocatore().getBorsa().toString();
		
		return this.messaggio;
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

}
