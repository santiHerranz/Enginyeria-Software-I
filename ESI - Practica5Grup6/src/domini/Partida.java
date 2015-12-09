package domini;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Partida {

	public static final int VALOR_GUANYADOR = 7;
	public static final int ESTAT_GUANYAT = 1;
	public static final int ESTAT_PERDUT = 0;
	
	Jugador j;
	int d1, d2, resultat, estat;
	Date d;

	public Partida(Jugador j, int d1, int d2) {

		this.j = j;
		this.d = new Date();
		this.d1 = d1;
		this.d2 = d2;

		int estat = 0;
		int resultat = d1 + d2;

		if(resultat == Partida.VALOR_GUANYADOR)
			estat = Partida.ESTAT_GUANYAT;
		else 
			estat = Partida.ESTAT_PERDUT;
		
		this.resultat = resultat;
		this.estat = estat;
		
	}
	
	@Override
	public String toString() {
		String value = j.getNom() +"  ";
		
		Date dNow = d;
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");

		value += ft.format(dNow) +" - > "+ d1 +" + "+ d2 +"  = "+ resultat +"  ";
		
		switch(estat) {
		case ESTAT_GUANYAT: 
			value += "GUANYAT";
			break;
		case ESTAT_PERDUT: 
			value += "PERDUT";
			break;
		default:
		}		
		return value;
	}

}
