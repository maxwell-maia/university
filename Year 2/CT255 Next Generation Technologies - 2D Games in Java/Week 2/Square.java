import java.awt.*;


public class Square
{
    //Maxwell Maia
    //21236277

    //"member data"
    private double x, y;
    private Color c;

    //constructor
    public Square()
    {
        //My own notes about square positioning
        //squares will be 40 x 40. Their starting positions have to be within the 600 x 600 panel
        //pretend the position is the centre of the square.
        //This will draw with x and y on the centre
        //fillRect(x-20, y-20, 40, 40)
        //draw a 40 x 40 rectangle. (starting from the top left)

        //the most right position is then x = 600-20 = 580
        //the most bottom position is then y = 600-20 = 580
        //most left and top position is 20.
        //These rules mean that squares will spawn at maximum exactly at the edge of the pane.
        //May spawn underneath the os window banner


        //choose a x position 20-580
        x = getRandomDouble(20.0, 580.0);
        //System.out.println("x: "+x);

        //choose a y position
        y = getRandomDouble(20.0, 580.0);
        //System.out.println("y: "+y);

        //choose a random colour
        int red = (int)(Math.random()*256);
        int green = (int)(Math.random()*256);
        int blue = (int)(Math.random()*256);

        //make the colour
        c = new Color(red, green, blue);
    }

    //Random int generator not used
    public int getRandomNumber(int min, int max)
    {
        return (int) ((Math.random() * (max - min)) + min);
    }

    //get a random double in a range
    public double getRandomDouble(double min, double max)
    {
        return ((Math.random() * (max-min)) + min);
    }

    //"public interface"
    public void move()
    {
        //update x and y to new random numbers

        //add a number between -5 and +5
        //get the number
        double moveXBy = getRandomDouble(-5, 5);
        double moveYBy = getRandomDouble(-5, 5);

        //update x
        x = x + moveXBy;

        //update y
        y = y + moveYBy;

        //positions updated
    }

    //method that paints this square. Called by paint() in MySquaresApplication
    public void paint(Graphics g)
    {
        g.setColor(c);
        g.fillRect((int)(x-20), (int)(y-20), 40, 40);
    }
}
