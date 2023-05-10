import java.awt.*;
import javax.swing.*;
public class MySquaresApplication extends JFrame implements Runnable
{
    //Maxwell Maia
    //21236277

    //"member data"
    private static final Dimension WindowSize = new Dimension(600, 600);
    private static final int NUMGAMEOBJECTS = 30;
    private Square squareArray[] = new Square[NUMGAMEOBJECTS];

    //constructor
    public MySquaresApplication()
    {
        //JFrame setup
        this.setTitle("Look at the Squares move!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Centre the window on the screen
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        int x = screensize.width/2 - WindowSize.width/2;
        int y = screensize.height/2 - WindowSize.height/2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);

        //instantiates the GameObjects in the array,
        for(int i = 0; i < NUMGAMEOBJECTS; i++)
        {
            //instantiate a square
            squareArray[i] = new Square();
        }


        //create a thread
        Thread animationThread = new Thread(this);
        //start thread
        animationThread.start();
        //.start(); will run run()

        System.out.println("hey");
    }

    //thread's entry point
    //threads are good for periodic execution of code
    //threads remain responsive.
    //threads are good for user interface because...
    //1 second calculations in the main program don't delay other threads //
    //threads are independent
    //threads are concurrent
    public void run()
    {
        //the square animation runs forever
        while(true)
        {
            //Animation.
            //Sleep. Update. this.repaint() repeat.
            //Note. The squares will appear at t = 0 milliseconds.
            //After constructors are done, paint is called by OS (squares first appear). Then the first change due to animation happens at t = 20 milliseconds.

            //Sleep for 20 milliseconds
            //try and catch is needed for sleeping threads
            try
            {
                Thread.sleep(20);
            }
            catch (InterruptedException e)
            {
                //don't need to handle this for this program.
                e.printStackTrace();
            }
            //System.out.println("Start of thread loop");

            //update x and y positions of all objects
            //call move() method of all squares
            for(int i = 0; i < NUMGAMEOBJECTS; i++)
            {
                squareArray[i].move();
            }

            //repaint
            this.repaint();

            //System.out.println("End of thread loop");
        }
    }

    //application's paint method
    public void paint(Graphics g)
    {
        //clear previous drawn squares
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, 600, 600);

        //Call paint graphics g of each square
        for(int i = 0; i < NUMGAMEOBJECTS; i++)
        {
            squareArray[i].paint(g);
        }
    }

    //application's entry point
    public static void main(String[] args)
    {
        MySquaresApplication w = new MySquaresApplication();

    }
}
