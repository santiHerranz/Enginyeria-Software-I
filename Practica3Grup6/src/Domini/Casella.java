package Domini;

public class Casella {
	private int coordX;
	private int coordY;
	private int contingut;
	
	public Casella(){
		
	}
	public Casella(int x, int y, int contingut){
		this.setContingut(x, y, contingut);
	}

	public int getContingut() {
		return contingut;
	}
	public void setContingut(int x, int y, int contingut) {
		this.coordX = x;
		this.coordY = y;
		this.contingut = contingut;
	}
	
	
	public int getX() {
		return coordX;
	}
	public void setX(int cordX) {
		this.coordX = cordX;
	}
	public int getY() {
		return coordY;
	}
	public void setY(int cordY) {
		this.coordY = cordY;
	}

}
