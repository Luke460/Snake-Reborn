package gestoreComandi;

import static supporto.Costanti.NOME_PLAYER_1;

import java.util.LinkedList;
import java.util.Queue;

import game.Partita;
import serpenti.Serpente;
import supporto.Direzione;
import video.Visualizzatore;

public class GestoreComandi {
	
	private Queue<Character> sequenzaComandi;
	private Partita partita;
	
	public GestoreComandi(Partita partita, Visualizzatore visualizzatore) {
		this.partita = partita;
		this.sequenzaComandi = new LinkedList<>();
		LettoreComandi.initControlliDaTastiera(visualizzatore,this.sequenzaComandi);
	}

	public void goUpP1() {
		Serpente serpente = partita.getSerpenti().get(NOME_PLAYER_1);
		Direzione dir = serpente.getDirezione();
		if(!(dir.getX()==0 && dir.getY()==1)){
			dir.setX(0);
			dir.setY(-1);
		}	

	}

	public  void goDownP1() {

		Serpente serpente = partita.getSerpenti().get(NOME_PLAYER_1);
		Direzione dir = serpente.getDirezione();
		if(!(dir.getX()==0 && dir.getY()==-1)){
			dir.setX(0);
			dir.setY(1);
		}	


	}

	public  void goLeftP1() {

		Serpente serpente = partita.getSerpenti().get(NOME_PLAYER_1);
		Direzione dir = serpente.getDirezione();
		if(!(dir.getX()==1 && dir.getY()==0)){
			dir.setX(-1);
			dir.setY(0);
		}	

	}

	public  void goRightP1() {

		Serpente serpente =partita.getSerpenti().get(NOME_PLAYER_1);
		Direzione dir = serpente.getDirezione();
		if(!(dir.getX()==-1 && dir.getY()==0)){
			dir.setX(1);
			dir.setY(0);
		}		

	}

	public  void turnLeftP1() {

		partita.getSerpentePlayer1().getDirezione().ruotaSX();

	}

	public  void turnRightP1() {

		partita.getSerpentePlayer1().getDirezione().ruotaDX();

	}

	public  void resuscitaPlayer1( ) {
		partita.resuscitaPlayer1();		
	}

	public  void gameOver() {
		partita.gameOver();
	}
	
	public void addToQueue(char c) {
		this.sequenzaComandi.add(c);
	}

	public void eseguiComando() {
		if(!this.sequenzaComandi.isEmpty()) {
			char codiceComando = this.sequenzaComandi.poll();
			switch(codiceComando){
			case 'w': 
				goUpP1();
				break;
			case 's':
				goDownP1();
				break;
			case 'd': 
				goRightP1();
				break;
			case 'a':
				goLeftP1();
				break;
			case 'r': 
				turnRightP1();
				break;
			case 'l':
				turnLeftP1();
				break;
			case 'i': 
				resuscitaPlayer1();
				break;
			case 'e':
				gameOver();
				break;
			}
		}

	}

}
