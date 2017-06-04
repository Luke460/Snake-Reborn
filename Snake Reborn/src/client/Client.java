package client;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import LukePack.LP;
import audio.GestoreSuoni;
import game.Main;

public class Client extends JFrame{

	private static final long serialVersionUID = 1L;
	private String nomeUtente;
	private String password;
	private boolean premuto;

	JPanel PannelloInserimentoNome;
	JPanel PannelloInserimentoPassword;
	JPanel PannelloInserimenti;
	JPanel PannelloOpzioni;
	JPanel PannelloTastiConferma;
	JLabel messaggioNome;
	JTextField nomeInserito;
	JLabel messaggioPassword;	
	JTextField passwordInserita;	
	JCheckBox opzMusica;
	JCheckBox opzEffetti;
	JButton accedi;
	JButton ospite;

	public Client(){
		super("Snake Reborn");

		creaPannelli();
		sistemaPannelli();
		preimpostaPannelli();
		aggiungiPannelliAlContainer();
		regolaDimensioni();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		leggiImpostazioni();
		
		preparaLetturaPulsanti();
		
		// autoregola dimensione finestra e posizionala al centro
		this.pack();
		this.setLocationRelativeTo(null);

		this.premuto = false;
		while(!premuto ){ // viene "sbloccato dal Listener"
			LP.waitFor(200);
		}
		leggiImpostazioni();
		try {
			Main.avviaIlGioco();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
	}

	private void preparaLetturaPulsanti() {
		Listener listener = new Listener();
		ospite.addActionListener(listener);
		accedi.addActionListener(listener);
	}

	private void leggiImpostazioni() {
		GestoreSuoni.setEffettiAbilitati(opzEffetti.isSelected());
		GestoreSuoni.setMusicaAbilitata(opzMusica.isSelected());
	}

	private void regolaDimensioni() {
		Dimension dim =
				Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(dim.getWidth()-this.getWidth())/2,
				(int)(dim.getHeight()-this.getHeight())/2);
	}

	private void aggiungiPannelliAlContainer() {
		getContentPane().add(PannelloInserimenti,BorderLayout.NORTH);
		getContentPane().add(PannelloOpzioni,BorderLayout.CENTER);
		getContentPane().add(PannelloTastiConferma,BorderLayout.SOUTH);
	}

	private void preimpostaPannelli() {
		opzEffetti.setSelected(true);
		opzMusica.setSelected(true);	
	}

	private void creaPannelli() {
		PannelloInserimentoNome = new JPanel();
		PannelloInserimentoPassword = new JPanel();
		PannelloInserimenti = new JPanel();
		PannelloOpzioni = new JPanel();
		PannelloTastiConferma = new JPanel();
		messaggioNome = new JLabel("Username");
		nomeInserito = new JTextField(16);
		messaggioPassword = new JLabel("Password");	
		passwordInserita = new JPasswordField(16);
		
		opzMusica = new JCheckBox("Musica di sottofondo");
		opzEffetti = new JCheckBox("Effetti Sonori");

		accedi=new JButton("Accedi e gioca");
		ospite=new JButton("Gioca come ospite");
		
	}

	private void sistemaPannelli() {
		PannelloInserimentoNome.add(messaggioNome);
		PannelloInserimentoNome.add(nomeInserito);
		PannelloInserimentoPassword.add(messaggioPassword);
		PannelloInserimentoPassword.add(passwordInserita);
		PannelloInserimenti.setLayout(new GridLayout(2,1));
		PannelloInserimenti.add(PannelloInserimentoNome);
		PannelloInserimenti.add(PannelloInserimentoPassword);
		PannelloOpzioni.setLayout(new GridLayout(1,2));
		PannelloOpzioni.add(opzEffetti);
		PannelloOpzioni.add(opzMusica);
		PannelloOpzioni.add(opzEffetti);
		PannelloOpzioni.add(opzMusica);
		PannelloTastiConferma.setLayout(new GridLayout(1, 2));
		PannelloTastiConferma.add(accedi);
		PannelloTastiConferma.add(ospite);
	}

	public String getNomeUtente() {
		return this.nomeUtente;
	}

	public String getPassword() {
		return this.password;
	}

	public void chiudiFinestra() {

	}

	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource();
			if (src == accedi){
				JOptionPane.showMessageDialog(null, 
						"Funzione non ancora implementata");
				Main.setNomeUtente(nomeInserito.getText());
				Main.setPassword(passwordInserita.getText());
			}
			if (src == ospite){
				setPremuto(true);
			}
		}
	}

	public boolean isPremuto() {
		return premuto;
	}

	public void setPremuto(boolean premuto) {
		this.premuto = premuto;
	}

}
