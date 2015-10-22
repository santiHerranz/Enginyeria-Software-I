package Domini;

import Presentacio.Entrada;
import Presentacio.Sortida;

public class Joc {

	private static int comptador;
	private static int cordX;
	private static int cordY;
	private static int actualX;
	private static int actualY;



	public static void introduirDades(){
		Joc.ompleTaulell();
		Joc.pintaTaulell();

		cordX = Entrada.readInput("x");
		cordY = Entrada.readInput("y");
	}
	

	public static void ompleTaulell(){
		for (int i = 1; i <= Taulell.files; ++i){
			for (int j = 1; j <= Taulell.columnes; ++j){
				if (i == cordX && j == cordY) Taulell.taulell[i-1][j-1] = comptador;
			}
		}
	}

	public static void pintaTaulell(){
		for (int i = 1; i <= Taulell.files; ++i) {
			for (int j = 1; j <= Taulell.columnes; ++j) {
				Sortida.missatge(Taulell.taulell[i-1][j-1] + "    ");
			}
			System.out.println();
		}
	}

	public static boolean comprobarTaulell(int cordX, int cordY){

		if ((cordX < 1 || cordX > Taulell.files) || (cordY < 1 || cordY > Taulell.columnes)){
			System.out.println("Error fila i/o columna fora del taullel");
			return false;
		} else if (Taulell.taulell[cordX-1][cordY-1] != 0){
			System.out.println("La posició ja està ocupada");
			return false;
		}
		return true;
	}

	public static boolean comprobarMov(){
		if(actualX == 0 && actualY == 0) return true;

		if(actualY-2 == cordY && actualX-1 == cordX) return true;
		if(actualY-2 == cordY && actualX+1 == cordX) return true;
		if(actualY-1 == cordY && actualX-2 == cordX) return true;
		if(actualY-1 == cordY && actualX+2 == cordX) return true;
		if(actualY+1 == cordY && actualX-2 == cordX) return true;
		if(actualY+1 == cordY && actualX+2 == cordX) return true;
		if(actualY+2 == cordY && actualX-1 == cordX) return true;
		if(actualY+2 == cordY && actualX+1 == cordX) return true;
		Sortida.missatge(String.format("El moviment %d, %d no és vàlid!, ha de ser el salt del cavall dels escacs.\n", cordX, cordY));
		return false;
	}

	public static void main(String args[]) {
		comptador = 0;
		Taulell.inicialitzaTaulell();

		while (comptador < Taulell.files*Taulell.columnes) {
			Joc.introduirDades();
			if (Joc.comprobarTaulell(cordX, cordY) && Joc.comprobarMov()){
				actualX = cordX;
				actualY = cordY;
				comptador++;
				Sortida.missatge(String.format("Molt bé! Queden %d moviments per guanyar\n", Taulell.files*Taulell.columnes-comptador));
			} else {
				cordX = 0;
				cordY = 0;
			}
		}
		if (comptador == Taulell.files*Taulell.columnes) {
			Joc.ompleTaulell();
			Joc.pintaTaulell();
			Sortida.missatge("Enhorabona! Has guanyat!");
		}
	}
}