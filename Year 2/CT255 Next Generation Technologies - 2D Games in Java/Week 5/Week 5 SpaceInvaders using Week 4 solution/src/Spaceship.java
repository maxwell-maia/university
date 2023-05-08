import java.awt.Image;
public class Spaceship extends Sprite2D {
    private double xSpeed=0;

    //isAlive is applicable to Alien and Spaceship but not PlayerBullet, So I didn't put this variable in Sprite2D
    private boolean isAlive = false;
    public Spaceship(Image i) {
        super(i, i); // invoke constructor on superclass Sprite2D
    }
    public void setXSpeed(double dx) {
        xSpeed=dx;
    }
    public void move() {
// apply current movement
        x+=xSpeed;
// stop movement at screen edge?
        if (x<=0) {
            x=0;
            xSpeed=0;
        }
        else if (x>=winWidth-myImage1.getWidth(null)) {
            x=winWidth-myImage1.getWidth(null);
            xSpeed=0;
        }
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