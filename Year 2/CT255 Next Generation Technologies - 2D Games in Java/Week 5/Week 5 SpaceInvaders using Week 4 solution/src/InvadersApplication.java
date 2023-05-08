import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Iterator;
public class InvadersApplication extends JFrame
        implements Runnable, KeyListener {
    // member data
    private static String workingDirectory;
    private static boolean isInitialised =
            false;
    private static final Dimension WindowSize = new Dimension(800,600);
    private BufferStrategy strategy;
    private Graphics offscreenGraphics;
    private static final int NUMALIENS = 30;
    private Alien[] AliensArray = new Alien[NUMALIENS];
    private Spaceship PlayerShip;

    private Image PlayerShipImage;

    private Image bulletImage;

    private PlayerBullet b;
    private ArrayList<PlayerBullet> bulletArrayList = new ArrayList<>();

    private boolean isGameInProgress = false;

    private int wave = 0;

    private int score = 0;

    private int bestScore = 0;

    // constructor
    public InvadersApplication() {
        //Display the window, centred on the screen
        Dimension screensize =
                java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width/2 - WindowSize.width/2;
        int y = screensize.height/2 - WindowSize.height/2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);
        this.setTitle("Space Invaders! .. (getting there)");

        // load image from disk
        ImageIcon icon = new ImageIcon(workingDirectory + "\\alien_ship_1.png");
        Image alienImage1 = icon.getImage();

        icon = new ImageIcon(workingDirectory + "\\alien_ship_2.png");
        Image alienImage2 = icon.getImage();

        // create and initialise some aliens, passing them each the image we have
        // loaded
        for (int i=0; i<NUMALIENS; i++)
        {
            AliensArray[i] = new Alien(alienImage1, alienImage2);
        }

        // create and initialise the player's spaceship
        icon = new ImageIcon(workingDirectory + "\\player_ship.png");
        PlayerShipImage = icon.getImage();
        PlayerShip = new Spaceship(PlayerShipImage);

        // tell all sprites the window width
        Sprite2D.setWinWidth(WindowSize.width);

        // store the bullet's image
        icon = new ImageIcon(workingDirectory + "\\bullet.png");
        bulletImage = icon.getImage();

        // create and start our animation thread
        Thread t = new Thread(this);
        t.start();

        // send keyboard events arriving into this JFrame back to its own event
        // handlers
        addKeyListener(this);

        // initialise double-buffering
        createBufferStrategy(2);
        strategy = getBufferStrategy();
        offscreenGraphics = strategy.getDrawGraphics();
        isInitialised = true;
    }
    // thread's entry point
    public void run() {

        // set up variables for PlayerShip-Alien collision detection
        double x1, y1, w1, h1;
        double x2;
        double y2;
        double w2 = PlayerShipImage.getWidth(null);
        double h2 = PlayerShipImage.getHeight(null);
        w1 = AliensArray[0].myImage1.getWidth(null);
        h1 = AliensArray[0].myImage1.getHeight(null);

        while (true) {
            // 1: sleep for 1/50 sec
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) { }

            //Game state
            if(isGameInProgress)
            {
                // 2: animate game objects
                boolean alienDirectionReversalNeeded = false;
                for (int i=0;i<NUMALIENS; i++) {
                    if (AliensArray[i].move())
                        alienDirectionReversalNeeded=true;
                }
                if (alienDirectionReversalNeeded) {
                    Alien.reverseDirection();
                    for (int i=0;i<NUMALIENS; i++)
                        AliensArray[i].jumpDownwards();
                }

                PlayerShip.move();

                //Loop through all bullets
                Iterator<PlayerBullet> iterator = bulletArrayList.iterator();
                while(iterator.hasNext()) {
                    PlayerBullet next = (PlayerBullet) iterator.next();

                    //Move each bullet
                    //If the bullet collides with an alien, then remove the bullet
                    if(next.move(AliensArray))
                    {
                        //Killed an alien
                        score += 10;

                        //Make the alien that the bullet killed not alive
                        AliensArray[next.getAlienKilled()].setIsAlive(false);
                        //Remove the bullet
                        iterator.remove();
                        //continue so that the Y isn't checked below because next has been removed

                        //Check if all the aliens are dead
                        boolean allAliensDead = true;
                        for(int i = 0; i < NUMALIENS; i++)
                        {
                            if(AliensArray[i].isAlive())
                            {
                                allAliensDead = false;
                            }

                        }

                        if(allAliensDead)
                        {
                            startNewWave();
                        }

                        continue;
                    }

                    //Remove a bullet that has gone off of the screen
                    if(next.getY() < 0)
                    {
                        iterator.remove();
                    }
                }

                //PlayerShip and Aliens collision detection
                //Collision detection
                //1 = alien
                //2 = playership
                //x and y = pixel on image starting from top left of image
                //h = height
                //w = width

                //The alien
                //x1, y1, w1, h1;

                //The playership
                x2 = PlayerShip.getX();
                y2 = PlayerShip.getY();
                //w1 and h1 set in setup in run() function

                //Check all the aliens to see if there was a collision
                for(int i = 0; i < AliensArray.length; i++)
                {
                    x1 = AliensArray[i].x;
                    y1 = AliensArray[i].y;

                    //Did a collision occur between the PlayerShips and Alien i
                    if ((
                            ( (x1<x2 && x1+w1>x2) ||
                                    (x2<x1 && x2+w2>x1) )
                                    &&
                                    ( (y1<y2 && y1+h1>y2) ||
                                            (y2<y1 && y2+h2>y1) )
                    ) && AliensArray[i].isAlive())
                    {
                        //PlayerShip collided with an alien
                        //PlayerShip died
                        //Game over

                        //Make all aliens disappear
                        for(int j = 0; j < AliensArray.length; j++)
                        {
                            AliensArray[j].setIsAlive(false);
                        }

                        //Make PlayerShip disappear
                        PlayerShip.setIsAlive(false);

                        //Update bestScore
                        if(score > bestScore)
                        {
                            bestScore = score;
                        }

                        //Change game state
                        isGameInProgress = false;
                    }
                    else
                    {
                        //PlayerShip did not collide with an alien
                    }

                }
            }
            //Menu state
            else
            {

            }


            // 3: force an application repaint
            this.repaint();
        }
    }
    // Three Keyboard Event-Handler functions
    public void keyPressed(KeyEvent e) {
        if(isGameInProgress)
        {
            if (e.getKeyCode()==KeyEvent.VK_LEFT)
                PlayerShip.setXSpeed(-4);
            else if (e.getKeyCode()==KeyEvent.VK_RIGHT)
                PlayerShip.setXSpeed(4);
            else if (e.getKeyCode()==KeyEvent.VK_SPACE)
            {
                //Bullet fired
                //Initialize a new bullet
                b = new PlayerBullet(bulletImage);

                //Set the bullet to the player ship's position
                b.setPosition(PlayerShip.getX() + 25, PlayerShip.getY() - 15);

                //Add a new bullet to the ArrayList
                bulletArrayList.add(b);
            }
        }
        else
        {
            //Button pressed, start new game
            this.startNewGame();
        }
    }

    public void keyReleased(KeyEvent e) {
        if(isGameInProgress)
        {
            if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT)
                PlayerShip.setXSpeed(0);
        }
        else
        {

        }


    }

    public void keyTyped(KeyEvent e) { }
    //
