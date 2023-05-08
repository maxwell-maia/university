
/**
 * Write a description of class MainTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainTest
{
    public static void main(String[] args)
    {
        //Random numbers
        double rnd = Math.random(); //0.0 - 0.9999
        
        System.out.println("Start");
        for(int i = 0; i<5; i ++)
        {
            rnd = Math.random();
            System.out.println("Math.random(): " + rnd);
        }
        System.out.println("End");
        
        int min = 5;
        int max = 11;
        //(max - min)
        //(11 - 5)
        //6
        //Make max 1 bigger because this wont get you the max value. only 1 less. which is 10.
        for(int i = 0; i < 100; i++)
        {
            double result = ((Math.random() * (max - min)) + min);
        
            System.out.println("Result = " + result);
        }
        //(int) ( ( Math.random() * (max-min) ) + min)
        
    }
    
    public int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
}
}
