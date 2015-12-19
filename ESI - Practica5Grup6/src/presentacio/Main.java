package presentacio;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Finestra window = new Finestra();
					window.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
