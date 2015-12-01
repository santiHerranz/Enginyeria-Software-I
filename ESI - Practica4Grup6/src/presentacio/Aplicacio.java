package presentacio;

import javax.swing.JFrame;
import domini.Joc;

public class Aplicacio {

	private TaulellGrafic taulellGrafic;

	public Aplicacio(Joc joc) throws Exception {
		
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
