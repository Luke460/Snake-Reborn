package terrenoDiGioco;

import static supporto.Costanti.NUMERO_SERPENTI_INIZIALI;

import game.Partita;
import serpenti.SerpenteBotEasy;
import serpenti.SerpenteBotHard;
import serpenti.SerpenteBotMedium;

public class PopolatoreSerpenti {
	Partita partita;
	public PopolatoreSerpenti(Partita partita){
		this.partita = partita;
	}
	public void creaPopoloIniziale() {
		
		int numeroSerpentiIniziali = 0;
		if(partita.getPopolazioneIniziale()=="bassa") numeroSerpentiIniziali = NUMERO_SERPENTI_INIZIALI/2;
		if(partita.getPopolazioneIniziale()=="alta") numeroSerpentiIniziali = NUMERO_SERPENTI_INIZIALI;

		do {
			int rand = (int)(Math.random()*partita.getLivello() + 1);
			if(rand==1) {
				partita.tryInserisciBot(SerpenteBotEasy.class.getSimpleName());
			}
			if(rand==2) {
				partita.tryInserisciBot(SerpenteBotMedium.class.getSimpleName());
			}
			if(rand==3) {
				partita.tryInserisciBot(SerpenteBotHard.class.getSimpleName());
			}
		} while(this.partita.getNumeroDiSerpenti() <= numeroSerpentiIniziali);
	}

	public void provaAdInserireUnSerpente() {
		int rand = (int)(Math.random()*partita.getLivello() + 1);
		if(rand==1) {
			partita.tryInserisciBot(SerpenteBotEasy.class.getSimpleName());
		}
		if(rand==2) {
			partita.tryInserisciBot(SerpenteBotMedium.class.getSimpleName());
		}
		if(rand==3) {
			partita.tryInserisciBot(SerpenteBotHard.class.getSimpleName());
		}
	}
	
}
