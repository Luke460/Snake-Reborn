package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SuonoWAV{
	private Clip clip;
	/**
Carica le melodie in memoria.
	 */
	public SuonoWAV(String filePath) {
		try {
			// Usa URL (invece di File) per leggere dal disco.
			File filePathRelativo = new File(filePath);
			File filePathAssoluto = new File(filePathRelativo.getAbsolutePath());
			//URL url = getClass().getResource(fileSuono.getAbsolutePath());
 			//File fileSuono = new File(filePath);
			// Crea uno stream d'input audio dal file del suono.
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(filePathAssoluto);
			// Ottieni il clip.
			
			AudioFormat af = audioInputStream.getFormat();
	        DataLine.Info info = new DataLine.Info(Clip.class, af);
	        this.clip = (Clip)AudioSystem.getLine(info);
			if(this.clip==null) throw new Exception("Clip is null");
			//clip = AudioSystem.getClip();
			// Apri l'audio del clip.
			this.clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//A questo punto, bisogna avere un metodo per eseguire il suono ogni volta che lo si desidera.


	/**
	 * Esegui il suono
	 */
	public void playClip() {
		if (this.clip==null) {
			//System.out.println("clip is null");
			return;
		}
		//if (this.clip.isRunning()) {
			this.clip.stop();   // Ferma il suono se e' ancora in esecuzione.
			//System.out.println("clip was stopped");
		//}
		this.clip.setFramePosition(0); // Riavvolgi il suono.
		this.clip.start();     // Esegui il suono.
		//System.out.println("clip restarted");
	}
	
	@SuppressWarnings("static-access")
	public void loopClip() {
		if (this.clip==null)return;
		//if (this.clip.isRunning())
		this.clip.stop();   // Ferma il suono se e' ancora in esecuzione.
		this.clip.setFramePosition(0); // Riavvolgi il suono.
		this.clip.loop(clip.LOOP_CONTINUOUSLY);     // Esegui il suono.
	}
}