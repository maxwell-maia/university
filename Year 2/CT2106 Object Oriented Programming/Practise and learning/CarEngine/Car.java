
/**
 * Write a description of class Car here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Car
{
    // instance variables - replace the example below with your own
    private double totalDistance;
    private Engine engine;

    /**
     * Constructor for objects of class Car
     */
    public Car()
    {
        
        totalDistance = 0;
        engine = new Engine(10);
    }

    public void setFuel(double fuel){
        engine.setFuel(fuel);
    }
    
    
    /**
     * The move method is called whenever a Car object is required to move
     *
     * @param  distance  : the distance the car wishes to move
     * @return   boolean: true or false based on whethe the car moved or not
     */
    public boolean move(double distance)
    {
        
        boolean moved = engine.go(distance); //checks to see if engine will allow this distance
  
        if(moved){
            totalDistance +=distance; //updates distance travelled
        }
        
        printState();
       
        return moved;
    }
    
    public void printState()
    {
        // Simulates output from a car dashboard computer.
        System.out.println("##################");
        System.out.printf("# Distance Travelled: %f \n ",totalDistance);
        System.out.printf("# Fuel Remaining : %f \n ", engine.getFuel());
        System.out.printf("# Available Distance: %f \n", engine.getRange());
        System.out.println("##################");
        System.out.println();
    }
    
    
}
