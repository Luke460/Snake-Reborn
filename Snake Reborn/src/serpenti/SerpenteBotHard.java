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

		// se davanti c'e' un ostacolo... (serve per evitare cappi)
		if(this.getCasellaDiTesta().getCasellaAdiacente(direzioneDritta).isMortale()){
			//...tento di andare nella direzione opposta all'ultima presa
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
		
		// se davanti di n caselle c'è cibo vado dritto
		Casella PrimaCasella = this.getCasellaDiTesta();
		int numeroDiCaselleDaControllareDritto = 40;
		int numeroDiCaselleDaControllareLaterale = 40;
		
		if(controllaCibo(PrimaCasella.getCasellaAdiacente(direzioneDritta), direzioneDritta, numeroDiCaselleDaControllareDritto)){
			super.Sposta(direzioneDritta);
			return;
		}
		
		if(controllaCibo(PrimaCasella.getCasellaAdiacente(direzioneAlternativaDX), direzioneAlternativaDX,numeroDiCaselleDaControllareLaterale)){
			super.Sposta(direzioneAlternativaDX);
			this.ultimaSterzata='d';
			return;
		}
		
		if(controllaCibo(PrimaCasella.getCasellaAdiacente(direzioneAlternativaSX), direzioneAlternativaSX,numeroDiCaselleDaControllareLaterale)){
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

	private boolean controllaCibo(Casella primaCasella, Direzione dir, int numeroCaselle) {
		Casella temp1 = primaCasella;
		if(primaCasella.isCibo()) return true;
		Casella temp2 = primaCasella.getCasellaAdiacente(dir);
		for(int i=0;i<numeroCaselle;i++){
			if(temp1.getStanza()!=temp2.getStanza()) return false;
			if(temp1.isMortale()) return false;
			if(temp2.isCibo()) return true; // cibo a dritta!
			temp1=temp2;
			temp2=temp2.getCasellaAdiacente(dir);
		}
		return false;
	}

}
