import java.awt.*;
public class Sprite2D {
    // member data
    protected double x,y;
    protected double xSpeed=0;
    protected Image myImage;
    int winWidth;
    // constructor
    public Sprite2D(Image i, int windowWidth) {
        //x = Math.random()*600;
        //y = Math.random()*600;
        myImage = i;
        winWidth = windowWidth;
    }

    public void setPosition(double xx, double yy) {
        x = xx;
        y = yy;
    }
    public void setXSpeed(double dx) {
        xSpeed=dx;
    }
    public void paint(Graphics g) {
        g.drawImage(myImage, (int)x, (int)y, null);
    }
}