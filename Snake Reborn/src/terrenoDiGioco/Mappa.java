package terrenoDiGioco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import supporto.Utility;

import LukePack.LP;

import static supporto.Costanti.*;

public class Mappa {

	private HashMap<Integer,Stanza> stanze;
	private int codGenStanze;

	public Mappa(){
		this.codGenStanze = 0;
		for(int i=0;i<DIMENSIONE_STANZA_DEFAULT;i++){
			this.codGenStanze = i;
			Stanza nuovaStanza = new Stanza(this.codGenStanze);
			collegaACaso(nuovaStanza);
			nuovaStanza.CaricaFile(this.generaNomeFile());
		}
	}

	public Mappa(int dimensione){
		this.codGenStanze = 0;
		stanze = new HashMap<>();
		for(int i=0;i<dimensione;i++){
			this.codGenStanze = i;
			Stanza nuovaStanza = new Stanza(this.codGenStanze);
			collegaACaso(nuovaStanza);
			nuovaStanza.CaricaFile(this.generaNomeFile());
			stanze.put(codGenStanze, nuovaStanza);
		}
	}

	public Mappa(String stringaDiConferma){
		if(stringaDiConferma.equals("mappa-1")){
			stanze = new HashMap<>();

			// creazione stanze
			for(codGenStanze=0;codGenStanze<NUMERO_STANZE_DEFAULT;codGenStanze++){
				Stanza nuovaStanza = new Stanza(this.codGenStanze);
				nuovaStanza.CaricaFile("stanze\\stanza-"+codGenStanze+".txt");
				stanze.put(codGenStanze, nuovaStanza);
			}

			String strutturaMappa = LP.readFile("mappe\\"+stringaDiConferma+".txt");
			ArrayList<Character> listaCaratteri = new ArrayList<Character>();
			listaCaratteri.addAll(Utility.stringaToArray(strutturaMappa));
			boolean rigaValida = false;
			int contatore = 0;
			int numeroStanza1 = -1;
			int numeroStanza2 = -1;
			String collegamento = "";
			for(char c:listaCaratteri){
				if(c!=CARATTERE_FINE_FILE){
					if(c=='>' && rigaValida == true){
						this.stanze.get(numeroStanza1).setCollegamenti(collegamento, this.stanze.get(numeroStanza2));
						this.stanze.get(numeroStanza1).coloraPorta(collegamento);
						this.stanze.get(numeroStanza2).setCollegamenti(getInversaCollegamento(collegamento), this.stanze.get(numeroStanza1));
						this.stanze.get(numeroStanza2).coloraPorta(getInversaCollegamento(collegamento));
						rigaValida = false;
						contatore = 0;
					}
					if(rigaValida){
						if(contatore==1){
							numeroStanza1=Integer.parseInt(c+"");
						}
						if(contatore==2){
							collegamento=generaCollegamento(c);
						}
						if(contatore==3){
							numeroStanza2=Integer.parseInt(c+"");
						}
						contatore++;
					}
					if(c=='<'){
						rigaValida = true;
						contatore = 1;
					}
				}
			}
		}
	}

	private String getInversaCollegamento(String collegamento) {
		if(collegamento.equals(NORD))return SUD;
		if(collegamento.equals(EST))return OVEST;
		if(collegamento.equals(SUD))return NORD;
		if(collegamento.equals(OVEST))return EST;
		return null;

	}

	private String generaCollegamento(char c) {
		if(c=='N') return NORD;
		if(c=='E') return EST;
		if(c=='S') return SUD;
		if(c=='O') return OVEST;
		return null;
	}

	private String generaNomeFile() {
		// Abbiamo nella cartella 6 tipi di stanza
		int numero = (int) (Math.random()*(5+1));
		return ("stanza-"+numero+".txt");
	}

	private void collegaACaso(Stanza nuovaStanza) {
		//collega il nord
		Stanza stanzaNord = this.stanze.get((int)Math.random()*(codGenStanze+1));
		nuovaStanza.setCollegamenti(NORD,stanzaNord);
		//collega il est
		Stanza stanzaEst = this.stanze.get((int)Math.random()*(codGenStanze+1));
		nuovaStanza.setCollegamenti(EST,stanzaEst);
		//collega il sud
		Stanza stanzaSud = this.stanze.get((int)Math.random()*(codGenStanze+1));
		nuovaStanza.setCollegamenti(SUD,stanzaSud);
		//collega il ovest
		Stanza stanzaOvest = this.stanze.get((int)Math.random()*(codGenStanze+1));
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

	public int getCodGenStanze() {
		return codGenStanze;
	}

	public void setCodGenStanze(int codiceGenerazioneStanze) {
		this.codGenStanze = codiceGenerazioneStanze;
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

	// controlla tutte le stanze e ritornami una stanza libera (se possibile)
	public Stanza getStanzaCasualeLibera_controlloSuTutteLeStanze() {
		ArrayList<Stanza> stanzeMischiate = new ArrayList<Stanza>();
		stanzeMischiate.addAll(this.getStanze().values());
		Collections.shuffle(stanzeMischiate);
		for(Stanza tempStanza:stanzeMischiate){
			if(tempStanza.isLibera()) return tempStanza;
		}
		return null;
	}

	// prendi una stanza casuale e ritornamela se è libera
	public Stanza getStanzaCasualeLibera_controlloSuStanzaSingolaCasuale() {
		int nr = (int)(Math.random()*codGenStanze);
		Stanza tempStanza = stanze.get(nr);
		if(tempStanza.isLibera()) return tempStanza;
		return null;
	}
}
