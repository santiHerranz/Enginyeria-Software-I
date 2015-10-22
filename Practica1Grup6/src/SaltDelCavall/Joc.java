package SaltDelCavall;

import java.util.Scanner;

public class Joc {

    private static int files = 5;
    private static int columnes = 5;
    private static int[][] taulell;
    private static int comptador;
    private static int cordX;
    private static int cordY;
    private static int actualX;
    private static int actualY;

    public Joc() {
    }

    public static void inicialitzaTaulell(){
        comptador = 0;
        Joc.taulell = new int[files][columnes];
        for (int i = 0; i < files; ++i){
            for (int j = 0; j < columnes; ++j){
                taulell[i][j] = 0;
            }
        }
    }

    public static void introduirDades(){
        Joc.ompleTaulell();
        Joc.pintaTaulell();

        cordX = readInput("x");
        cordY = readInput("y");
    }
    
    private static int readInput(String lletra) {
        String s = "";
        Scanner scan;
        scan = new Scanner(System.in);
        
        while(true) {
            System.out.println("Entra una coordenada " + lletra +" o retorn per finalitzar: ");
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

    public static void ompleTaulell(){
        for (int i = 1; i <= files; ++i){
            for (int j = 1; j <= columnes; ++j){
                if (i == cordX && j == cordY) taulell[i-1][j-1] = comptador;
            }
        }
    }

    public static void pintaTaulell(){
        for (int i = 1; i <= files; ++i) {
            for (int j = 1; j <= columnes; ++j) {
                System.out.print(taulell[i-1][j-1] + "    ");
            }
            System.out.println();
        }
    }

    public static boolean comprobarTaulell(int cordX, int cordY){
        if ((cordX < 1 || cordX > files) || (cordY < 1 || cordY > columnes)){
            System.out.println("Error fila i/o columna fora del taullel.");
            return false;
        } else if (taulell[cordX-1][cordY-1] != 0){
            System.out.println("La posició ja està ocupada!");
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
        System.out.println("El moviment (" + cordX +","+cordY+") no és vàlid! Ha de ser el salt del cavall dels escacs.(L)");
        return false;
    }

    public static void main(String args[]) {
        Joc.inicialitzaTaulell();

        while (comptador < files*columnes) {
            Joc.introduirDades();
            if (Joc.comprobarTaulell(cordX, cordY) && Joc.comprobarMov()){
                actualX = cordX;
                actualY = cordY;
                comptador++;
                System.out.println("Molt bé! Queden " + (files*columnes-comptador) +" moviments per guanyar\n");
            } else {
                cordX = 0;
                cordY = 0;
            }
        }
        if (comptador == files*columnes) {
            Joc.ompleTaulell();
            Joc.pintaTaulell();
            System.out.println("Enhorabona! Has guanyat!");
        }
    }
}