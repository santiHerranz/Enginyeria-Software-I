package Presentacio;

import java.util.Scanner;

public class Entrada {

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
	
}
