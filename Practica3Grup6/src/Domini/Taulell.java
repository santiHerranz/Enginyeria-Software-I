package Domini;

public class Taulell {

	private String CASELLA_BUIDA = " ";
	private int mida = 0;
	private Casella[][] taulell; // El taulell és un array de dos dimensions de caselles

	
	/*
	 * Constructor de taulell
	 */
	Taulell(int mida) {
		if(mida <3) throw new  IllegalArgumentException("La mida no pot ser inferior a 3");
		if(mida >10) throw new  IllegalArgumentException("La mida no pot ser superior a 10");
		this.mida = mida;
		this.taulell = new Casella[mida][mida];
		inicialitzar();
	}
	
	/*
	 * Métode que estableix el taulell a les condicions inicials
	 */
	private void inicialitzar(){
		for (int i = 0; i < this.mida; ++i){
			for (int j = 0; j < this.mida; ++j){
				this.taulell[i][j] = new Casella(this.CASELLA_BUIDA);
			}
		}
	}
	
	public boolean esCasellaBuida(int x, int y) throws Exception {

		if ((x < 1 || x > this.mida) || (y < 1 || y > this.mida)) 
			throw new Exception("Error fila i/o columna fora del taullel");

		if (this.getContingut(x, y) != this.CASELLA_BUIDA) 
			return false;
		
		return true;
	}
	
	
	/*
	 * Métode que posa el valor a la coordenada  
	 */
	public void moure(int x, int y, String value) throws Exception {

		if ((x < 1 || x > this.mida) || (y < 1 || y > this.mida)) 
			throw new Exception("Error fila i/o columna fora del taullel");

		if (this.getContingut(x, y) != this.CASELLA_BUIDA) 
			throw new Exception("La posició ja està ocupada");
				
		this.taulell[x-1][y-1].setContingut(value);
	}
	
	/*
	 * Métode que esborra el contingut de la coordenada  
	 */
	public void esborrar(int x, int y) throws Exception {

		if ((x < 1 || x > this.mida) || (y < 1 || y > this.mida)) 
			throw new Exception("Error fila i/o columna fora del taullel");

		if (this.getContingut(x, y) == this.CASELLA_BUIDA) 
			throw new Exception("La posició ja està esborrada");
				
		this.taulell[x-1][y-1].setContingut(CASELLA_BUIDA);
	}	
		
	/*
	 * Métode que obté el valor guardat de la coordenada   
	 */
	private String getContingut(int x, int y){
		return this.taulell[x-1][y-1].getContingut();
	}
	
	
		public String[][] estatTaulell(){
			String[][] sb = new String[this.mida][this.mida];
			
		for (int x = 0; x < this.mida; x++) {
			for (int y = 0; y < this.mida; y++) {
				sb[x][y] = String.valueOf(getContingut(x+1,y+1));
			}
		}
		return sb;
	}	
	
	
	
}
	
