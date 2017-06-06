package video;

import static java.awt.event.KeyEvent.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Partita;
import supporto.Utility;
import terrenoDiGioco.Casella;

import static supporto.Costanti.*;

public class GUI extends JPanel {

	static final private long serialVersionUID = 0L;

	static final public int VK_HEARTBEAT = VK_SHIFT; // meglio un tasto "innocuo"
	
	final private Partita partita;
	int dimensioneCasella;
	final private JFrame finestra;

	public GUI(final Partita partita) {
		this.partita = partita;
		this.finestra = new JFrame("Snake Reborn");		
		finestra.add(this);
		finestra.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.dimensioneCasella = calcolaDimensioneCasellaMassima();
		finestra.setSize((int)((DIMENSIONE_STANZA_DEFAULT+0.9)*this.dimensioneCasella), (int) ((DIMENSIONE_STANZA_DEFAULT+2.2)*dimensioneCasella));
		finestra.setVisible(true);
		//jframe.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		finestra.setLocationRelativeTo(null);

	}

	private int calcolaDimensioneCasellaMassima() {
		Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
		int larghezza = (int) screenSize.getWidth();
		int altezza = (int) screenSize.getHeight();
		return (int) ((Utility.minimoTra(larghezza,altezza)/(DIMENSIONE_STANZA_DEFAULT))*RAPPORTO_DIMENSIONE_SCHERMO);
	}

	public void initControlliDaTastiera(final Partita partita) {

		// Gestione eventi associati alla tastiera
		this.finestra.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case VK_ENTER:
					partita.resuscitaPlayer1();
					break;
				case VK_LEFT:
					partita.turnLeftP1();
					break;
				case VK_RIGHT:
					partita.turnRightP1();
					break;
				case VK_W:
					partita.goUpP1();
					break;
				case VK_S:
					partita.goDownP1();
					break;
				case VK_A:
					partita.goLeftP1();
					break;
				case VK_D:
					partita.goRightP1();
					break;
				case VK_ESCAPE:
					partita.gameOver();
					break;
				}
				repaint();
			}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, DIMENSIONE_STANZA_DEFAULT*dimensioneCasella, DIMENSIONE_STANZA_DEFAULT*dimensioneCasella);
		for (Casella c : this.partita.getStanzaPlayer1().getCaselle().values()) {
			disegnaCasella(g, c);
		}
		disegnaStanza(g);
		riportaStatisticheSullaFinestra(this.partita.getPunteggioPlayer1());
	}

	
	private void riportaStatisticheSullaFinestra(long punteggio) {
		this.finestra.setTitle("  Livello: " + partita.getLivello() + 
				"        Numero avversari: " + (this.partita.getNumeroDiSerpenti()-1)+ 
				"        Record: " + this.partita.getVecchioRecord() + 
				"        Punteggio: " + punteggio);
	}
	
	private void disegnaStanza(Graphics g) {
		for (Casella c : this.partita.getStanzaPlayer1().getCaselle().values()) {
			disegnaCasella(g, c);
		}
	}

	private void disegnaCasella(Graphics g, Casella casella) {
		final int x = casella.getPosizione().getX();
		final int y = casella.getPosizione().getY();
		final Color colore = Pittore.getColore(casella.getStato());
		g.setColor(colore);
		int d = dimensioneCasella;
		int gx = x*d, gy = y*d;
		if(casella.getStato()==CARATTERE_CASELLA_PORTALE){
			disegnaCasellaInRilievo(g, d, gx, gy);
		} else {
			disegnaCasellaNormale(g, d, gx, gy);
		}
	}

	private void disegnaCasellaNormale(Graphics g, int l, int x, int y) {
		
		g.fill3DRect(   x,  y,   l-1, l-1, true);
		g.fillRoundRect(x+2,y+2, l-4, l-4, 2, 2 );
		
	}
	
	private void disegnaCasellaInRilievo(Graphics g, int l, int x, int y) {
		
		g.fill3DRect(   x,  y,   l-1, l-1, true);
		g.fillRoundRect(x+2,y+2, l-4, l-4, 2, 2 );
		g.fill3DRect(   x+2,y+2, l-5, l-5, true );
		
	}

}
