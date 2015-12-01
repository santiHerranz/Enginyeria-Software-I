package presentacio;

import javax.swing.JFrame;
import domini.Joc;

public class Finestra {

	private TaulellGrafic taulellGrafic;

	public Finestra(Joc joc) throws Exception {
		
		taulellGrafic = new TaulellGrafic(joc);
		taulellGrafic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		refreshGui();
        taulellGrafic.pack();
        taulellGrafic.setLocationRelativeTo(null); // center the application window
		taulellGrafic.setVisible(true);	}

	private final void refreshGui(){
		taulellGrafic.refreshGui();
    }	
	
	


}
