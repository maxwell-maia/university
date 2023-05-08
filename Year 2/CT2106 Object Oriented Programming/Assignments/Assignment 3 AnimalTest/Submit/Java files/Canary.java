
public class Canary extends Bird
{
    
    /**
     * Constructor for objects of class Canary
     */
    public Canary(String name)
    {
        super(); // call the constructor of the superclass Bird
        this.name = name; //overrides the name given in Animal
        colour = "yellow"; // this overrides the value inherited from Bird
    }
    
    /**
     * Sing method overrides the sing method
     * inherited from superclass Bird
     */
    @Override // good programming practice to use @Override to denote overridden methods
    public void sing(){
        System.out.println("tweet tweet tweet");
    }
    
    /**
     * toString method returns a String representation of the bird
     * What superclass has Canary inherited this method from? java.lang.Object
     */
    @Override
    public String toString()
    {
        String strng ="";
        strng+= "Canary; ";
        strng+= "\n";
        // Include the fields and attributes inherited 
        //from Bird and Animal in the String representation
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

    
    /**
     * equals method defines how equality is defined between 
     * the instances of the Canary class
     * param Object
     * return true or false depending on whether the input object is 
     * equal to this Canary object
     * 
     * Rules for equality
     * Same name, same colour
     */
    
    @Override
    public boolean equals(Object obj){
        
        //Make sure that the object reference isn't empty. (no object)
        if(obj == null)
        {
            //no object (obj is null) was input to compare this object to
            return false;
        }
        
        //Check that the object reference is the same class as this class
        if(obj instanceof Canary)
        {
            
            //Upcast the object so that checks can be done below
            Canary can = (Canary)obj;
            
            //Check the rules for equality
            if(this.name.equals(can.getName())
             && this.colour.equals(can.getColour()))
            {
                //the objects are equal
                return true;
            }
        }
        
        //the objects are not equal
        return false;
    }
    
}
