package audio;

import supporto.OSdetector;

public class GestoreSuoni {

	private static boolean effettiAbilitati;
	private static boolean musicaAbilitata;

	private static SuonoWAV suonoSlain;
	private static SuonoWAV suonoSpawn;
	private static SuonoWAV suonoExplode;
	private static SuonoWAV suonoTake;
	private static SuonoWAV suonoMusic;

	public static void inizzializzaSuoni(){
		suonoSlain = new SuonoWAV("suoni"+OSdetector.getPathSeparator()+"slain.wav");
		suonoSpawn = new SuonoWAV("suoni"+OSdetector.getPathSeparator()+"spawn.wav");
		suonoExplode = new SuonoWAV("suoni"+OSdetector.getPathSeparator()+"explode.wav");
		suonoTake = new SuonoWAV("suoni"+OSdetector.getPathSeparator()+"take.wav");
		suonoMusic = new SuonoWAV("suoni"+OSdetector.getPathSeparator()+"music.wav");
		// MUSIC.WAV va aggiunta!!!!!!!
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
