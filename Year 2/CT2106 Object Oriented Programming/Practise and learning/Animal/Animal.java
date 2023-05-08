
/**
 * Write a description of class Animal here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Animal
{
    
    boolean hasSkin;
    boolean breathes;
    String colour;
    
    //accesible to all child classes
    
    
    
    public Animal()
    {

        breathes = true;
        hasSkin = true;
        colour = "grey";
    }

    public void move(int distance)
    {
        System.out.printf("I move %d metres \n", distance);
    }
}
