
/**
 * Trip
 * 
 * Store and allow retrieval of information about a Trip
 * Able to set a new value for availableSeats (does not allow availableSeats variable to be set to a negative number)
 *
 * @author Maxwell Maia
 * @student id 21236277
 * @version 1.0
 */
public class Trip
{
    int tripID;
    String startingLocation;
    String destination;
    String dateOfDeparture;
    String timeOfDeparture;
    String dateOfArrival;
    String timeOfArrival;
    double fare;
    int availableSeats;
    
    
    public Trip(int tripID, String startingLocation, String destination, String dateOfDeparture, String timeOfDeparture, String dateOfArrival, String timeOfArrival, double fare, int availableSeats)
    {
        this.tripID = tripID;
        this.startingLocation = startingLocation;
        this.destination = destination;
        this.dateOfDeparture = dateOfDeparture;
        this.timeOfDeparture = timeOfDeparture;
        this.dateOfArrival = dateOfArrival;
        this.timeOfArrival = timeOfArrival;
        this.fare = fare;
        this.availableSeats = availableSeats;
    }
    
    //Getters for all fields.
    public int getTripID()
    {
        return tripID;
    }
    
    public String getStartingLocation()
    {
        return startingLocation;
    }
    
    public String getDestination()
    {
        return destination;
    }
    
    public String getDateOfDeparture()
    {
        return dateOfDeparture;
    }
    
    public String getTimeOfDeparture()
    {
        return timeOfDeparture;
    }
    
    public String getDateOfArrival()
    {
        return dateOfArrival;
    }
    
    public String getTimeOfArrival()
    {
        return timeOfArrival;
    }
    
    public double getFare()
    {
        return fare;
    }
    
    public int getAvailableSeats()
    {
        return availableSeats;
    }
    
    //Setter for available seats. Validity check for changing data.
    public boolean setAvailableSeats(int newAvailableSeats)
    {
        if(newAvailableSeats >= 0)
        {
            availableSeats = newAvailableSeats;
            return true;
        }
        
        //Return false and do not change seats if the new number of available seats is negative.
        return false;
    }
}
