
public abstract class Fish extends Animal
{
    boolean hasFins;
    boolean hasGills;

    /**
     * Constructor for objects of class Fish
     */
    public Fish()
    {
        hasFins = true;
        hasGills = true;
        hasSkin = false; //Override value set in Animal. All subclasses inherit this value.
    }

    @Override
    public void move(int distance)
    {
        System.out.printf("I swim %d metres \n", distance);
    }
    
    //Getter for hasFins attribute
    public boolean hasFins()
    {
        return hasFins;
    }
    
    //Getter for hasGills attribute
    public boolean hasGills()
    {
        return hasGills;
    }
}
