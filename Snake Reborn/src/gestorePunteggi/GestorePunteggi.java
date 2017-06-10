package gestorePunteggi;

import LukePack.LP;
import game.Partita;

import static supporto.Costanti.*;

public class GestorePunteggi {

	private static Partita partita;

	public static void inizializza(Partita p){
		partita=p;
	}

	public static void aggiornaFileRecord() {
		if(!punteggioValido()) return;
		int nuovoRecord = partita.getPunteggioPlayer1();
		int vecchioRecord = partita.getVecchioRecord();
		if(nuovoRecord>vecchioRecord){
			ScriviPunteggio scrittore = new ScriviPunteggio(NOME_FILE_RECORD, nuovoRecord+"");
			scrittore.start();
			partita.setVecchioRecord(nuovoRecord);
		}
	}

	public static int getPunteggioDaFileRecord() { // sistemare
		String testoFileVecchioRecord = LP.readFile(NOME_FILE_RECORD);
		if(testoFileVecchioRecord!=null){
			int record = Integer.parseInt(testoFileVecchioRecord);
			return record;
		} else {
			ScriviPunteggio scrittore = new ScriviPunteggio(NOME_FILE_RECORD, "0");
			scrittore.run();
			return 0;	
		}
	}

	private static boolean punteggioValido() {
		if(partita.getLivello()==3 && partita.getFattorePopolazione()==2){
			return true;
		} else {
			return false;
		}
	}
}
