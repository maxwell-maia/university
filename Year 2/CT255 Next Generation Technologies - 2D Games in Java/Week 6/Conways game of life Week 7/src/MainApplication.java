//imports
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

//javax.swing.*;
public class MainApplication extends JFrame implements Runnable, MouseListener
{

    private static final Dimension WindowSize = new Dimension(800, 800);

    private static boolean isInitialised = false;

    private BufferStrategy strategy;

    private Graphics offscreenGraphics;

    private boolean[][] gameState = new boolean[40][40];
    
    public MainApplication()
    {
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        int x  = screensize.width/2 - WindowSize.width/2;
        int y = screensize.height/2 - WindowSize.height/2;

        setBounds(x, y, WindowSize.width, WindowSize.height);

        this.setVisible(true);

        this.setTitle("Conway's Game of Life");

        //Initialise gameState array to be empty
        for(int i = 0; i < 40; i++)
        {
            for(int j = 0; j < 40; j++)
            {
                gameState[i][j] = false;
            }
        }


        Thread t = new Thread(this);

        t.start();


        addMouseListener(this);

        createBufferStrategy(2);
        strategy = getBufferStrategy();
        offscreenGraphics = strategy.getDrawGraphics();
        isInitialised = true;
    }

    public void run()
    {

        while(true)
        {
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException e)
            {

            }

            this.repaint();
        }
    }

    //java.awt.event.*;
    public void mousePressed(MouseEvent e)
    {
        int x = e.getX()/20;
        int y = e.getY()/20;

        //System.out.println("Clicked cell: " + x + " " + y);
        gameState[x][y] = !gameState[x][y];
    }

    public void mouseReleased(MouseEvent e)
    {

    }

    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {

    }

    public void mouseClicked(MouseEvent e)
    {

    }

    public void paint(Graphics g)
    {
        if(!isInitialised)
        {
            return;
        }

        g = offscreenGraphics;

        //Background black
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WindowSize.width, WindowSize.height);

        //Draw game objects
        g.setColor(Color.WHITE);

        //Cycle through gameState array
        //if the boolean is true, paint a square at the mapped location of its postions
        // mapped location: 800 x 800 to a 40 x 40 grid
        // 800/40 : each cell is 20 pixels
        // each cell is at
        // x: i*20
        // y: j*20

        for(int i = 0; i < 40; i++)
        {
            for(int j = 0; j < 40; j++)
            {
                if(gameState[i][j])
                {
                    g.fillRect(i*20, j*20, 20, 20);
                    //System.out.println("Painted at cell: " + i + " " + j);
                }
            }
        }

        strategy.show();
    }

    public static void main(String[] args)
    {
        MainApplication w = new MainApplication();
    }







}
