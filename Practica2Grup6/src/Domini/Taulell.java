package Domini;

public class Taulell {

	public static int[][] taulell;
	public static int files = 5;
	public static int columnes = 5;

	Taulell() {
	}

	
	public static void inicialitzaTaulell(){
		taulell = new int[files][columnes];
		for (int i = 0; i < files; ++i){
			for (int j = 0; j < columnes; ++j){
				Taulell.taulell[i][j] = 0;
			}
		}
	}	
}
	
