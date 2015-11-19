package presentacio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import domini.Coord;
import domini.Joc;

public class TaulellGrafic extends JFrame {

	private static final long serialVersionUID = 1L;

    private static JLabel lblEstat;
    private static JButton btnDesfer;

    private CasellaGrafica[][] casellesTaulell;
    private ImageIcon imatgeCavall;
    private ImageIcon imatgeCavallOfegat;
    private ImageIcon imatgeCavallGuanyador;
    private Joc joc;
    
    private final Color CASELLA_SEGUENT = Color.GREEN;
    private final Color CASELLA_NEGRA = Color.BLACK;
    private final Color CASELLA_BLANCA = Color.WHITE;
    
    /*
     * 
     */
	TaulellGrafic(Joc joc) throws Exception{
		
		if(joc == null) throw new Exception("El taulell depén d'un joc");
		
		// punt de referencia al joc vinculat
		this.joc = joc;
		
		
		casellesTaulell = new CasellaGrafica[joc.mida][joc.mida];
		
//		this.setMinimumSize(new Dimension(600, 600));
		this.setBounds(0, 0, 600, 600);
		
		lblEstat = new JLabel(" ");
		lblEstat.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstat.setText("Clica sobre el taulell per col·locar el cavall");
		this.getContentPane().add(lblEstat, BorderLayout.NORTH);
		
		btnDesfer = new JButton("Desfer darrer moviment");
		btnDesfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					joc.desferMoviment();
					refreshGui();
					lblEstat.setText(String.format("Moviment desfet!") );
				} catch (Exception ex) {
					lblEstat.setText(ex.getMessage());
				}
			}
		});
		this.getContentPane().add(btnDesfer, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		this.getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel chessBoard;
    	
        chessBoard = new JPanel(new GridLayout(0, joc.mida));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
    	

        // Crea les caselles i les afegeix al taulell
        int w = 64; //this.getSize().width/joc.mida;
        for (int ii = 0; ii < joc.mida; ii++) {
            for (int jj = 0; jj < joc.mida; jj++) {
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		refreshGui();
        
	}

	
	public void refreshGui(){
        String[][] sb = joc.estatTaulell();

        // Neteja el taulell i col·loca els números de moviment
        for (int x = 0; x < sb.length; x++) {
			for (int y = 0; y < sb[x].length; y++) {
				CasellaGrafica cg = this.casellesTaulell[x][y]; 
				cg.setText(sb[x][y]);
				cg.removeAll();
				cg.repaint();
			}
		}
        
        // Obté la posició actual del cavall i afegeix la imatge del estatus corresponent
        Coord cavall = joc.posicioCavall();
		if(cavall!=null){
			CasellaGrafica cg = this.casellesTaulell[cavall.x-1][cavall.y-1];

			ImageIcon image = imatgeCavall;
			if (joc.acabat()) 
				image = imatgeCavallGuanyador;
			else if (joc.ofegat())
				image = imatgeCavallOfegat;
			
	        cg.add(new JLabel(image));
	        cg.repaint();
		}
		
        // Pinta les caselles
	    for (int x = 0; x < sb.length; x++) {
	        Color color = CASELLA_NEGRA;
	        if(x%2==0) color = CASELLA_BLANCA;
	        
			for (int y = 0; y < sb[x].length; y++) {
				
				CasellaGrafica cg = this.casellesTaulell[x][y]; 
				cg.setBackground(color);

				if(joc.moviments()>0) {
					try {
						if(joc.casellaBuida(x+1, y+1))
							if (joc.comprovarMovimentCavall(x+1, y+1)) 
								cg.setBackground(CASELLA_SEGUENT);
					} catch (Exception e) { }
				}
				
				cg.repaint();
                if(color != CASELLA_NEGRA) color = CASELLA_NEGRA; else color = CASELLA_BLANCA;
			}
		}
	    
	    

		//btnDesfer.setEnabled(joc.moviments()>0); // Es pot deasctivar el botó si no hi ha moviments que desfer
        
	}
	
	

	
	private void casellaClick(CasellaGrafica b){
		
		try {
			if(!joc.acabat() && !joc.ofegat()){
				joc.mouCavall(b.x, b.y);
				this.refreshGui();
//				lblEstat.setText(String.format("Moviment %s,%s correcte", x,y) );
				lblEstat.setText(String.format("(%s,%s) Correcte, queden %s moviments per guanyar", b.x, b.y , joc.mida*joc.mida - joc.moviments()) );
			}

			if(joc.ofegat())
				lblEstat.setText(String.format("El cavall està ofegat, pots desfer els moviments."));
			
			if(joc.acabat())
				lblEstat.setText(String.format("Joc acabat, HAS GUANYAT!"));
				
			
		} catch (Exception e1) {
			lblEstat.setText(e1.getMessage());
		}		
		
	}
	
}
