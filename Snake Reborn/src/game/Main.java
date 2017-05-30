package game;

import static supporto.Costanti.*;

import java.awt.AWTException;

import LukePack.LP;
import audio.SuonoWAV;
import video.GUI;

public class Main {

	static Partita partita;
	private static GUI gui;
	public static SuonoWAV suonoSlain;
	public static SuonoWAV suonoSpawn;
	public static SuonoWAV suonoExplode;
	public static SuonoWAV suonoTake;
	public static SuonoWAV suonoMusic;

	public static void main(String[] args) throws Exception {

		partita = new Partita();
		partita.ImpostaPartita();
		gui = new GUI(partita);
		gui.initControlliDaTastiera(partita);
		inizializzaSuoni();
		suonoMusic.loopClip();
		cominciaIlGioco(partita);
	}

	private static void inizializzaSuoni() {
		suonoSlain = new SuonoWAV("suoni\\slain.wav");
		suonoSpawn = new SuonoWAV("suoni\\spawn.wav");
		suonoExplode = new SuonoWAV("suoni\\explode.wav");
		suonoTake = new SuonoWAV("suoni\\take.wav");
		suonoMusic = new SuonoWAV("suoni\\musicCutted.wav");
	}

	private static void cominciaIlGioco(game.Partita partita) throws AWTException {
		creaPopoloIniziale(partita);
		gui.repaint();
		LP.waitFor(1000);
		suonoSpawn.playClip();
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
				int rand = (int)(Math.random()*2 + 1);
				if(rand==1) {
					partita.TryInserisciBotEasy();
				}
				if(rand==2) {
					partita.TryInserisciBotHard();
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
		partita.inserisciBotEasy();
		partita.inserisciBotEasy();
		partita.inserisciBotEasy();
	}

}