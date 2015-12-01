package presentacio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import domini.Joc;

public class TaulellGrafic extends JFrame {

	private static final long serialVersionUID = 1L;

	private Joc joc;
    private CasellaGrafica[][] casellesTaulell;

    private JLabel lblEstat;
    private JButton btnDesfer;

    private ImageIcon imatgeCavall;
    private ImageIcon imatgeCavallOfegat;
    private ImageIcon imatgeCavallGuanyador;
    
    private final Color CASELLA_SEGUENT = Color.GREEN;
    private final Color CASELLA_NEGRA = Color.BLACK;
    private final Color CASELLA_BLANCA = Color.WHITE;
    
    /*
     * Constructor
     */
	TaulellGrafic(Joc joc) throws Exception{
		
		if(joc == null) throw new Exception("El taulell depén d'un joc");
		
		// punt de referencia al joc vinculat
		this.joc = joc;
		
		
		casellesTaulell = new CasellaGrafica[joc.getMida()][joc.getMida()];
		
		this.setBounds(0, 0, 600, 600);
		
		lblEstat = new JLabel(" ");
		lblEstat.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstat.setText("Clica sobre el taulell per col·locar el cavall");
		this.getContentPane().add(lblEstat, BorderLayout.NORTH);
		
		btnDesfer = new JButton("Desfer darrer moviment");
		btnDesfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblEstat.setText(joc.desferMoviment());
				refreshGui();
			}
		});
		this.getContentPane().add(btnDesfer, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		this.getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel chessBoard;
    	
        chessBoard = new JPanel(new GridLayout(0, joc.getMida()));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
    	

        // Crea les caselles i les afegeix al taulell
        int w = 64; //this.getSize().width/joc.mida;
        for (int ii = 0; ii < joc.getMida(); ii++) {
            for (int jj = 0; jj < joc.getMida(); jj++) {
            	CasellaGrafica b = new CasellaGrafica(ii, jj, w);
            	
            	// Establir acció de cada botó
                b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
	                	casellaClick((CasellaGrafica)e.getSource());
					}
                });
                chessBoard.add(b);
                casellesTaulell[ii][jj] = b;
            }
        }
        panel.add(chessBoard);

        //Carregar imatges
		try {
			imatgeCavall = new ImageIcon(ImageIO.read(new File("res/cavall.png")));
			imatgeCavallOfegat = new ImageIcon(ImageIO.read(new File("res/cavall_ofegat.png")));
			imatgeCavallGuanyador = new ImageIcon(ImageIO.read(new File("res/cavall_guanyador.png")));
		} catch (IOException e) {}
		
		refreshGui();
        
	}

	
	public void refreshGui(){
        int[][] sb = joc.estatTaulell();

        // Neteja el taulell i col·loca els números de moviment
        for (int x = 0; x < sb.length; x++) {
			for (int y = 0; y < sb[x].length; y++) {
				CasellaGrafica cg = this.casellesTaulell[x][y]; 
				cg.removeAll();
				cg.setText("");

				int value = sb[x][y];
				if(value != Joc.CASELLA_BUIDA)
					cg.setText(String.valueOf(value));

				cg.repaint();
			}
		}
        
        // Obté la posició actual del cavall i afegeix la imatge del status corresponent
        if(joc.getHistorial().getMoviments()>0){
        	int[] posicio = joc.getHistorial().ultimMoviment();
	        if(posicio!=null){
				CasellaGrafica cg = this.casellesTaulell[posicio[0]][posicio[1]];

				ImageIcon image = imatgeCavall;
				if (joc.getStatus() == Joc.STATUS_ACABAT) 
					image = imatgeCavallGuanyador;
				else if (joc.getStatus() == Joc.STATUS_OFEGAT)
						image = imatgeCavallOfegat;
				
		        cg.add(new JLabel(image));
		        cg.repaint();
			}        	
        }
		
        // Pinta les caselles
	    for (int x = 0; x < sb.length; x++) {
	        Color color = CASELLA_NEGRA;
	        if(x%2==0) color = CASELLA_BLANCA;
	        
			for (int y = 0; y < sb[x].length; y++) {
				
				CasellaGrafica cg = this.casellesTaulell[x][y]; 
				cg.setBackground(color);

				if(joc.getHistorial().getMoviments()>0) {
					
					int[] actual;
					try {
						actual = joc.getHistorial().ultimMoviment();

						int actualX = actual[0];
						int actualY = actual[1];
						
						if(joc.estatTaulell()[x][y] == Joc.CASELLA_BUIDA )
							if (joc.comprovarMovimentCavall(x, y, actualX, actualY))  
								cg.setBackground(CASELLA_SEGUENT);
					
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				cg.repaint();
                if(color != CASELLA_NEGRA) color = CASELLA_NEGRA; else color = CASELLA_BLANCA;
			}
		}

	    //btnDesfer.setEnabled(joc.moviments()>0); // Es pot deasctivar el botó si no hi ha moviments que desfer
	}
	
	private void casellaClick(CasellaGrafica casella){
		lblEstat.setText(joc.mouCavall(casella.getFila(),casella.getColumna()));
		this.refreshGui();
	}
	
}
