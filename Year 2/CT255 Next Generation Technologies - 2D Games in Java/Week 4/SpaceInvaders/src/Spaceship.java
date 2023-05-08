import java.awt.Image;
public class Spaceship extends Sprite2D
{
    public Spaceship(Image i, int windowWidth)
    {
        super(i, windowWidth);
    }

    public void move()
    {
        x += xSpeed;
    }
}
