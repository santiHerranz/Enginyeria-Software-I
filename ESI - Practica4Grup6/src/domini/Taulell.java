package domini;

public class Taulell {

	private final static int CASELLA_BUIDA = -1;
	private int[][] taulell; // El taulell es un array de dos dimensions de enters
	
	/*
	 * Constructor de taulell
	 */
	public Taulell(int mida) {
		this.taulell = new int[mida][mida];

		//Crear caselles buides del taulell
		for (int i = 0; i < mida; ++i){
			for (int j = 0; j < mida; ++j){
				this.taulell[i][j] = Taulell.CASELLA_BUIDA;
			}
		}
	}
	
	public int[][] estatTaulell(){
		return this.taulell;
	}	

	public boolean esCasellaBuida(int fila, int columna) throws Exception {
		if (foraLimits(fila, columna))
			throw new Exception("Error fila i/o columna fora del taullel");

		return (this.taulell[fila][columna] == Taulell.CASELLA_BUIDA);
	}

	public void omplirCasella(int fila, int columna, int value) throws Exception {
		if (foraLimits(fila, columna))
			throw new Exception("Error fila i/o columna fora del taullel");

			if (this.taulell[fila][columna] != Taulell.CASELLA_BUIDA) 
				throw new Exception("La posició ja està ocupada");
		
		this.taulell[fila][columna] = value;
	}
	
	public void buidarCasella(int fila, int columna) throws Exception {
		if (foraLimits(fila, columna))
			throw new Exception("Error fila i/o columna fora del taullel");
		this.taulell[fila][columna] = Taulell.CASELLA_BUIDA;
	}
	

	private boolean foraLimits(int fila, int columna) throws Exception{
		return ((fila < 0 || fila > this.taulell.length) || (columna < 0 || columna > this.taulell.length)); 
			
	}

	
}
	
