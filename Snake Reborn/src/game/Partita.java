package game;

import java.util.HashMap;
import java.util.Iterator;

import serpenti.Serpente;
import serpenti.SerpenteBotEasy;
import serpenti.SerpenteBotHard;
import serpenti.SerpenteGiocatore;
import terrenoDiGioco.Mappa;
import terrenoDiGioco.Stanza;
import static supporto.Costanti.*;

public class Partita {

	private HashMap<String,Serpente> serpenti;
	private String nomePlayer1;
	private String nomePlayer2;
	private Mappa mappa;
	private int numeroDiGiocatoriUmani;
	private int numerettoPerSerpentiBot;

	public Partita(){
		this.serpenti = new HashMap<String,Serpente>();
		this.mappa = new Mappa("mappa-1");
		this.numerettoPerSerpentiBot = 0;
		// this.mappa.riempi();
	}

	public void ImpostaPartita() {
		/* non implementato
		LP.outln("IMPOSTAZIONI:");
		LP.out("Numero di giocatori Umani (max 2): ");
		this.numeroDiGiocatoriUmani = 0;
		while(this.numeroDiGiocatoriUmani<1 || this.numeroDiGiocatoriUmani>2){
			this.numeroDiGiocatoriUmani = LP.inInt();
		}
		if(this.numeroDiGiocatoriUmani==1 || this.numeroDiGiocatoriUmani==2){
			LP.out("Nome Player 1: ");
			this.nomePlayer1=LP.inString();
			Stanza stanzaCasuale = this.mappa.getStanzaCasualeLibera();
			Serpente serpentePlayer1 = new SerpenteGiocatore(this.nomePlayer1, stanzaCasuale);
			this.serpenti.put(this.nomePlayer1, serpentePlayer1);
		}
		if(this.numeroDiGiocatoriUmani==2){
			LP.out("Nome Player 2: ");
			this.nomePlayer2=LP.inString();
			Serpente serpentePlayer2 = new SerpenteGiocatore(this.nomePlayer2, this.mappa.getStanzaCasualeLibera());
			this.serpenti.put(this.nomePlayer2, serpentePlayer2);
		}
		 */

		// Serpente Player 1

		Stanza stanzaCasuale = this.mappa.getStanzaCasualeLibera();
		this.nomePlayer1 = NOME_PLAYER_1;
		Serpente serpentePlayer1 = new SerpenteGiocatore(this.nomePlayer1, stanzaCasuale);
		this.serpenti.put(this.nomePlayer1, serpentePlayer1);

	}

	public void inserisciBotEasy(){
		Stanza stanza = this.mappa.getStanzaCasualeLibera();
		if(stanza!=null){
			Serpente bot = new SerpenteBotEasy("bot"+numerettoPerSerpentiBot, stanza);
			this.serpenti.put("bot"+numerettoPerSerpentiBot, bot);
			numerettoPerSerpentiBot++;
		}
	}
	public void inserisciBotHard(){
		Stanza stanza = this.mappa.getStanzaCasualeLibera();
		if(stanza!=null){
			Serpente bot = new SerpenteBotHard("bot"+numerettoPerSerpentiBot, stanza);
			this.serpenti.put("bot"+numerettoPerSerpentiBot, bot);
			numerettoPerSerpentiBot++;
		}
	}

	public void TryInserisciBotEasy(){
		Stanza stanza = this.mappa.TryGetStanzaCasualeLibera();
		if(stanza!=null){
			Serpente bot = new SerpenteBotEasy("bot"+numerettoPerSerpentiBot, stanza);
			this.serpenti.put("bot"+numerettoPerSerpentiBot, bot);
			numerettoPerSerpentiBot++;
		}
	}
	public void TryInserisciBotHard(){
		Stanza stanza = this.mappa.TryGetStanzaCasualeLibera();
		if(stanza!=null){
			Serpente bot = new SerpenteBotHard("bot"+numerettoPerSerpentiBot, stanza);
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
			}
		}
	}

	public HashMap<String,Serpente> getSerpenti() {
		return serpenti;
	}

	public void setSerpenti(HashMap<String,Serpente> serpenti) {
		this.serpenti = serpenti;
	}

	public void speedUpP1() {
		// TODO Auto-generated method stub

	}

	public void speedDownP1() {
		// TODO Auto-generated method stub

	}

	public void turnLeftP1() {
		this.serpenti.get(this.nomePlayer1).getDirezione().ruotaSX();
	}

	public void turnRightP1() {
		this.serpenti.get(this.nomePlayer1).getDirezione().ruotaDX();

	}

	public void speedUpP2() {
		// TODO Auto-generated method stub

	}

	public void speedDownP2() {
		// TODO Auto-generated method stub

	}

	public void turnLeftP2() {
		this.serpenti.get(this.nomePlayer2).getDirezione().ruotaSX();

	}

	public void turnRightP2() {
		this.serpenti.get(this.nomePlayer2).getDirezione().ruotaDX();

	}

	public void gameOver() {
		System.out.println("Punteggio finale: "+this.getPunteggio());
		System.exit(-1);		
	}

	public Stanza getStanzaPlayer1() {
		return this.serpenti.get(this.nomePlayer1).getCasellaDiTesta().getStanza();
	}

	public Stanza getStanzaPlayer2() {
		return this.serpenti.get(nomePlayer2).getCasellaDiTesta().getStanza();
	}

	public int getPunteggio() {
		return this.getSerpenti().get(nomePlayer1).getHP()*10;
	}

	public String getNomePlayer1() {
		return nomePlayer1;
	}

	public void setNomePlayer1(String nomePlayer1) {
		this.nomePlayer1 = nomePlayer1;
	}

	public String getNomePlayer2() {
		return nomePlayer2;
	}

	public void setNomePlayer2(String nomePlayer2) {
		this.nomePlayer2 = nomePlayer2;
	}

	public Mappa getMappa() {
		return mappa;
	}

	public void setMappa(Mappa mappa) {
		this.mappa = mappa;
	}

	public int getNumetoDiGiocatoriUmani() {
		return numeroDiGiocatoriUmani;
	}

	public void setNumetoDiGiocatoriUmani(int numetoDiGiocatoriUmani) {
		this.numeroDiGiocatoriUmani = numetoDiGiocatoriUmani;
	}

	public void aggiungiCiboRandom() {
		for(Stanza s:this.mappa.getStanze().values()){
			s.aggiungiCiboInPosizioneCasuale();
		}
	}

	public void resuscitaPlayer1() {
		Serpente p1 = this.serpenti.get(NOME_PLAYER_1);
		if(p1.isMorto()){
			game.Main.suonoSpawn.playClip();
			Stanza stanzaP1 = p1.getStanza();
			this.serpenti.remove(NOME_PLAYER_1);
			Serpente serpenteGiocatore1 = new SerpenteGiocatore(NOME_PLAYER_1, stanzaP1);
			this.serpenti.put(NOME_PLAYER_1, serpenteGiocatore1);
		}
	}

	public int getPunteggioPlayer1() {
		int punteggio = 0;
		Serpente p1 = this.serpenti.get(NOME_PLAYER_1);
		punteggio += p1.getCiboPreso()*MOLTIPLICATORE_PUNTEGGIO_CIBO;
		return punteggio;
	}
	
}
