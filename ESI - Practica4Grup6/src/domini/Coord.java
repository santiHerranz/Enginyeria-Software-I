package domini;

public class Coord {
	//public int x; public int y;
	private int x; public int y;
	
	Coord(int x,int y){ this.x = x; this.y=y;};
	
	public int [] getCoord () {
		return new int [] {x, y};
	}
}

