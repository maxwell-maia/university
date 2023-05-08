
/**
 * Write a description of class Bird here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bird extends Animal //inserted inheritence relationship
//Every lower class
{
    //ALL FIELDs OF Animal is here technically.
    
    boolean hasFeathers;
    boolean hasWings;
    boolean flies;

    /**
     * Constructor for objects of class Bird
     */ //EXTENDING IS FOR IS-A RELATIONSHIPS
    public Bird()
    {
        super(); ///whats my parent class? call its contructor
        //calss the constructor of its superclass
        //the round brackets here needs to match the class above.
        
        colour = "black";
        hasFeathers = true;
        hasWings = true;
        flies = true;
        
    }

    // A bird is abstract. no such thing
    // WONT BE ABLE TO INSTANSIATE AN INSTANCE OF THIS CLASS
    // because A bird doesn't exist. A canary exists.
    // public 
    
    
    //use super class as the type (convention)
    
    // Because canary, is a bird, then you can say
    // Bird bird = new Canary("John");
    // or
    // Animal animal = new Canary("John"); (this is best) of max class (superclass)
    
    public void sing()
    {
        System.out.println("tr la la");
    }
}
