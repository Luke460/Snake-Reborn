package gestorePunteggi;
import game.Partita;
import server.client.Client;

public class GestorePunteggi {

	private static Partita partita;

	public static void inizializza(Partita p){
		partita=p;
	}
	/*
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
	*/

	private static boolean punteggioValido() {
		if(partita.getLivello()==3 && partita.getFattorePopolazione()==2){
			return true;
		} else {
			return false;
		}
	}	

	public static double getMoltiplicatorePunteggio() {
		if(partita.getLivello()==1) return 1;
		if(partita.getLivello()==2) return 2;
		if(partita.getLivello()==3) return 5;
		return 0;
	}

	
	public static void inviaPunteggio() {
		if(!punteggioValido()) return;
		int nuovoRecord = partita.getPunteggioPlayer1();
		int vecchioRecord = partita.getVecchioRecord();
		if(nuovoRecord>vecchioRecord){
			InviaPunteggio inviatore = new InviaPunteggio(partita);
			inviatore.start();
		}

	}
	
	public static int getRecord() {
		try{
			Client c = partita.getClient();
		return c.getRecord();
		} catch (Exception e4){
			return 0;
		}
	}
}
