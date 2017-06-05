package audio;

public class GestoreSuoni {

	private static boolean effettiAbilitati;
	private static boolean musicaAbilitata;

	private static SuonoWAV suonoSlain;
	private static SuonoWAV suonoSpawn;
	private static SuonoWAV suonoExplode;
	private static SuonoWAV suonoTake;
	private static SuonoWAV suonoMusic;
	private static SuonoWAV suonoLevel;

	public static void inizzializzaSuoni(){
		suonoSlain = new SuonoWAV("suoni\\slain.wav");
		suonoSpawn = new SuonoWAV("suoni\\spawn.wav");
		suonoExplode = new SuonoWAV("suoni\\explode.wav");
		suonoTake = new SuonoWAV("suoni\\take.wav");
		suonoMusic = new SuonoWAV("suoni\\music.wav");
		suonoLevel = new SuonoWAV("suoni\\levelUp.wav");
	}

	public static void playMusicaInLoop(){
		if(musicaAbilitata) suonoMusic.loopClip();
	}
	
	public static void playSlainSound(){
		if(effettiAbilitati) suonoSlain.playClip();
	}
	
	public static void playSpawnSound(){
		if(effettiAbilitati) suonoSpawn.playClip();
	}
	
	public static void playExplodeSound(){
		if(effettiAbilitati) suonoExplode.playClip();
	}
	
	public static void playTakeSound(){
		if(effettiAbilitati) suonoTake.playClip();
	}
	
	public static void playLevelSound(){
		if(effettiAbilitati) suonoLevel.playClip();
	}

	public static boolean isEffettiAbilitati() {
		return effettiAbilitati;
	}

	public static void setEffettiAbilitati(boolean b) {
		effettiAbilitati = b;
	}

	public static boolean isMusicaAbilitata() {
		return musicaAbilitata;
	}

	public static void setMusicaAbilitata(boolean b) {
		musicaAbilitata = b;
	}

}
