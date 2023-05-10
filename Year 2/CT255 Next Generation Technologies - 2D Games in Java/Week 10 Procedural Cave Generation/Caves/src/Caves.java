import java.awt.*;
import javax.swing.*;
public class Caves extends JFrame implements Runnable
{
    private boolean caveState[][][] = new boolean [200][200][2];

    private int currVisibleState = 0;

    private int epochs = 4;

    public Caves()
    {
        Dimension ss = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = ss.width/2-400;
        int y = ss.width/2 - 600;
        setBounds(x, y, 800, 800);
        setVisible(true);
        setTitle("MINECRAFT CAVES");

        //Generate a random number 1 - 10
        int randomInt = (int)Math.floor(Math.random() * (10 - 1 + 1) + 1);

        //initialize cave state
        //60% chance of a cell being a wall
        //40% chance of a cell being a floor
        for(int i = 0; i < 200; i++)
        {
            for(int j = 0; j < 200; j++)
            {
                //Wall = true
                //Floor = false
                if(randomInt >= 5)
                {
                    //60% Wall
                    caveState[i][j][0] = true;
                    caveState[i][j][1] = true;
                }
                else
                {
                    //40% floor
                    caveState[i][j][0] = false;
                    caveState[i][j][1] = false;
                }

                //Generate a new random number 1 - 10
                randomInt = (int)Math.floor(Math.random() * (10 - 1 + 1) + 1);
            }
        }

        // create and start our animation thread
        Thread t = new Thread(this);
        t.start();
    }

    public void run()
    {
        int epochsRemaining = epochs;
        while (true)
        {
            //Sleep for 4 seconds
            try
            {
                Thread.sleep(4000);
            }
            catch (InterruptedException e)
            {

            }

            //Animate
            if(epochsRemaining > 0)
            {
                int numNeighbours = 0;
                int tempX = -100;
                int tempY = -100;

                //For each cell
                for(int x = 0; x<200; x++)
                {
                    for(int y = 0; y<200; y++)
                    {
                        //Count the cell's neighbours of the current Visible State
                        //Cell x y 's neighbours
                        for(int xx = -1; xx <= 1; xx++)
                        {
                            for(int yy = -1; yy <= 1; yy++)
                            {
                                if(xx != 0 || yy != 0)
                                {
                                    //out of bounds (of screen) check

                                    //Use tempX and tempY to help with this
                                    //The screen wraps around to the other side
                                    //The neighbour of a cell at the edge of the screen
                                    // is the cell on the opposite side of the screen.

                                    if(x+xx == -1)
                                    {
                                        //When out of bounds of the left side
                                        //Wrap around to the other side
                                        tempX = 200;
                                    }
                                    else if(x+xx == 200)
                                    {
                                        //When out of bounds of the right side
                                        //Wrap around to the other side
                                        tempX = -1;
                                    }
                                    else
                                    {
                                        //x is in bounds
                                        tempX = x;
                                    }

                                    if(y+yy == -1)
                                    {
                                        //When out of bounds on the top
                                        //Wrap to the other side
                                        tempY = 200;
                                    }
                                    else if(y+yy == 200)
                                    {
                                        //When out of bounds on the bottom
                                        //Wrap around to the other side
                                        tempY = -1;
                                    }
                                    else
                                    {
                                        //y is in bounds
                                        tempY = y;
                                    }

                                    //Now I know that tempX+xx and tempY+yy is in bounds
                                    //Increment neighbour counter
                                    if(caveState[tempX+xx][tempY+yy][currVisibleState])
                                    {
                                        numNeighbours++;
                                    }
                                }
                            }
                        }

                        //End of counting a cell's neighbours

                        //Make the background buffer the same as the visible buffer
                        caveState[x][y][1-currVisibleState] = caveState[x][y][currVisibleState];

                        //Update the background buffer based on what is read from the visible (front) buffer
                        //This prevents updates from affecting the result of the next cell
                        //Updating rules:
                        //if a cell has 5 or more neighbours (5 or more neighbouring walls)
                        //then set it to a wall
                        //else set it to a floor
                        System.out.println(numNeighbours);
                        if(numNeighbours >= 5)
                        {
                            caveState[x][y][1-currVisibleState] = true;
                        }
                        else
                        {
                            caveState[x][y][1-currVisibleState] = false;
                        }

                        //Reset the count for the next cell
                        numNeighbours = 0;
                    }
                }

                //Swap the buffers
                currVisibleState = 1 - currVisibleState;

                epochsRemaining--;
            }

            //Force an application repaint
            this.repaint();
        }
    }

    //Application's paint method
    public void paint(Graphics g)
    {
        //Clear the canvas with a black rectangle
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 800);

        //Redraw all games objects
        g.setColor(Color.WHITE);
        for(int x = 0; x < 200; x++)
        {
            for(int y = 0; y < 200; y++)
            {
                if(caveState[x][y][currVisibleState])
                {
                    g.fillRect(x*4, y*4, 4, 4);
                }
            }
        }

    }

    //Application entry point
    public static void main(String[] args)
    {
        Caves w = new Caves();
    }
}