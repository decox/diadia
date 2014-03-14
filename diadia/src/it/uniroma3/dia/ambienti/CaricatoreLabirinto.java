package it.uniroma3.dia.ambienti;

import it.uniroma3.dia.attrezzi.Attrezzo;
import it.uniroma3.dia.personaggi.Personaggio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Classe usata per caricare dinamicamente la struttura di un labirinto 
 * da un file.
 * @author luigi
 * @version 0.2
 */
public class CaricatoreLabirinto {
	private final String  STANZE   = "Stanze:";
	private final String  ATTREZZI = "Attrezzi:";
	private final String  USCITE   = "Uscite:";
	private final String  ESTREMI = "Estremi:";
	private final String PERSONAGGI = "Personaggi:";
	private BufferedReader reader;
	private Map<String, Stanza> nome2stanza;
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private int numeroLinea;

	public CaricatoreLabirinto(String nomeFile) {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.numeroLinea = 0;
		try {
			this.reader = new BufferedReader(new FileReader(nomeFile));
		} catch (FileNotFoundException e) {
			System.err.println("File " + nomeFile + " non trovato");
			e.printStackTrace();
		}
	}

	public void carica() {
		try {
			this.leggiStanze();
			this.leggiInizialeEvincente();
			this.leggiPersonaggi();
			this.leggiAttrezzi();
			this.leggiUscite();
		} catch (FormatoFileNonValidoException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private String leggiRiga(BufferedReader reader) throws FormatoFileNonValidoException {
		try {
			this.numeroLinea ++;
			String riga = reader.readLine();
			System.err.println("Letta riga " + this.numeroLinea + ": " + riga);
			return riga;
		} catch (IOException e) {
			throw new FormatoFileNonValidoException("Problemi lettura file [" + this.numeroLinea + "]");
		}
	}

	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRiga(reader);

		if (!this.stanzaValida(nomeStanzaIniziale))
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.numeroLinea + "]: stanza "+ nomeStanzaIniziale +" non definita");

		String nomeStanzaVincente = this.leggiRiga(reader);

		if (!this.stanzaValida(nomeStanzaVincente))
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.numeroLinea + "]: stanza"+ nomeStanzaVincente+" non definita");

		String token = this.leggiRiga(reader);

