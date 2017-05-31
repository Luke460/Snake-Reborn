package video;

import java.awt.Color;
import static supporto.Costanti.*;

public class Pittore {

	public static Color getColore(char stato) {
		switch (stato) {

		case CARATTERE_CASELLA_PLAYER_GENERICO:
			return Color.darkGray;
		
		case CARATTERE_CASELLA_PLAYER1:
			return Color.blue;
			
		case CARATTERE_CASELLA_PLAYER2:
			return Color.magenta;
			
		case CARATTERE_CASELLA_MURO:
			return Color.gray;
			
		case CARATTERE_CASELLA_BOT_EASY:
			return Color.green;
			
		case CARATTERE_CASELLA_BOT_MEDIUM:
			return new Color(250, 150, 0);
			
		case CARATTERE_CASELLA_BOT_HARD:
			return Color.red;
			
		case CARATTERE_CASELLA_CIBO:
			return Color.yellow;
			
		case CARATTERE_CASELLA_VUOTA:
			return Color.black;
			
		case CARATTERE_CASELLA_PORTALE:
			return Color.cyan;
		
		default: // CARATTERE_CASELLA_VUOTA
			return Color.darkGray;
		}	
	}
}
