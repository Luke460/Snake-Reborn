package serpenti;
import static supporto.Costanti.*;

import supporto.Direzione;
import supporto.Utility;
import terrenoDiGioco.Casella;
import terrenoDiGioco.Stanza;

public class SerpenteBotMedium extends Serpente {

	public SerpenteBotMedium(String nome, Stanza stanza) {
		super(nome, stanza);
		for(Casella c:this.getCaselle()){
			c.setStato(CARATTERE_CASELLA_BOT_MEDIUM);		
		}
	}

	@Override
	public void FaiMossa() {
		Direzione direzioneDritta = new Direzione(super.getDirezione().getX(),super.getDirezione().getY());
		Direzione direzioneAlternativaDX = new Direzione(super.getDirezione().getX(),super.getDirezione().getY());
		direzioneAlternativaDX.ruotaDX();
		Direzione direzioneAlternativaSX = new Direzione(super.getDirezione().getX(),super.getDirezione().getY());
		direzioneAlternativaSX.ruotaSX();

		// se davanti c'e' cibo vado dritto
		if(this.getCasellaDiTesta().getCasellaAdiacente(direzioneDritta).isCibo()){
			super.Sposta(direzioneDritta);
			return;
		}
		
		// se davanti di due caselle c'e' cibo vado dritto
		if(this.getCasellaDiTesta().getCasellaAdiacente(direzioneDritta).getCasellaAdiacente(direzioneDritta).isCibo() && 
				!this.getCasellaDiTesta().getCasellaAdiacente(direzioneDritta).isMortale()){
			super.Sposta(direzioneDritta);
			return;
		}
		
		// se a dx c'e cibo giro a dx
		if(this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaDX).isCibo()){
			super.Sposta(direzioneAlternativaDX);
			return;
		}
		
		// se a sx c'e cibo giro a sx
		if(this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaSX).isCibo()){
			super.Sposta(direzioneAlternativaSX);
			return;
		}
		
		// caso: vado dritto, ma ogni tanto giro lo stesso
		if((Utility.veroAl(95) && !this.getCasellaDiTesta().getCasellaAdiacente(direzioneDritta).isMortale())){
			super.Sposta(direzioneDritta);
			return;
		}
		// priorità dx
		if(Utility.veroAl(50)){
			if(!this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaDX).isMortale()){
				super.Sposta(direzioneAlternativaDX);
				return;
			}
			
			if(!this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaSX).isMortale()){
				super.Sposta(direzioneAlternativaSX);
				return;
			}
			
		} else { // priorita' a sx
			if(!this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaSX).isMortale()){
				super.Sposta(direzioneAlternativaSX);
				return;
			}
			if(!this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaDX).isMortale()){
				super.Sposta(direzioneAlternativaDX);
				return;
			}
		}
		
		// caso: vado dritto - ignora il casuale
		if((!this.getCasellaDiTesta().getCasellaAdiacente(direzioneDritta).isMortale())){
			super.Sposta(direzioneDritta);
			return;
		}
		
		super.Sposta(direzioneDritta);
	}

}