		if (!token.equals(PERSONAGGI))
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.numeroLinea + "]:" +ATTREZZI +" non trovato");		

		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiStanze() throws FormatoFileNonValidoException  {
		String definizioneStanza = null;
		definizioneStanza = this.leggiRiga(reader);

		if (!definizioneStanza.equals(STANZE))
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.numeroLinea + "]"+": "+STANZE +" non trovato");

		definizioneStanza = this.leggiRiga(reader);

		while (!definizioneStanza.equals(ESTREMI)) {
			if (definizioneStanza.isEmpty())
				throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
			Scanner scannerLinea = new Scanner(definizioneStanza);
			String nomeStanza = null;
			String tipoStanza = null;
			String parametroStanza = null;
			
			nomeStanza = scannerLinea.next();

			if(nomeStanza == null) {
				scannerLinea.close();
				throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
			}

			Stanza stanza;

			if(!scannerLinea.hasNext()) {
				stanza = new Stanza(nomeStanza);
			} else {
				tipoStanza = scannerLinea.next();
				
				parametroStanza = scannerLinea.next();
				
				if(parametroStanza == null) {
					scannerLinea.close();
					throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "] : manca il parametro della stanza.");
				}
				
				String nomeClasse = "it.uniroma3.dia.ambienti.Stanza" 
						+ Character.toUpperCase(tipoStanza.charAt(0))
						+ tipoStanza.substring(1);
				
				try {
					stanza = (Stanza) Class.forName(nomeClasse).getConstructor(String.class, String.class).newInstance(nomeStanza, parametroStanza);
				} catch (Exception e) {
					scannerLinea.close();
					System.err.println(e.getMessage());
					throw new FormatoFileNonValidoException("La stanza " + nomeClasse + " non esiste nel gioco.");
				}
			}

			this.nome2stanza.put(nomeStanza, stanza);
			definizioneStanza = this.leggiRiga(reader);

			scannerLinea.close();
		}

	}
	
	private void leggiPersonaggi() throws FormatoFileNonValidoException {
		String nomePersonaggio = null;
		String tipoPersonaggio = null;
		String presentazionePersonaggio = null;
		String nomeStanza = null; 
		String definizionePersonaggio = this.leggiRiga(reader);

		while (!definizionePersonaggio.equals(ATTREZZI)) {
			Scanner scannerLinea = new Scanner(definizionePersonaggio);
			nomePersonaggio = scannerLinea.next();

			if (nomePersonaggio == null) {
				scannerLinea.close();
				throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
			}
			
			tipoPersonaggio = scannerLinea.next();

			if (tipoPersonaggio == null) {
				scannerLinea.close();
				throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
			}
			
			nomeStanza = scannerLinea.next();

			if (!stanzaValida(nomeStanza)) {
				scannerLinea.close();
				throw new FormatoFileNonValidoException("Definizione personaggio " + nomePersonaggio + " errata [" + this.numeroLinea + "]" +": stanza" +nomeStanza+" inesistente");
			}
			
			presentazionePersonaggio = scannerLinea.next();
			while(scannerLinea.hasNext())
				presentazionePersonaggio += " " + scannerLinea.next();
			
			if (presentazionePersonaggio == null) {
				scannerLinea.close();
				throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
			}

			Personaggio personaggio;
			
			String nomeClasse = "it.uniroma3.dia.personaggi." 
					+ Character.toUpperCase(tipoPersonaggio.charAt(0))
					+ tipoPersonaggio.substring(1);
			
			try {
				personaggio = (Personaggio) Class.forName(nomeClasse).getConstructor(String.class, String.class).newInstance(nomePersonaggio, presentazionePersonaggio);
			} catch (Exception e) {
				scannerLinea.close();
				e.printStackTrace();
				throw new FormatoFileNonValidoException("Il personaggio " + tipoPersonaggio + " non esiste nel gioco.");
			}
			
			aggiungiPersonaggio(nome2stanza.get(nomeStanza), personaggio);
			definizionePersonaggio = this.leggiRiga(reader);
			scannerLinea.close();
		}
	}
	
	private void aggiungiPersonaggio(Stanza s, Personaggio p) {
		s.setPersonaggio(p);
	}
	
	private void posaAttrezzo(Stanza s, Attrezzo a) {
		s.addAttrezzo(a);
	}

	private void leggiAttrezzi() throws FormatoFileNonValidoException {
		String nomeAttrezzo = null;
		String pesoAttrezzo = null;
		String nomeStanza = null; 
		String definizioneAttrezzo = this.leggiRiga(reader);

		while (!definizioneAttrezzo.equals(USCITE)) {
			int peso;
			Scanner scannerLinea = new Scanner(definizioneAttrezzo);
			nomeAttrezzo = scannerLinea.next();

			if (nomeAttrezzo == null) {
				scannerLinea.close();
				throw new FormatoFileNonValidoException("Termine inaspettata del file [" + this.numeroLinea + "].");
			}

			pesoAttrezzo = scannerLinea.next();

			try {
				peso = Integer.parseInt(pesoAttrezzo);
			} catch (NumberFormatException e) {
				scannerLinea.close();
				throw new FormatoFileNonValidoException("Peso attrezzo "+nomeAttrezzo+" non valido [" + this.numeroLinea + "].");
			}

			nomeStanza = scannerLinea.next();

			if (!stanzaValida(nomeStanza)) {
				scannerLinea.close();
				throw new FormatoFileNonValidoException("Definizione attrezzo "+ nomeAttrezzo+" errata [" + this.numeroLinea + "]" +": stanza" +nomeStanza+" inesistente");
			}

			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			posaAttrezzo(nome2stanza.get(nomeStanza), attrezzo);
			definizioneAttrezzo = this.leggiRiga(reader);
			scannerLinea.close();
		}
	}

	private boolean stanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void impostaUscita(String nomeUscita, String nomeStanzaPartenza, String nomeStanzaDestinazione) {
		Stanza sPartenza = this.nome2stanza.get(nomeStanzaPartenza);
		Stanza sDestinazione = this.nome2stanza.get(nomeStanzaDestinazione);
		sPartenza.impostaStanzaAdiacente(nomeUscita, sDestinazione);
		
		System.err.println("Uscita impostata: " + nomeStanzaPartenza + " => " + nomeStanzaDestinazione + " : " + nomeUscita);
	}

	private void leggiUscite() throws FormatoFileNonValidoException {
		String nomeStanzaPartenza = null;
		String nomeUscita = null;
		String nomeStanzaDestinazione = null;
		String datiUscita = this.leggiRiga(reader);

		while (datiUscita != null) {
			Scanner scannerDiLinea = new Scanner(datiUscita);			

			while (scannerDiLinea.hasNext()) {
				nomeStanzaPartenza = scannerDiLinea.next();
				nomeUscita = scannerDiLinea.next();
				nomeStanzaDestinazione = scannerDiLinea.next();

				if (!stanzaValida(nomeStanzaPartenza)) {
					scannerDiLinea.close();
					throw new FormatoFileNonValidoException("Definizione errata uscita [" + this.numeroLinea + "]" + nomeUscita);
				}

				if (!stanzaValida(nomeStanzaDestinazione)) {
					scannerDiLinea.close();
					throw new FormatoFileNonValidoException("Definizione errata uscita [" + this.numeroLinea + "]" + nomeUscita);
				}

				impostaUscita(nomeUscita, nomeStanzaPartenza, nomeStanzaDestinazione);
			}

			datiUscita = this.leggiRiga(reader);
			scannerDiLinea.close();
		} 
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
}