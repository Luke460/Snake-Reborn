package game;

import static supporto.Costanti.*;

import java.awt.AWTException;

import LukePack.LP;
import audio.GestoreSuoni;
import client.Client;
import serpenti.SerpenteBotEasy;
import serpenti.SerpenteBotHard;
import serpenti.SerpenteBotMedium;
import video.GUI;

public class Main {

	static Partita partita;
	private static GUI gui;
	private static String nomeUtente;
	private static String password;
	
	public static void main(String[] args){
		avviaClient();
	}

	public static void avviaIlGioco() throws AWTException {
		partita = new Partita();
		partita.ImpostaPartita();
		gui = new GUI(partita);
		gui.initControlliDaTastiera(partita);
		GestoreSuoni.inizzializzaSuoni();
		
		GestoreSuoni.playMusicaInLoop();
		cominciaIlGioco(partita);
	}

	public static void avviaClient() {
		new Client();
		// l'utente inserisce i dati e clicca su Accedi
		//tuttoOk = ClasseEsterna.metodoHttp(nomeUtente, password);
	}



	private static void cominciaIlGioco(game.Partita partita) throws AWTException {
		creaPopoloIniziale(partita);
		gui.repaint();
		LP.waitFor(1000);
		GestoreSuoni.playSpawnSound();
		int contaRipopolaCibo=0;
		int contaRipopolaSerpenti=-RITARDO_INIZIALE_RIPOPOLAMENTO_SERPENTI;

		while(true) {
			// sistema anti-lag
			long tempoInizioAlgoritmo = System.currentTimeMillis(); 

			contaRipopolaCibo++;
			contaRipopolaSerpenti++;
			if(contaRipopolaCibo==TEMPO_RIPOPOLAMENTO_CIBO){
				partita.aggiungiCiboRandom();
				contaRipopolaCibo = 0;
			}
			if(contaRipopolaSerpenti==TEMPO_RIPOPOLAMENTO_SERPENTI){
				int rand = (int)(Math.random()*3 + 1);
				if(rand==1) {
					partita.tryInserisciBot(SerpenteBotEasy.class.getSimpleName());
				}
				if(rand==2) {
					partita.tryInserisciBot(SerpenteBotMedium.class.getSimpleName());
				}
				if(rand==3) {
					partita.tryInserisciBot(SerpenteBotHard.class.getSimpleName());
				}
				contaRipopolaSerpenti = 0;
			}

			partita.eseguiTurni();
			gui.repaint(); // lo metto dopo in modo che il giocatore ha
			//100 ms per reagire

			// sistema anti-lag
			long tempoFineAlgoritmo = System.currentTimeMillis();
			long ritardoAlgoritmo = tempoFineAlgoritmo-tempoInizioAlgoritmo;
			if(TEMPO_BASE-(ritardoAlgoritmo)>0){
				LP.waitFor(TEMPO_BASE-(ritardoAlgoritmo));
			}
		}
	}

	private static void creaPopoloIniziale(game.Partita partita) {
		partita.aggiungiCiboRandom();
		for(int i=0;i<(NUMERO_STANZE_DEFAULT/2);i++){
			partita.inserisciBot(SerpenteBotEasy.class.getSimpleName());
		}
	}

	public static String getNomeUtente() {
		return nomeUtente;
	}

	public static void setNomeUtente(String nomeUtente) {
		Main.nomeUtente = nomeUtente;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Main.password = password;
	}

}