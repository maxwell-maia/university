import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
public class InvadersApplication extends JFrame implements
        Runnable, KeyListener {

    // member data
    private static String workingDirectory;
    private static boolean isGraphicsInitialised = false;
    private static final Dimension WindowSize = new
            Dimension(800,600);
    private static final int NUMALIENS = 30;
    private Alien[] AliensArray = new
            Alien[NUMALIENS];
    private Spaceship PlayerShip;

    //Double buffering
    private BufferStrategy strategy;

    // constructor
    public InvadersApplication() {
        //Display the window, centred on the screen
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width/2 - WindowSize.width/2;
        int y = screensize.height/2 - WindowSize.height/2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);
        this.setTitle("Space Invaders! .. (sort of)");

        // load image from disk. Make sure the path is right! For Mac use / rather than \\
        ImageIcon icon = new ImageIcon(workingDirectory + "\\alien_ship_1.png");
        Image alienImage = icon.getImage();

        //Variables used to position alien formation on startup
        int row = 0;
        int xx = 0;

        // create and initialise some aliens, passing them each the image we have loaded
        for (int i=0; i<NUMALIENS; i++) {
            AliensArray[i] = new Alien(alienImage, WindowSize.width);

            //Set alien position in grid formation
            AliensArray[i].setPosition((xx % 480) + 96, row*50 + 80);
            xx += 80;

            //At the end of this row, go the next row
            if (xx%480 == 0)
            {
                row++;
            }

            //Set the alien speed
            AliensArray[i].setXSpeed(2);
        }

        // create and initialise the player's spaceship
        icon = new ImageIcon(workingDirectory + "\\player_ship.png");
        Image shipImage = icon.getImage();
        PlayerShip = new Spaceship(shipImage, WindowSize.width);
        PlayerShip.setPosition(300,530);

        // create and start our animation thread
        Thread t = new Thread(this);
        t.start();

        // send keyboard events arriving into this JFrame back to its own event handlers
        addKeyListener(this);

        //Double buffering is painting to a memory version of the screen
        //Then calling a function when fully painted to apply the painted version
        //on the next vertical sync = monitor refresh.
        //Double buffering
        //Create a double buffer strategy for this JFrame
        createBufferStrategy(2);
        //Assign double buffer strategy to strategy
        strategy = getBufferStrategy();

        isGraphicsInitialised = true; // it’s now safe to paint the images
    }
    // thread's entry point
    public void run() {
        while ( 1==1 ) { // the game loop
// 1: sleep for 1/50 sec
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) { }
// 2: animate game objects

            boolean aliensEdgeOfScreen = false;
            for (int i=0;i<NUMALIENS; i++)
            {
                if(AliensArray[i].move())
                {
                    aliensEdgeOfScreen = true;
                }
            }

            //If any aliens hit the edge of the screen, reverse their direction
            if(aliensEdgeOfScreen)
            {
                for(int i = 0; i < NUMALIENS; i++)
                {
                    AliensArray[i].reverseDirection();
                }
                aliensEdgeOfScreen = false;
            }

            PlayerShip.move();
            this.repaint(); // 3: force an application repaint
        }
    }
    // Three Keyboard Event-Handler functions
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_LEFT)
            PlayerShip.setXSpeed(-4);
        else if (e.getKeyCode()==KeyEvent.VK_RIGHT)
            PlayerShip.setXSpeed(4);
    }
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT)
            PlayerShip.setXSpeed(0);
    }
    public void keyTyped(KeyEvent e) {}
    //
    // application's paint method
    public void paint(Graphics g) {
        //Double buffering
        //Redirect drawing calls to the buffer in memory
        g = strategy.getDrawGraphics();

        if (isGraphicsInitialised) { // don’t try to draw uninitialized objects!
// clear the canvas with a big black rectangle
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WindowSize.width, WindowSize.height);
// redraw all game objects
            for (int i=0;i<NUMALIENS; i++)
                AliensArray[i].paint(g);
            PlayerShip.paint(g);
        }

        //At the end of paint
        //We want to say: now you can update to the screen (on the next vertical sync)
        strategy.show();

        //Double buffering: used when the screen is being refreshed with all this painted objects
        //before all the objects we want to paint this frame have been painted

        //Double buffering waits until all of the items have been painted and then updates
    }
    // application entry point
    public static void main(String[] args) {
        workingDirectory = System.getProperty("user.dir");
        InvadersApplication w = new InvadersApplication();
    }
}