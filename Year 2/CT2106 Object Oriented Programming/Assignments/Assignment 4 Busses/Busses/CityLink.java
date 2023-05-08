import java.util.ArrayList;
/**
 * CityLink
 * 
 * Is-a Vendor
 * Provides concrete definitions of Vendor class.
 * Stores an ArrayList of all of the trips this vendor offers.
 * Provides concrete definitions of the following methods:
 * displayTrips() display all information of all its trips.
 * getTrip() return a trip by searching through its ArrayList for a trip with a specific ID.
 * makeBooking() make a booking, check that there are enough seats available.
 *
 * @author Maxwell Maia, 21236277
 * @version 1.0
 */
public class CityLink implements Vendor
{
    private ArrayList<Trip> trips = new ArrayList();
    
    /**
     * Constructor for objects of class BusEireann
     */
    public CityLink()
    {
        //Add trips on initialization. (Assignment says that hardcoding this is allowed).
        Trip trip1 = new Trip(2010, "Galway", "Cork", "2022/11/25", "22:00", "2022/11/26", "01:10", 12.50, 60);
        Trip trip2 = new Trip(6124, "Galway", "Limerick", "2022/12/22", "16:15", "2022/12/22", "17:30", 6.50, 1);
        
        trips.add(trip1);
        trips.add(trip2);
    }

    public String displayTrips()
    {
        String ret = "";
        ret += "\n-------------------------------------";
        //Loop through the trip arraylist and compile a string to display all information on all trips.
        for(Trip trip : trips)
        {
            ret += "\nCompany: City-Link";
            ret += "\nTrip ID: " + trip.getTripID();
            ret += "\nOrigin: " + trip.getStartingLocation();
            ret += "\nDestination: " + trip.getDestination();
            ret += "\nDeparture Date: " + trip.getDateOfDeparture();
            ret += "\nDeparture Time: " + trip.getTimeOfDeparture();
            ret += "\nArrival Date: " + trip.getDateOfArrival();
            ret += "\nArrival Time: " + trip.getTimeOfArrival();
            ret += "\nFare: €" + trip.getFare() + " per passenger";
            ret += "\nCurrently Available seats: " + trip.getAvailableSeats();
            ret += "\n";
        }
        ret += "\n-------------------------------------";
        return ret;
    }
    
    public Trip getTrip(int id)
    {
        //Search through the trip arraylist and return the trip that matches the trip id entered.
        for(Trip trip : trips)
        {
            if(id == trip.getTripID())
            {
                return trip;
            }
        }
        //If the trip with a matching ID has not been found, return null.
        return null;
    }
    
    /* Make a booking.
     * Check that there are enough seats on trip to accommodate the booking.
     * Update seats if there are enough seats for the booking.
     * Return a boolean that is true if the booking was a success.
     */
    public boolean makeBooking(Booking booking)
    {
        //Get available seats
        int availableSeats = booking.getTrip().getAvailableSeats();
        
        //Get requested seats (number of passengers in booking)
        int requestedSeats = booking.getRequestedSeats();
        
        //If there are enough seats to accomdate the customer's request, the booking will be successful.
        if(availableSeats >= requestedSeats)
        {
            //There are enough seats.
            //Change new amount of available seats and return "true" to indicated a successful booking.
            booking.getTrip().setAvailableSeats(availableSeats - requestedSeats);
            return true;
        }
        
        //Else there are not enough seats, so the booking was not a success.
        //Return "false" to indicate an unsuccessful booking.
        return false;
    }
}
