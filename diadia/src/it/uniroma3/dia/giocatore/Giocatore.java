package it.uniroma3.dia.giocatore;

public class Giocatore {
	private static int DEFAULT_CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		this(DEFAULT_CFU_INIZIALI);
	}
	
	public Giocatore(int cfu) {
		this.cfu = cfu;
		this.borsa = new Borsa(); 
	}
	
	/**
	 * Restituisce vero se e solo se il giocatore ha piu' di 0 cfu.
	 * @return Vero se il giocatore è vivo.
	 */
	public boolean isVivo() {
		return this.cfu > 0;
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
	
	public Borsa getBorsa() {
		return borsa;
	}
	
	public String toString() {
		String s = new String();
		
		s+= "Dati Giocatore\nCfu Rimanenti: " + this.getCfu() + "\n";
		s+= "Oggetti nella borsa:\n" + this.borsa.toString();
		
		return s;
	}
	
}
