package Domini;

import Presentacio.*;

public class Joc {

	private int comptador;
	private Taulell taulell;
	
	public Joc(){
		this.comptador = 0;
		this.taulell = new Taulell(5,5);
		this.taulell.inicialitzar();
	}
	
	public void mouCavall( int x, int y){
		this.taulell.guardar(x, y, ++this.comptador);
		this.taulell.actual.x(x);
		this.taulell.actual.y(y);
		IO.missatgeln(String.format("Molt bé! Queden %d moviments per guanyar", this.taulell.files*this.taulell.columnes-this.comptador));
	}
	
	/*
	 * Métode per pintar el taulell
	 */
	public void pintarTaulell() {
		this.taulell.pintar();
	}
	
	/*
	 * Métode que comprova si el joc ha acabat
	 */
	public boolean acabat(){
		return this.comptador == this.taulell.files*this.taulell.columnes;		
	}

	/*
	 * Métode que comprova el moviment al taulell
	 */
	public boolean comprovarTaulell(int x, int y){
		return this.taulell.comprovar(x, y);
	}
	
	/*
	 * Métode que comprova el moviment del cavall
	 */
	public boolean comprovarMovimentCavall(int jugadorX, int jugadorY) throws Exception {
		Casella actual = this.taulell.actual;
		if(actual.x() == 0 && actual.y() == 0) return true;

		if(actual.y()-2 == jugadorY && actual.x()-1 == jugadorX) return true;
		if(actual.y()-2 == jugadorY && actual.x()+1 == jugadorX) return true;
		if(actual.y()-1 == jugadorY && actual.x()-2 == jugadorX) return true;
		if(actual.y()-1 == jugadorY && actual.x()+2 == jugadorX) return true;
		if(actual.y()+1 == jugadorY && actual.x()-2 == jugadorX) return true;
		if(actual.y()+1 == jugadorY && actual.x()+2 == jugadorX) return true;
		if(actual.y()+2 == jugadorY && actual.x()-1 == jugadorX) return true;
		if(actual.y()+2 == jugadorY && actual.x()+1 == jugadorX) return true;
		throw new Exception(String.format("El moviment %d, %d no és vàlid!, ha de ser el salt del cavall dels escacs.", jugadorX, jugadorY));
	}

}