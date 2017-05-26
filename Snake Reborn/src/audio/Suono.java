package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Suono{
	private Clip clip;
	/**
Carica le melodie in memoria.
	 */
	public Suono(String nomeFile) {
		try {
			// Usa URL (invece di File) per leggere dal disco.
			File fileSuono = new File(nomeFile);
			// Crea uno stream d'input audio dal file del suono.
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileSuono);
			// Ottieni il clip.
			clip = AudioSystem.getClip();
			// Apri l'audio del clip.
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}


	//A questo punto, bisogna avere un metodo per eseguire il suono ogni volta che lo si desidera.


	/**
	 * Esegui il suono
	 */
	public void playClip() {
		if (clip.isRunning())
			clip.stop();   // Ferma il suono se è ancora in esecuzione.
		clip.setFramePosition(0); // Riavvolgi il suono.
		clip.start();     // Esegui il suono.
	}
}