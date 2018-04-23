package supporto;

public interface Costanti {

	static final public int TEMPO_BASE = 83; // 12 fps	

	public static final int DIMENSIONE_STANZA_DEFAULT = 40;
	//public static final int NUMERO_STANZE_DEFAULT = 8;
	public static final double RAPPORTO_DIMENSIONE_SCHERMO = 0.9; // 90% del lato minimo
	
	public static final String EST = "est";
	public static final String OVEST = "ovest";
	public static final String SUD = "sud";
	public static final String NORD = "nord";
	
	public static final char CARATTERE_FINE_FILE = '$';
	public static final char CARATTERE_INIZIO_RIGA = '<';
	public static final char CARATTERE_FINE_RIGA = '>';
	
	//public static final String NOME_FILE_RECORD = "temp.int";
	public static final String NOME_FILE_INDIRIZZO_SERVER = "config/index.ini";
	public static final String FILE_NOME_SERVER = "config/server.ini";
	public static final String NOME_FILE_USERNAME_TEMPORANEO = "config/username.ini";
	
	public static final char CARATTERE_CASELLA_VUOTA = ' ';
	public static final char CARATTERE_CASELLA_PLAYER_GENERICO = 'X';
	public static final char CARATTERE_CASELLA_PLAYER1 = '1';
	public static final char CARATTERE_CASELLA_PLAYER2 = '2';
	public static final char CARATTERE_CASELLA_MURO = 'W';
	public static final char CARATTERE_CASELLA_BOT_EASY = 'E';
	public static final char CARATTERE_CASELLA_BOT_NORMAL = 'N';
	public static final char CARATTERE_CASELLA_BOT_HARD = 'H';
	public static final char CARATTERE_CASELLA_BOT_MEDIUM = 'M';
	public static final char CARATTERE_CASELLA_CIBO = 'F';
	public static final char CARATTERE_CASELLA_PORTALE = 'P';
	
	public static final int VITA_SERPENTE_DEFAULT = 8;
	static final public int VITA_SERPENTE_MASSIMA = 40;
	
	static final public int QTA_CIBO_TESTA_SERPENTE = 4; // 4 unit� cibo
	static final public int QTA_CIBO_BASE = 1; // 1 unit� cibo
	static final public int MOLTIPLICATORE_PUNTEGGIO_CIBO = 10;
	static final public int TEMPO_RIPOPOLAMENTO_CIBO = 50; // 5 sec
	
	static final public int TEMPO_RIPOPOLAMENTO_SERPENTI_ALTO = 30; // 3 sec
	static final public int TEMPO_RIPOPOLAMENTO_SERPENTI_BASSO = 60; // 6 sec
	static final public int NUMERO_SERPENTI_INIZIALI_ALTO = 6;
	static final public int NUMERO_SERPENTI_INIZIALI_BASSO = 3;
	static final public int LIMITE_SERPENTI_BASSO = 5;
	static final public int LIMITE_SERPENTI_ALTO = 9;
	
	static final public String NOME_PLAYER_1 = "Giocatore 1";
	
}
