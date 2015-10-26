package Domini;

import Presentacio.Entrada;
import Presentacio.Sortida;

public class Joc {

	private  int comptador;
	
	private Taulell taulell;
	private Casella actual; // Coordenada de l'ultim moviment
	private Casella coord; // Coordenada llegida

	public Joc(){
		taulell = new Taulell(4,4);
		coord = new Casella();
		actual = new Casella();
	}

	/*
	 * Métode que comprova el moviment del cavall
	 */
	public boolean comprovarMovimentCavall(Casella ultima, Casella actual){
		if(actual.x() == 0 && actual.y() == 0) return true;

		if(actual.y()-2 == ultima.y() && actual.x()-1 == ultima.x()) return true;
		if(actual.y()-2 == ultima.y() && actual.x()+1 == ultima.x()) return true;
		if(actual.y()-1 == ultima.y() && actual.x()-2 == ultima.x()) return true;
		if(actual.y()-1 == ultima.y() && actual.x()+2 == ultima.x()) return true;
		if(actual.y()+1 == ultima.y() && actual.x()-2 == ultima.x()) return true;
		if(actual.y()+1 == ultima.y() && actual.x()+2 == ultima.x()) return true;
		if(actual.y()+2 == ultima.y() && actual.x()-1 == ultima.x()) return true;
		if(actual.y()+2 == ultima.y() && actual.x()+1 == ultima.x()) return true;
		Sortida.missatgeln(String.format("El moviment %d, %d no és vàlid!, ha de ser el salt del cavall dels escacs.\n", ultima.x(), ultima.y()));
		return false;
	}

	public static void main(String args[]) {
		
		Sortida.missatge("Inici del joc del cavall\n");
		
		Joc joc = new Joc();
		
		joc.comptador = 0;
		joc.taulell.inicialitza();

		while (joc.comptador < joc.taulell.files*joc.taulell.columnes) {
			joc.taulell.pinta();

			joc.coord.x(Entrada.readInput("x"));
			joc.coord.y(Entrada.readInput("y"));
			
			if (joc.taulell.comprobar(joc.coord) && joc.comprovarMovimentCavall(joc.coord, joc.actual)){
				joc.comptador++;
				joc.taulell.omple(joc.coord, joc.comptador);
				Sortida.missatgeln(String.format("Molt bé! Queden %d moviments per guanyar\n", joc.taulell.files*joc.taulell.columnes-joc.comptador));

				joc.actual.x(joc.coord.x());
				joc.actual.y(joc.coord.y());
			} else {
				joc.coord.x(0);
				joc.coord.y(0);
			}
		}
		if (joc.comptador == joc.taulell.files*joc.taulell.columnes) {
			joc.taulell.pinta();
			Sortida.missatgeln("Enhorabona! Has guanyat!");
		}
	}
}