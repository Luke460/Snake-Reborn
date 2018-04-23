package terrenoDiGioco;
import java.util.*;

import org.hibernate.tuple.entity.EntityMetamodel.GenerationStrategyPair;

import serpenti.Serpente;
import supporto.*;

import static supporto.Costanti.*;

public class Stanza {

	private byte numerettoPerHash; // 128 numeri, va bene per circa 100-200 stanze
	private int codiceUnivocoStanza;
	private HashMap<Posizione,Casella> caselle;
	private HashMap<String,Stanza> collegamenti;
	private String nome;

	public Stanza(int codice){
		this.numerettoPerHash = (byte) (Math.random()*128);
		this.codiceUnivocoStanza = codice;
		this.caselle = new HashMap<>();
		this.nome = "Stanza non inizializzata";
		this.collegamenti=new HashMap<>();
		// collegamenti di default
		this.collegamenti.put(NORD, this);
		this.collegamenti.put(EST, this);
		this.collegamenti.put(SUD, this);
		this.collegamenti.put(OVEST, this);
	}

	public void CaricaFile(String nomeFile){
		CaricatoreStanza.CaricaFile(nomeFile, this);
	}

	public HashMap<Posizione, Casella> getCaselle() {
		return this.caselle;
	}

	public void setCaselle(HashMap<Posizione, Casella> caselle) {
		this.caselle = caselle;
	}

	public byte getNumerettoPerHash() {
		return numerettoPerHash;
	}

	public void setNumerettoPerHash(byte numerettoPerHash) {
		this.numerettoPerHash = numerettoPerHash;
	}

	public int getCodiceUnivocoStanza() {
		return codiceUnivocoStanza;
	}

	public HashMap<String, Stanza> getCollegamenti() {
		return collegamenti;
	}

	public void setCollegamenti(String direzione, Stanza stanzaDaCollegare) {
		this.collegamenti.put(direzione, stanzaDaCollegare);
	}

	@Override
	public int hashCode(){
		return this.numerettoPerHash;
	}

	@Override
	public boolean equals(Object o){
		Stanza that = (Stanza)o;
		return this.getCodiceUnivocoStanza()==that.getCodiceUnivocoStanza();
	}

	public void coloraPorta(String orientamentoPorta) {
		if(orientamentoPorta==NORD){
			this.caselle.get(new Posizione(15,0)).setStato(CARATTERE_CASELLA_PORTALE);
			this.caselle.get(new Posizione(24,0)).setStato(CARATTERE_CASELLA_PORTALE);
		}
		if(orientamentoPorta==EST){
			this.caselle.get(new Posizione(39,15)).setStato(CARATTERE_CASELLA_PORTALE);
			this.caselle.get(new Posizione(39,24)).setStato(CARATTERE_CASELLA_PORTALE);
		}
		if(orientamentoPorta==SUD){
			this.caselle.get(new Posizione(15,39)).setStato(CARATTERE_CASELLA_PORTALE);
			this.caselle.get(new Posizione(24,39)).setStato(CARATTERE_CASELLA_PORTALE);
		}
		if(orientamentoPorta==OVEST){
			this.caselle.get(new Posizione(0,15)).setStato(CARATTERE_CASELLA_PORTALE);
			this.caselle.get(new Posizione(0,24)).setStato(CARATTERE_CASELLA_PORTALE);
		}


	}

	public Casella getCasellaAdiacente(Direzione d, Casella casella) {
		Posizione posizioneNuovaCasella = new Posizione(casella.getPosizione().getX()+d.getX(),casella.getPosizione().getY()+d.getY());


		//controllo out of stanza
		if(posizioneNuovaCasella.getX()>=DIMENSIONE_STANZA_DEFAULT){
			posizioneNuovaCasella = new Posizione(0,posizioneNuovaCasella.getY()); 
			return this.collegamenti.get(EST).getCaselle().get(posizioneNuovaCasella);
		}

		if(posizioneNuovaCasella.getX()<0){
			posizioneNuovaCasella = new Posizione(DIMENSIONE_STANZA_DEFAULT-1,posizioneNuovaCasella.getY()); 
			return this.collegamenti.get(OVEST).getCaselle().get(posizioneNuovaCasella);
		}

		if(posizioneNuovaCasella.getY()>=DIMENSIONE_STANZA_DEFAULT){
			posizioneNuovaCasella = new Posizione(posizioneNuovaCasella.getX(),0); 
			return this.collegamenti.get(SUD).getCaselle().get(posizioneNuovaCasella);
		}
		if(posizioneNuovaCasella.getY()<0){
			posizioneNuovaCasella = new Posizione(posizioneNuovaCasella.getX(),DIMENSIONE_STANZA_DEFAULT-1); 
			return this.collegamenti.get(NORD).getCaselle().get(posizioneNuovaCasella);
		}

		// stiamo nei confini della stanza
		return  this.caselle.get(posizioneNuovaCasella);

	}


	public void setCasellaOccupataDalVerme(Serpente serpente, Casella casella, char stato) {
		casella.setStato(stato);
		casella.setVita(serpente.getHP());
	}

	public String getNome() {
		return this.nome;
	}

	public boolean isLibera() {
		for(Casella c:this.getCaselle().values()){
			// non � libera se � diversa da
			if(c.getStato()!=CARATTERE_CASELLA_VUOTA &&
					c.getStato()!=CARATTERE_CASELLA_CIBO &&
					c.getStato()!=CARATTERE_CASELLA_MURO &&
					c.getStato()!=CARATTERE_CASELLA_PORTALE){
				return false;
			}
			Posizione posizioneCentrale = new Posizione(DIMENSIONE_STANZA_DEFAULT/2,DIMENSIONE_STANZA_DEFAULT/2);
			Casella casellaCentrale = this.getCaselle().get(posizioneCentrale);
			if(casellaCentrale.getStato()!=CARATTERE_CASELLA_VUOTA && casellaCentrale.getStato()!=CARATTERE_CASELLA_CIBO) {
				return false;
			}
		}
		return true;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}