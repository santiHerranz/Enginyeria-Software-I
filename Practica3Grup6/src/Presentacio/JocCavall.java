package Presentacio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

import Domini.Joc;

public class JocCavall implements  ActionListener {
	
	// http://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel

    static int mida = 5;
    public JButton[][] chessBoardSquares = new JButton[mida][mida];
	
    private final JPanel gui = new JPanel(new GridLayout(2, 0));
    private JPanel chessBoard;
    private JPanel chessMenu;
    
    static JocCavall cb;
    static Joc j;

    JButton redButton;
    
    JocCavall() {
        initializeGui();
    }

    public final void initializeGui() {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(0, 0, 0, 0));

        chessBoard = new JPanel(new GridLayout(0, mida));
        chessBoard.setMinimumSize(new Dimension(500, 500));
        
        chessBoard.setBorder(new LineBorder(Color.BLACK));

        // create the chess board squares
        Insets buttonMargin = new Insets(1,1,1,1);

        Color toogle = Color.BLACK;
        for (int ii = 0; ii < mida; ii++) {
            for (int jj = 0; jj < mida; jj++) {
                JButton b = new JButton();
                b.setToolTipText((ii+1) +","+ (jj+1));
                b.setForeground(Color.GRAY);
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                b.setBackground(toogle);
                b.addActionListener(this);
                chessBoard.add(b);
                chessBoardSquares[ii][jj] = b;

                if(toogle != Color.BLACK) toogle = Color.BLACK; else toogle = Color.WHITE;
            }
        }
        gui.add(chessBoard);

        chessMenu = new JPanel();
        FlowLayout flowLayout = (FlowLayout) chessMenu.getLayout();
        flowLayout.setAlignOnBaseline(true);
      chessMenu.setBorder(new LineBorder(Color.BLACK));
      JButton b2 = new JButton("Desfer darrer moviment");
      b2.addActionListener(this);
      chessMenu.add(b2);

      chessMenu.setMaximumSize(new Dimension(500, 100));
      gui.add(chessMenu);
    
    }


    public final JComponent getGui() {
        return gui;
    }

    public static void main(String[] args) {

    	cb = new JocCavall();

        JFrame f = new JFrame("Taulell");
        f.getContentPane().add(cb.getGui(), BorderLayout.NORTH);
        f.setMinimumSize(new Dimension(500, 500));
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);

        // ensures the frame is the minimum size it needs to be in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
        
        j = new Joc(cb.chessBoardSquares.length);
        cb.refreshGui();
    }

    public final void entradaCoord(int x, int y) throws Exception{
		j.mouCavall(x, y);
		cb.refreshGui();
    }
    
    public final void refreshGui(){
        String[][] sb = j.estatTaulell();
		for (int x = 0; x < sb.length; x++) {
			for (int y = 0; y < sb[x].length; y++) {
				cb.chessBoardSquares[x][y].setText(sb[x][y]);
			}
		}        
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton  b = (JButton)e.getSource();
		String s = b.getToolTipText();
		
		String[] ss = s.split(",");
		int x = Integer.parseInt(ss[0]);
		int y = Integer.parseInt(ss[1]);
		
		try {
			entradaCoord(x,y);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(gui, e1.getMessage());
		}

	}
	
	
	
}