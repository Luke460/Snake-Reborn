package popolatori;

import static supporto.Costanti.*;

import game.Partita;
import serpenti.SerpenteBotEasy;
import serpenti.SerpenteBotHard;
import serpenti.SerpenteBotMedium;

public class PopolatoreSerpenti {
	Partita partita;
	
	public static void creaPopoloIniziale(Partita partita) {
		
		int numeroSerpentiIniziali = 0;
		if(partita.getFattorePopolazione()==1) numeroSerpentiIniziali = NUMERO_SERPENTI_INIZIALI_BASSO;
		if(partita.getFattorePopolazione()==2) numeroSerpentiIniziali = NUMERO_SERPENTI_INIZIALI_ALTO;

		while (partita.getNumeroDiSerpenti() <= numeroSerpentiIniziali){
			int rand = (int)(Math.random()*partita.getLivello() + 1);
			if(rand==1) {
				partita.inserisciBotAccurato(SerpenteBotEasy.class.getSimpleName());
			}
			if(rand==2) {
				partita.inserisciBotAccurato(SerpenteBotMedium.class.getSimpleName());
			}
			if(rand==3) {
				partita.inserisciBotAccurato(SerpenteBotHard.class.getSimpleName());
			}
		}
	}

	public static void provaAdInserireUnSerpente(Partita partita) {
		int rand = (int)(Math.random()*partita.getLivello() + 1);
		if(rand==1) {
			partita.inserisciBotVeloce(SerpenteBotEasy.class.getSimpleName());
		}
		if(rand==2) {
			partita.inserisciBotVeloce(SerpenteBotMedium.class.getSimpleName());
		}
		if(rand==3) {
			partita.inserisciBotVeloce(SerpenteBotHard.class.getSimpleName());
		}
	}
	
}
