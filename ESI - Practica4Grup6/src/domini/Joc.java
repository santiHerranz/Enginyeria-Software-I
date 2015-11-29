package domini;

public class Joc {

	
	public int mida;
	private Taulell taulell;
	private Apuntador apuntador;
	
	public Joc(int mida){
		if(mida <3) throw new  IllegalArgumentException("La mida no pot ser inferior a 3");
		if(mida >10) throw new  IllegalArgumentException("La mida no pot ser superior a 10");
		this.mida = mida;
		this.taulell = new Taulell(mida);
		this.apuntador = new Apuntador();
	}
	
	public void mouCavall( int x, int y) throws Exception {
		
		if (this.comprovarMovimentCavall(x,y)) { 
			this.taulell.moure(x, y, String.valueOf(this.apuntador.getMoviments()+1));
			this.apuntador.guardar(x,y);
		}
	}
	public void desferMoviment() throws Exception {
		
		Coord p = this.apuntador.ultimMoviment();
		this.taulell.esborrar(p.x, p.y);
		this.apuntador.desferUltimMoviment();
	}	

	/*
	 * Métode per obtenir la representació del taulell
	 */
	public String[][] estatTaulell() {
		return this.taulell.estatTaulell();
	}
	
	/*
	 * Métode que comprova si el joc ha acabat
	 */
	public boolean acabat(){
		return this.apuntador.getMoviments() == this.mida*this.mida;		
	}
	
	/*
	 * Métode que comprova si el cavall està ofegat
	 */
	public boolean ofegat() {
		if(this.apuntador.getMoviments()==0) return false; //El primer moviment sempre és vàlid

	        String[][] sb = this.estatTaulell();
	        for (int x = 0; x < sb.length; x++) {
				for (int y = 0; y < sb[x].length; y++) {
					try {
						if (this.taulell.esCasellaBuida(x+1, y+1))
							if (comprovarMovimentCavall(x+1,y+1))
								return false;
					} catch (Exception e) {}
				}
	        }
			return true;
	}
	
	public int moviments() {
		return this.apuntador.getMoviments();
	}

	public Coord posicioCavall() {
		try{
			return this.apuntador.ultimMoviment();
		} catch(Exception e) {
			return null;
		}
	}	
	
	
	
	/*
	 * Métode que comprova el moviment del cavall
	 */
	public boolean casellaBuida(int x, int y) {
		try {
			return this.taulell.esCasellaBuida(x, y);
		} catch (Exception e) {return true;	}
	}
	
	
	
	
	/*
	 * Métode que comprova el moviment del cavall
	 */
	public boolean comprovarMovimentCavall(int x, int y) throws Exception {
		
		if(this.apuntador.getMoviments()==0) return true; //El primer moviment sempre és vàlid

		Coord actual = this.apuntador.ultimMoviment();
		
		if(actual.y-2 == y && actual.x-1 == x) return true;
		if(actual.y-2 == y && actual.x+1 == x) return true;
		if(actual.y-1 == y && actual.x-2 == x) return true;
		if(actual.y-1 == y && actual.x+2 == x) return true;
		if(actual.y+1 == y && actual.x-2 == x) return true;
		if(actual.y+1 == y && actual.x+2 == x) return true;
		if(actual.y+2 == y && actual.x-1 == x) return true;
		if(actual.y+2 == y && actual.x+1 == x) return true;
		throw new Exception(String.format("El moviment %d, %d no és vàlid!, ha de ser el salt del cavall dels escacs.", x, y));
	}

}