package gestorePunteggi;

import game.Partita;
import server.model.Match;

public class MatchFactory {
	
	public Match buildMatch(Partita partita){
		Match m = new Match(); 
		m.setScore(GestorePunteggi.getPunteggioPlayer1());
		m.setPlayingTime((long)partita.getSerpentePlayer1().getTempoSopravvissuto());
	    m.setKills(partita.getSerpentePlayer1().getNumeroUccisioni());
	    return m;
	}
	
}
