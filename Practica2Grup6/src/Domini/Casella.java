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
	
	
	public int x() {
		return coordX;
	}
	public void x(int cordX) {
		this.coordX = cordX;
	}
	public int y() {
		return coordY;
	}
	public void y(int cordY) {
		this.coordY = cordY;
	}

}
