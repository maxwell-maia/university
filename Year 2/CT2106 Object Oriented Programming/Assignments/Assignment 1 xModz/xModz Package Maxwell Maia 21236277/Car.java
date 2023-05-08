
/**
 * This is the car class. The car is composed of an engine.
 *
 * @author Maxwell Maia
 * @version 2.1
 */
public class Car
{
    //Declared fields to store information about a car.
    private String name;
    private double distance;
    private double totalKm;
    
    //Variable to store the engine object.
    //The car needs an engine object to function because a car is composed of an engine.
    //The car relies on the service of the engine.
    private Engine carEngine;

    /**
     * Constructor for objects of class Car
     */
    public Car(String carName, String engineName, double tpl, String wheelName, double radius)
    {
        //Instantiate the Car object.
        this.name = carName;
        
        //Create an engine object.
        //Also pass the attributes for wheel as arguments.
        carEngine = new Engine(engineName, tpl, wheelName, radius);
    }

    
    public void setFuel(double fuel) //Set the fuel in the engine object.
    {
        carEngine.setFuel(fuel);
    }
    
    public double getFuel() 
    {
        //Get the fuel from the engine object.
        double tempFuel = carEngine.getFuel();
        
        //Return the fuel to the TestCar class.
        return tempFuel;
    }
    
    //Method to make the car drive until it runs out of fuel.
    public void drive()
    {
        //We are ready to drive.
        //Let's calculate how many times we can turn
        //the wheel with the fuel that we have. The result is stored in the "turns" variable.
        double turns = carEngine.getFuel() * carEngine.getTpl();
        
        //The car used all of it's fuel to turn the wheel.
        //Set the fuel to 0.
        carEngine.setFuel(0);
        
        //We will now ask the engine to turn the wheel
        //this many times.
        //The "distance" variable will recieve a value for the distance
        //travelled by the wheel due to this request.
        distance = carEngine.turnWheelCalcDistance(turns);
        
        //Add the "distance of this trip" to the "total distance travelled."
        totalKm += distance;
    }
    
    public void printState()
    {
        //Print out statements showing the value of the fields that describe the car.
        //Getters are used to retrieve fields that are not stored in this class.
        System.out.println("Configuration: Car Body " + name);
        
        System.out.println("Engine name: "+ carEngine.getName());
        
        //.2f is used to show the value to 2 decimal places after the decimal point.
        System.out.printf("Engine turns per litre: %.2f\n", carEngine.getTpl());
        
        System.out.println("Engine's total turn count: " + carEngine.getTotalNumTurns());
        
        System.out.println("Wheel name: " + carEngine.getWheelName());
        
        System.out.println("Wheel radius: " + carEngine.getWheelRadius());
        
        System.out.printf("Wheel circumference (distance per turn): %.2f\n", carEngine.getWheelCircumference());
        
        System.out.printf("Distance this trip: %.2f\n", distance);
        
        System.out.printf("Total distance Travelled: %.2f\n", totalKm);
        
        System.out.printf("Current fuel: %.2f\n\n", carEngine.getFuel());
    }
}
