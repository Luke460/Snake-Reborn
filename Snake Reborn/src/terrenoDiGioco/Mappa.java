package terrenoDiGioco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import static supporto.Costanti.*;

public class Mappa {
	
	private HashMap<Integer,Stanza> stanze;
	private int codiceGenerazioneStanze;

	public Mappa(){
		this.codiceGenerazioneStanze = 0;
		for(int i=0;i<DIMENSIONE_STANZA_DEFAULT;i++){
			this.codiceGenerazioneStanze = i;
			Stanza nuovaStanza = new Stanza(this.codiceGenerazioneStanze);
			collegaACaso(nuovaStanza);
			nuovaStanza.CaricaFile(this.generaNomeFile());
		}
	}
	
	public Mappa(int dimensione){
		this.codiceGenerazioneStanze = 0;
		stanze = new HashMap<>();
		for(int i=0;i<dimensione;i++){
			this.codiceGenerazioneStanze = i;
			Stanza nuovaStanza = new Stanza(this.codiceGenerazioneStanze);
			collegaACaso(nuovaStanza);
			nuovaStanza.CaricaFile(this.generaNomeFile());
			stanze.put(codiceGenerazioneStanze, nuovaStanza);
		}
	}

	public Mappa(String stringaDiConferma){
		if(stringaDiConferma.equals("mappa-1")){
			int dimensione = 6;
			this.codiceGenerazioneStanze = 0;
			stanze = new HashMap<>();
			
			// creazione stanze
			for(int i=0;i<dimensione;i++){
				this.codiceGenerazioneStanze = i;
				Stanza nuovaStanza = new Stanza(this.codiceGenerazioneStanze);
				nuovaStanza.CaricaFile("stanze\\stanza-"+i+".txt");
				stanze.put(codiceGenerazioneStanze, nuovaStanza);
			}
			
			// collegamento stanze
			/*
			 *                 1--5*
			 *                 |  |
			 *   partenza > 0--4--3
			 *              |  |
			 *             *5--2
			 * 
			 */
			
			this.stanze.get(0).setCollegamenti(EST, this.stanze.get(4));
			this.stanze.get(0).coloraPorta(EST);
			this.stanze.get(4).setCollegamenti(OVEST, this.stanze.get(0));
			this.stanze.get(4).coloraPorta(OVEST);
			
			this.stanze.get(1).setCollegamenti(SUD, this.stanze.get(4));
			this.stanze.get(1).coloraPorta(SUD);
			this.stanze.get(4).setCollegamenti(NORD, this.stanze.get(1));
			this.stanze.get(4).coloraPorta(NORD);
			
			this.stanze.get(3).setCollegamenti(OVEST, this.stanze.get(4));
			this.stanze.get(3).coloraPorta(OVEST);
			this.stanze.get(4).setCollegamenti(EST, this.stanze.get(3));
			this.stanze.get(4).coloraPorta(EST);
			
			this.stanze.get(2).setCollegamenti(NORD, this.stanze.get(4));
			this.stanze.get(2).coloraPorta(NORD);
			this.stanze.get(4).setCollegamenti(SUD, this.stanze.get(2));
			this.stanze.get(4).coloraPorta(SUD);
			
			this.stanze.get(5).setCollegamenti(EST, this.stanze.get(2));
			this.stanze.get(5).coloraPorta(EST);
			this.stanze.get(2).setCollegamenti(OVEST, this.stanze.get(5));
			this.stanze.get(2).coloraPorta(OVEST);
			
			this.stanze.get(5).setCollegamenti(NORD, this.stanze.get(0));
			this.stanze.get(5).coloraPorta(NORD);
			this.stanze.get(0).setCollegamenti(SUD, this.stanze.get(5));
			this.stanze.get(0).coloraPorta(SUD);
			
			this.stanze.get(5).setCollegamenti(OVEST, this.stanze.get(1));
			this.stanze.get(5).coloraPorta(OVEST);
			this.stanze.get(1).setCollegamenti(EST, this.stanze.get(5));
			this.stanze.get(1).coloraPorta(EST);
			
			this.stanze.get(5).setCollegamenti(SUD, this.stanze.get(3));
			this.stanze.get(5).coloraPorta(SUD);
			this.stanze.get(3).setCollegamenti(NORD, this.stanze.get(5));
			this.stanze.get(3).coloraPorta(NORD);
			
		}
	}

	private String generaNomeFile() {
		// Abbiamo nella cartella 6 tipi di stanza
		int numero = (int) (Math.random()*(5+1));
		return ("stanza-"+numero+".txt");
	}

	private void collegaACaso(Stanza nuovaStanza) {
		//collega il nord
		Stanza stanzaNord = this.stanze.get((int)Math.random()*(codiceGenerazioneStanze+1));
		nuovaStanza.setCollegamenti(NORD,stanzaNord);
		//collega il est
		Stanza stanzaEst = this.stanze.get((int)Math.random()*(codiceGenerazioneStanze+1));
		nuovaStanza.setCollegamenti(EST,stanzaEst);
		//collega il sud
		Stanza stanzaSud = this.stanze.get((int)Math.random()*(codiceGenerazioneStanze+1));
		nuovaStanza.setCollegamenti(SUD,stanzaSud);
		//collega il ovest
		Stanza stanzaOvest = this.stanze.get((int)Math.random()*(codiceGenerazioneStanze+1));
		nuovaStanza.setCollegamenti(OVEST,stanzaOvest);
	}

	public void AutoRegolaMappa(int numeroGiocatori){
		// da implementare: 4 stanze per ciascun giocatore
	}

	public HashMap<Integer, Stanza> getStanze() {
		return stanze;
	}

	public void setStanze(HashMap<Integer, Stanza> mappa) {
		this.stanze = mappa;
	}

	public int getCodiceGenerazioneStanze() {
		return codiceGenerazioneStanze;
	}

	public void setCodiceGenerazioneStanze(int codiceGenerazioneStanze) {
		this.codiceGenerazioneStanze = codiceGenerazioneStanze;
	}

	public static String getNord() {
		return NORD;
	}

	public static String getSud() {
		return SUD;
	}

	public static String getOvest() {
		return OVEST;
	}

	public static String getEst() {
		return EST;
	}

	public static int getDimensioneDefault() {
		return DIMENSIONE_STANZA_DEFAULT;
	}

	public Stanza getStanzaCasualeLibera() {
		ArrayList<Stanza> stanzeMischiate = new ArrayList<Stanza>();
		stanzeMischiate.addAll(this.getStanze().values());
		Collections.shuffle(stanzeMischiate);
		for(Stanza tempStanza:stanzeMischiate){
			if(tempStanza.isLibera()) return tempStanza;
		}
		return null;
	}
	
	public Stanza TryGetStanzaCasualeLibera() {
		int nr = (int)(Math.random()*codiceGenerazioneStanze);
		Stanza tempStanza = stanze.get(nr);
		if(tempStanza.isLibera()) return tempStanza;
		return null;
	}
}
