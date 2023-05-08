import java.util.ArrayList;

/**
 * Write a description of class Group here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Group
{
    private ArrayList<Cube> cubes;

    /**
     * Constructor for objects of class Group
     */
    public Group()
    {
        cubes = new ArrayList<>();
    }
    
    public void addCubeToGroup(Cube c)
    {
        cubes.add(c);
    }
    
}
