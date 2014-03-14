package it.uniroma3.dia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	private static String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda", 
		"interagisci", "saluta","regala"};
	private String messaggio;
	
	/**
	 * Stampa l'elenco dei comandi.
	 */
	@Override
	public String esegui(Partita partita) {
		this.messaggio = elencoComandi[0] + " ";
		for(int i=1; i< elencoComandi.length; i++) 
			this.messaggio += elencoComandi[i] + " ";
		
		return this.messaggio;
	}
	
	@Override
	public void setParametro(String parametro) {
		//Il comando aiuto non ha Parametri.
	}

}
