package domini;

public class Joc {

	public final static int CASELLA_BUIDA = -1;

	public final static int STATUS_NORMAL = 1;
	public final static int STATUS_ACABAT = 100;
	public final static int STATUS_OFEGAT = 8;

	private Taulell taulell;		// Taulell de joc
	private int mida;				// Mida del taulell
	private Historial historial;    // Seqüència ordenada de moviments realitzats
	private int status;				// Estat del joc 

	public int getMida() {
		return mida;
	}

	public Historial getHistorial() {
		return historial;
	}

	public int getStatus() {
		return status;
	}
	
	
	public Joc(int mida){
		if(mida <3) throw new  IllegalArgumentException("La mida no pot ser inferior a 3");
		if(mida >10) throw new  IllegalArgumentException("La mida no pot ser superior a 10");

		this.mida = mida;
		this.taulell = new Taulell(mida);
		this.historial = new Historial();
		this.status = STATUS_NORMAL;
	}
	
	public String mouCavall( int x, int y) {
		
		if(this.status == STATUS_OFEGAT || this.status == STATUS_ACABAT ){
			// Recordem al jugador l'estat del joc
			return descripcioStatus();
		}

		if(this.historial.getMoviments()>0) {
			
			try {
				int[] actual = this.historial.obtenirUltimMoviment();
	
				if (!this.esCorrecte(x, y, actual))  
					throw new Exception("El moviment invalid!, ha de ser el salt del cavall dels escacs.");

			} catch (Exception e) {
				return e.getMessage();
			}
		}

		try {

			this.taulell.omplirCasella(x, y, this.historial.getMoviments()+1);
			this.historial.guardar(x,y);
			this.status = STATUS_NORMAL;
			
			if(this.acabat()) 
				this.status = STATUS_ACABAT;
			else if(this.ofegat()) 
				this.status = STATUS_OFEGAT;
			
			return descripcioStatus();
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}

	private String descripcioStatus() {
			switch(this.status){
			case STATUS_NORMAL:
				return String.format("Correcte, queden %s moviments per guanyar", this.mida*this.mida - this.historial.getMoviments());		
			case STATUS_ACABAT:
				return String.format("Joc acabat, HAS GUANYAT!");
			case STATUS_OFEGAT:
				return String.format("El cavall està ofegat, pots desfer els moviments.");
			default:
		}
		return "";
	}

	public String desferMoviment() {
			int p[] = this.historial.obtenirUltimMoviment();
			if(p == null)
				return "No hi ha més moviments!";
			else {
				try {
					this.taulell.omplirCasella(p[0], p[1], Joc.CASELLA_BUIDA);
					this.historial.desferUltimMoviment();
					
					this.status = STATUS_NORMAL;

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
	public boolean ofegat() throws Exception {
		if(this.historial.getMoviments()==0) return false; //El primer moviment sempre es valid

		int[] actual = this.historial.obtenirUltimMoviment();
		if(actual!= null){

			int[][] sb = this.estatTaulell();
	        for (int x = 0; x < sb.length; x++) {
				for (int y = 0; y < sb[x].length; y++) {

					if (this.taulell.esCasellaBuida(x, y)) {
						if (this.esCorrecte(x, y, actual))
							return false;
					}
				}
	        }
		}
		return true;
	}


	/*
	 * Metode que comprova el moviment del cavall
	 */
	public boolean esCorrecte(int x, int y, int[] actual) {
		int actualX = actual[0];
		int actualY = actual[1];
		
		// 8 Moviments posibles del cavall
		final int CoordenadaX[]={ 1, 2, 2, 1,-1,-2,-2,-1 };
		final int CoordenadaY[]={ 2, 1,-1,-2,-2,-1, 1, 2 };		
		
		int col, fil;
		for (int i=0; i<8; i++){
			fil=actualX+CoordenadaX[i];
			col=actualY+CoordenadaY[i];
			if(fil== x && col == y) 
				return true;
		}

		return false;
	}


}