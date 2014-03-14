package it.uniroma3.dia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	private String messaggio;
	
	@Override
	public String esegui(Partita partita) {
		this.messaggio = "Comando inserito non valido!";
		
		return this.messaggio;
	}

	@Override
	public void setParametro(String parametro) {
		//Non usa nessun parametro
	}

}
