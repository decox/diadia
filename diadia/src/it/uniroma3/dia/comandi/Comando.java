package it.uniroma3.dia.comandi;
import it.uniroma3.diadia.Partita;

public interface Comando {
	/**
	 * Esegue il comando.
	 * @param partita la partita che il giocatore sta svolgendo.
	 */
	public String esegui(Partita partita);
	
	/**
	 * Imposta l'eventuale parametro del comando.
	 * Puo anche non essere impostato in alcuni comandi.
	 * @param parametro Il parametro del comando
	 */
	public void setParametro(String parametro);
}
