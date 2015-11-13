package Domini;

public class Taulell {

	private String CasellaBuida = "   ";
	private int mida = 0;
	private Casella[][] taulell; // El taulell �s un array de dos dimensions de caselles

	
	/*
	 * Constructor de taulell
	 */
	Taulell(int mida) {
		this.mida = mida;
		this.taulell = new Casella[mida][mida];
		inicialitzar();
	}
	
	/*
	 * M�tode que estableix el taulell a les condicions inicials
	 */
	private void inicialitzar(){
		for (int i = 0; i < this.mida; ++i){
			for (int j = 0; j < this.mida; ++j){
				this.taulell[i][j] = new Casella(this.CasellaBuida);
			}
		}
	}

	
	
	/*
	 * M�tode que guarda a la coordenada el valor del comptador  
	 */
	public void moure(int x, int y, String value) throws Exception {

		if ((x < 1 || x > this.mida) || (y < 1 || y > this.mida)) 
			throw new Exception("Error fila i/o columna fora del taullel");

		if (this.getContingut(x, y) != this.CasellaBuida) 
			throw new Exception("La posici� ja est� ocupada");
				
		this.taulell[x-1][y-1].setContingut(value);
	}
	
	public void esborrar(int x, int y) throws Exception {

		if ((x < 1 || x > this.mida) || (y < 1 || y > this.mida)) 
			throw new Exception("Error fila i/o columna fora del taullel");

		if (this.getContingut(x, y) == this.CasellaBuida) 
			throw new Exception("La posici� ja est� esborrada");
				
		this.taulell[x-1][y-1].setContingut(CasellaBuida);
	}	
		
	/*
	 * M�tode que obt� el valor guardat de la coordenada   
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
	
