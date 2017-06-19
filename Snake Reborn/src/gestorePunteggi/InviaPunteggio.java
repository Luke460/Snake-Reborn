package gestorePunteggi;

import game.Partita;
import server.model.Match;

// la scrittura non deve interrompere l'esecuzione del gioco, 
// meglio avviare un altro thread!

public class InviaPunteggio extends Thread {

	private Partita partita;

	public InviaPunteggio(Partita partita){
		this.partita = partita;
	}

	public void run() {
		try {
			MatchFactory mf = new MatchFactory();
			Match match = mf.buildMatch(partita);
			partita.getClient().addMatch(match);
		} catch (Exception e4){
			return;
		}

	}

}