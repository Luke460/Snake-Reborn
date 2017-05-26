package serpenti;

import static supporto.Costanti.*;

import LukePack.LP;
import supporto.Utility;
import terrenoDiGioco.Casella;
import terrenoDiGioco.Stanza;

public class SerpenteGiocatore extends Serpente {

	public SerpenteGiocatore(String nome, Stanza stanza) {
		super(nome, stanza);
		for(Casella c:this.getCaselle()){
			c.setStato(CARATTERE_CASELLA_PLAYER1);		
		}
	}

	@Override
	public void FaiMossa() {
		if(!super.isMorto()){
			super.Sposta(this.getDirezione());
		}
	}
	
	public String getNomeGiocatore() {
		return super.getNome();
	}

	public void setNomeGiocatore(String nomeGiocatore) {
		super.setNome(nomeGiocatore);
	}

	public void incrementaVitaSerpente() {
		game.Main.suonoTake.playClip();
		super.incrementaVitaSerpente();
	}
	
	public void muori(){
		game.Main.suonoExplode.playClip();
		String testo = LP.readFile("record.txt");
		aggiornaFileRecord(testo);
		for(Casella c:super.getCaselle()){
			if(Utility.isPari(c.getPosizione().getX())&&Utility.isPari(c.getPosizione().getY())) {
				c.libera();
				c.setStato(CARATTERE_CASELLA_CIBO);
			} else if((!Utility.isPari(c.getPosizione().getX()))&&(!Utility.isPari(c.getPosizione().getY()))){
				c.libera();
				c.setStato(CARATTERE_CASELLA_CIBO);
			} else {
				c.libera();
				c.setStato(CARATTERE_CASELLA_VUOTA);
			}
		}
		super.getCaselle().clear();
		super.SetIsVivo(false);
	}
	
	private void aggiornaFileRecord(String testo) {
		int nuovoRecord = this.getCiboPreso()*MOLTIPLICATORE_PUNTEGGIO_CIBO;
		if(testo!=null){
			int vecchioRecord = Integer.parseInt(testo);
			if(nuovoRecord>vecchioRecord){
				LP.writeNewFile("record.txt", nuovoRecord+"");
			}
		} else {
			LP.writeNewFile("record.txt", nuovoRecord+"");
		}
	}
}
