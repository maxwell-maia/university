
/**
 * @author Maxwell Maia
 * Class used to test the program.
 */
public class AnimalTest
{
    public AnimalTest()
    {
        
    }
    
    public static void main(String[] args)
    {
        //Create an object of the test class to run tests on.
        AnimalTest test = new AnimalTest();
        
        //Tests
        test.testToString(); //test1: demonstrate toString functionality
        test.testEquals(); //test2: demonstrate equals functionality
        test.testMove(); //demonstrate move and sing methods wor
    }
    
    //test1
    public void testToString()
    {
        System.out.println("=========Test 1=========");
        System.out.println("Test toString in all classes.\n\n");

        Animal[] animals = new Animal[4];
        //This declares and allocates memory for an array of Animal references with 4 spaces
        
        
        //Fill the Animals array with objects
        animals[0] = new Canary("Bluey");
        animals[1] = new Ostrich("Osty");
        animals[2] = new Shark("Hark");
        animals[3] = new Trout("Smekko");
        
        //For each element in the animal array
        // print out what is inside that element (toString)
        for(Animal animal : animals)
        {
            System.out.println(animal); //call toString() the animal
        }
        
        
    }
    
    //test2
    public void testEquals()
    {
        System.out.println("\n\n\n=========Test 2=========");
        System.out.println("Test the equals method in all classes.\n\n");

        
        //CANARY
        //Create an array for canaries
        Canary[] canary = new Canary[3];
        
        //Create some animals
        canary[0] = new Canary("jOhn");
        canary[1] = new Canary("jOhn");
        canary[2] = new Canary("Rizz");
        
        //.equals checking for Canary
        // equal
        System.out.println("Canary called " + canary[0].getName() +" vs " + canary[1].getName() + " are they equal? ");
        System.out.println(canary[0].equals(canary[1]) ? "true" : "false");
        // not equal
        System.out.println("Canary called " + canary[0].getName() +" vs " + canary[2].getName() + " are they equal? ");
        System.out.println(canary[0].equals(canary[2]) ? "true" : "false");
        
        //Formatting
        System.out.println("\n\n");
        
        
        
        //OSTRICH
        //Create an array for ostriches
        Ostrich[] ostrich = new Ostrich[3];
        
        //Create some animals
        ostrich[0] = new Ostrich("Maxwell");
        ostrich[1] = new Ostrich("Maxwell");
        ostrich[2] = new Ostrich("Ganyu");
        
        //.equals checking for Canary
        // equal
        System.out.println("Ostrich called " + ostrich[0].getName() +" vs " + ostrich[1].getName() + " are they equal? ");
        System.out.println(ostrich[0].equals(ostrich[1]) ? "true" : "false");
        // not equal
        System.out.println("Ostrich called " + ostrich[0].getName() +" vs " + ostrich[2].getName() + " are they equal? ");
        System.out.println(ostrich[0].equals(ostrich[2]) ? "true" : "false");
        
        //Formatting
        System.out.println("\n\n");
        
        
        
        //SHARK
        //Create an array for ostriches
        Shark[] shark = new Shark[3];
        
        //Create some animals
        shark[0] = new Shark("Yanfei");
        shark[1] = new Shark("Yanfei");
        shark[2] = new Shark("BOBBLES");
        
        //.equals checking for Canary
        // equal
        System.out.println("Shark called " + shark[0].getName() +" vs " + shark[1].getName() + " are they equal? ");
        System.out.println(shark[0].equals(shark[1]) ? "true" : "false");
        // not equal
        System.out.println("Shark called " + shark[0].getName() +" vs " + shark[2].getName() + " are they equal? ");
        System.out.println(shark[0].equals(shark[2]) ? "true" : "false");
        
        //Formatting
        System.out.println("\n\n");
        
        
        
        
        //TROUT
        //Create an array for ostriches
        Trout[] trout = new Trout[3];
        
        //Create some animals
        trout[0] = new Trout("Ahri");
        trout[1] = new Trout("Ahri");
        trout[2] = new Trout("Redox");
        
        //.equals checking for Canary
        // equal
        System.out.println("Trout called " + trout[0].getName() +" vs " + trout[1].getName() + " are they equal? ");
        System.out.println(trout[0].equals(trout[1]) ? "true" : "false");
        // not equal
        System.out.println("Trout called " + trout[0].getName() +" vs " + trout[2].getName() + " are they equal? ");
        System.out.println(trout[0].equals(trout[2]) ? "true" : "false");
        
        //Formatting
        System.out.println("\n\n");
        
        
        
        
        
        //Are different object types equal
        System.out.println("\nChecking that equals methods work for different types\n");
        
        Animal[] animals = new Animal[4];
        
        animals[0] = new Trout("Ahri");
        animals[1] = new Shark("Yanfei");
        animals[2] = new Canary("Rizz");
        animals[3] = new Ostrich("Maxwell");
        
        //.equals checking for different types
        // equal
        System.out.println("Trout called '" + animals[0].getName() +"' vs Trout called '" + animals[0].getName() + "' are they equal? ");
        System.out.println(animals[0].equals(animals[0]) ? "true" : "false");
        // not equal
        System.out.println("Trout called '" + animals[0].getName() +"' vs Shark called '" + animals[1].getName() + "' are they equal? ");
        System.out.println(animals[0].equals(animals[1]) ? "true" : "false");
        // not equal
        System.out.println("Ostrich called '" + animals[3].getName() +"' vs Trout called '" + animals[0].getName() + "' are they equal? ");
        System.out.println(animals[3].equals(animals[0]) ? "true" : "false");
        // not equal
        System.out.println("Ostrich called '" + animals[3].getName() +"' vs Canary called '" + animals[2].getName() + "' are they equal? ");
        System.out.println(animals[3].equals(animals[2]) ? "true" : "false");
        
    }
    
    public void testMove()
    {
        System.out.println("\n\n\n=========Test 3=========");
        System.out.println("Test move() method in all classes.\n\n");

        //Fill the Animals array with objects
        Animal[] animals = new Animal[4];        
        animals[0] = new Canary("Bluey");
        animals[1] = new Ostrich("Osty");
        animals[2] = new Shark("Hark");
        animals[3] = new Trout("Smekko");
        
        //loop through animal array
        for(Animal animal : animals)
        {
            animal.move(5); //test move() method
            System.out.println(animal); //toString
        }
    }
}

