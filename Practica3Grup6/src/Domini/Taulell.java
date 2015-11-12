package Domini;

import Presentacio.*;

public class Taulell {

	int CasellaBuida = 0;
	public int files = 0;
	public int columnes = 0;
	public Casella[][] taulell; // El taulell és un array de dos dimensions de caselles
	public Casella actual; // Coordenada de l'ultim moviment

	/*
	 * Constructor de taulell
	 */
	Taulell(int mida) {
		this(mida,mida);
	}
	Taulell(int files, int columnes) {
		this.files = files;
		this.columnes = columnes;
		this.taulell = new Casella[files][columnes];
		actual = new Casella();
		inicialitzar();
	}
	
	/*
	 * Métode que estableix el taulell a les condicions inicials
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
	 * Métode que guarda a la coordenada el valor del comptador  
	 */
	public void guardar(int x, int y, int comptador) throws Exception {

		if ((x < 1 || x > this.files) || (y < 1 || y > this.columnes)) 
			throw new Exception("Error fila i/o columna fora del taullel");

		if (this.getContingut(x, y) != this.CasellaBuida) 
			throw new Exception("La posició ja està ocupada");
				
		this.taulell[x-1][y-1].setContingut(x, y, comptador);
		this.actual.setX(x);
		this.actual.setY(y);
	}	
	
	/*
	 * Métode que obté el valor guardat de la coordenada   
	 */
	private int getContingut(int x, int y){
		return this.taulell[x-1][y-1].getContingut();
	}
	
	
		public String[][] estatTaulell(){
			String[][] sb = new String[this.files][this.columnes];
			
		for (int x = 0; x < this.files; x++) {
			for (int y = 0; y < this.columnes; y++) {
				sb[x][y] = String.valueOf(getContingut(x+1,y+1));
			}
		}
		return sb;
	}	
	
	
	
}
	
