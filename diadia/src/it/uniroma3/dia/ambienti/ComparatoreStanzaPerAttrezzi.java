package it.uniroma3.dia.ambienti;

import it.uniroma3.dia.ambienti.Stanza;
import java.util.Comparator;

public class ComparatoreStanzaPerAttrezzi implements Comparator<Stanza> {

	@Override
	public int compare(Stanza stanza1, Stanza stanza2) {
		int attrezziStanza1 = stanza1.getNumeroAttrezzi();
		int attrezziStanza2 = stanza2.getNumeroAttrezzi();
		
		return attrezziStanza1 - attrezziStanza2;
	}

}
