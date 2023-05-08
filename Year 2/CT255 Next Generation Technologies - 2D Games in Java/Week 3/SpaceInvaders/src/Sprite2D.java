import java.awt.*;
public class Sprite2D
{
    //Maxwell Maia
    //21236277

    // member data
    private double x, y;
    private double xSpeed = 0;
    private Image myImage;

    //constructor
    public Sprite2D(Image i)
    {
        System.out.println("In");
        myImage = i;
    }

    //public interface
    public void moveEnemy()
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

    public double getRandomDouble(double min, double max)
    {
        return ((Math.random() * (max-min)) + min);
    }

    public void setPosition(double xx, double yy)
    {
        x = xx;
        y = yy;
        System.out.println("pos set");
    }

    public void movePlayer()
    {
        x = x + xSpeed;
    }

    public void setXSpeed(double dx)
    {
        xSpeed = dx;
    }

    public void paint(Graphics g)
    {
        //draw the image at x, y
        g.drawImage(myImage, (int)x, (int)y, null);
    }
}
