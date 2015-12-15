package domini;

import java.util.ArrayList;

public class Joc {
	
	private Jugador jugador;
	private Dau D1; 
	private Dau D2;
	private Partida partida;
	private ArrayList<Partida> historial;

	public Joc(String nom) throws Exception{
		this.jugador = new Jugador(nom);
		this.D1 = new Dau();
		this.D2 = new Dau();
		this.historial = new ArrayList<Partida>();
	}
	

	public String[] getHistorial() {
		
		
		String[] items = new String[this.historial.size()];

		for(int i=0; i< historial.size(); i++)
			items[i] = historial.get(i).toString(); 
		
		return items;
	}
	
	public void tirarDaus() {
		this.D1.tirarDau();
		this.D2.tirarDau();
		
		this.partida = new Partida(this.jugador.getNom(), D1.getValue(), D2.getValue());		
		this.historial.add(this.partida);
	}

	public int getDau1() {
		return D1.getValue();
	}

	public int getDau2() {
		return D2.getValue();
	}


	public String getResultat() {
		return partida.getResultat();
	}


	public float getPercentatge() {
		float total = historial.size();
		float guanyat = 0;

		for(Partida p: historial)
			if (p.estat == Partida.PARTIDA_GUANYADA)
				guanyat ++;
		
		return guanyat/total*100.0f;
	}
}
