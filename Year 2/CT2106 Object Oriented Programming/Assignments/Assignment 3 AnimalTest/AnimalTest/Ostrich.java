
public class Ostrich extends Bird
{
    boolean isTall;
    boolean hasThinLongLegs;
    
    /**
     * Constructor for objects of class Ostrich
     // */
    public Ostrich(String name)
    {
        super();
        this.name = name; //Overrides value set in Animal.
        
        isTall = true;
        hasThinLongLegs = true;
        flies = false; //Override value set in Bird. he can't fly :(
    }
    
    /**
     * toString method returns a String representation of the bird
     * What superclass has Ostrich inherited this method from? java.lang.Object
     */
    @Override
    public String toString()
    {
        String strng = "";
        strng+= "Ostrich; ";
        strng+= "\n";
        strng+= "isTall: ";
        strng+= isTall;
        strng+= "; ";
        strng+= "hasThinLongLegs: ";
        strng+= hasThinLongLegs;
        strng+= "; ";
        strng+= "\n";
        
        strng+= "is a Bird; ";
        strng+= "hasFeathers: ";
        strng+= hasFeathers;
        strng+= "; ";
        strng+= "hasWings: ";
        strng+= hasWings;
        strng+= "; ";
        strng+= "flies: ";
        strng+= flies;
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
     * This method defines what makes Ostrich objects equal
     * Returns true if the objects are equal, else false.
     * 
     * Rules for equality
     * Same name, same isTall, same hasThinLongLegs
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
        {
            //no object input
            return false;
        }
        
        if(obj instanceof Ostrich)
        {
            Ostrich ost = (Ostrich)obj;
            
            if(this.name.equals(ost.getName())
             && this.isTall == ost.isTall()
             && this.hasThinLongLegs == ost.hasThinLongLegs())
            {
                //objects equal
                return true;
            }
        }
        
        //objects not equal
        return false;
    }
    
    //Getter for isTall attribute
    public boolean isTall()
    {
        return isTall;
    }
    
    //Getter for hasThinLongLegs attribute
    public boolean hasThinLongLegs()
    {
        return hasThinLongLegs;
    }
}
