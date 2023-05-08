import java.awt.Image;
public class Alien extends Sprite2D
{
    public Alien(Image i, int windowWidth)
    {
        super(i, windowWidth);
    }

    //public interface
    public boolean move()
    {
        //Move alien
        x += xSpeed;

        if(x < 10 || x > winWidth-60)
        {
            //Alien hit edge of screen
            return true;
        }

        //Alien didn't hit edge of screen
        return false;
    }

    public void reverseDirection()
    {
        //Reverse direction
        xSpeed = (-1)*xSpeed;
        //Move the alien down the screen
        y = y + 20;
    }
}
