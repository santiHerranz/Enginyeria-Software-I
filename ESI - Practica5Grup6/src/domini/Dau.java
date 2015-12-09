package domini;

import java.util.Random;

public class Dau {

	private int value;
	
	public void llançar(){
		Random  r = new Random();
		this.value = 1+(r.nextInt(6));
	}

	public int getValue() {
		return this.value;
	}
}
