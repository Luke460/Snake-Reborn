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

	public void Inverti() {
		this.ruotaDX();
		this.ruotaDX();
	}	
	
	@Override
	public String toString(){
		return "X = " + this.getX() + "  Y = " + this.getY();
	}
}
