package game;

import static supporto.Costanti.*;

import java.awt.AWTException;

import LukePack.LP;
import audio.GestoreSuoni;
import client.Client;
import terrenoDiGioco.PopolatoreSerpenti;
import video.GUI;

public class Main {

	static Partita partita;
	private static GUI gui;
	private static String nomeUtente;
	private static String password;

	public static void main(String[] args){
		partita = new Partita();
		avviaClient(partita);
	}

	public static void avviaIlGioco() throws AWTException {
		partita.ImpostaPartita();
		gui = new GUI(partita);
		gui.initControlliDaTastiera(partita);
		GestoreSuoni.inizzializzaSuoni();

		GestoreSuoni.playMusicaInLoop();
		cominciaIlGioco(partita);
	}

	public static void avviaClient(Partita partita) {
		new Client(partita);
		// l'utente inserisce i dati e clicca su Accedi
		// String errNome = null;
		// String errPassword = null;
		// boolean tuttoOk;
		// tuttoOk = ClasseEsterna.metodoHttp(nomeUtente, password, errNome, errPassword);
		// la classe esterna fornisce metodi per leggere il record del profilo ed eventualmente
		// inviare il nuovo
	}



	private static void cominciaIlGioco(Partita partita) throws AWTException {
		PopolatoreSerpenti popolatoreSerpenti = new PopolatoreSerpenti(partita);
		popolatoreSerpenti.creaPopoloIniziale();
		partita.aggiungiCiboRandom();
		gui.repaint();
		LP.waitFor(1000);
		GestoreSuoni.playSpawnSound();
		int contaCicli=0;

		while(true) {
			// sistema anti-lag
			long tempoInizioAlgoritmo = System.currentTimeMillis(); 

			contaCicli++;

			if((contaCicli%TEMPO_RIPOPOLAMENTO_CIBO)==0){
				partita.aggiungiCiboRandom();
			}
			
			if(partita.getFattorePopolazione()==1){
				if((contaCicli%(TEMPO_RIPOPOLAMENTO_SERPENTI*3)==0)){ // ogni n sec
					popolatoreSerpenti.provaAdInserireUnSerpente();
				}
			} else if(partita.getFattorePopolazione()==2){
				if((contaCicli%(TEMPO_RIPOPOLAMENTO_SERPENTI)==0)){ // ogni 3n sec
					popolatoreSerpenti.provaAdInserireUnSerpente();
				}
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