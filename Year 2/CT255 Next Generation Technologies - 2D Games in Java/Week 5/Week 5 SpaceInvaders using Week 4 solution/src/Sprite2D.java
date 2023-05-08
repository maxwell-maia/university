import java.awt.*;
public class Sprite2D {
    // member data
    protected double x,y;
    protected Image myImage1;
    protected Image myImage2;
    // static member data
    protected static int winWidth;

    private int framesDrawn = 0;
    // constructor
    public Sprite2D(Image i1, Image i2) {
        myImage1 = i1;
        myImage2 = i2;
    }
    public void setPosition(double xx, double yy) {
        x=xx;
        y=yy;
    }
    public void paint(Graphics g) {
        //g.drawImage(myImage, (int)x, (int)y, null);

        framesDrawn++;
        if ( framesDrawn%100<50 )
            g.drawImage(myImage1, (int)x, (int)y, null);
        else
            g.drawImage(myImage2, (int)x, (int)y, null);
    }
    public static void setWinWidth(int w) {
        winWidth = w;
    }

    //Getters for sprite's co-ordinates
    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }
}
