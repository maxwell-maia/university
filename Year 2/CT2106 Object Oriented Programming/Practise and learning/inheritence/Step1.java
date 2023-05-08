
/**
 * Write a description of class Step1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Step1
{
    public static void main(String args[])
    {
        String str1 = "Java";
        String str2 = "Ja";
        String str3 = "va";
        String str4 = str2 + str3;
        
        if (str1.equals(str4)) // the equivalence of the reference object (reference object has a memory address stored.)
        {
            System.out.println("TRUE");
            
        }
        else
        {
            System.out.println("false");
        }
        
        // ==
        // THe result is no, because the 2 exactly same objects
        // are stored in different memory locations.
        // memory locations different.
        
        // How do we compare strings?
        
        // .equals() method
        // Will check multiple fields or whatever you want to check to be equivalent.
        
        // make a method to return false
        
        // equals method is auto in every class
        
        // we are going to override this auto equals method
    }   
}
