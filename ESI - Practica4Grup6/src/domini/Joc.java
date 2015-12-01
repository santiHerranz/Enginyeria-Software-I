package domini;

public class Joc {

	public final static int CASELLA_BUIDA = -1;

	public final static int STATUS_NORMAL = 1;
	public final static int STATUS_ACABAT = 100;
	public final static int STATUS_OFEGAT = 8;

	private Taulell taulell;
	private int mida;
	private Historial historial;
	private int status;

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
			return descripcioStatus();
		}

		if(this.historial.getMoviments()>0) {
			
			try {
				int[] actual = this.historial.ultimMoviment();
				
				int actualX = actual[0];
				int actualY = actual[1];
				
				if (!this.comprovarMovimentCavall(x, y, actualX, actualY))  
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
			int p[] = this.historial.ultimMoviment();
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

		int[] actual = this.historial.ultimMoviment();
		if(actual!= null){
			int actualX = actual[0];
			int actualY = actual[1];

			int[][] sb = this.estatTaulell();
	        for (int x = 0; x < sb.length; x++) {
				for (int y = 0; y < sb[x].length; y++) {

					if (this.taulell.esCasellaBuida(x, y)) {
						if (this.comprovarMovimentCavall(x, y, actualX, actualY))
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