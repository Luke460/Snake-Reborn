package terrenoDiGioco;

import java.util.ArrayList;

import LukePack.LP;
import supporto.Posizione;
import supporto.Utility;
import static supporto.Costanti.*;

public class CaricatoreStanza {
	public static void CaricaFile(String nomeFile, Stanza stanza){
		stanza.setNome(nomeFile);
		String testoMappa = LP.readFile(nomeFile);
		ArrayList<Character> listaCaratteri = new ArrayList<>();
		listaCaratteri.addAll(Utility.stringaToArray(testoMappa));

		boolean rigaValida=false;
		int riga = 0;
		int colonna = 0;

		for(char c:listaCaratteri){
			if (c!=CARATTERE_FINE_FILE){ // finche' il file non è finito...
				// controllo
				if(c==CARATTERE_INIZIO_RIGA){
					rigaValida=true;
				}
				if(c==CARATTERE_FINE_RIGA){
					rigaValida=false;
					riga++;
					colonna=0;
				}
				// fine controllo
				if(rigaValida && (c!=CARATTERE_INIZIO_RIGA && c!=CARATTERE_FINE_RIGA)){
					Posizione p = new Posizione(colonna,riga);
					stanza.getCaselle().put(p,new Casella(stanza,p, c));
					colonna++;
				}
			}
		}
	}

}
