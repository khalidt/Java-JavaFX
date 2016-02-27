/** Raupe 
 *  A Java AWT application that explores the Timer class.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Raupe 
    extends JFrame 
    implements ActionListener {
        
    // -------------------------------------------------------
    public static void main ( String[] args ) {
        Raupe R = new Raupe(20, 600, 0);
        Raupe S = new Raupe(8, 1600, 250);
        Raupe T = new Raupe(15, 2000, 480);
        T.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    }
        
    // ------------------------------------------------------ Parts of the Model
    private static int shared = -1;    // Modified by the action listener.
    private int step = -1;             // Modified by the action listener.
    private int head;
    
    // ----------------------------------------------------- Parts of the Viewer
    private Color[] palette;
    private int[] x;
    private int[] y;
    Color purple = new Color( 200, 50, 200);
    private Font myfont = new Font("serif", Font.BOLD, 30);
        
    // -------------------------------------------------- Parts of the Contoller
    private Timer tm;
    
    /** Construct a window with a caterpillar.
     *  @param N - Number of segments in the full-grown caterpillar.
     *  @param speed - Number of milliseconds per segment of growth.
     *  @param position - The y-coordinate of the UL corner of the window.
     */
    public Raupe( int N, int speed, int position ) {
        super("The Little Caterpillar");
        head = N-1;  // Initialize the model: Caterpillar's head.

        // Compute the tables of colors and positions for the Viewer
        palette = new Color[N];
        x = new int[N];
        y = new int[N];
        for( int k=0; k<N; ++k) {
            x[k] = 10 + k*25;
            y[k] = 100 + k*4; 
            if( k%3==0) y[k]-= 8;
            palette[k] = new Color(125, 0, 255-k*13);
        }
        
        // Initialize the controller ---------------------------
        setBounds(200, position, 640, 210);
        setVisible(true);
        tm = new Timer( speed, this);
        tm.start();
    }
        
    //----------------------------------------------------------
    public void paint (Graphics g) {
        g.setFont(myfont);
        g.setColor(purple);
        g.drawString("The Little Caterpillar", 8, 60);
        int k=1;
        for( ; k<step; ++k) {    // Paint the segments of the body.
            g.setColor(palette[step-k]);
            g.fillOval(x[k], y[k], 30, 25 );
        }
        // Paint tree trunk, eye, and feelers.
        g.setColor(Color.BLACK);
        g.fillRect(x[0], y[0], 30, 40);
        g.fillOval(x[k-1]+15, y[k-1]+7, 10, 10);
        if(k>=head-1){
            g.drawLine(x[k-1]+35,y[k-1]-12, x[k-1]+20,y[k-1]);
            g.drawLine(x[k-1]+35,y[k-1]-12, x[k-1]+22,y[k-1]);
            tm.stop();    // Stop timer when body reaches desired length.
        }
    }
        
    //----------------------------------------------------------
    public void actionPerformed(ActionEvent e) { 
        repaint (); 
        ++step;    
        ++shared;
        System.out.printf( "shared = %d; step = %d\n", shared, step );
    }
}
