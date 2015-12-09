package domini;

import java.awt.Component;

public class Joc {
	
	
	private Jugador jugador;
	private Historial historial;
	private Dau D1; 
	private Dau D2;


	public Joc(String nom) throws Exception{
		jugador = new Jugador(nom);
		historial = new Historial();
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

	public void comprovarResultat() {
		
		int d1 = D1.getValue();
		int d2 = D2.getValue();
		
		Partida p = new Partida(jugador, d1, d2);		

		historial.registrar(p);
		
	}

	public Historial getHistorial() {
		return historial;
	}
	
	

}
