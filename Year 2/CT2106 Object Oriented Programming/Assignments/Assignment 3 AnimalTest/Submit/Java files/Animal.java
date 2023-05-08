
public abstract class Animal
{
    boolean hasSkin;
    boolean breathes;
    String colour;
    boolean eats;
    
    String name;
    /*
     * I moved the "name" attribute to the Animal class because it is a
     * general attribute that all classes will have.
     */
    
    
    /**
     * Constructor for objects of class Animal
     */
    public Animal()
    {
        breathes = true; //all the subclasses of Animal inherit this property and value
        hasSkin = true; // all the subclasses of Animal inherit this property and value
        colour = "grey"; //all the subclasses of Animal inherit this property and value
        eats = true; //all the subclasses of Animal inherit this property and value
        name = "unnamed"; //all the subclasses of Animal inherit this property and value
    }
    
    /**
     * move method
     * param int distance - the distance the Animal should move
     * All subclasses inherit this method
     */
    public abstract void move(int distance);
        
    /**
     * getter method for colour field
     * All subclasses inherit this method
     */
    public String getColour(){
        return colour;
    }
    
     /**
     * 'getter' method for haSkin field
     * All subclasses inherit this method
     */
    
    public boolean hasSkin(){
        return hasSkin;
    }
    
    //Getter for breathes attribute
    public boolean breathes()
    {
        return breathes;
    }
    
    //Getter for eats attribute
    public boolean eats()
    {
        return eats;
    }
    
    //Getter for name attribute
    public String getName()
    {
        return name;
    }
}
