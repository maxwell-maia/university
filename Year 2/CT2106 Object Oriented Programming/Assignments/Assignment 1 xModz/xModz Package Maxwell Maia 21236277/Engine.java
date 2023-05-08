
/**
 * 
 * This is the engine class. The engine is composed of a wheel.
 *
 * @author Maxwell Maia
 * @version 2.1
 */
public class Engine
{
    //Declared fields to store information about the engine of a car.
    private double fuelLevel;
    private String name;
    private double tpl;
    private int totalNumTurns;
    
    //Variable to store the engine object.
    //The engine needs an wheel object to function because a engine is composed of a wheel.
    //The engine relies on the service of the wheel.
    private Wheel engineWheel;
    
    /**
     * Constructor for objects of class Engine
     */
    public Engine(String name, double tpl, String wheelName, double radius)
    {
        // initialise instance variables
        this.name = name;
        
        //Make sure that the tpl is a positive number using Math.abs() .
        this.tpl = Math.abs(tpl);
        
        //Create a wheel object.
        engineWheel = new Wheel(wheelName, radius);
    }
    
    public void setFuel(double fuel) //Set the fuel
    {
        //Protect against in valid in puts.
        //Only allow a range of 0 to 100 (inclusive).
        if(fuel >= 0 && fuel <= 100)
        {
            this.fuelLevel = fuel;
        }
        else
        {
            System.out.println("Fuel input is not in the correct range. Fuel input must be a value from 0 - 100 (inclusive).");
        }
    }
    
    public double getFuel() //Return the fuel to the car.
    {
        return fuelLevel;
    }
    
    public String getName() //Return the name of the engine.
    {
        return name;
    }
    
    public double getTpl() //Return the tpl.
    {
        return tpl;
    }
    
    public int getTotalNumTurns() //Return the totalNumTurns.
    {
        return totalNumTurns;
    }
    
    
    //The car has called on the engine to turn the wheel..
    //Here the engine calls on the wheel to turn, and
    //the distance travelled by the wheel is returned.
    public double turnWheelCalcDistance(double turns)
    {     
        //Call for wheel to turn and store the returned value.
        double distanceDueToTurn = engineWheel.turn(turns);
        
        //Increment the engine's total turn count.
        /*
         * Note: In the sample output, the "Engine's total
         * turn count" shows an integer with no decimal places.
         * For this reason an integer is used here.
         * This is okay but when this function is called
         * while the engine has either a fuelLevel that is not 
         * a whole number or a TPL that is not a whole number,
         * data will be lost when converting a double to an
         * integer. This happens in the statement below.
         * Solution:
         * a) use a double for totalNumTurns.
         * b) be okay with a number of turns
         * that is not decimal precise. AKA number of
         * completed turns.
         */
        totalNumTurns += turns;
        
        
        //Return the distance travelled to the Car object.
        return distanceDueToTurn;
    }
    
    public String getWheelName() //Return the name of the wheel that is stored in the wheel object.
    {
        return engineWheel.getName();
    }
    
    public double getWheelRadius() //Return the radius of the wheel that is stored in the wheel object.
    {
        return engineWheel.getRadius();
    }
    
    public double getWheelCircumference() //Return the circumference of the wheel that is stored in the wheel object.
    {
        return engineWheel.getCircumference();        
    }
}
