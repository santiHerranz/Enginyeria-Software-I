package Domini;

import Presentacio.Sortida;

public class Taulell {

	public int[][] taulell;
	public int files = 0;
	public int columnes = 0;

	/*
	 * Constructor de taulell
	 */
	Taulell(int files, int columnes) {
		this.files = files;
		this.columnes = columnes;
	}

	
	/*
	 * Métode que estableix el taulell a les condicions inicials
	 */
	public void inicialitza(){
		this.taulell = new int[this.files][this.columnes];
		for (int i = 0; i < this.files; ++i){
			for (int j = 0; j < this.columnes; ++j){
				this.taulell[i][j] = 0;
			}
		}
	}
	
	/*
	 * Métode que guarda a la coordenada el valor del comptador  
	 */
	public void omple(Casella coord, int comptador){
		for (int i = 1; i <= this.files; ++i){
			for (int j = 1; j <= this.columnes; ++j){
				if (i == coord.x() && j == coord.y())
					this.taulell[i-1][j-1] = comptador;
			}
		}
	}	
	

/*
 * Métode que comprova si la coordenada està dins del taulell i està lliure 
 */
	public boolean comprobar(Casella coord){

		if ((coord.x() < 1 || coord.x() > this.files) || (coord.y() < 1 || coord.y() > this.columnes)){
			Sortida.missatgeln("Error fila i/o columna fora del taullel");
			return false;
		} else if (this.taulell[coord.x()-1][coord.y()-1] != 0){
			Sortida.missatgeln("La posició ja està ocupada");
			return false;
		}
		return true;
	}	

	/*
	 *  Métode que representa l'estat actual del taulell
	 */
	public void pinta(){
		for (int i = 1; i <= this.files; ++i) {
			for (int j = 1; j <= this.columnes; ++j) {
				Sortida.missatge(this.taulell[i-1][j-1] + "    ");
			}
			Sortida.missatgeln("");
		}
	}	
	
	
}
	
