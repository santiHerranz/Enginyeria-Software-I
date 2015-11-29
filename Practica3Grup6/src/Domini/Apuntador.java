package Domini;

import java.util.Stack;

public class Apuntador {
	
	
	private int moviments = 0;
	
	public int getMoviments () {
		return this.moviments;
	}	

	Apuntador(){
		this.moviments = 0;
		this.historial = new Stack<Coord>();
	}

	private Stack<Coord> historial ;
	
	
	public void guardar(int x, int y) {
		historial.push(new Coord(x,y));
		moviments++;
	}
	
	public Coord ultimMoviment() throws Exception{
		if (historial.empty())
			throw new Exception ("Error últim moviment:  no hi ha cap moviment");
		return historial.peek();
	}

	public Coord desferUltimMoviment() throws Exception{
		if (historial.empty())
			throw new Exception ("Error desfer últim moviment:  no hi ha cap moviment");
		
		moviments--;
		return historial.pop();
	}
}
