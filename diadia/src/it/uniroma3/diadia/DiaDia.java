package it.uniroma3.diadia;
import it.uniroma3.dia.comandi.Comando;
import it.uniroma3.dia.comandi.FabbricaDiComandi;
import it.uniroma3.dia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.dia.interfacce.InterfacciaUtente;
import it.uniroma3.dia.interfacce.InterfacciaUtenteConsole;
import it.uniroma3.diadia.Partita;



/**
 *  Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 *  Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 *  Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  Paolo Merialdo (da un'idea di Michael Kolling and David J. Barnes) *
 * @version 0.1
 */

public class DiaDia {
	private Partita partita;
	private InterfacciaUtente interfaccia;
	
	public DiaDia() {
		this.partita = new Partita();
		this.interfaccia = new InterfacciaUtenteConsole();
	}

	public void gioca() {
		String istruzione; 

		this.interfaccia.mostraMessaggio(partita.getLabirinto().getMessaggioBenvenuto());
		do	{	
			istruzione = this.interfaccia.prendiIstruzione();
		}
		while (!processaIstruzione(istruzione));

	}   


	/**
	 * Processa una istruzione 
	 * @param istruzione Il comando da eseguire
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();

		comandoDaEseguire = factory.costruisciComando(istruzione);
		this.interfaccia.mostraMessaggio(comandoDaEseguire.esegui(this.partita)); 

		if (this.partita.vinta())
			this.interfaccia.mostraMessaggio("Hai vinto");
		if (!this.partita.giocatoreIsVivo())
			this.interfaccia.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}   

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}