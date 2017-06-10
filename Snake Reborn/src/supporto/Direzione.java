package supporto;

public class Direzione {

	// +---x
	// | +
	// y   +

	private int y;
	private int x;

	public Direzione(int x, int y){
		this.x = x;
		this.y = y;
	}

	public Direzione(){
		int casuale = (int) ((Math.random()*4)+1); // da 1 a 4
		if(casuale==1){
			// destra
			this.x = 1;
			this.y = 0;
		} else if (casuale==2){
			// sinistra
			this.x = 0;
			this.y = 1;
		} else if (casuale==3){
			// sopra
			this.x = -1;
			this.y = 0;
		} else if (casuale==4){
			// sotto
			this.x = 0;
			this.y = -1;
		}
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void ruotaDX(){
		if(this.getX()==0 && this.getY()==-1){
			this.setX(1);
			this.setY(0);
			return;
		}
		if(this.getX()==1 && this.getY()==0){
			this.setX(0);
			this.setY(1);
			return;
		}
		if(this.getX()==0 && this.getY()==1){
			this.setX(-1);
			this.setY(0);
			return;
		}
		if(this.getX()==-1 && this.getY()==0){
			this.setX(0);
			this.setY(-1);
			return;
		}
	}

	public void ruotaSX(){
		if(this.getX()==0 && this.getY()==-1){
			this.setX(-1);
			this.setY(0);
			return;
		}
		if(this.getX()==1 && this.getY()==0){
			this.setX(0);
			this.setY(-1);
			return;
		}
		if(this.getX()==0 && this.getY()==1){
			this.setX(1);
			this.setY(0);
			return;
		}
		if(this.getX()==-1 && this.getY()==0){
			this.setX(0);
			this.setY(1);
			return;
		}
	}
	
	public Direzione getDirezioneDX(){
		Direzione ritorno = new Direzione();
		ritorno.setX(this.getX());
		ritorno.setY(this.getY());
		ritorno.ruotaDX();
		return ritorno;
	}
	
	public Direzione getDirezioneSX(){
		Direzione ritorno = new Direzione();
		ritorno.setX(this.getX());
		ritorno.setY(this.getY());
		ritorno.ruotaSX();
		return ritorno;
	}

	public void Inverti() {
		this.ruotaDX();
		this.ruotaDX();
	}
	
	public Direzione getInversa() {
		Direzione nuovaDirezione = new Direzione(this.getX(),this.getY());
		nuovaDirezione.ruotaDX();
		nuovaDirezione.ruotaDX();
		return nuovaDirezione;
	}

	@Override
	public String toString(){
		return "X = " + this.getX() + "  Y = " + this.getY();
	}
}
