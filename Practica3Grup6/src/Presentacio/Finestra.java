package Presentacio;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Domini.Joc;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Finestra implements  ActionListener {

	private JFrame frame;
    static final int MIDA = 5;
    public JButton[][] casellesTaulell = new JButton[MIDA][MIDA];
    static JButton btnDesfer;
    
    static JLabel lblEstat;
    
    private static Finestra finestra;
    static Joc joc;    
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					finestra = new Finestra();
			        joc = new Joc(MIDA);

			        finestra.refreshGui();
					
					finestra.frame.setVisible(true);

					lblEstat.setText("Clica sobre el taulell per col·locar el cavall");
					
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
		frame.setBounds(100, 100, 500, 500);
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
    	
        chessBoard = new JPanel(new GridLayout(0, 5));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
    	
        // create the chess board squares
        Insets buttonMargin = new Insets(0,0,0,0);

        Color toogle = Color.BLACK;
        for (int ii = 0; ii < MIDA; ii++) {
            for (int jj = 0; jj < MIDA; jj++) {
                JButton b = new JButton();
        		b.setHorizontalTextPosition(SwingConstants.CENTER);
        		b.setFont(new Font("Tahoma", Font.PLAIN, 24));
                b.setToolTipText((ii+1) +","+ (jj+1));
                b.setForeground(Color.GRAY);
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                b.setBackground(toogle);
                b.addActionListener(this);
                chessBoard.add(b);
                casellesTaulell[ii][jj] = b;

                if(toogle != Color.BLACK) toogle = Color.BLACK; else toogle = Color.WHITE;
            }
        }
        
        panel.add(chessBoard);
	}

	
	
	

    public final void refreshGui(){
        String[][] sb = joc.estatTaulell();
		for (int x = 0; x < sb.length; x++) {
			for (int y = 0; y < sb[x].length; y++) {
				finestra.casellesTaulell[x][y].setText(sb[x][y]);
			}
		} 
		//btnDesfer.setEnabled(joc.moviments()>0); // Es pot deasctivar el botó si no hi ha moviments que desfer
    }	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource().equals(btnDesfer)) {
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
			

			if(!joc.acabat()){
				joc.mouCavall(x, y);
				finestra.refreshGui();
				lblEstat.setText(String.format("Moviment %s,%s correcte", x,y) );
			}

			if(joc.acabat())
				lblEstat.setText(String.format("Joc acabat, HAS GUANYAT!"));
				
			
		} catch (Exception e1) {
			lblEstat.setText(e1.getMessage());
		}
		
	}

}
