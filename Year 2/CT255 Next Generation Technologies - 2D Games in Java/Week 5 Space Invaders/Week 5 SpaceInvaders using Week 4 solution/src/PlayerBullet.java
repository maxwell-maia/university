import java.awt.Image;
//Class that contains the player's bullet attributes and behaviours
public class PlayerBullet extends Sprite2D
{
    //Index of alien that the bullet killed
    private int alienKilled = 0;

    //Alien array for checking all aliens positions to check for collisions
    //I would rather send a new Aliens array to every bullet on each move function than the array globally accessible,
    // I'm not sure if making the aliens array globally accessible means that someone could tamper with the values,
    // but I want to protect against that by using this method of securely passing the array into each Bullet each move
    private Alien[] AliensArray;
    public PlayerBullet(Image i)
    {
        super(i, i);
    }


    //Move the bullet, returns true if the bullet collides with an alien
    public boolean move(Alien[] aliensArray)
    {
        y -= 5;

        //Collision detection
        //1 = alien
        //2 = bullet
        //x and y = pixel on image starting from top left of image
        //h = height
        //w = width

        //The alien
        double x1, y1, w1, h1;
        //The bullet
        double x2 = x;
        double y2 = y;
        double w2 = myImage1.getWidth(null);
        double h2 = myImage1.getHeight(null);

        //Check all the aliens to see if there was a collision
        for(int i = 0; i < aliensArray.length; i++)
        {
            x1 = aliensArray[i].x;
            y1 = aliensArray[i].y;
            w1 = aliensArray[i].myImage1.getWidth(null);
            h1 = aliensArray[i].myImage1.getHeight(null);

            //Did a collision occur between the bullet and Alien i
            if ((
                    ( (x1<x2 && x1+w1>x2) ||
                            (x2<x1 && x2+w2>x1) )
                            &&
                            ( (y1<y2 && y1+h1>y2) ||
                                    (y2<y1 && y2+h2>y1) )
            ) && aliensArray[i].isAlive())
            {
                //Bullet collided with an alien
                alienKilled = i;
                return true;
            }
            else
            {
                //Bullet did not collide with an alien
            }

        }
        return false;
    }

    // getter for alienKilled
    public int getAlienKilled()
    {
        return alienKilled;
    }
}
