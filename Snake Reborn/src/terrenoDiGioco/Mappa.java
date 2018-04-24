package terrenoDiGioco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import supporto.OSdetector;
import supporto.Utility;

import LukePack.LP;

import static supporto.Costanti.*;

public class Mappa {

	private HashMap<Integer,Stanza> stanze;
	private int codGenStanze;
	private int dimensione;
/*
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
*/
	public Mappa(String nomeMappa){
		//if(nomeMappa.equals("mappa-1")){
		stanze = new HashMap<>();

		// creazione stanze
		boolean continua=true;
		codGenStanze=0;
		while(continua) {
			Stanza nuovaStanza = new Stanza(this.codGenStanze);
			try {
			nuovaStanza.CaricaFile("stanze"+OSdetector.getPathSeparator()+"stanza-"+codGenStanze+".txt");
			stanze.put(codGenStanze, nuovaStanza);
			codGenStanze++;
			}catch (Exception e) {
				continua=false;
			}
		}
		this.dimensione = codGenStanze;
		//System.out.println(dimensione);
		
		for(codGenStanze=0;codGenStanze<this.dimensione;codGenStanze++){
			Stanza nuovaStanza = new Stanza(this.codGenStanze);
			nuovaStanza.CaricaFile("stanze"+OSdetector.getPathSeparator()+"stanza-"+codGenStanze+".txt");
			stanze.put(codGenStanze, nuovaStanza);
		}

		String strutturaMappa = LP.readFile("mappe"+OSdetector.getPathSeparator()+nomeMappa+".txt");
		ArrayList<Character> listaCaratteri = new ArrayList<Character>();
		listaCaratteri.addAll(Utility.stringaToArray(strutturaMappa));
		boolean rigaInLettura = false;
		int contatore = 0;
		int numeroStanza1 = -1;
		int numeroStanza2 = -1;
		String collegamento = "";
		for(char c:listaCaratteri){
			if(c!=CARATTERE_FINE_FILE){
				if(c=='>' && rigaInLettura == true){
					//fine riga
					this.stanze.get(numeroStanza1).setCollegamenti(collegamento, this.stanze.get(numeroStanza2));
					this.stanze.get(numeroStanza1).coloraPorta(collegamento);
					this.stanze.get(numeroStanza2).setCollegamenti(getInversaCollegamento(collegamento), this.stanze.get(numeroStanza1));
					this.stanze.get(numeroStanza2).coloraPorta(getInversaCollegamento(collegamento));
					rigaInLettura = false;
					contatore = 0;
				}
				if(rigaInLettura){
					if(contatore==1){
						numeroStanza1=getNumber(c);
					}
					if(contatore==2){
						collegamento=generaCollegamento(c);
					}
					if(contatore==3){
						numeroStanza2=getNumber(c);
					}
					contatore++;
				}
				if(c=='<'){
					//inizio riga
					rigaInLettura = true;
					contatore = 1;
				}
			}
		}

	}
	
	public int getNumber(char c) {
		if(c=='0') return 0;
		if(c=='1') return 1;
		if(c=='2') return 2;
		if(c=='3') return 3;
		if(c=='4') return 4;
		if(c=='5') return 5;
		if(c=='6') return 6;
		if(c=='7') return 7;
		if(c=='8') return 8;
		if(c=='9') return 9;
		if(c=='a') return 10;
		if(c=='b') return 11;
		if(c=='c') return 12;
		if(c=='d') return 13;
		if(c=='e') return 14;
		if(c=='f') return 15;
		if(c=='g') return 16;
		if(c=='h') return 17;
		if(c=='i') return 18;
		if(c=='j') return 19;
		if(c=='k') return 20;
		if(c=='l') return 21;
		if(c=='m') return 22;
		if(c=='n') return 23;
		if(c=='o') return 24;
		if(c=='p') return 25;
		if(c=='q') return 26;
		if(c=='r') return 27;
		if(c=='s') return 28;
		if(c=='t') return 29;
		if(c=='u') return 30;
		if(c=='v') return 31;
		if(c=='w') return 32;
		if(c=='x') return 33;
		if(c=='y') return 34;
		if(c=='z') return 35;
		return 36;
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

	// prendi una stanza casuale e ritornamela se � libera
	public Stanza getStanzaCasualeLibera_controlloSuStanzaSingolaCasuale() {
		int nr = (int)(Math.random()*codGenStanze);
		Stanza tempStanza = stanze.get(nr);
		if(tempStanza.isLibera()) return tempStanza;
		return null;
	}
}
