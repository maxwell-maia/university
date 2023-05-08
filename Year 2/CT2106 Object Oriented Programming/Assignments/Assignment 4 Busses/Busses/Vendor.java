
/**
 * Vendor
 * 
 * Provides function definitions that each vendor has to use
 * 
 * @author Maxwell Maia
 * @student id 21236277
 * @version 1.0
 */
public interface Vendor
{
    
    //Display all available trips and all the information regarding them
    // e.g. ID, Origin, Destination... , currently available seats.
    // (used to print all of the available fields of all Trip objects in an ArrayList).
    public String displayTrips();
    
    //Search for (in the ArrayList) and return the Trip object, given an id.
    //Returns null if no object is found with the id that is input.
    public Trip getTrip(int id);
    
    //Make a booking.
    //Will check that the available seats is more (or equal to) the number of passengers (requestedSeats).
    //Returns true if the booking was made.
    //Returns a false if the booking was not made. invalid details:
    // e.g. more seats booked than are available.
    public boolean makeBooking(Booking booking);
}
