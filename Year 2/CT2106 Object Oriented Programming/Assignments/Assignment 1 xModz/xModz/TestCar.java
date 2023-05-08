
/**
 * This class will be used to create objects and call functions to test the functionality of the xModz program.
 *
 * @author Maxwell Maia
 * @version 1.1
 */
public class TestCar
{ 
    public static void main(String[] args)
    {
        //Create a car object.
        Car car = new Car("X7");
        
        //Create an engine object.
        Engine engine = new Engine("DR9", 43);
        
        car.add(engine); //Pass the engine object
        //into the car object.
        //This makes the engine object a child
        //of the car object.
        
        //Create a wheel object.
        Wheel wheel = new Wheel("Wichelin15", 15);
        
        //Pass the wheel object we just created
        //into the car object. In the car
        //object, the wheel is passed to the engine object.
        
        //So the wheel, is the child of the engine,
        //and the engine is the child of the car.
        car.add(wheel);
        
        //Set the fuel. This is parsed into car,
        //then into engine (in the car class).
        car.setFuel(100);
        
        //Print the fuel status using a getter.
        System.out.printf("Current fuel: %.2f\n", car.getFuel());
        
        //Method to make the car drive, using up all its fuel.
        car.drive();
        
        //Method that prints the fields of the car to the terminal.
        car.printState();
        
        
        
        //Refilling fuel... going on another trip...
        car.setFuel(50);
        
        //Print the fuel status using a getter.
        System.out.printf("Current fuel: %.2f\n", car.getFuel());
        
        //The car drives a second time, using up all its fuel.
        car.drive();
        
        //Method that prints the fields of the car to the terminal.
        car.printState();
    }
}
