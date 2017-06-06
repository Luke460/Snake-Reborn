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
		String testo = LP.readFile(NOME_FILE_RECORD);
		if(testo!=null){
			int vecchioRecord = Integer.parseInt(testo);
			if(nuovoRecord>vecchioRecord){
				LP.writeNewFile(NOME_FILE_RECORD, nuovoRecord+"");
				partita.setVecchioRecord(nuovoRecord);
			}
		} else {
			LP.writeNewFile(NOME_FILE_RECORD, nuovoRecord+"");
		}
	}
	
	public static int getPunteggioDaFileRecord() {
		return Integer.parseInt(LP.readFile(NOME_FILE_RECORD));
	}

	private static boolean punteggioValido() {
		if(partita.getLivello()==3 && partita.getFattorePopolazione()==2){
			return true;
		} else {
			return false;
		}
	}
}
