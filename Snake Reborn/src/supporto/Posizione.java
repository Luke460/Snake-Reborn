package supporto;

/**
 * Rappresenta una posizione, modellata come coppia di interi 
 * ascissa (x) ed ordinata (y), all'interno del {@link Pozzo}.
 * 
 */
public class Posizione {

	private int x;
	
	private int y;
	
	public Posizione(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public Posizione traslata(int dx, int dy) {
		return new Posizione(getX()+dx, getY()+dy);
	}
	
	public Posizione traslata(Direzione d) {
		return new Posizione(getX()+d.getX(),getY()+d.getY());
	}
	
	@Override
	public int hashCode(){
		return this.getX()+this.getY();
	}
	
	@Override
	public boolean equals(Object o){
		Posizione that = (Posizione) o;
		return (this.getX()==that.getX() &&
				this.getY()==that.getY());
	}
	@Override
	public String toString(){
		return "X = " + this.getX() + "  Y = " + this.getY();
	}

}
