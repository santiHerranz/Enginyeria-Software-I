package domini;

public class Joc {

	
	public int mida;
	private Taulell taulell;
	private Historial apuntador;
	
	public Joc(int mida){
		if(mida <3) throw new  IllegalArgumentException("La mida no pot ser inferior a 3");
		if(mida >10) throw new  IllegalArgumentException("La mida no pot ser superior a 10");
		this.mida = mida;
		this.taulell = new Taulell(mida);
		this.apuntador = new Historial();
	}
	
	public void mouCavall( int x, int y) throws Exception {
		
		if (this.comprovarMovimentCavall(x,y)) { 
			this.taulell.moure(x, y, String.valueOf(this.apuntador.getMoviments()+1));
			this.apuntador.guardar(x,y);
		}else		
			throw new Exception(String.format("El moviment %d, %d no �s v�lid!, ha de ser el salt del cavall dels escacs.", x, y));

	}
	public void desferMoviment() throws Exception {
		
		Coord p = this.apuntador.ultimMoviment();
		this.taulell.esborrar(p.getCoord()[0], p.getCoord()[1]);
		this.apuntador.desferUltimMoviment();
	}	

	/*
	 * M�tode per obtenir la representaci� del taulell
	 */
	public String[][] estatTaulell() {
		return this.taulell.estatTaulell();
	}
	
	/*
	 * M�tode que comprova si el joc ha acabat
	 */
	public boolean acabat(){
		return this.apuntador.getMoviments() == this.mida*this.mida;		
	}
	
	/*
	 * M�tode que comprova si el cavall est� ofegat
	 */
	public boolean ofegat() throws Exception {
		if(this.apuntador.getMoviments()==0) return false; //El primer moviment sempre �s v�lid

	        String[][] sb = this.estatTaulell();
	        for (int x = 0; x < sb.length; x++) {
				for (int y = 0; y < sb[x].length; y++) {
					if (this.taulell.esCasellaBuida(x+1, y+1))
						if (comprovarMovimentCavall(x+1,y+1)) return false;
						else throw new Exception(String.format("El moviment %d, %d no �s v�lid!, ha de ser el salt del cavall dels escacs.", x, y));

					
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
	 * M�tode que comprova el moviment del cavall
	 */
	public boolean casellaBuida(int x, int y) {
		try {
			return this.taulell.esCasellaBuida(x, y);
		} catch (Exception e) {return true;	}
	}
	
	
	
	
	/*
	 * M�tode que comprova el moviment del cavall
	 */
	public boolean comprovarMovimentCavall(int x, int y) {
		
		if(this.apuntador.getMoviments()==0) return true; //El primer moviment sempre �s v�lid

		Coord actual = this.apuntador.ultimMoviment();
		
		if(actual.y-2 == y && actual.x-1 == x) return true;
		if(actual.y-2 == y && actual.x+1 == x) return true;
		if(actual.y-1 == y && actual.x-2 == x) return true;
		if(actual.y-1 == y && actual.x+2 == x) return true;
		if(actual.y+1 == y && actual.x-2 == x) return true;
		if(actual.y+1 == y && actual.x+2 == x) return true;
		if(actual.y+2 == y && actual.x-1 == x) return true;
		if(actual.y+2 == y && actual.x+1 == x) return true;
		return false;
	}

}