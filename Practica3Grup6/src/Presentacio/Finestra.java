package Presentacio;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Domini.Coord;
import Domini.Joc;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.SwingConstants;

public class Finestra implements  ActionListener {

	private JFrame frame;
    private static final int MIDA = 5;
    private JButton[][] casellesTaulell = new JButton[MIDA][MIDA];
    private static JLabel lblEstat;
    private static JButton btnDesfer;
    
    private static Finestra finestra;
    private static Joc joc;    
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			        joc = new Joc(MIDA);

					finestra = new Finestra();
					finestra.frame.setBounds(0, 0, 600, 600);

					finestra.refreshGui();
			        finestra.frame.pack();
			        finestra.frame.setLocationRelativeTo(null); // center the application window
					finestra.frame.setVisible(true);

					lblEstat.setText("Clica sobre el taulell per col�locar el cavall");

					
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
		frame.setBounds(0, 0, 600, 600);
		frame.setMinimumSize(new Dimension(600, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblEstat = new JLabel(" ");
		lblEstat.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblEstat, BorderLayout.NORTH);
		
		btnDesfer = new JButton("Desfer darrer moviment");
		btnDesfer.addActionListener(this);
		frame.getContentPane().add(btnDesfer, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		
		JPanel chessBoard;
    	
        chessBoard = new JPanel(new GridLayout(0, Finestra.MIDA));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
    	
        // create the chess board squares
        Insets buttonMargin = new Insets(0,0,0,0);

        int w = frame.getSize().width/MIDA;
        
        Color toogle = Color.BLACK;
        for (int ii = 0; ii < MIDA; ii++) {
            for (int jj = 0; jj < MIDA; jj++) {
                JButton b = new JButton();
        		b.setHorizontalTextPosition(SwingConstants.CENTER);
        		b.setFont(new Font("Tahoma", Font.PLAIN, 18));
                b.setToolTipText((ii+1) +","+ (jj+1));
                b.setForeground(Color.GRAY);
                b.setMargin(buttonMargin);
                b.setLayout(new GridBagLayout()); // Estableix imatge al centre

                ImageIcon icon = new ImageIcon(
                        new BufferedImage(w, w, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                b.setBackground(toogle);
                b.addActionListener(this);
                chessBoard.add(b);
                casellesTaulell[ii][jj] = b;

                if(toogle != Color.BLACK) toogle = Color.black; else toogle = Color.WHITE;
            }
        }
        
        panel.add(chessBoard);
	}

	
	
	

	private final void refreshGui(){
        String[][] sb = joc.estatTaulell();

        for (int x = 0; x < sb.length; x++) {
			for (int y = 0; y < sb[x].length; y++) {
				finestra.casellesTaulell[x][y].setText(sb[x][y]);
				finestra.casellesTaulell[x][y].removeAll();
				finestra.casellesTaulell[x][y].repaint();
			}
		} 
		

		Coord cavall = joc.posicioCavall();

		if(cavall!=null){
	        BufferedImage image;
			try {
				String imageSrc;
				if (joc.acabat()) 
					imageSrc="cavall_guanyador.png";
				else if (joc.ofegat())
					imageSrc = "cavall_ofegat.png";
				else 
					imageSrc = "cavall.png";
				
				image = ImageIO.read(new File("res/"+ imageSrc));
		        JLabel picLabel = new JLabel(new ImageIcon(image));
		        finestra.casellesTaulell[cavall.x-1][cavall.y-1].add(picLabel);
		        finestra.casellesTaulell[cavall.x-1][cavall.y-1].repaint();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//btnDesfer.setEnabled(joc.moviments()>0); // Es pot deasctivar el bot� si no hi ha moviments que desfer
    }	
	
	
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource() == btnDesfer) {
			try {
				joc.desferMoviment();
				finestra.refreshGui();
				lblEstat.setText(String.format("Moviment desfet!") );
			} catch (Exception e) {
				lblEstat.setText(e.getMessage());
			}
			return;
		}
		
		JButton  b = (JButton)arg0.getSource();
		String s = b.getToolTipText();
		
		String[] ss = s.split(",");
		int x = Integer.parseInt(ss[0]);
		int y = Integer.parseInt(ss[1]);
		
		try {
			

			if(!joc.acabat() && !joc.ofegat()){
				joc.mouCavall(x, y);
				finestra.refreshGui();
				lblEstat.setText(String.format("(%s,%s) Correcte, queden %s moviments per guanyar", x, y , MIDA*MIDA - joc.moviments()) );
			}

			if(joc.ofegat())
				lblEstat.setText(String.format("El cavall est� ofegat, pots desfer els moviments."));
			
			if(joc.acabat())
				lblEstat.setText(String.format("Joc acabat, HAS GUANYAT!"));
				
			
		} catch (Exception e1) {
			lblEstat.setText(e1.getMessage());
		}
		
	}


}
