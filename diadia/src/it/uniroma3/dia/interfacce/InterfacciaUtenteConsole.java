package it.uniroma3.dia.interfacce;

import java.util.Scanner;

public class InterfacciaUtenteConsole implements InterfacciaUtente {

	private Scanner scannerDiLinee;
	
	public InterfacciaUtenteConsole(){
		scannerDiLinee = new Scanner(System.in);
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.println(messaggio);
	}

	@Override
	public String prendiIstruzione() {
		
		
		return scannerDiLinee.nextLine();
	}

	
}
