package serpenti;

import static supporto.Costanti.*;

import audio.GestoreSuoni;
import gestorePunteggi.GestorePunteggi;
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
		GestoreSuoni.playTakeSound();
		super.incrementaVitaSerpente();
	}
	
	public void muori(){
		GestoreSuoni.playExplodeSound();
		GestorePunteggi.aggiornaFileRecord();
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

}
