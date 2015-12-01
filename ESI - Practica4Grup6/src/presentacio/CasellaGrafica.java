package presentacio;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class CasellaGrafica extends JButton {
	private static final long serialVersionUID = 1L;
	
	private int fila;
	private int columna;
	
	public int getFila() {
		return fila;
	}
	public int getColumna() {
		return columna;
	}

    // create the chess board squares
    private Insets buttonMargin = new Insets(0,0,0,0);
    
	CasellaGrafica(int x, int y, int ample){

		//Coordenada
		this.fila = x;
		this.columna = y;
		
		// Text
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setFont(new Font("Tahoma", Font.PLAIN,ample/3));
		this.setForeground(Color.GRAY);
		this.setToolTipText((this.fila+1) +","+ (this.columna+1));

		// Mida del boto forçat amb una imatge
		this.setLayout(new GridBagLayout()); // Estableix imatge al centre
        ImageIcon icon = new ImageIcon(
                new BufferedImage(ample, ample, BufferedImage.TYPE_INT_ARGB));
        this.setIcon(icon);
		this.setMargin(buttonMargin);
		
	}


}
