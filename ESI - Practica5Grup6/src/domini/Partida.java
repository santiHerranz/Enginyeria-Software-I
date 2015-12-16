package domini;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Partida {

	static final int PARTIDA_VALOR_GUANYADOR = 7;
	static final int PARTIDA_GUANYADA = 100;
	static final int PARTIDA_PERDUDA = 50;
	
	static int contador;

	private int numero;
	private String nomJugador;
	private int dau1, dau2;
	private int resultat;
	int estat;
	private Date data;

	public Partida(String nomJugador, int d1, int d2) {

		dau1 = d1;
		dau2 = d2;
		
		contador++;
		this.numero = contador;
		this.nomJugador = nomJugador;
		this.data = new Date();

		int nouEstat = 0;
		int resultat = d1 + d2;

		if(resultat == Partida.PARTIDA_VALOR_GUANYADOR)
			nouEstat = Partida.PARTIDA_GUANYADA;
		else 
			nouEstat = Partida.PARTIDA_PERDUDA;
		
		this.resultat = resultat;
		this.estat = nouEstat;
	}
	
	public String getResultat() {
		switch(estat) {
		case PARTIDA_GUANYADA: 
			 return this.nomJugador + " HAS GUANYAT!";
		case PARTIDA_PERDUDA: 
			 return this.nomJugador + " HAS PERDUT";
		default:
			return "";
		}		
	}
	
	@Override
	public String toString() {
		String value = numero + ". ";
		
		Date dNow = data;
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");

		value += ft.format(dNow) +" - > "+ dau1 +" + "+ dau2 +"  = "+ resultat +"  ";
		
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

	public int getDau1() {
		return dau1;
	}
	public int getDau2() {
		return dau2;
	}


}
