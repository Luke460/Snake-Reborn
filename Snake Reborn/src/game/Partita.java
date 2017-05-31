package game;

import java.util.HashMap;
import java.util.Iterator;

import serpenti.Serpente;
import serpenti.SerpenteBotEasy;
import serpenti.SerpenteBotHard;
import serpenti.SerpenteBotMedium;
import serpenti.SerpenteGiocatore;
import supporto.Direzione;
import terrenoDiGioco.Mappa;
import terrenoDiGioco.Stanza;
import static supporto.Costanti.*;

public class Partita {

	private HashMap<String,Serpente> serpenti;
	private String nomePlayer1;
	private Mappa mappa;
	private int numerettoPerSerpentiBot;

	public Partita(){
		this.serpenti = new HashMap<String,Serpente>();
		this.mappa = new Mappa("mappa-1");
		this.numerettoPerSerpentiBot = 0;
		// this.mappa.riempi();
	}

	public void ImpostaPartita() {
		// un solo giocatore
		Stanza stanzaCasuale = this.mappa.getStanzaCasualeLibera();
		this.nomePlayer1 = NOME_PLAYER_1;
		Serpente serpentePlayer1 = new SerpenteGiocatore(this.nomePlayer1, stanzaCasuale);
		this.serpenti.put(this.nomePlayer1, serpentePlayer1);

	}

	public void inserisciBot(String classe){
		Stanza stanza = this.mappa.getStanzaCasualeLibera();
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
	
	// metodi try: solo se si trova una stanza casuale che è anche liber
	public void tryInserisciBot(String classe){
		Stanza stanza = this.mappa.TryGetStanzaCasualeLibera();
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
			} else if(!s.getClass().toString().equals(SerpenteGiocatore.class.toString())){
				iteratore.remove();
			}
		}
	}

	public HashMap<String,Serpente> getSerpenti() {
		return serpenti;
	}

	public void setSerpenti(HashMap<String,Serpente> serpenti) {
		this.serpenti = serpenti;
	}

	public void gameOver() {
		System.out.println("Punteggio finale: "+this.getPunteggio());
		System.exit(-1);		
	}

	public Stanza getStanzaPlayer1() {
		return this.serpenti.get(this.nomePlayer1).getCasellaDiTesta().getStanza();
	}

	public int getPunteggio() {
		return this.getSerpenti().get(nomePlayer1).getHP()*MOLTIPLICATORE_PUNTEGGIO_CIBO;
	}

	public String getNomePlayer1() {
		return nomePlayer1;
	}

	public void setNomePlayer1(String nomePlayer1) {
		this.nomePlayer1 = nomePlayer1;
	}

	public Mappa getMappa() {
		return mappa;
	}

	public void setMappa(Mappa mappa) {
		this.mappa = mappa;
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

	public void goUpP1() {
		Serpente serpente = this.serpenti.get(NOME_PLAYER_1);
		Direzione dir = serpente.getDirezione();
		if(!(dir.getX()==0 && dir.getY()==1)){
			dir.setX(0);
			dir.setY(-1);
		}	
	}

	public void goDownP1() {
		Serpente serpente = this.serpenti.get(NOME_PLAYER_1);
		Direzione dir = serpente.getDirezione();
		if(!(dir.getX()==0 && dir.getY()==-1)){
			dir.setX(0);
			dir.setY(1);
		}	
		
	}

	public void goLeftP1() {
		Serpente serpente = this.serpenti.get(NOME_PLAYER_1);
		Direzione dir = serpente.getDirezione();
		if(!(dir.getX()==1 && dir.getY()==0)){
			dir.setX(-1);
			dir.setY(0);
		}	
	}

	public void goRightP1() {
		Serpente serpente = this.serpenti.get(NOME_PLAYER_1);
		Direzione dir = serpente.getDirezione();
		if(!(dir.getX()==-1 && dir.getY()==0)){
			dir.setX(1);
			dir.setY(0);
		}		
	}
	
	public void turnLeftP1() {
		this.serpenti.get(this.nomePlayer1).getDirezione().ruotaSX();
	}

	public void turnRightP1() {
		this.serpenti.get(this.nomePlayer1).getDirezione().ruotaDX();

	}
	
}
