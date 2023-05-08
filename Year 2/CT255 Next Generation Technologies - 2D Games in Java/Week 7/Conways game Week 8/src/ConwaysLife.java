import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
public class ConwaysLife extends JFrame implements Runnable, MouseListener {
    // member data
    private BufferStrategy strategy;
    private Graphics offscreenBuffer;
    private boolean gameState[][][] = new boolean[40][40][2];

    private boolean isGameInProgress = false;
    private int currVisibleState = 0;
    //game state buffers
    //Initially is 0, we will display the "0 gamestate" first while players draw on the screen:
    // gameState[][][currVisibleState] where currVisibleState=0

    //When the game is playing...
    //Going to swap between the buffers like so
    //Iterate through cells from the *current buffer* (gameState[][][currVisibleState] is displayed on screen) and apply rules of game to each cell,
    //the rules will turn each cell true or false, This is updated for the *next buffer*: gameState[][][1 - currVisibleState]
    //on the next frame currVisibleState = 1 - currVisibleState

    // constructor
    public ConwaysLife() {
        //Display the window, centred on the screen
        Dimension ss = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = ss.width / 2 - 400;
        int y = ss.height / 2 - 400;
        setBounds(x, y, 800, 800);
        setVisible(true);
        this.setTitle("Conway's game of life");

        // initialise double-buffering
        createBufferStrategy(2);
        strategy = getBufferStrategy();
        offscreenBuffer = strategy.getDrawGraphics();

        // register the Jframe itself to receive mouse events
        addMouseListener(this);

        // initialise the game state
        for(int i = 0; i<2; i++)
        {
            for (x = 0; x < 40; x++) {
                for (y = 0; y < 40; y++) {
                    gameState[x][y][i] = false;
                }
            }
        }

        // create and start our animation thread
        Thread t = new Thread(this);
        t.start();
    }

    // thread's entry point
    public void run() {
        while (1 == 1) {
// 1: sleep for 1/5 sec
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
// 2: animate game objects [nothing yet!]

            if(isGameInProgress)
            {
                //gameState[][][currVisibleState] where currVisibleState = 0
                //is painted

                int numNeighbours = 0;
                int tempX = -100;
                int tempY = -100;

                //For each cell
                for(int x = 0; x<40; x++)
                {
                    for(int y = 0; y<40; y++)
                    {
                        //Count the cell's neighbours
                        //x, y cell's neighbours:
                        // count the live neighbours of cell [x][y][0]
                        for (int xx=-1;xx<=1;xx++)
                        {
                            for (int yy=-1;yy<=1;yy++)
                            {
                                if (xx!=0 || yy!=0)
                                {
                                    // want to check cell [x+xx][y+yy][currVisibleState]
                                    // but may check out of bounds of array

                                    //Using tempX and tempY to eliminate out of bounds errors
                                    //Solution to checking out of bounds: wrap around to the opposite end of screen

                                    //Checking neighbour out of bounds on the left side
                                    if(x+xx == -1)
                                    {
                                        //Wrap around to the other side
                                        tempX = 40;
                                    }
                                    else if (x+xx == 40)
                                    {
                                        //Checking neighbour out of bounds on the right side
                                        //Wrap around to the other side
                                        tempX = -1;
                                    }
                                    else
                                    {
                                        //x is in bounds
                                        tempX = x;
                                    }

                                    //Checking neighbour out of bounds on the top side
                                    if(y+yy == -1)
                                    {
                                        //Wrap around to the other side
                                        tempY = 40;
                                    }
                                    else if (y+yy == 40)
                                    {
                                        //Checking neighbour out of bounds on the bottom
                                        //Wrap around to the other side
                                        tempY = -1;
                                    }
                                    else
                                    {
                                        //y is in bounds
                                        tempY = y;
                                    }


                                    //Now check the neighbour
                                    //knowing that you will not be checking out of bounds of the array
                                    if(gameState[tempX+xx][tempY+yy][currVisibleState])
                                    {
                                        numNeighbours++;
                                    }
                                }
                            }
                        }

                        //End of counting a cell's neighbours

                        //Set the background buffer to look like this buffer
                        gameState[x][y][1-currVisibleState] = gameState[x][y][currVisibleState];

                        //Update cells of background buffer based on rules
                        //ie We are going to update the next gameState
                        //based on the current gameState
                        //This prevents updates that we make from affecting the result
                        //due to updating each cell iteratively
                        //So read from: currVisibleState
                        //Update: 1-currVisibleState
                        if(gameState[x][y][currVisibleState])
                        {
                            //Rules for alive cells

                            //Rule 1
                            if(numNeighbours < 2)
                            {
                                //Cell dies
                                gameState[x][y][1-currVisibleState] = false;
                            }

                            //Rule 2
                            if ((numNeighbours == 2) || (numNeighbours == 3))
                            {
                                //Stays alive
                                gameState[x][y][1-currVisibleState] = true;
                            }

                            //Rule 3
                            if(numNeighbours > 3)
                            {
                                //Cell dies
                                gameState[x][y][1-currVisibleState] = false;
                            }
                        }
                        else
                        {
                            //Rules for dead cells
                            //4
                            if(numNeighbours == 3)
                            {
                                //Cell comes to life
                                gameState[x][y][1-currVisibleState] = true;
                            }
                        }


                        //Reset neighbour count for the next cell
                        numNeighbours = 0;
                    }
                }

                //Swap the gameState buffers
                currVisibleState = 1 - currVisibleState;
            }

// 3: force an application repaint
            this.repaint();
        }
    }

