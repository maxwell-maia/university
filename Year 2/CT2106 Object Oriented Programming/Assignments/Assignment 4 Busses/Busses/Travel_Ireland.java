
/**
 * Write a description of class Travel_Ireland here.
 *
 * @author Maxwell Maia
 * @student id 21236277
 * @version 1.0
 */
public class Travel_Ireland
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to Travel Ireland\n\nAvailable trips:");
        
        
        //Vendor objects.
        //Create a vendor object (BusEireann). It's array list is populated with default trips on initilization.
        Vendor be = new BusEireann();
        //Create a vendor object (CityLink). It's array list is populated with default trips on initilization.
        Vendor cl = new CityLink();
        //Create a vendor object (GoBus). It's array list is populated with default trips on initilization.
        Vendor gb = new GoBus();
        
        //Put vendors into a vendor array.
        //Able to do this because each vendor is a Vendor.
        Vendor[] vendorArray = {be, cl, gb};
        int vendArrSize = 3;
        
        //Display all available trips from all vendors.
        for(int i = 0; i < vendArrSize; i++)
        {
            System.out.println(vendorArray[i].displayTrips());
        }
        
        
        //BOOKINGS
        //Initialize variables
        Trip selectedTrip = null;
        Booking booking = null;
        Boolean success = false;
        
        //BUS-EIREANN Booking
        //Get the trip object that the customer wants. e.g. trip with id 1010
        selectedTrip = be.getTrip(1010);
        
        //Test code
        //This is just code that helps verify if a booking should be successful or not.
        System.out.println("\n\nAttempting to book 10 passengers from on Bus-Eireann trip ID: 1010.");
        System.out.println("There are " + selectedTrip.getAvailableSeats() + " seats available on that trip.");
        if(selectedTrip.getAvailableSeats() >= 10)
        {
            System.out.println("There are enough seats.\nThe booking should be successful.");
        }
        else
        {
            System.out.println("There are not enough seats.\nThe booking should not be successful.");
        }
        
        //Make a booking with the selected trip and the number of seats the customer wants to book.
        booking = new Booking(selectedTrip, 10);
        success = be.makeBooking(booking);
        
        //Display appropriate output based on whether the booking was successful or not.
        if(success)
        {
            System.out.println("\nBooking successful.");
            System.out.println("===============================================================");
            System.out.println("Number of passengers: " + booking.getRequestedSeats());
            System.out.println("Trip Details: ["+booking.getTrip().getStartingLocation()+"] to ["+booking.getTrip().getDestination()+"]");
            System.out.println("Trip ID: " + booking.getTrip().getTripID());
            System.out.println("Total cost: €‎" + booking.getTotalCost());
            System.out.println("===============================================================");
        }
        else
        {
            System.out.println("\n\nBooking failed!");
        }
        
        
        
        
        //CITY-LINK Booking
        //Get the trip object that the customer wants. e.g. trip with id 1010
        selectedTrip = cl.getTrip(6124);
        
        //Test code
        //This is just code that helps verify if a booking should be successful or not.
        System.out.println("\n\nAttempting to book 1 passenger from on City-Link trip ID: 6124.");
        System.out.println("There are " + selectedTrip.getAvailableSeats() + " seats available on that trip.");
        if(selectedTrip.getAvailableSeats() >= 1)
        {
            System.out.println("There are enough seats.\nThe booking should be successful.");
        }
        else
        {
            System.out.println("There are not enough seats.\nThe booking should not be successful.");
        }
        
        //Make a booking with the selected trip and the number of seats the customer wants to book.
        booking = new Booking(selectedTrip, 1);
        success = cl.makeBooking(booking);
        
        //Display appropriate output based on whether the booking was successful or not.
        if(success)
        {
            System.out.println("\nBooking successful.");
            System.out.println("===============================================================");
            System.out.println("Number of passengers: " + booking.getRequestedSeats());
            System.out.println("Trip Details: ["+booking.getTrip().getStartingLocation()+"] to ["+booking.getTrip().getDestination()+"]");
            System.out.println("Trip ID: " + booking.getTrip().getTripID());
            System.out.println("Total cost: €‎" + booking.getTotalCost());
            System.out.println("===============================================================");
        }
        else
        {
            System.out.println("\n\nBooking failed!");
        }
        
        
        
        
        //GOBUS Booking
        //Get the trip object that the customer wants. e.g. trip with id 1010
        selectedTrip = gb.getTrip(7124);
        
        //Test code
        //This is just code that helps verify if a booking should be successful or not.
        System.out.println("\n\nAttempting to book 2 passengers from on Bus-Eireann trip ID: 7124.");
        System.out.println("There are " + selectedTrip.getAvailableSeats() + " seats available on that trip.");
        if(selectedTrip.getAvailableSeats() >= 2)
        {
            System.out.println("There are enough seats.\nThe booking should be successful.");
        }
        else
        {
            System.out.println("There are not enough seats.\nThe booking should not be successful.");
        }
        
        //Make a booking with the selected trip and the number of seats the customer wants to book.
        booking = new Booking(selectedTrip, 2);
        success = gb.makeBooking(booking);
        
        //Display appropriate output based on whether the booking was successful or not.
        if(success)
        {
            System.out.println("\nBooking successful.");
            System.out.println("===============================================================");
            System.out.println("Number of passengers: " + booking.getRequestedSeats());
            System.out.println("Trip Details: ["+booking.getTrip().getStartingLocation()+"] to ["+booking.getTrip().getDestination()+"]");
            System.out.println("Trip ID: " + booking.getTrip().getTripID());
            System.out.println("Total cost: €‎" + booking.getTotalCost());
            System.out.println("===============================================================");
        }
        else
        {
            System.out.println("\n\nBooking failed!");
        }
        
        
        //Display all available trips from all vendors.
        for(int i = 0; i < vendArrSize; i++)
        {
            System.out.println(vendorArray[i].displayTrips());
        }
    }
}