// application's paint method
    public void paint(Graphics g) {
        if (!isInitialised)
            return;
        g = offscreenGraphics; // draw to offscreen buffer
// clear the canvas with a big black rectangle
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WindowSize.width, WindowSize.height);
// redraw all game objects

        //Paint game state
        if(isGameInProgress)
        {
            for (int i=0;i<NUMALIENS; i++)
            {
                //Paint the aliens that are alive
                if(AliensArray[i].isAlive())
                {
                    AliensArray[i].paint(g);
                }
            }

            //Paint the player ship if it is alive
            if(PlayerShip.isAlive())
            {
                PlayerShip.paint(g);
            }

            //Paint the bullets
            Iterator<PlayerBullet> iterator = bulletArrayList.iterator();
            while(iterator.hasNext()) {
                PlayerBullet next = iterator.next();
                next.paint(g);
            }

            //Paint the score
            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 310, 50);
            g.drawString("Best: " + bestScore, 460, 50);
        }
        else
        {
            //Paint menu state
            //Paint text using drawString
            g.setColor(Color.WHITE);
            //g.setFont(Font.SANS_SERIF);
            g.drawString("GAME OVER", 375, 200);
            g.drawString("Press any key to play", 350, 350);
            g.drawString("[Arrow keys to move, space to fire]", 320, 380);
        }


// flip the buffers offscreen<-->onscreen
        strategy.show();
    }
    // application entry point
    public static void main(String[] args) {
        workingDirectory = System.getProperty("user.dir");
        System.out.println("Working Directory = " + workingDirectory);
        InvadersApplication w = new InvadersApplication();
    }

    public void startNewWave()
    {
        wave++;

        //Make visible and position Spaceship
        PlayerShip.setPosition(300,530);
        PlayerShip.setIsAlive(true);

        //Make visible and position Aliens
        for(int i = 0; i < NUMALIENS; i++)
        {
            double xx = (i%5)*80 + 70;
            double yy = (i/5)*40 + 50;
            AliensArray[i].setPosition(xx, yy);
            AliensArray[i].setIsAlive(true);
        }

        //Set the Alien's speed
        Alien.setFleetXSpeed(1 + 3*wave);
        //1+3(wave) gets quite difficult quick. This helps with testing the program
        //It is also a decent difficulty for the game considering that you can hold space
        //and spawn a bullet lightsaber because there is no fire rate limit
    }
    public void startNewGame()
    {
        //Change game state
        isGameInProgress = true;

        //Start the first wave
        wave = 1;
        this.startNewWave();
    }
}