    // mouse events which must be implemented for MouseListener
    public void mousePressed(MouseEvent e) {
        // determine which cell of the gameState array was clicked on
        int x = e.getX();
        int y = e.getY();

        if(!isGameInProgress && ((x>=10 && x<=160)&&(y>=34 && y<=49)))
        {
            //Start button clicked
            isGameInProgress = true;
        }
        else if (!isGameInProgress && ((x>=175 && x<=325)&&(y>=34 && y<=49)))
        {
            //Random button clicked
            //Generate a random number to randomly assign some cells to be set alive
            //Random number 1-10
            int randomInt = (int)Math.floor(Math.random() * (10 - 0 + 1) + 0);
            //Going to update the current gameState buffer which will be 0 before start is clicked
            //see buffer explanation in class member declarations
            for (int i = 0; i < 40; i++)
            {
                for (int j = 0; j < 40; j++)
                {
                    //update gameState with random cells alive
                    //loop through all cells
                    //20% chance that a cell starts alive
                    if(randomInt == 1 || randomInt == 2)
                    {
                        gameState[i][j][currVisibleState] = true;
                    }
                    else
                    {
                        gameState[i][j][currVisibleState] = false;
                    }
                    //update random number
                    randomInt = (int)Math.floor(Math.random() * (10 - 0 + 1) + 0);
                }
            }
        }
        else if (!isGameInProgress)
        {
            x = x / 20;
            y = y / 20;

            // toggle the state of the cell
            gameState[x][y][currVisibleState] = !gameState[x][y][currVisibleState];
        }

        // request an extra repaint, to get immediate visual feedback
        this.repaint();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    //
// application's paint method
    public void paint(Graphics g) {
        g = offscreenBuffer; // draw to offscreen buffer
// clear the canvas with a black rectangle
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 800);

// redraw all game objects
        g.setColor(Color.WHITE);
        for (int x = 0; x < 40; x++) {
            for (int y = 0; y < 40; y++) {
                if (gameState[x][y][currVisibleState]) {
                    g.fillRect(x * 20, y * 20, 20, 20);
                }
            }
        }

        // Paint the buttons if game is not in progress
        if(!isGameInProgress)
        {
            g.setColor(Color.GREEN);
            g.fillRect(10, 34, 150, 15);
            g.fillRect(175, 34, 150,15);
            g.setColor(Color.BLUE);
            g.drawString("Play", 25, 45);
            g.drawString("Randomise", 190, 45);
        }

// flip the buffers
        strategy.show();
    }

    // application entry point
    public static void main(String[] args)
    {
        ConwaysLife w = new ConwaysLife();
    }
}