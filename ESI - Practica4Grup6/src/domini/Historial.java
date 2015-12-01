package domini;

import java.util.Stack;

public class Historial {
	
	private int moviments = 0;
	
	public int getMoviments () {
		return this.moviments;
	}

	Historial(){
		this.moviments = 0;
		this.historial = new Stack<int[]>();
	}

	private Stack<int[]> historial ;
	
	
	public void guardar(int x, int y) {
		historial.push(new int[]{x,y});
		moviments++;
	}
	
	public int[] ultimMoviment(){
		if (historial.empty())
			return null;
		
		return historial.peek();
	}

	public int[] desferUltimMoviment() throws Exception{
		if (historial.empty())
			throw new Exception ("Error desfer últim moviment:  no hi ha cap moviment");
		
		moviments--;
		return historial.pop();
	}
}
