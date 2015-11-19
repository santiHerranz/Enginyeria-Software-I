package presentacio;

import java.awt.EventQueue;

import javax.swing.JFrame;

import domini.Joc;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;

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
		frame.setBounds(100, 100, 329, 202);
		frame.setLocationRelativeTo(null); // center the application window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Box verticalBox = Box.createVerticalBox();
		frame.getContentPane().add(verticalBox);
		
		JLabel lblMida = new JLabel("Mida:");
		verticalBox.add(lblMida);
		
		textField = new JTextField();
		verticalBox.add(textField);
		textField.setColumns(10);
		
		JButton btnAcceptar = new JButton("Acceptar");
		verticalBox.add(btnAcceptar);
		btnAcceptar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
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
				}
				
				
			}});
		
		
	}

}
