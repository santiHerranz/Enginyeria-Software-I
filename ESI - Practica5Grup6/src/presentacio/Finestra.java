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
	private JLabel lblDau1;
	private JLabel lblDau2;
	private JLabel lblResultat;
	private JLabel lblGuanyat;
	private JList<String> listPartides;
	
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
		frame.setTitle("Enginyeria Software I - Pr\u00E0ctica 5");
		frame.setBounds(100, 100, 784, 530);
		frame.setLocationRelativeTo(null); // Centra l'aplicació en la finestra
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doJugar();
			}

		});
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, btnJugar, 86, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnJugar, -339, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -375, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnJugar, 6, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, textField, 52, SpringLayout.WEST, frame.getContentPane());
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		frame.getContentPane().add(btnJugar);
		
		JLabel lblJugador = new JLabel("Jugador:");
		springLayout.putConstraint(SpringLayout.SOUTH, lblJugador, -411, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.SOUTH, lblJugador);
		springLayout.putConstraint(SpringLayout.WEST, lblJugador, 0, SpringLayout.WEST, textField);
		frame.getContentPane().add(lblJugador);
		
		listPartides = new JList<String>();
		listPartides.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPartides.setModel(new DefaultListModel<String>());
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.EAST, btnJugar, -77, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, textField, -39, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 53, SpringLayout.NORTH, frame.getContentPane());
		scrollPane.setViewportView(listPartides);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -42, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, -501, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -79, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 179, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -64, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 52, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -39, SpringLayout.WEST, scrollPane);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDau = new JLabel("Dau 1:");
		lblDau.setBounds(10, 23, 58, 14);
		panel.add(lblDau);
		springLayout.putConstraint(SpringLayout.NORTH, lblDau, 95, SpringLayout.SOUTH, btnJugar);
		springLayout.putConstraint(SpringLayout.WEST, lblDau, 112, SpringLayout.WEST, frame.getContentPane());
		
		lblDau1 = new JLabel("");
		lblDau1.setBounds(65, 11, 67, 47);
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
		
		lblDau2 = new JLabel("");
		lblDau2.setBounds(65, 87, 67, 47);
		panel.add(lblDau2);
		springLayout.putConstraint(SpringLayout.NORTH, lblDau2, 258, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, lblDau2, -186, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblDau2, -592, SpringLayout.EAST, frame.getContentPane());
		lblDau2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		springLayout.putConstraint(SpringLayout.WEST, lblDau2, 0, SpringLayout.WEST, lblDau1);
		
		
		lblResultat = new JLabel("");
		lblResultat.setBounds(0, 163, 176, 47);
		panel.add(lblResultat);
		springLayout.putConstraint(SpringLayout.NORTH, lblResultat, -31, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblResultat, -60, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblResultat, -747, SpringLayout.EAST, frame.getContentPane());
		lblResultat.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		springLayout.putConstraint(SpringLayout.WEST, lblResultat, -58, SpringLayout.WEST, scrollPane);
		
		JButton btnLlistatPartides = new JButton("Partides jugades");
		springLayout.putConstraint(SpringLayout.WEST, btnLlistatPartides, 0, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, btnLlistatPartides, -6, SpringLayout.NORTH, scrollPane);
		btnLlistatPartides.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doPartidesJugades();
			}
		});
		frame.getContentPane().add(btnLlistatPartides);
		
		JLabel lblNewLabel = new JLabel("Guanyes si els daus sumen 7");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 6, SpringLayout.SOUTH, btnJugar);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 31, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -12, SpringLayout.WEST, scrollPane);
		
		lblGuanyat = new JLabel(String.format("%.2f %%",0.0));
		lblGuanyat.setHorizontalAlignment(SwingConstants.LEFT);
		lblGuanyat.setBounds(77, 232, 89, 21);
		panel.add(lblGuanyat);
		
		JLabel lblGuanyat_1 = new JLabel("Guanyat:");
		lblGuanyat_1.setBounds(10, 235, 58, 14);
		panel.add(lblGuanyat_1);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
	}
	
	private void doJugar() {
		
		try {
			if(joc == null) {
				joc = new Joc(textField.getText());
				textField.setEditable(false);
			}

			joc.llançar();
			
			lblResultat.setText(joc.getResultat());
			lblGuanyat.setText(String.format("%.2f %%",joc.getPercentatge()));

			lblDau1.setText(String.valueOf(joc.getDau1()));
			lblDau2.setText(String.valueOf(joc.getDau2()));

			
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
			String[] list = joc.getHistorial();
			if(list.length>0) {
				int index = list.length-1;
				listPartides.setListData(list);
				listPartides.ensureIndexIsVisible(index);
			}
		}
		
	}
}
