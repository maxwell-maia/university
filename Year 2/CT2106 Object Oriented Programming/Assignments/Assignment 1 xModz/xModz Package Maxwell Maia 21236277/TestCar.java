
/**
 * This class will be used to create objects and call functions to test the functionality of the xModz program.
 *
 * @author Maxwell Maia
 * @version 2.1
 */
public class TestCar
{ 
    public static void main(String[] args)
    {
        //Create a car object.
        //Also pass the attributes for the engine and wheel as arguments.
        Car nissan = new Car("R34", "Brroooommm", 43, "Toyo15", 15);
        Car toyota = new Car ("C-HR", "Hybrid Electric", 25, "Bridgetone13", 13);
        Car subaru = new Car("Impreza", "Brrrr", 64, "OneOfEach12", 12);

        
        //Set the fuel. This is passed into car,
        //then from the car into the engine.
        nissan.setFuel(100);
        toyota.setFuel(100);
        subaru.setFuel(100);

        
        
        System.out.println("Nissan");
        //Print the fuel status using a getter.
        System.out.printf("Current fuel: %.2f\n", nissan.getFuel());
        //Method to make the car drive, using up all its fuel.
        nissan.drive();
        //Method that prints the fields of the car to the terminal.
        nissan.printState();
        
        System.out.println("Toyota");
        System.out.printf("Current fuel: %.2f\n", toyota.getFuel());
        toyota.drive();
        toyota.printState();
        
        
        System.out.println("Subaru");
        System.out.printf("Current fuel: %.2f\n", subaru.getFuel());
        subaru.drive();
        subaru.printState();
        
        
        //Refilling fuel... going on another trip...
        subaru.setFuel(80);
        System.out.println("Subaru 2nd Trip");
        //Print the fuel status using a getter.
        System.out.printf("Current fuel: %.2f\n", subaru.getFuel());
        
        //The car drives a second time, using up all its fuel.
        subaru.drive();
        
        //Method that prints the fields of the car to the terminal.
        subaru.printState();
    }
}
