package it.uniroma3.dia.comandi;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	private String messaggio;
	
	@Override
	public String esegui(Partita partita) {
		this.messaggio = "Grazie di aver giocato!";  // si desidera smettere
		partita.setFinita();
		
		return this.messaggio;
	}

	@Override
	public void setParametro(String parametro) {
		//Fine non ha nessun parametro.
	}

}
