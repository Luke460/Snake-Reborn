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
		int nuovoRecord = partita.getPunteggioPlayer1();
		String testo = LP.readFile(NOME_FILE_RECORD);
		if(testo!=null){
			int vecchioRecord = Integer.parseInt(testo);
			if(nuovoRecord>vecchioRecord){
				LP.writeNewFile(NOME_FILE_RECORD, nuovoRecord+"");
			}
		} else {
			LP.writeNewFile(NOME_FILE_RECORD, nuovoRecord+"");
		}
	}
}
