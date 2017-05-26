package terrenoDiGioco;

import static supporto.Costanti.*;
import serpenti.Serpente;
import supporto.Direzione;
import supporto.Posizione;

public class Casella  {

	private Posizione posizione;
	private char stato;
	private Stanza stanza;
	private int vita;
	private Serpente serpente;

	public Casella(Stanza stanza, Posizione posizione, char stato) {
		this.posizione = posizione;
		this.stato = stato;
		this.vita = -1;
		this.stanza = stanza;
	}

	public Posizione getPosizione() {
		return this.posizione;
	}

	public char getStato() {
		return stato;
	}

	public void setStato(char stato) {
		this.stato = stato;
	}

	public void setPosizione(Posizione posizione) {
		this.posizione = posizione;
	}

	public Stanza getStanza() {
		return stanza;
	}

	public void setStanza(Stanza stanza) {
		this.stanza = stanza;
	}

	@Override
	public int hashCode(){
		return this.getPosizione().hashCode();
	}

	@Override
	public boolean equals(Object o){
		Casella that = (Casella) o;
		return (this.getPosizione().equals(that.getPosizione())
				&& this.getStanza().equals(that.getStanza()));
	}

	public int getVita() {
		return vita;
	}

	public void setVita(int vita) {
		this.vita = vita;
	}

	public void decrementaVita() {
		this.vita--;
		if(vita<=0){
			this.vita=0;
			this.setStato(CARATTERE_CASELLA_VUOTA); // libera la casella
			this.serpente = null;
		}
	}

	public void setCasellaOccupataDalSerpente(Serpente serpente, int vita, char stato) {
		this.setSerpente(serpente);
		this.setVita(vita);
		this.setStato(stato);
	}

	public Serpente getSerpente() {
		return serpente;
	}

	public void setSerpente(Serpente serpente) {
		this.serpente = serpente;
	}

	@Override
	public String toString(){
		return "Stanza: " + this.getStanza().getNome() + "  Vita: " + this.getVita() + "  " + this.getPosizione().toString();
	}

	public boolean isMorta() {
		if (this.getVita()<=0) return true;
		return false;
	}

	public boolean isMortale() {
		if (this.getStato() == CARATTERE_CASELLA_VUOTA || this.getStato() == CARATTERE_CASELLA_CIBO) return false;
		return true;
	}

	public void libera() {
		this.stato = CARATTERE_CASELLA_VUOTA;
		this.vita = -1;
	}

	public boolean isCibo() {
		if(this.getStato() == CARATTERE_CASELLA_CIBO) return true;
		return false;
	}

	public boolean isVuota() {
		if(this.getStato() == CARATTERE_CASELLA_VUOTA) return true;
		return false;
	}

	public void incrementaVita() {
		if(this.vita<VITA_SERPENTE_MASSIMA){
			this.vita++;
		}
	}

	public boolean isMuro() {
		if (this.getStato() == CARATTERE_CASELLA_MURO || this.getStato() == CARATTERE_CASELLA_PORTALE) return true;
		return false;
	}

	public Casella getCasellaAdiacente(Direzione direzione) {
		return this.stanza.getCasellaAdiacente(direzione, this);
	}
}
