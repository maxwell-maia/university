import java.awt.Image;
public class Alien extends Sprite2D {
    private static double xSpeed=0;
    private boolean isAlive = false;
    public Alien(Image i1, Image i2) {
        super(i1, i2); // invoke constructor on superclass Sprite2D
    }
    // public interface
    public boolean move() {
        x+=xSpeed;
// direction reversal needed?
        if ((x<=0 || x>=winWidth-myImage1.getWidth(null)) && isAlive)
            return true;
        else
            return false;
    }
    public static void setFleetXSpeed(double dx) {
        xSpeed=dx;
    }
    public static void reverseDirection() {
        xSpeed=-xSpeed;
    }
    public void jumpDownwards() {
        y+=20;
    }

    // getter for isAlive
    public boolean isAlive()
    {
        return isAlive;
    }

    // setter for isAlive
    public void setIsAlive(boolean alive)
    {
        isAlive = alive;
    }
}