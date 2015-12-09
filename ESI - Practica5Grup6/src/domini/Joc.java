package domini;

import java.util.ArrayList;

public class Joc {
	
	
	private Jugador jugador;
	private ArrayList<Partida> historial;
	private Dau D1; 
	private Dau D2;


	public Joc(String nom) throws Exception{
		jugador = new Jugador(nom);
		historial = new ArrayList<Partida>();
		D1 = new Dau();
		D2 = new Dau();
	}
	
	public Dau getDau1(){
		return D1;
	}
	
	public Dau getDau2(){
		return D2;
	}
	
	
	public String getNomJugador() {
		return jugador.getNom();
	}


	public Partida[] getHistorial() {
			Partida[] items = new Partida[this.historial.size()];
			historial.toArray(items);
			return items;
	}
	
	public String llançar() {
		
		this.D1.llançar();
		this.D2.llançar();
		
		Partida p = new Partida(jugador, this.D1.getValue(), this.D2.getValue());		

		historial.add(p);
		
		return p.getResultat();
		
	}
	
	

}
