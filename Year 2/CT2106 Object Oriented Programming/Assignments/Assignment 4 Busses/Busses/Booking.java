
/**
 * Booking
 * 
 * Store and allow retrieval of information about a booking
 * Calculates the total cost of a booking
 *
 * @author Maxwell Maia
 * @student id 21236277
 * @version 1.0
 */
public class Booking
{
    Trip trip;
    int requestedSeats;
    
    
    public Booking(Trip trip, int requestedSeats)
    {
        this.trip = trip;
        this.requestedSeats = requestedSeats;
    }
    
    
    //Getters for all fields.
    public Trip getTrip()
    {
        return trip;
    }
    
    public int getRequestedSeats()
    {
        return requestedSeats;
    }
    
    //Calculates and returns the total cost of the booking.
    public double getTotalCost()
    {
        return trip.getFare() * requestedSeats;
    }
}
