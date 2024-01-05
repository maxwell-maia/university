//Maxwell Maia
//21236277

import java.time.LocalDateTime;
import java.time.Month;

public class NCTBooking implements NCTBookingSlotWebservice
{
	//static nextBookingID will increase in the constructor to keep the ID's unique
    private static int nextBookingID = 1;

    //declare instance variables
    private int bookingID;
	private String regNum;
	private TestCentre centre;
	private LocalDateTime dateTime;
	
	//Web service to give date and time for a given test centre
	private NCTBookingSlotWebservice slotService;
	
	//establish web service in class
	public void setSlotWebservice(NCTBookingSlotWebservice service)
	{
		this.slotService = service;
	}
	
	//constructor for specified date and time bookings
	public NCTBooking(String regNum, TestCentre centre, LocalDateTime dateTime) throws InvalidDateTimeException
	{
		//User defined Exception. Throws exception if the date is in the past.
		if (dateTime.isBefore(LocalDateTime.now()))
		{
			throw new InvalidDateTimeException("Cannot create a booking with a Date/Time in the past");
		}
		this.regNum = regNum;
		this.centre = centre;
		this.dateTime = dateTime;
		this.bookingID = nextBookingID++;
	}
	
	//overloaded constructor
	//constructor for unspecified date and time bookings (date and time will come from an external API web service)
	public NCTBooking(String regNum, TestCentre centre)
	{
		this.regNum = regNum;
		this.centre = centre;
		this.bookingID = nextBookingID++;
	}
	
	//method to get the date and time from external API web service
	public void setDateTimeExternal()
	{
		this.dateTime = slotService.getBookingDateTime(this.centre);
	}
	
	//override method to get the date and time from external API web service
	@Override
	public LocalDateTime getBookingDateTime(TestCentre testCentre)
	{
		return LocalDateTime.of(2023, Month.SEPTEMBER, 30, 12, 0);
	}

	//getter for testCentre
	public TestCentre queryTestCentre()
	{
		return centre;
	}
	
	//getter for regNum
	public String queryRegNum()
	{
		return regNum;
	}
	
	//setter for regNum
	public void editRegNum(String newRegNum)
	{
		regNum = newRegNum;
	}
	
	//getter for dateTime
	public LocalDateTime queryDateTime()
	{
		return dateTime;
	}
	
	//getter for bookingID
	public int getBookingID()
	{
        return bookingID;
    }
	
	//my toString method to format booking information
    @Override
    public String toString()
    {
        return "Booking ID Number: " + bookingID + "\n" +
                "Registration Number: " + regNum + "\n" +
                "Centre: " + centre.getName() + "\n" +
                "Address: " + centre.getAddress() + "\n"  +
                "Date & Time: " + dateTime + "\n";
    }
}