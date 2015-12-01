package presentacio;

import javax.swing.JFrame;
import domini.Joc;

public class Finestra {


	private TaulellGrafic taulellGrafic;

	public Finestra(int mida) throws Exception {
		
		taulellGrafic = new TaulellGrafic(mida);
		taulellGrafic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		refreshGui();
        taulellGrafic.pack();
        taulellGrafic.setLocationRelativeTo(null); // center the application window
		taulellGrafic.setVisible(true);	}

	private final void refreshGui(){
		taulellGrafic.refreshGui();
    }	
	
	


}
