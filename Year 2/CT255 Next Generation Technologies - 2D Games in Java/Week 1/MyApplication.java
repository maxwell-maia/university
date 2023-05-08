package MyApplication;

import java.awt.*;
import javax.swing.*;

public class MyApplication extends JFrame {

    private static final Dimension WindowSize = new Dimension(600,600);
    public MyApplication() {
        //Create and set up the window.
        this.setTitle("Pacman, or something..");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window, centred on the screen
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width/2 - WindowSize.width/2;
        int y = screensize.height/2 - WindowSize.height/2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true); //needed
    }
    public static void main(String [ ] args)
    {
        MyApplication w = new MyApplication();
    }

//    public void paint ( Graphics g ) {
//        //super.paint(g);//this line is from google
//
//        Font f = new Font( "Times", Font.PLAIN, 24 );
//        g.setFont(f);
//        Color c = Color.BLACK;
//        g.setColor(c);
//        g.drawString("Pacman!", 20, 60);
//    }

    public void paint (Graphics g)
    {
        //posX and posY are used to position the entire grid of rectangles
        int posX = 15;
        int posY = 35;

        //Variables keeping track of where to draw the next rectangle
        int posDrawX = 0;
        int posDrawY = 0;


        for(int i = 0; i<20; i++) //Rows
        {
            for(int j = 0; j<20; j++) //Columns
            {
                //Variables keeping track of where to draw the next rectangle
                posDrawX = j*50 + posX;
                posDrawY = i*50 + posY;

                //Want to stay between the values 600 and 600. But the square is 45 pixels large. So subtract 45 from the valid range: 600-45.
                if(posDrawX < (600-45) && posDrawY < (600-45))
                {
                    //Make a random color
                    Color c = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
                    //Set the color
                    g.setColor(c);
                    //Draw a rectangle
                    g.fillRect(posDrawX, posDrawY, 45, 45);
                }
                else
                {
                    //Out of valid range. No need to keep looping
                    break;
                }
            }
        }
    }
}