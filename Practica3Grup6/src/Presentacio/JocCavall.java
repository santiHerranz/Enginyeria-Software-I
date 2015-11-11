package Presentacio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class JocCavall implements  ActionListener {
	
	// http://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel

    private final JPanel gui = new JPanel(new GridLayout(2, 0));
    private JPanel chessBoard;
    private JPanel chessMenu;

    JButton redButton;
    
    JocCavall() {
        initializeGui();
    }

    public final void initializeGui() {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(0, 0, 0, 0));

        chessBoard = new JPanel(new GridLayout(0, 5));
        chessBoard.setBorder(new LineBorder(Color.BLACK));

        // create the chess board squares
        Insets buttonMargin = new Insets(1,1,1,1);

        Color toogle = Color.BLACK;
        for (int ii = 0; ii < 5; ii++) {
            for (int jj = 0; jj < 5; jj++) {
                JButton b = new JButton(ii+" "+jj);
                b.setForeground(Color.GRAY);
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                b.setBackground(toogle);
                b.addActionListener(this);
                chessBoard.add(b);

                if(toogle != Color.BLACK) toogle = Color.BLACK; else toogle = Color.WHITE;
            }
        }
        gui.add(chessBoard);

        chessMenu = new JPanel();
      chessMenu.setBorder(new LineBorder(Color.BLACK));
      JButton b2 = new JButton("Desfer darrer moviment");
      chessMenu.add(b2);
      gui.add(chessMenu);
    
    }


    public final JComponent getGui() {
        return gui;
    }

    public static void main(String[] args) {

    	JocCavall cb = new JocCavall();

        JFrame f = new JFrame("Taulell");
        f.getContentPane().add(cb.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);

        // ensures the frame is the minimum size it needs to be in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == redButton)
        {
        }		
	}
}