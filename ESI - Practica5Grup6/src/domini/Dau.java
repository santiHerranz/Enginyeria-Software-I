package domini;

import java.util.Random;

public class Dau {

	private int value;
	
	public void llan�ar(){
		Random  r = new Random();
		value = 1+(r.nextInt(6));
	}

	public int getValue() {
		return value;
	}
}
