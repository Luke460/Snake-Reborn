package gestorePunteggi;

import LukePack.LP;

// la scrittura non deve interrompere l'esecuzione del gioco, 
// meglio avviare un altro thread!

public class ScriviPunteggio extends Thread {
	private String nomeFile;
	private String contenuto;
	public ScriviPunteggio(String nomeFile, String contenuto){
		this.nomeFile = nomeFile;
		this.contenuto = contenuto;
	}
	
	public void run() {
		LP.writeNewFile(nomeFile, contenuto);
	}
}
