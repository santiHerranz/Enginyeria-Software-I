package domini;

import java.util.ArrayList;

import javax.swing.ListModel;

public class Historial {
	
	ArrayList<Partida> historial;

	Historial(){
		this.historial = new ArrayList<Partida>();
	}
	
	public ArrayList<Partida> getHistorial () {
		return historial;
	}

	public void registrar(Partida partida) {
		historial.add(partida);
	}
	
	public Partida[] historialList(){
		Partida[] items = new Partida[historial.size()];
		historial.toArray(items);
		return items;
	}
	
}
