import java.util.Scanner;

/**
 * Write a description of class TestVariables here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestVariables
{
    public static void main(String[] args)
    {
        boolean locked = false;
        
        int hellpo = 160000000;
        
        float doubled = 15;
        
        System.out.println(doubled);
        
        
        String limbp = "15.0";
        
        /*
        String is; an object; reference variable; and not; a primitive; // "variable";
        */
       
       
        double lol = doubled;
        
        float lol2 = (float) lol;
        
        System.out.println(lol);
        System.out.println(lol2+" wow");

        
        
        //Create a scanner
        Scanner myScanner = new Scanner(System.in);
        
        
        System.out.println("Enter your username");
        
        //Get string from user and store it in new string called userName
        String userName = myScanner.nextLine();
        
        //display what was rotten 
        System.out.println("Username is: "+userName);
        
        //convert a string to an integer by putting the String inside of Integer.parseInt(variable);
        int converted = Integer.parseInt(userName);
        
        //out comes the number if its not a number it shits its pants. official term
        System.out.printf("My name is jeff (%d)", converted);
        
    }


}
