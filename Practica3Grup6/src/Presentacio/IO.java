package Presentacio;
import java.util.Scanner;

import Domini.Joc;



public class IO {

	@SuppressWarnings("resource")
	public static int readInput(String lletra) {
		String s = "";
		Scanner scan;
		scan = new Scanner(System.in);
		
		while(true) {
			System.out.println(String.format("Entra una cordenada '%s' o retorn per finalitzar: ", lletra));
			s = scan.nextLine();
			
			if(s.isEmpty()){
				System.out.println("Programa finalitzat!");
				System.exit(0);
			}

			try{
				return Integer.parseInt(s);
			} catch(Exception e){
				System.out.println("La coordenada no es vàlida!");
			} 
		}
	}

	
	public static void missatge(String text) {
		System.out.print(text);
	}
	public static void missatgeln(String text) {
		System.out.println(text);
	}
	

	public static void main(String args[]) {
		
		missatgeln("Inici del joc del cavall");
		
		int jugadorX, jugadorY;
		
		Joc joc = new Joc();
		
		while (!joc.acabat()) {
			joc.pintarTaulell();

			jugadorX = IO.readInput("x");
			jugadorY = IO.readInput("y");
			
			try {
				if (joc.comprovarTaulell(jugadorX, jugadorY) ){
					if(joc.comprovarMovimentCavall(jugadorX, jugadorY)){
						joc.mouCavall(jugadorX, jugadorY);
					}
				}
			} catch (Exception e) {
				IO.missatgeln(e.getMessage());
			}
		}
		if (joc.acabat()) {
			joc.pintarTaulell();
			IO.missatgeln("Enhorabona! Has guanyat!");
		}
	}	
	
}
