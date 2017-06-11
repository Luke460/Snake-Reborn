package gestoreComandi;

import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_W;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import game.Partita;
import video.Visualizzatore;

public class LettoreComandi {
	public Partita partita;
	public int dimensioneCasella;
	public JFrame finestra;


	public static void initControlliDaTastiera(Visualizzatore visualizzatore) {

		// Gestione eventi associati alla tastiera
		visualizzatore.getFinestra().addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case VK_ENTER:
					GestoreComandi.resuscitaPlayer1(visualizzatore.getPartita());
					break;
				case VK_LEFT:
					GestoreComandi.turnLeftP1(visualizzatore.getPartita());
					break;
				case VK_RIGHT:
					GestoreComandi.turnRightP1(visualizzatore.getPartita());
					break;
				case VK_W:
					GestoreComandi.goUpP1(visualizzatore.getPartita());
					break;
				case VK_S:
					GestoreComandi.goDownP1(visualizzatore.getPartita());
					break;
				case VK_A:
					GestoreComandi.goLeftP1(visualizzatore.getPartita());
					break;
				case VK_D:
					GestoreComandi.goRightP1(visualizzatore.getPartita());
					break;
				case VK_ESCAPE:
					GestoreComandi.gameOver(visualizzatore.getPartita());
					break;
				}
				//repaint();
			}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}
}