package serpenti;
import static supporto.Costanti.*;

import supporto.Direzione;
import supporto.Utility;
import terrenoDiGioco.Casella;
import terrenoDiGioco.Stanza;

public class SerpenteBotHard extends Serpente {

	private char ultimaSterzata;

	public SerpenteBotHard(String nome, Stanza stanza) {
		super(nome, stanza);
		ultimaSterzata = '0';
		for(Casella c:this.getCaselle()){
			c.setStato(CARATTERE_CASELLA_BOT_HARD);		
		}
	}

	@Override
	public void FaiMossa() {
		int numeroCasuale = (int)((Math.random() * 40)+1);
		Direzione direzioneDritta = new Direzione(super.getDirezione().getX(),super.getDirezione().getY());
		Direzione direzioneAlternativaDX = new Direzione(super.getDirezione().getX(),super.getDirezione().getY());
		direzioneAlternativaDX.ruotaDX();
		Direzione direzioneAlternativaSX = new Direzione(super.getDirezione().getX(),super.getDirezione().getY());
		direzioneAlternativaSX.ruotaSX();

		// se davanti c'e' un mio pezzo...
		if(this.getCasellaDiTesta().getCasellaAdiacente(direzioneDritta).getSerpente()!=null){
			if(this.getCasellaDiTesta().getCasellaAdiacente(direzioneDritta).getSerpente().getNome().equals(this.getNome())){
				//...vado nella direzione opposta all'ultima presa
				if(this.ultimaSterzata=='d'){
					if(!this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaSX).isMortale()){
						super.Sposta(direzioneAlternativaSX);
						this.ultimaSterzata='s';
						return;
					}
				}
				if(this.ultimaSterzata=='s'){
					if(!this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaDX).isMortale()){
						super.Sposta(direzioneAlternativaDX);
						this.ultimaSterzata='d';
						return;
					}
				}
			}
		}

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
			this.ultimaSterzata='d';
			return;
		}
		
		// se a dx di 2 caselle c'e cibo giro a dx
		if(this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaDX).getCasellaAdiacente(direzioneAlternativaDX).isCibo()&&
				!this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaDX).isMortale()){
			super.Sposta(direzioneAlternativaDX);
			this.ultimaSterzata='d';
			return;
		}

		// se a sx c'e cibo giro a sx
		if(this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaSX).isCibo()){
			super.Sposta(direzioneAlternativaSX);
			this.ultimaSterzata='s';
			return;
		}
		
		// se a sx di 2 caselle c'e cibo giro a sx
		if(this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaSX).getCasellaAdiacente(direzioneAlternativaSX).isCibo()&&
				!this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaSX).isMortale()){
			super.Sposta(direzioneAlternativaSX);
			this.ultimaSterzata='s';
			return;
		}

		// caso: vado dritto
		if((numeroCasuale!=4 && numeroCasuale!=5 && !this.getCasellaDiTesta().getCasellaAdiacente(direzioneDritta).isMortale())){
			super.Sposta(direzioneDritta);
			return;
		}
		// priorità dx
		if(Utility.isPari(numeroCasuale)){
			if(!this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaDX).isMortale()){
				super.Sposta(direzioneAlternativaDX);
				this.ultimaSterzata='d';
				return;
			}

			if(!this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaSX).isMortale()){
				super.Sposta(direzioneAlternativaSX);
				this.ultimaSterzata='s';
				return;
			}

		} else { // priorita' a sx
			if(!this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaSX).isMortale()){
				super.Sposta(direzioneAlternativaSX);
				this.ultimaSterzata='s';
				return;
			}
			if(!this.getCasellaDiTesta().getCasellaAdiacente(direzioneAlternativaDX).isMortale()){
				super.Sposta(direzioneAlternativaDX);
				this.ultimaSterzata='d';
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
