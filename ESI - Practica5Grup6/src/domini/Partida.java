package domini;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Partida {

	public static final int PARTIDA_VALOR_GUANYADOR = 7;
	public static final int PARTIDA_GUANYADA = 1;
	public static final int PARTIDA_PERDUDA = 0;
	
	Jugador j;
	int d1, d2, estat;
	int resultat;
	Date d;

	public Partida(Jugador j, int d1, int d2) {

		this.j = j;
		this.d = new Date();
		this.d1 = d1;
		this.d2 = d2;

		int estat = 0;
		int resultat = d1 + d2;

		if(resultat == Partida.PARTIDA_VALOR_GUANYADOR)
			estat = Partida.PARTIDA_GUANYADA;
		else 
			estat = Partida.PARTIDA_PERDUDA;
		
		this.resultat = resultat;
		this.estat = estat;
		
	}
	
	public String getResultat() {
		switch(estat) {
		case PARTIDA_GUANYADA: 
			 return "HAS GUANYAT";
		case PARTIDA_PERDUDA: 
			 return "HAS PERDUT";
		default:
			return "";
		}		
	}
	
	
	@Override
	public String toString() {
		String value = j.getNom() +"  ";
		
		Date dNow = d;
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");

		value += ft.format(dNow) +" - > "+ d1 +" + "+ d2 +"  = "+ resultat +"  ";
		
		switch(estat) {
		case PARTIDA_GUANYADA: 
			value += "GUANYAT";
			break;
		case PARTIDA_PERDUDA: 
			value += "PERDUT";
			break;
		default:
		}		
		return value;
	}

}
