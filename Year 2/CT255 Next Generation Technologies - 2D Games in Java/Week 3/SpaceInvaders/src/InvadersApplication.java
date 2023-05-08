import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class InvadersApplication extends JFrame implements Runnable, KeyListener
{
    //Maxwell Maia
    //21236277

    //member data
    private static final Dimension WindowSize = new Dimension(600, 600);
    private static final int NUMALIENS = 30;
    private Sprite2D[] AliensArray = new Sprite2D[NUMALIENS];
    private Sprite2D PlayerShip;

    private int playerSpeed = 0;

    //get image
    //get directory for image
    private static String workingDirectory;
    private Image alienImage;
    private Image playerImage;

    //constructor
    public InvadersApplication()
    {
        //JFrame setup
        this.setTitle("Space Invaders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Centre the window on the screen
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        int x = screensize.width/2 - WindowSize.width/2;
        int y = screensize.height/2 - WindowSize.height/2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        //setVisible(true); moved to bottom of this bracket

        //Get the alien image
        ImageIcon alienIcon = new ImageIcon(workingDirectory + "\\alien_ship_1.png");
        alienImage = alienIcon.getImage();

        //Get the PlayerShip image
        ImageIcon playerIcon = new ImageIcon(workingDirectory + "\\player_ship.png");
        playerImage = playerIcon.getImage();

        double xx = 100;
        double yy = 100;
        //instantiate the aliens
        for(int i = 0; i < NUMALIENS; i++)
        {
            //instantiate an alien
            AliensArray[i] = new Sprite2D(alienImage);
            //set the starting position of the aliens
            xx = getRandomDouble(18, 526);
            yy = getRandomDouble(30, 316);
            AliensArray[i].setPosition(xx, yy);
        }

        //instantiate the player
        PlayerShip = new Sprite2D(playerImage);
        //set the starting position of the player
        PlayerShip.setPosition(250, 500);

        //Create a thread
        Thread animationThread = new Thread(this);

        //Start a thread
        animationThread.start();
        //run() is now active in a separate thread

        //Make keyboard inputs work
        //implement KeyListener
        //provide functions for keyPressed(), keyReleased() and keyTyped()
        //send keyboard events arriving into this JFrame to its own event handlers
        addKeyListener(this);

        setVisible(true);
    }

    public double getRandomDouble(double min, double max)
    {
        return ((Math.random() * (max-min)) + min);
    }

    //thread's entry point
    //periodic execution of code
    //move the aliens randomly
    //move
    //update
    public void run()
    {
        //Thread is running

        //infinite loop
        while(true)
        {
            //Animation

            //wait 20 milliseconds
            try
            {
                Thread.sleep(20);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            //update the x and y positions of all aliens
            //call moveEnemy() on all Aliens
            for(int i = 0; i < NUMALIENS; i++)
            {
                AliensArray[i].moveEnemy();
            }

            //update the x of the player
            PlayerShip.movePlayer();


            //repaint
            this.repaint();
        }
    }


    //Player movement strategy
    //calculate what the speed should be here
    //send the speed to the player object
    //ask the player object to update itself based on its speed

    //KeyListener interface methods
    public void keyPressed(KeyEvent e)
    {
        //if left is pressed down, we want to move left
        //make speed negative
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            playerSpeed = -5;
            //playerSpeed = playerSpeed - 5;

            //set the speed after every event
            PlayerShip.setXSpeed(playerSpeed);
        }

        //if right is pressed, we want to move right
        //make speed positive
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            playerSpeed = 5;
            //playerSpeed = playerSpeed + 5;

            //set the speed after every event
            PlayerShip.setXSpeed(playerSpeed);
        }
    }

    public void keyReleased(KeyEvent e)
    {
        //if left is released, we no longer want to move left
        //make speed 0
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            playerSpeed = 0;
            //playerSpeed = playerSpeed + 5;

            //set the speed after every event
            PlayerShip.setXSpeed(playerSpeed);
        }

        //if right is released, we no longer want to move right
        //make speed 0
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            playerSpeed = 0;
            //playerSpeed = playerSpeed - 5;

            //set the speed after every event
            PlayerShip.setXSpeed(playerSpeed);
        }


    }

    public void keyTyped(KeyEvent e)
    {
        //will be using keyReleased and KeyPressed
        //using this to change x will make movement depend on keyboard repeat rate
        //don't want that
    }

    // application's paint method
    public void paint(Graphics g)
    {
        //blank the page
        //clear previous drawn squares
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 600, 600);

        //Call paint graphics g of the PlayerShip
        PlayerShip.paint(g);

        //Call paint graphics g of each alien
        for(int i = 0; i < NUMALIENS; i++)
        {
            AliensArray[i].paint(g);
        }
    }

    // application entry point
    public static void main(String[] args)
    {
        workingDirectory = System.getProperty("user.dir");
        System.out.println("Working Directory = " + workingDirectory);
        InvadersApplication a = new InvadersApplication();
    }
}
