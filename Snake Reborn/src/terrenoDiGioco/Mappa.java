package terrenoDiGioco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import supporto.Utility;

import LukePack.LP;

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
			int dimensione = NUMERO_STANZE_DEFAULT;
			this.codiceGenerazioneStanze = 0;
			stanze = new HashMap<>();

			// creazione stanze
			for(int i=0;i<dimensione;i++){
				this.codiceGenerazioneStanze = i;
				Stanza nuovaStanza = new Stanza(this.codiceGenerazioneStanze);
				nuovaStanza.CaricaFile("stanze\\stanza-"+i+".txt");
				stanze.put(codiceGenerazioneStanze, nuovaStanza);
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
