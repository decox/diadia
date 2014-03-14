package it.uniroma3.dia.personaggi;

import java.util.HashSet;
import java.util.Set;

import it.uniroma3.dia.ambienti.ComparatoreStanzaPerAttrezzi;
import it.uniroma3.dia.ambienti.Stanza;
import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

public class Strega extends Personaggio {
	private static final String MESSAGGIO_HA_SALUTATO = "Sei un ragazzo molto educato, " +
			"per ricompensarti in una stanza piena di attrezzi sei stato portato! ";
	private static final String MESSAGGIO_NON_HA_SALUTATO = "Sei un vero maleducato, " + 
			"per punirti in una stanza con pochi attrezzi verrai teletrasportato!";
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		Set<String> direzioni = partita.getStanzaCorrente().getDirezioni();
		Set<Stanza> stanzeAdiacenti = new HashSet<>();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza;
		
		for(String direzione : direzioni) 
			stanzeAdiacenti.add(stanzaCorrente.getStanzaAdiacente(direzione));
		
		if(haSalutato()) {
			prossimaStanza = java.util.Collections.max(stanzeAdiacenti, new ComparatoreStanzaPerAttrezzi());
			msg = MESSAGGIO_HA_SALUTATO;
		} else {
			prossimaStanza = java.util.Collections.min(stanzeAdiacenti, new ComparatoreStanzaPerAttrezzi());
			msg = MESSAGGIO_NON_HA_SALUTATO;
		}
		
		partita.setStanzaCorrente(prossimaStanza);
		
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "ahahahahahahahahahah";
	}
	
}
