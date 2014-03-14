package it.uniroma3.dia.ambienti;

public class StanzaBuia extends Stanza {
	private String nomeAttrezzoIlluminante;

	public StanzaBuia(String nome, String nomeAttrezzoIlluminante) {
		super(nome);
		this.nomeAttrezzoIlluminante = nomeAttrezzoIlluminante;
	}

	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(nomeAttrezzoIlluminante))
			return super.getDescrizione();
		else 
			return "Qui c'e' un buio pesto! Servirebbe un lume nella stanza!";
	}

}
