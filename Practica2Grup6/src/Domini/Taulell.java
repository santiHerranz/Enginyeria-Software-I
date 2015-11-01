package Domini;

import Presentacio.*;

public class Taulell {

	int CasellaBuida = 0;
	public int files = 0;
	public int columnes = 0;
	public Casella[][] taulell; // El taulell �s un array de dos dimensions de caselles
	public Casella actual; // Coordenada de l'ultim moviment

	/*
	 * Constructor de taulell
	 */
	Taulell(int files, int columnes) {
		this.files = files;
		this.columnes = columnes;
		this.taulell = new Casella[files][columnes];
		actual = new Casella();
		
	}
	
	/*
	 * M�tode que estableix el taulell a les condicions inicials
	 */
	public void inicialitzar(){
		for (int i = 0; i < this.files; ++i){
			for (int j = 0; j < this.columnes; ++j){
				this.taulell[i][j] = new Casella(i, j, this.CasellaBuida);
			}
		}
		actual.setContingut(0, 0, 0);
	}
	
	/*
	 * M�tode que guarda a la coordenada el valor del comptador  
	 */
	public void guardar(int x, int y, int comptador){
		this.taulell[x-1][y-1].setContingut(x, y, comptador);
	}	
	
	/*
	 * M�tode que obt� el valor guardat de la coordenada   
	 */
	private int getContingut(int x, int y){
		return this.taulell[x-1][y-1].getContingut();
	}
	

/*
 * M�tode que comprova si la coordenada est� dins del taulell i est� lliure 
 */
	public boolean comprovar(int x, int y){

		if ((x < 1 || x > this.files) || (y < 1 || y > this.columnes)){
			IO.missatgeln("Error fila i/o columna fora del taullel");
			return false;
		} else if (this.getContingut(x, y) != this.CasellaBuida){
			IO.missatgeln("La posici� ja est� ocupada");
			return false;
		}
		return true;

	}	

	/*
	 *  M�tode que representa l'estat actual del taulell
	 */
	public void pintar(){
		for (int x = 1; x <= this.files; ++x) {
			for (int y = 1; y <= this.columnes; ++y) {
				IO.missatge(getContingut(x,y) + "\t");
			}
			IO.missatgeln("");
		}
	}	
	
	
}
	
