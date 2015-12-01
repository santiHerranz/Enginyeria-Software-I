package domini;

public class Joc {

	public static final int CASELLA_BUIDA = -1;

	private Taulell taulell;

	private int mida;
	public int getMida() {
		return mida;
	}

	private Historial historial;
	public Historial getHistorial() {
		return historial;
	}

	public Joc(int mida){
		if(mida <3) throw new  IllegalArgumentException("La mida no pot ser inferior a 3");
		if(mida >10) throw new  IllegalArgumentException("La mida no pot ser superior a 10");

		this.mida = mida;
		this.taulell = new Taulell(mida);
		this.historial = new Historial();
	}
	
	public String mouCavall( int x, int y) {

		if(this.historial.getMoviments()>0) {
			
			if(this.acabat())
				return String.format("Joc acabat, HAS GUANYAT!");
			
			if(this.ofegat())
				return String.format("El cavall està ofegat, pots desfer els moviments.");
			
			try {
				int[] actual = this.historial.ultimMoviment();
				
				int actualX = actual[0];
				int actualY = actual[1];
				
				if (!this.comprovarMovimentCavall(x, y, actualX, actualY))  
					throw new Exception(String.format("El moviment (%s,%s) no es valid!, ha de ser el salt del cavall dels escacs.", x+1, y+1));

			} catch (Exception e) {
				return e.getMessage();
			}
		}

		try {
			this.taulell.omplirCasella(x, y, this.historial.getMoviments()+1);

			this.historial.guardar(x,y);
			return String.format("(%s,%s) Correcte, queden %s moviments per guanyar", x+1, y+1 , this.mida*this.mida - this.historial.getMoviments());		

		} catch (Exception e) {
			return e.getMessage();
		}
		
	}

	public String desferMoviment() {
			int p[] = this.historial.ultimMoviment();
			if(p == null)
				return "No hi ha més moviments!";
			else {
				try {
					this.taulell.omplirCasella(p[0], p[1], Joc.CASELLA_BUIDA);
					this.historial.desferUltimMoviment();
					
					if(this.historial.getMoviments()==0)
						return "Ultim moviment desfet!";
					return "Moviment desfet!";
				} catch (Exception e) {
					return e.getMessage();
				}
			}
	}	

	/*
	 * Metode per obtenir la representacio del taulell
	 */
	public int[][] estatTaulell() {
		return this.taulell.estatTaulell();
	}
	
	/*
	 * Metode que comprova si el joc ha acabat
	 */
	public boolean acabat(){
		return this.historial.getMoviments() == this.mida*this.mida;		
	}
	
	/*
	 * Metode que comprova si el cavall esta ofegat
	 */
	public boolean ofegat() {
		if(this.historial.getMoviments()==0) return false; //El primer moviment sempre es valid

		int[] actual = this.historial.ultimMoviment();
		if(actual!= null){
			int actualX = actual[0];
			int actualY = actual[1];

			int[][] sb = this.estatTaulell();
	        for (int x = 0; x < sb.length; x++) {
				for (int y = 0; y < sb[x].length; y++) {

					try {
						if (this.taulell.esCasellaBuida(x, y)) {
							if (this.comprovarMovimentCavall(x, y, actualX, actualY))
								return false;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        }
		}
		return true;
	}


	/*
	 * Metode que comprova el moviment del cavall
	 */
	public boolean comprovarMovimentCavall(int x, int y, int actualX, int actualY) {
	
		if(actualY-2 == y && actualX-1 == x) return true;
		if(actualY-2 == y && actualX+1 == x) return true;
		if(actualY-1 == y && actualX-2 == x) return true;
		if(actualY-1 == y && actualX+2 == x) return true;
		if(actualY+1 == y && actualX-2 == x) return true;
		if(actualY+1 == y && actualX+2 == x) return true;
		if(actualY+2 == y && actualX-1 == x) return true;
		if(actualY+2 == y && actualX+1 == x) return true;

		return false;
	}

}