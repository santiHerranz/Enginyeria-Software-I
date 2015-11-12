package Domini;

public class Joc {

	private int comptador;
	private Taulell taulell;
	
	public Joc(int mida){
		this.comptador = 0;
		this.taulell = new Taulell(mida);
	}
	
	public void mouCavall( int x, int y) throws Exception {
		
		if (comprovarMovimentCavall(x,y)) { 
			this.taulell.guardar(x, y, ++this.comptador);
		}
	}
	public void desferMoviment() throws Exception {
		throw new Exception("No hi ha més moviments per desfer");
	}	

	/*
	 * Métode per pintar el taulell
	 */
	public String[][] estatTaulell() {
		return this.taulell.estatTaulell();
	}
	
	/*
	 * Métode que comprova si el joc ha acabat
	 */
	public boolean acabat(){
		return this.comptador == this.taulell.files*this.taulell.columnes;		
	}
	
	/*
	 * Métode que comprova el moviment del cavall
	 */
	public boolean comprovarMovimentCavall(int jugadorX, int jugadorY) throws Exception {
		Casella actual = this.taulell.actual;
		if(actual.getX() == 0 && actual.getY() == 0) return true;

		if(actual.getY()-2 == jugadorY && actual.getX()-1 == jugadorX) return true;
		if(actual.getY()-2 == jugadorY && actual.getX()+1 == jugadorX) return true;
		if(actual.getY()-1 == jugadorY && actual.getX()-2 == jugadorX) return true;
		if(actual.getY()-1 == jugadorY && actual.getX()+2 == jugadorX) return true;
		if(actual.getY()+1 == jugadorY && actual.getX()-2 == jugadorX) return true;
		if(actual.getY()+1 == jugadorY && actual.getX()+2 == jugadorX) return true;
		if(actual.getY()+2 == jugadorY && actual.getX()-1 == jugadorX) return true;
		if(actual.getY()+2 == jugadorY && actual.getX()+1 == jugadorX) return true;
		throw new Exception(String.format("El moviment %d, %d no és vàlid!, ha de ser el salt del cavall dels escacs.", jugadorX, jugadorY));
	}

}