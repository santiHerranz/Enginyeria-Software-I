package presentacio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import domini.Partida;
import domini.Joc;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.ListSelectionModel;

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
					window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		frame.setLocationRelativeTo(null); // Centra l'aplicació en la finestra
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doJugar();
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
		listPartides.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPartides.setModel(new DefaultListModel<Partida>());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(listPartides);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -42, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, -501, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 163, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -79, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 26, SpringLayout.SOUTH, btnJugar);
		springLayout.putConstraint(SpringLayout.WEST, panel, 34, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDau = new JLabel("Dau 1:");
		lblDau.setBounds(10, 23, 58, 14);
		panel.add(lblDau);
		springLayout.putConstraint(SpringLayout.NORTH, lblDau, 95, SpringLayout.SOUTH, btnJugar);
		springLayout.putConstraint(SpringLayout.WEST, lblDau, 112, SpringLayout.WEST, frame.getContentPane());
		
		lblDau1 = new DauGrafic("");
		lblDau1.setBounds(99, 12, 67, 30);
		panel.add(lblDau1);
		springLayout.putConstraint(SpringLayout.NORTH, lblDau1, 199, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, lblDau1, -518, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblDau1, -245, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblDau1, -592, SpringLayout.EAST, frame.getContentPane());
		lblDau1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblDau_1 = new JLabel("Dau 2:");
		lblDau_1.setBounds(10, 101, 58, 14);
		panel.add(lblDau_1);
		springLayout.putConstraint(SpringLayout.NORTH, lblDau_1, 182, SpringLayout.SOUTH, btnJugar);
		springLayout.putConstraint(SpringLayout.WEST, lblDau_1, 112, SpringLayout.WEST, frame.getContentPane());
		
		lblDau2 = new DauGrafic("");
		lblDau2.setBounds(99, 90, 67, 30);
		panel.add(lblDau2);
		springLayout.putConstraint(SpringLayout.NORTH, lblDau2, 258, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, lblDau2, -186, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblDau2, -592, SpringLayout.EAST, frame.getContentPane());
		lblDau2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		springLayout.putConstraint(SpringLayout.WEST, lblDau2, 0, SpringLayout.WEST, lblDau1);
		
		
		lblResultat = new JLabel("");
		lblResultat.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultat.setBounds(10, 160, 156, 39);
		panel.add(lblResultat);
		springLayout.putConstraint(SpringLayout.WEST, lblResultat, 207, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblResultat, -663, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblResultat, -77, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblResultat, -118, SpringLayout.SOUTH, frame.getContentPane());
		lblResultat.setFont(new Font("Tahoma", Font.PLAIN, 24));
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 0, SpringLayout.SOUTH, lblResultat);
		springLayout.putConstraint(SpringLayout.EAST, panel, 105, SpringLayout.EAST, lblResultat);
		
		JButton btnNewButton = new JButton("Partides jugades");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doPartidesJugades();
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, 0, SpringLayout.SOUTH, btnJugar);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -231, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnNewButton);
	}
	
	private void doJugar() {
		
		try {
			if(joc == null) {
				joc = new Joc(textField.getText());
				textField.setEditable(false);
			}

			lblResultat.setText(joc.llançar());

			lblDau1.setText(String.valueOf(joc.getDau1().getValue()));
			lblDau2.setText(String.valueOf(joc.getDau2().getValue()));
			

			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame,
				    e.getMessage(),
				    "error",
				    JOptionPane.ERROR_MESSAGE);
			textField.requestFocus();
		}
		
		
	}
	private void doPartidesJugades() {

		if(joc != null) {
			Partida[] list = joc.getHistorial();
			if(list.length>0) {
				int index = list.length-1;
				listPartides.setListData(list);
				listPartides.ensureIndexIsVisible(index);
			}
		}
		
	}

}
