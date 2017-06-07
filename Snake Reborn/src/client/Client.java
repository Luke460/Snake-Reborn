package client;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import javax.swing.JTextField;


import LukePack.LP;
import audio.GestoreSuoni;
import game.Main;
import game.Partita;

public class Client extends JFrame{

	private static final long serialVersionUID = 1L;
	private String nomeUtente;
	private String password;
	private boolean premuto;

	JPanel PannelloMessaggioLogin;
	JPanel PannelloInserimentoNome;
	JPanel PannelloInserimentoPassword;
	JPanel PannelloInserimenti;
	JPanel PannelloOpzioni;
	JPanel PannelloTastiConferma;
	JLabel messaggioLogin;
	JLabel messaggioNome;
	JTextField nomeInserito;
	JLabel messaggioPassword;
	JLabel messaggioLivello;
	JLabel messaggioPopolazione;
	JTextField passwordInserita;	
	JCheckBox opzMusica;
	JCheckBox opzEffetti;
	JComboBox<String> selettoreLivello;
	JComboBox<String> selettorePopolazione;
	JLabel messaggioInformativo;
	JButton accedi;
	JButton ospite;
	Partita partita;

	public Client(Partita partita){
		super("Snake Reborn");
		this.partita = partita;
		creaPannelli();
		sistemaPannelli();
		preimpostaPannelli();
		aggiungiPannelliAlContainer();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		leggiImpostazioni();
		
		preparaLetturaPulsanti();
		
		regolaFinestra();

		this.premuto = false;
		while(!premuto ){ // viene "sbloccato dal Listener" (busy waiting)
			LP.waitFor(250); // 4 volte al secondo
		}
		leggiImpostazioni();
		try {
			Main.avviaIlGioco();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	private void regolaFinestra() {
		// autoregola dimensione finestra e posizionala al centro
		//this.pack();
		this.setSize(360,230);
		this.setResizable(false);
		// centra la finestra
		this.setLocationRelativeTo(null);
	}

	private void preparaLetturaPulsanti() {
		Listener listener = new Listener();
		ospite.addActionListener(listener);
		accedi.addActionListener(listener);
	}

	private void leggiImpostazioni() {
		GestoreSuoni.setEffettiAbilitati(opzEffetti.isSelected());
		GestoreSuoni.setMusicaAbilitata(opzMusica.isSelected());
		partita.setLivello(selettoreLivello.getSelectedIndex()+1);
		partita.setFattorePopolazione(selettorePopolazione.getSelectedIndex()+1);
	}

	private void aggiungiPannelliAlContainer() {
		getContentPane().add(PannelloInserimenti,BorderLayout.NORTH);
		getContentPane().add(PannelloOpzioni,BorderLayout.CENTER);
		getContentPane().add(PannelloTastiConferma,BorderLayout.SOUTH);
	}

	private void preimpostaPannelli() {
		opzEffetti.setSelected(true);
		opzMusica.setSelected(true);
		selettoreLivello.setSelectedIndex(2);
		selettorePopolazione.setSelectedIndex(1);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void creaPannelli() {
		PannelloInserimentoNome = new JPanel();
		PannelloInserimentoPassword = new JPanel();
		PannelloInserimenti = new JPanel();
		PannelloOpzioni = new JPanel();
		PannelloTastiConferma = new JPanel();
		PannelloMessaggioLogin =  new JPanel();
		messaggioLogin = new JLabel("Login:");
		messaggioLogin.setFont(new Font(messaggioLogin.getFont().getFontName(), 2, 18));
		messaggioNome = new JLabel("Username");
		nomeInserito = new JTextField(16);
		messaggioPassword = new JLabel("Password");	
		passwordInserita = new JPasswordField(16);
		
		messaggioLivello=new JLabel(" Livello avversari:");
		messaggioPopolazione=new JLabel(" Popolazione serpenti:");
		opzMusica = new JCheckBox("Musica di sottofondo");
		opzEffetti = new JCheckBox("Effetti sonori");
		String[] data1 = {"basso", "medio", "alto*"}; 
		selettoreLivello = new JComboBox(data1);
		String[] data2 = {"bassa", "alta*"};
		selettorePopolazione = new JComboBox(data2);
		messaggioInformativo = new JLabel("*punteggio valido");

		accedi=new JButton("Accedi e gioca");
		ospite=new JButton("Gioca come ospite");
		
	}

	private void sistemaPannelli() {
		
		PannelloMessaggioLogin.add(messaggioLogin);
		
		PannelloInserimentoNome.add(messaggioNome);
		PannelloInserimentoNome.add(nomeInserito);
		PannelloInserimentoPassword.add(messaggioPassword);
		PannelloInserimentoPassword.add(passwordInserita);
		
		PannelloInserimenti.setLayout(new GridLayout(3,1));
		
		PannelloInserimenti.add(PannelloMessaggioLogin);
		PannelloInserimenti.add(PannelloInserimentoNome);
		PannelloInserimenti.add(PannelloInserimentoPassword);
		
		PannelloOpzioni.setLayout(new GridLayout(4,2));
		PannelloOpzioni.add(messaggioLivello);
		PannelloOpzioni.add(messaggioPopolazione);
		PannelloOpzioni.add(selettoreLivello);
		PannelloOpzioni.add(selettorePopolazione);
		PannelloOpzioni.add(opzEffetti);
		PannelloOpzioni.add(messaggioInformativo);
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
