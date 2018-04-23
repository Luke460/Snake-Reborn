package audio;

import supporto.PathSeparator;

public class GestoreSuoni {

	private static boolean effettiAbilitati;
	private static boolean musicaAbilitata;

	private static SuonoWAV suonoSlain;
	private static SuonoWAV suonoSpawn;
	private static SuonoWAV suonoExplode;
	private static SuonoWAV suonoTake;
	private static SuonoWAV suonoMusic;

	public static void inizzializzaSuoni(){
		suonoSlain = new SuonoWAV("suoni"+PathSeparator.get()+"slain.wav");
		suonoSpawn = new SuonoWAV("suoni"+PathSeparator.get()+"spawn.wav");
		suonoExplode = new SuonoWAV("suoni"+PathSeparator.get()+"explode.wav");
		suonoTake = new SuonoWAV("suoni"+PathSeparator.get()+"take.wav");
		suonoMusic = new SuonoWAV("suoni"+PathSeparator.get()+"music.wav");
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
