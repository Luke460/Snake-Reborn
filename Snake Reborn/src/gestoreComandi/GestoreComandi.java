package gestoreComandi;

import static supporto.Costanti.NOME_PLAYER_1;

import game.Partita;
import serpenti.Serpente;
import supporto.Direzione;

public class GestoreComandi {
	
	public static void goUpP1(Partita partita) {
		if(partita.isIlGiocatoreHaFattoLaMossa()==false){
			Serpente serpente = partita.getSerpenti().get(NOME_PLAYER_1);
			Direzione dir = serpente.getDirezione();
			if(!(dir.getX()==0 && dir.getY()==1)){
				dir.setX(0);
				dir.setY(-1);
			}	
		}
		partita.setIlGiocatoreHaFattoLaMossa(true);
	}

	public static void goDownP1(Partita partita) {
		if(partita.isIlGiocatoreHaFattoLaMossa()==false){
			Serpente serpente = partita.getSerpenti().get(NOME_PLAYER_1);
			Direzione dir = serpente.getDirezione();
			if(!(dir.getX()==0 && dir.getY()==-1)){
				dir.setX(0);
				dir.setY(1);
			}	
		}
		partita.setIlGiocatoreHaFattoLaMossa(true);

	}

	public static void goLeftP1(Partita partita) {
		if(partita.isIlGiocatoreHaFattoLaMossa()==false){
			Serpente serpente = partita.getSerpenti().get(NOME_PLAYER_1);
			Direzione dir = serpente.getDirezione();
			if(!(dir.getX()==1 && dir.getY()==0)){
				dir.setX(-1);
				dir.setY(0);
			}	
		}
		partita.setIlGiocatoreHaFattoLaMossa(true);
	}

	public static void goRightP1(Partita partita) {
		if(partita.isIlGiocatoreHaFattoLaMossa()==false){
			Serpente serpente =partita.getSerpenti().get(NOME_PLAYER_1);
			Direzione dir = serpente.getDirezione();
			if(!(dir.getX()==-1 && dir.getY()==0)){
				dir.setX(1);
				dir.setY(0);
			}		
		}
		partita.setIlGiocatoreHaFattoLaMossa(true);
	}

	public static void turnLeftP1(Partita partita) {
		if(partita.isIlGiocatoreHaFattoLaMossa()==false){
			partita.getSerpenti().get(partita.getNomePlayer1()).getDirezione().ruotaSX();
		}
		partita.setIlGiocatoreHaFattoLaMossa(true);
	}

	public static void turnRightP1(Partita partita) {
		if(partita.isIlGiocatoreHaFattoLaMossa()==false){
			partita.getSerpenti().get(partita.getNomePlayer1()).getDirezione().ruotaDX();
		}
		partita.setIlGiocatoreHaFattoLaMossa(true);
	}

	public static void resuscitaPlayer1(Partita partita) {
		partita.resuscitaPlayer1();		
	}

	public static void gameOver(Partita partita) {
		partita.gameOver();
	}

}
