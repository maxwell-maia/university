
public abstract class Bird extends Animal
{
    //instance variables (fields)
    boolean hasFeathers;
    boolean hasWings;
    boolean flies;

    /**
     * Constructor for objects of class Bird
     */
    public Bird()
    {
        super(); //calls the constructor of its superclass  - Animal
        colour = "black"; //overrides the value of colour inherited from Animal
        hasFeathers = true; //all the subclasses of Bird inherit this property and value
        hasWings = true; //all the subclasses of Bird inherit this property and value
        flies = true; //all the subclasses of Bird inherit this property and value
    }

    /**
     * move method overrides the move method
     * inherited from superclass Animal
     */
    
    // good programming practice to use @Override to denote overridden methods
    
    @Override
    public void move(int distance)
    {
        //If the Bird cannot fly the output should be different.
        // Check this using an "if" and the "flies" boolean.
        if(flies)
        {
            System.out.printf("I fly %d metres \n", distance);
        }
        else
        {
            System.out.printf("I am a bird but cannot fly.\n");
        }
    }
    
    /**
     * sing method that all birds have
     */
    public void sing(){
        System.out.println("tra la la");
    }
    
    /**
     * 'getter' method for the hasWings field
     */
    public boolean hasWings(){
        return hasWings;
    }
    
    /**
     * 'getter' method for the hasFeathers field
     */
    public boolean hasFeathers(){
        return hasFeathers;
    }
    
    //Getter for flies attribute
    public boolean flies()
    {
        return flies;
    }
}
