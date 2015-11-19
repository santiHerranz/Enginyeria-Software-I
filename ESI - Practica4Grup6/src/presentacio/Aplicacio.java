package presentacio;

import java.awt.EventQueue;
import javax.swing.JFrame;
import domini.Joc;



public class Aplicacio {

	private TaulellGrafic taulellGrafic;
    
    private static Aplicacio aplicacio;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			        Joc joc = new Joc(5);
					aplicacio = new Aplicacio(joc);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
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
