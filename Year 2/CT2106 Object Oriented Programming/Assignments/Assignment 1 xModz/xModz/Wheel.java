
/**
 * This is the wheel class. The wheel is not composed of any objects.
 *
 * @author Maxwell Maia
 * @version 1.1
 */
public class Wheel
{
    //Declared fields to store information about the wheel that is connected to the engine of a car.
    private double radius;
    /*
     * A wheel's radius may include a decimal,
     * so a double is used.
     */
    
    private String name;
    
    private double distanceTravelled;
    
    //1 turn of a wheel is 1 circumference of distance.
    //calculate circumference
    private double circumference;
    
    /**
     * Constructor for objects of class Wheel
     */
    public Wheel(String name, double radius)
    {
        // initialise instance variables
        this.name = name;
        
        //Make sure that the radius is a positive number using Math.abs() .
        this.radius = Math.abs(radius);
        circumference = 2 * Math.PI * Math.abs(radius);
    }
    
    //Function to "turn the wheel" the amount of times
    //that it has been requested to.
    //The function returns the distance it has travelled
    //due to this call to turn the wheel.
    public double turn(double turns)
    {
        //1 turn of a wheel is 1 circumference of distance.
        distanceTravelled = circumference * turns;
        return distanceTravelled;
    }
    
    
    //Getters to return the fields of the wheel.
    
    public String getName()
    {
        return name;
    }
    
    public double getRadius()
    {
        return radius;
    }
    
    public double getCircumference()
    {
        return circumference;
    }
}
