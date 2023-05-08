
/**
 * Write a description of class Canary here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Canary extends Bird
{
    String name;

    /**
     * Constructor for objects of class Canary
     */
    public Canary(String name)
    {
        super();
        this.name = name;
        colour = "yellow";
    }

    @Override
    public void sing()
    {
        System.out.println("tweet tweet tweet");
    }
}
