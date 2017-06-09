package terrenoDiGioco;

import static supporto.Costanti.CARATTERE_CASELLA_CIBO;
import static supporto.Costanti.CARATTERE_CASELLA_VUOTA;
import static supporto.Costanti.DIMENSIONE_STANZA_DEFAULT;

import java.util.List;

import supporto.Posizione;
import supporto.Utility;

public class PopolatoreCibo {
	
	public static void aggiungiCiboRandom(Mappa m) {
		for(Stanza s:m.getStanze().values()){
			aggiungiCiboInPosizioneCasuale(s);
		}
	}

	public static void aggiungiCiboInPosizioneCasuale(Stanza s) {
		int posX = (int)(Math.random() * DIMENSIONE_STANZA_DEFAULT) ;     // da 0 a N-1 compresi
		int posY = (int)(Math.random() * DIMENSIONE_STANZA_DEFAULT) ;
		Posizione pos = new Posizione(posX, posY);
		Casella c = s.getCaselle().get(pos);
		// posiziono il cibo solo in caselle libere e con posizione pari
		if (c.isVuota()){
			if(posizioneValidaPerCibo(pos)){
				c.libera();
				c.setStato(CARATTERE_CASELLA_CIBO);
			} else {
				c.libera();
				c.setStato(CARATTERE_CASELLA_VUOTA);
			}
		}
	}
	public static void rilasciaCiboNelleCaselle(List<Casella> caselle){
		for(Casella c:caselle){
			if(posizioneValidaPerCibo(c.getPosizione())){
				c.libera();
				c.setStato(CARATTERE_CASELLA_CIBO);
			} else {
				c.libera();
				c.setStato(CARATTERE_CASELLA_VUOTA);
			}
		}
	}

	public static boolean posizioneValidaPerCibo(Posizione p){
		// pre: casella vuota
		int x = p.getX();
		int y = p.getY();
		if(Utility.isPari(x)&&Utility.isPari(y)) {
			return true;
		} else if(!Utility.isPari(x)&&(!Utility.isPari(y))){
			return true;
		} else {
			return false;
		}
	}
}
