package game;

import java.util.HashMap;
import java.util.Iterator;

import audio.GestoreSuoni;
import gestorePunteggi.GestorePunteggi;
import serpenti.Serpente;
import serpenti.SerpenteBotEasy;
import serpenti.SerpenteBotHard;
import serpenti.SerpenteBotMedium;
import serpenti.SerpenteGiocatore;
import terrenoDiGioco.Mappa;
import terrenoDiGioco.Stanza;
import static supporto.Costanti.*;

public class Partita {

	private HashMap<String,Serpente> serpenti;
	private String nomePlayer1;
	private Mappa mappa;
	private int numerettoPerSerpentiBot;
	private boolean ilGiocatoreHaFattoLaMossa;
	private int livello;
	private int fattorePopolazione;
	private int vecchioRecord;
	private UserLocal userLocal;
	private boolean ospite;

	public Partita(){
		GestorePunteggi.inizializza(this);
		this.ilGiocatoreHaFattoLaMossa = false;
		this.serpenti = new HashMap<String,Serpente>();
		this.mappa = new Mappa("mappa-1");
		this.numerettoPerSerpentiBot = 0;
		this.vecchioRecord = GestorePunteggi.getPunteggioDaFileRecord();
		// this.mappa.riempi();
	}

	public void ImpostaPartita() {
		// un solo giocatore
		Stanza stanzaCasuale = this.mappa.getStanzaCasualeLibera_controlloSuTutteLeStanze();
		this.nomePlayer1 = NOME_PLAYER_1;
		Serpente serpentePlayer1 = new SerpenteGiocatore(this.nomePlayer1, stanzaCasuale);
		this.serpenti.put(this.nomePlayer1, serpentePlayer1);

	}

	public void inserisciBotAccurato(String classe){
		Stanza stanza = this.mappa.getStanzaCasualeLibera_controlloSuTutteLeStanze();
		if(stanza!=null){
			Serpente bot = null;
			if(classe.equals(SerpenteBotEasy.class.getSimpleName())){
				bot = new SerpenteBotEasy("bot"+numerettoPerSerpentiBot, stanza);
			} else if(classe.equals(SerpenteBotMedium.class.getSimpleName())){
				bot = new SerpenteBotMedium("bot"+numerettoPerSerpentiBot, stanza);
			} else if(classe.equals(SerpenteBotHard.class.getSimpleName())){
				bot = new SerpenteBotHard("bot"+numerettoPerSerpentiBot, stanza);
			}
			this.serpenti.put("bot"+numerettoPerSerpentiBot, bot);
			numerettoPerSerpentiBot++;
		}
	}

	// metodi try: solo se si trova una stanza casuale che è anche libera
	public void inserisciBotVeloce(String classe){
		Stanza stanza = this.mappa.getStanzaCasualeLibera_controlloSuStanzaSingolaCasuale();
		if(stanza!=null){
			Serpente bot = null;
			if(classe.equals(SerpenteBotEasy.class.getSimpleName())){
				bot = new SerpenteBotEasy("bot"+numerettoPerSerpentiBot, stanza);
			} else if(classe.equals(SerpenteBotMedium.class.getSimpleName())){
				bot = new SerpenteBotMedium("bot"+numerettoPerSerpentiBot, stanza);
			} else if(classe.equals(SerpenteBotHard.class.getSimpleName())){
				bot = new SerpenteBotHard("bot"+numerettoPerSerpentiBot, stanza);
			}
			this.serpenti.put("bot"+numerettoPerSerpentiBot, bot);
			numerettoPerSerpentiBot++;
		}
	}

	public void eseguiTurni() {
		Iterator<Serpente> iteratore = this.getSerpenti().values().iterator();	
		while(iteratore.hasNext()){
			Serpente s = iteratore.next();
			if(!s.isMorto()){
				s.FaiMossa();
			} else if(!s.getClass().getSimpleName().equals(SerpenteGiocatore.class.getSimpleName())){
				// rimuove tutti tranne il giocatore 1 (la sua stanza deve essere comunque visualizzata)
				iteratore.remove();
			}
		}
		ilGiocatoreHaFattoLaMossa = false;
	}
	
	public Serpente getSerpentePlayer1(){
		return this.serpenti.get(NOME_PLAYER_1);
	}

	public HashMap<String,Serpente> getSerpenti() {
		return serpenti;
	}

	public void setSerpenti(HashMap<String,Serpente> serpenti) {
		this.serpenti = serpenti;
	}

	public void gameOver() {
		System.exit(-1);		
	}

	public Mappa getMappa() {
		return mappa;
	}

	public void setMappa(Mappa mappa) {
		this.mappa = mappa;
	}

	public void resuscitaPlayer1() {
		Serpente p1 = this.serpenti.get(NOME_PLAYER_1);
		if(p1.isMorto()){
			GestoreSuoni.playSpawnSound();
			Stanza stanzaP1 = p1.getStanza();
			Stanza stanzaAlternativa = this.mappa.getStanzaCasualeLibera_controlloSuTutteLeStanze();
			if(stanzaAlternativa!=null){
				stanzaP1 = stanzaAlternativa;
			}
			this.serpenti.remove(NOME_PLAYER_1);
			Serpente serpenteGiocatore1 = new SerpenteGiocatore(NOME_PLAYER_1, stanzaP1);
			this.serpenti.put(NOME_PLAYER_1, serpenteGiocatore1);
		}
	}
	
	public int getNumeroDiSerpenti(){
		return this.serpenti.size();
	}

	public int getLivello() {
		return livello;
	}

	public void setLivello(int livello) {
		this.livello = livello;
	}

	public int getFattorePopolazione() {
		return fattorePopolazione;
	}

	public void setFattorePopolazione(int i) {
		this.fattorePopolazione = i;
	}

	public int getVecchioRecord() {
		return vecchioRecord;
	}

	public void setVecchioRecord(int vecchioRecord) {
		this.vecchioRecord = vecchioRecord;
	}

	public boolean isIlGiocatoreHaFattoLaMossa() {
		return ilGiocatoreHaFattoLaMossa;
	}

	public void setIlGiocatoreHaFattoLaMossa(boolean ilGiocatoreHaFattoLaMossa) {
		this.ilGiocatoreHaFattoLaMossa = ilGiocatoreHaFattoLaMossa;
	}

	public UserLocal getUtente() {
		return userLocal;
	}

	public void setUtente(UserLocal userLocal) {
		this.userLocal = userLocal;
	}

	public boolean isOspite() {
		return ospite;
	}

	public void setOspite(boolean ospite) {
		this.ospite = ospite;
	}
}
