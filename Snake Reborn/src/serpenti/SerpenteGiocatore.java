package serpenti;

import static supporto.Costanti.*;

import audio.GestoreSuoni;
import gestorePunteggi.GestorePunteggi;
import terrenoDiGioco.Casella;
import terrenoDiGioco.Stanza;

public class SerpenteGiocatore extends Serpente {

	public SerpenteGiocatore(String nome, Stanza stanza, int vitaIniziale) {
		super(nome, stanza, vitaIniziale);
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
	
	@Override
	public void incrementaVitaSerpente(int qta) {
		GestoreSuoni.playTakeSound();
		super.incrementaVitaSerpente(qta);
	}
	
	@Override
	public void muori(){
		GestoreSuoni.playExplodeSound();
		GestorePunteggi.inviaPunteggio();
		super.setHpPreMorte(this.getCasellaDiTesta().getVita());
		super.rilasciaCibo();
		super.getCaselle().clear();
		super.SetIsVivo(false);
	}

}
