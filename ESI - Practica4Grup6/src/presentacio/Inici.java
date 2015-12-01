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
	private JTextField textField;

	/**
	 * Launch the application.
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

	/**
	 * Create the application.
	 */
	public Inici() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Practica 4 - Grup 6");
		frame.setBounds(100, 100, 329, 185);
		frame.setLocationRelativeTo(null); // center the application window
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
		
		textField = new JTextField();
		horizontalBox.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter(){
			
			public void keyPressed(KeyEvent e) {
		           int key = e.getKeyCode();
		           if (key == KeyEvent.VK_ENTER) {
		        	   iniciarJoc();
		              }
		         }			
			
		});
		
		JButton btnAcceptar = new JButton("Acceptar");
		horizontalBox.add(btnAcceptar);
		btnAcceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAcceptar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				iniciarJoc();
			}});
		
		
	}

	
	private void iniciarJoc(){
		String text = textField.getText();
		
		try {

			int value = Integer.parseInt(text);
			Joc joc = new Joc(value);

			Aplicacio app = new Aplicacio(joc);
			frame.setVisible(false);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame,
				    e.getMessage(),
				    "error",
				    JOptionPane.ERROR_MESSAGE);
			 textField.setText("");
			 textField.requestFocus();
		}		
	}
}
