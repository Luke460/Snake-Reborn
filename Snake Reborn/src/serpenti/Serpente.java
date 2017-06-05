package serpenti;

import java.util.Iterator;
import java.util.LinkedList;

import audio.GestoreSuoni;
import supporto.Direzione;
import supporto.Posizione;
import terrenoDiGioco.Casella;
import terrenoDiGioco.Stanza;
import static supporto.Costanti.*;
import supporto.Utility;

public abstract class Serpente {

	private LinkedList<Casella> caselle;
	private String nome;
	private Direzione direzione;
	private Casella casellaDiTesta;
	private boolean isVivo;	
	private int ciboPreso;

	public Serpente(String nome, Stanza stanza) {
		this.nome=nome;
		this.isVivo=true;
		this.ciboPreso=0;
		// sempre al centro della stanza (20,20)
		Posizione posizionePrimaCasella = new Posizione(DIMENSIONE_STANZA_DEFAULT/2,DIMENSIONE_STANZA_DEFAULT/2);
		// direzione casuale
		Direzione direzioneSerpente = new Direzione();
		this.setDirezione(direzioneSerpente);
		Direzione direzioneCreazioneCaselle = direzioneSerpente.getInversa();
		// creo la testa del serpente
		this.setCaselle(new LinkedList<Casella>());
		Casella primaCasella = stanza.getCaselle().get(posizionePrimaCasella);
		this.setCasellaDiTesta(primaCasella);
		primaCasella.setStato(CARATTERE_CASELLA_PLAYER1);
		primaCasella.setSerpente(this);
		int vitaCasella = VITA_SERPENTE_DEFAULT;
		primaCasella.setVita(vitaCasella);
		this.getCaselle().add(primaCasella);

		// creo le altre caselle del serpente

		Casella casellaPrecedente = primaCasella;
		for(int i=0; i<VITA_SERPENTE_DEFAULT-1; i++){
			Casella casella = stanza.getCasellaAdiacente(direzioneCreazioneCaselle, casellaPrecedente);
			casella.setStato(CARATTERE_CASELLA_PLAYER1);
			casella.setSerpente(this);
			vitaCasella--;
			casella.setVita(vitaCasella);
			this.getCaselle().add(casella);
			casellaPrecedente = casella;
		}
	}

	public Stanza getStanzaCorrente() {
		return this.caselle.getFirst().getStanza();
	}

	public Casella getCasellaDiTesta(){
		return this.casellaDiTesta;
	}

	public Casella getCasellaDiCoda(){
		return this.caselle.getLast();
	}

	public int getHP(){
		return this.casellaDiTesta.getVita();
	}

	public LinkedList<Casella> getCaselle() {
		return caselle;
	}

	public void setCaselle(LinkedList<Casella> caselle) {
		this.caselle = caselle;
	}

	public Stanza getStanza() {
		return this.getCasellaDiTesta().getStanza();
	}

	public void Sposta(Direzione d){
		this.setDirezione(d);
		Casella nuovaCasella = this.getCasellaDiTesta().getStanza().getCasellaAdiacente(d, this.getCasellaDiTesta());

		if(!nuovaCasella.isMortale()){
			if(nuovaCasella.isCibo()){
				this.incrementaVitaSerpente();
				nuovaCasella.setCasellaOccupataDalSerpente(this,this.getHP()+1,this.getCasellaDiTesta().getStato());
			} else {
				nuovaCasella.setCasellaOccupataDalSerpente(this,this.getHP(),this.getCasellaDiTesta().getStato());
			}
			int vitaSerpente = this.getHP();


			nuovaCasella.setVita(vitaSerpente);
			this.setCasellaDiTesta(nuovaCasella);

			Iterator<Casella> iteratore = this.getCaselle().iterator();
			while(iteratore.hasNext()){
				Casella temp = iteratore.next();
				temp.decrementaVita(); // la testa non si tocca
				if(temp.isMorta()){
					temp.libera();
					iteratore.remove();
				}
			}
			this.caselle.add(nuovaCasella);
		} else { // casella mortale
			if(nuovaCasella.isOccupataDaSerpente()){
				Serpente altroSerpente = nuovaCasella.getSerpente();
				if(altroSerpente.getCasellaDiTesta().getPosizione().equals(nuovaCasella.getPosizione())){
					altroSerpente.muori();
				}
			}
			this.muori();
		}
	}

	public void incrementaVitaSerpente() {
		this.ciboPreso++;
		for(Casella c : this.getCaselle()){
			c.incrementaVita();
		}
	}

	public void setCasellaDiTesta(Casella nuovaCasella) {
		this.casellaDiTesta = nuovaCasella;
	}

	public void muori(){
		
		Serpente serpenteDavanti = this.getCasellaDiTesta().getCasellaAdiacente(this.direzione).getSerpente();
		if(serpenteDavanti!=null){
			if(serpenteDavanti.getNome().equals(NOME_PLAYER_1)){
				GestoreSuoni.playSlainSound();
			}
		}
		for(Casella c:this.caselle){
			if(Utility.isPari(c.getPosizione().getX())&&Utility.isPari(c.getPosizione().getY())) {
				c.libera();
				c.setStato(CARATTERE_CASELLA_CIBO);
			} else if((!Utility.isPari(c.getPosizione().getX()))&&(!Utility.isPari(c.getPosizione().getY()))){
				c.libera();
				c.setStato(CARATTERE_CASELLA_CIBO);
			} else {
				c.libera();
				c.setStato(CARATTERE_CASELLA_VUOTA);
			}
		}
		this.caselle.clear();
		this.isVivo = false;
	}

	abstract public void FaiMossa();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Direzione getDirezione() {
		return direzione;
	}

	public void setDirezione(Direzione direzione) {
		this.direzione = direzione;
	}

	public boolean isMorto() {
		return !this.isVivo;
	}

	public void SetIsVivo(boolean b) {
		this.isVivo = b;
	}

	public int getCiboPreso() {
		return this.ciboPreso;
	}

	public void setCiboPreso(int ciboPreso) {
		this.ciboPreso = ciboPreso;
	}
	
	@Override
	public String toString(){
		return this.getNome() + " \t " + this.getClass().getSimpleName() + " \t " + (this.getHP()*MOLTIPLICATORE_PUNTEGGIO_CIBO);
	}

}
