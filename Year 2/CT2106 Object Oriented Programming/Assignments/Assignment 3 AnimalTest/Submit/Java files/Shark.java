
public class Shark extends Fish
{
    boolean canBite;
    boolean isDangerous;

    /**
     * Constructor for objects of class Shark
     */
    public Shark(String name)
    {
        super();
        
        this.name = name; //Overrides value set in Animal.
        
        canBite = true;
        isDangerous = true;
    }
    
    /**
     * toString method returns a String representation of the fish
     * What superclass has Shark inherited this method from? java.lang.Object
     */
    @Override
    public String toString()
    {
        String strng = "";
        
        strng+= "Shark; ";
        strng+= "\n";
        strng+= "canBite: ";
        strng+= canBite;
        strng+= "; ";
        strng+= "isDangerous: ";
        strng+= isDangerous;
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
     * This method defines what makes Shark objects equal
     * Returns true if the objects are equal, else false.
     * 
     * Rules for equality
     * Same name, same canBite, same isDangrous
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
        {
            //no object input
            return false;
        }
        
        if(obj instanceof Shark)
        {
            Shark shk = (Shark)obj;
            
            if(this.name.equals(shk.getName())
             && this.canBite == shk.canBite()
             && this.isDangerous == shk.isDangerous())
            {
                //objects equal
                return true;
            }
        }
        
        //objects not equal
        return false;
    }
    
    //Getter for canBite attribute
    public boolean canBite()
    {
        return canBite;
    }
    
    //Getter for isDangerous attribute
    public boolean isDangerous()
    {
        return isDangerous;
    }
}
