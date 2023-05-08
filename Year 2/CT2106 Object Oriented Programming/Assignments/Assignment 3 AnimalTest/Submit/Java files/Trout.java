
public class Trout extends Fish
{
    boolean hasSpikes;
    boolean isEdible;
    boolean swimsUpriverToLayEggs;

    /**
     * Constructor for objects of class Trout
     */
    public Trout(String name)
    {
        super();
        
        this.name = name; //Overrides value set in Animal.
        
        hasSpikes = true;
        isEdible = true;
        swimsUpriverToLayEggs = true;
        colour = "brown"; //Overrides value set in Animal
    }
    
    /**
     * toString method returns a String representation of the fish
     * What superclass has Trout inherited this method from? java.lang.Object
     */
    @Override
    public String toString()
    {
        String strng = "";
        
        strng+= "Trout; ";
        strng+= "\n";
        strng+= "hasSpikes: ";
        strng+= hasSpikes;
        strng+= "; ";
        strng+= "isEdible: ";
        strng+= isEdible;
        strng+= "; ";
        strng+= "swimsUpriverToLayEggs: ";
        strng+= swimsUpriverToLayEggs;
        strng+= "; ";
        strng+= "\n";
        
        strng+= "is a Fish; ";
        strng+= "hasFins: ";
        strng+= hasFins;
        strng+= "; ";
        strng+= "hasGills: ";
        strng+= hasGills;
        strng+= "; ";
        strng+= "\n";
        
        strng+= "is an Animal; ";
        strng+= "name: ";
        strng+= name;
        strng+= "; ";
        strng+= "colour: ";
        strng+= colour;
        strng+= "; ";
        strng+= "hasSkin: ";
        strng+= hasSkin;
        strng+= "; ";
        strng+= "breathes: ";
        strng+= breathes;
        strng+= "; ";
        strng+= "eats: ";
        strng+= eats;
        strng+= "; ";
        strng+= "\n";
        
        return strng;
    }
    
    /*
     * Equals method for leaf class.
     * This method defines what makes Trout objects equal
     * Returns true if the objects are equal, else false.
     * 
     * Rules for equality
     * Same name, same hasSpikes, same isEdible, same swimsUpriverToLayEggs
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
        {
            //no object input
            return false;
        }
        
        if(obj instanceof Trout)
        {
            Trout trt = (Trout)obj;
            
            if(this.name.equals(trt.getName())
             && this.hasSpikes == trt.hasSpikes()
             && this.isEdible == trt.isEdible()
             && this.swimsUpriverToLayEggs == trt.swimsUpriverToLayEggs())
            {
                //objects equal
                return true;
            }
        }
        
        //objects not equal
        return false;
    }
    
    //Getter for hasSpikes attribute
    public boolean hasSpikes()
    {
        return hasSpikes;
    }
    
    //Getter for isEdible attribute
    public boolean isEdible()
    {
        return isEdible;
    }
    
    //Getter for swimsUpriverToLayEggs attribute
    public boolean swimsUpriverToLayEggs()
    {
        return swimsUpriverToLayEggs;
    }
}
