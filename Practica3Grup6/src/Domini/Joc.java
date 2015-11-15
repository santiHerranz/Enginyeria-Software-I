package Domini;

public class Joc {

	private int mida;
	private Taulell taulell;
	private Apuntador apuntador;
	
	public Joc(int mida){
		this.mida = mida;
		this.taulell = new Taulell(mida);
		this.apuntador = new Apuntador();
	}
	
	public void mouCavall( int x, int y) throws Exception {
		
		if (this.comprovarMovimentCavall(x,y)) { 
			this.taulell.moure(x, y, String.valueOf(this.apuntador.moviments+1));
			this.apuntador.guardar(x,y);
		}
	}
	public void desferMoviment() throws Exception {
		if (this.apuntador.moviments==0)
			throw new Exception("No hi ha m�s moviments per desfer");
		
		Coord p = this.apuntador.ultimMoviment();
		this.taulell.esborrar(p.x, p.y);
		this.apuntador.desferUltimMoviment();
	}	

	/*
	 * M�tode per obtenir la representaci� del taulell
	 */
	public String[][] estatTaulell() {
		return this.taulell.estatTaulell();
	}
	
	/*
	 * M�tode que comprovar si el joc ha acabat
	 */
	public boolean acabat(){
		return this.apuntador.moviments == this.mida*this.mida;		
	}
	/*
	 * M�tode que comprovar si el cavall est� ofegat
	 */
	public boolean ofegat() {
		if(this.apuntador.moviments==0) return false; //El primer moviment sempre �s v�lid

	        String[][] sb = this.estatTaulell();
	        for (int x = 0; x < sb.length; x++) {
				for (int y = 0; y < sb[x].length; y++) {
					try {
						if (comprovarMovimentCavall(x,y))
							if (this.taulell.esCasellaBuida(x, y))
								return false;
					} catch (Exception e) {}
				}
	        }
			return true;
	}
	
	public int moviments() {
		return this.apuntador.moviments;
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
	public boolean comprovarMovimentCavall(int x, int y) throws Exception {
		
		if(this.apuntador.moviments==0) return true; //El primer moviment sempre �s v�lid

		Coord actual = this.apuntador.ultimMoviment();
		
		if(actual.y-2 == y && actual.x-1 == x) return true;
		if(actual.y-2 == y && actual.x+1 == x) return true;
		if(actual.y-1 == y && actual.x-2 == x) return true;
		if(actual.y-1 == y && actual.x+2 == x) return true;
		if(actual.y+1 == y && actual.x-2 == x) return true;
		if(actual.y+1 == y && actual.x+2 == x) return true;
		if(actual.y+2 == y && actual.x-1 == x) return true;
		if(actual.y+2 == y && actual.x+1 == x) return true;
		throw new Exception(String.format("El moviment %d, %d no �s v�lid!, ha de ser el salt del cavall dels escacs.", x, y));
	}

}