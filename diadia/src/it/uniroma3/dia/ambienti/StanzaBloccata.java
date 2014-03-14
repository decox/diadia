package it.uniroma3.dia.ambienti;

public class StanzaBloccata extends Stanza {
	private String direzioneBloccata;
	private String nomeChiave;

	public StanzaBloccata(String nome, String direzioneBloccata, 
			String nomeChiave) {
		super(nome);

		this.direzioneBloccata = direzioneBloccata;
		this.nomeChiave = nomeChiave;
	}

	public String getDescrizione() {
		String descrizione;
		descrizione = super.getDescrizione() + "\n";
		descrizione += "Nella stanza c'e' una direzione bloccata : " + 
				this.direzioneBloccata + "\n" +
				"Per sbloccare la direzione e' neccessario l'attrezzo " + 
				this.nomeChiave + "\n";

		return descrizione;
	}

	public Stanza getStanzaAdiacente(String direzione) {
		if(!this.direzioneBloccata.equals(direzione) || 
				super.hasAttrezzo(this.nomeChiave))
			return super.getStanzaAdiacente(direzione);
		else
			return this;
	}


}
