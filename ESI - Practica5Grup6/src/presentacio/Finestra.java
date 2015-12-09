package presentacio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import domini.Historial;
import domini.Partida;
import domini.Joc;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;

public class Finestra {

	private JFrame frame;
	private JTextField textField;
	private DauGrafic lblDau1;
	private DauGrafic lblDau2;
	private JLabel lblResultat;
	private JList<Partida> listPartides;
	
	private Joc joc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Finestra window = new Finestra();
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
	public Finestra() {
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 784, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); // Centra l'aplicació en la finestra
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if(joc == null) {
						joc = new Joc(textField.getText());
						textField.setEditable(false);
					}
					doJugar();
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame,
						    e.getMessage(),
						    "error",
						    JOptionPane.ERROR_MESSAGE);
					textField.requestFocus();
				}
				
			}

		});
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.SOUTH, btnJugar, -354, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnJugar, 178, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, btnJugar, 22, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, btnJugar, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -411, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, 258, SpringLayout.WEST, frame.getContentPane());
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		frame.getContentPane().add(btnJugar);
		
		JLabel lblJugador = new JLabel("Jugador:");
		springLayout.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.SOUTH, lblJugador);
		springLayout.putConstraint(SpringLayout.WEST, lblJugador, 74, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, lblJugador);
		springLayout.putConstraint(SpringLayout.NORTH, lblJugador, 30, SpringLayout.NORTH, frame.getContentPane());
		frame.getContentPane().add(lblJugador);
		
		listPartides = new JList<Partida>();
		springLayout.putConstraint(SpringLayout.WEST, listPartides, -501, SpringLayout.EAST, frame.getContentPane());
		listPartides.setModel(new DefaultListModel<Partida>());
		springLayout.putConstraint(SpringLayout.NORTH, listPartides, 163, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, listPartides, -79, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, listPartides, -28, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(listPartides);
		
		JLabel lblDau = new JLabel("Dau 1:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDau, 95, SpringLayout.SOUTH, btnJugar);
		springLayout.putConstraint(SpringLayout.WEST, lblDau, 112, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblDau);
		
		JLabel lblDau_1 = new JLabel("Dau 2:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDau_1, 73, SpringLayout.SOUTH, lblDau);
		springLayout.putConstraint(SpringLayout.WEST, lblDau_1, 0, SpringLayout.WEST, lblDau);
		frame.getContentPane().add(lblDau_1);
		
		lblDau1 = new DauGrafic("");
		springLayout.putConstraint(SpringLayout.NORTH, lblDau1, -13, SpringLayout.NORTH, lblDau);
		lblDau1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		frame.getContentPane().add(lblDau1);
		
		lblDau2 = new DauGrafic("");
		springLayout.putConstraint(SpringLayout.EAST, lblDau2, -366, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblDau1, 0, SpringLayout.EAST, lblDau2);
		springLayout.putConstraint(SpringLayout.NORTH, lblDau2, -13, SpringLayout.NORTH, lblDau_1);
		lblDau2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		frame.getContentPane().add(lblDau2);
		
		lblResultat = new JLabel("");
		springLayout.putConstraint(SpringLayout.WEST, lblResultat, 0, SpringLayout.WEST, lblDau1);
		springLayout.putConstraint(SpringLayout.SOUTH, lblResultat, -79, SpringLayout.SOUTH, frame.getContentPane());
		lblResultat.setFont(new Font("Tahoma", Font.PLAIN, 24));
		frame.getContentPane().add(lblResultat);
	}
	
	private void doJugar() {
		
		joc.getDau1().llançar();
		joc.getDau2().llançar();
		
		joc.comprovarResultat();
		
		refreshGUI();
	}

	
	private void refreshGUI() {
		
		lblDau1.setText(String.valueOf(joc.getDau1().getValue()));
		lblDau2.setText(String.valueOf(joc.getDau2().getValue()));
		
		listPartides.setListData(joc.getHistorial().historialList());
		
	}
	
}
