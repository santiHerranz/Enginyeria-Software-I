package presentacio;

import java.awt.EventQueue;

import javax.swing.JFrame;

import domini.Joc;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class Inici {

	private JFrame frame;
	private JTextField txtMida;
	private JButton btnAcceptar;
	
	private TaulellGrafic finestra;

	/**
	 * Llen�a l'aplicaci�.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inici window = new Inici();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Inici() {
		initialize();
	}

	/**
	 * Inicialitza el contingut de la finestra.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Pr�ctica 4 - Grup 6");
		frame.setBounds(100, 100, 329, 185);
		frame.setLocationRelativeTo(null); // Centra l'aplicaci� en la finestra
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(verticalBox);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 50));
		verticalBox.add(verticalStrut);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		JLabel lblMida = new JLabel("Mida:");
		horizontalBox.add(lblMida);
		lblMida.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		txtMida = new JTextField();
		horizontalBox.add(txtMida);
		txtMida.setColumns(10);
		txtMida.addKeyListener(new KeyAdapter(){
			
			public void keyPressed(KeyEvent e) {
		           int key = e.getKeyCode();
		           if (key == KeyEvent.VK_ENTER) {
		        	   obrirFinestra();
		              }
		         }			
		});
		
		btnAcceptar = new JButton("Acceptar");
		horizontalBox.add(btnAcceptar);
		btnAcceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAcceptar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				obrirFinestra();
			}});
	}

	
	private void obrirFinestra(){
		String text = txtMida.getText();
		
		try {

			int mida = Integer.parseInt(text);
			finestra = new TaulellGrafic(mida);
			finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			finestra.pack();
			finestra.setLocationRelativeTo(null); // center the application window
			finestra.setVisible(true);	

			frame.setVisible(false);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame,
				    e.getMessage(),
				    "error",
				    JOptionPane.ERROR_MESSAGE);
			 txtMida.setText("");
			 txtMida.requestFocus();
		}		
	}
}
