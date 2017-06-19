package server.client;

import game.Partita;
import server.model.Match;

public class MatchFactory {

	public MatchFactory(){
		
	}
	
	public Match build(Partita partita) {
		Match match = new Match();
		match.setScore(partita.getPunteggioPlayer1());
		match.setPlayingTime((long)partita.getSerpentePlayer1().getTempoSopravvissuto());
		match.setKills(partita.getSerpentePlayer1().getNumeroUccisioni());
		return match;
	}
}
