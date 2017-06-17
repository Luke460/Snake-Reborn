package gestorePunteggi;

import LukePack.LP;
import game.Partita;
import serpenti.Serpente;

import static supporto.Costanti.*;

public class GestorePunteggi {

	private static Partita partita;

	public static void inizializza(Partita p){
		partita=p;
	}

	public static void aggiornaFileRecord() {
		if(!punteggioValido()) return;
		int nuovoRecord = getPunteggioPlayer1();
		int vecchioRecord = partita.getVecchioRecord();
		if(nuovoRecord>vecchioRecord){
			ScriviPunteggio scrittore = new ScriviPunteggio(NOME_FILE_RECORD, String.valueOf(nuovoRecord));
			scrittore.start();
			partita.setVecchioRecord(nuovoRecord);
		}
	}

	public static int getPunteggioDaFileRecord() { // sistemare
		String testoFileVecchioRecord = LP.readFile(NOME_FILE_RECORD);
		if(testoFileVecchioRecord!=null && testoFileVecchioRecord!=""){
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
	
	public static int getPunteggioPlayer1() {
		int punteggio = 0;
		Serpente p1 = partita.getSerpentePlayer1();
		punteggio += (int) p1.getCiboPreso()*MOLTIPLICATORE_PUNTEGGIO_CIBO*getMoltiplicatorePunteggio();
		return punteggio;
	}	

	private static double getMoltiplicatorePunteggio() {
		if(partita.getLivello()==1) return 1;
		if(partita.getLivello()==2) return 2;
		if(partita.getLivello()==3) return 5;
		return 0;
	}

	/*
	public static void inviaPunteggio() {
		Comunicatore.pushMatch(partita.getUtente().getNomeUtente(),
							   partita.getUtente().getPassword(),
							   getPunteggioPlayer1(),
							   partita.getSerpentePlayer1().getTempoSopravvissuto(),
						       partita.getSerpentePlayer1().getNumeroUccisioni());
	}
	
	public static int getRecord() {
		Comunicatore.getRecord(partita.getUtente().getNomeUtente(),
							   partita.getUtente().getPassword());
	}
	*/
}
