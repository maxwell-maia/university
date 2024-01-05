//Maxwell Maia
//21236277

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

class TestNCTBooking{

	//valid booking testing test centre query works
	@Test
	void testTestCentre() throws InvalidDateTimeException
	{
		TestCentre centre1 = new TestCentre("Ballinasloe", "Unit 9, Pollboy Industrial Estate, Ballinasloe, Galway H53 NW94");
		NCTBooking booking1 = new NCTBooking("16-WH-59741", centre1, LocalDateTime.of(2023, Month.SEPTEMBER, 22, 8, 0));
		TestCentre centreFromBooking = booking1.queryTestCentre();
		assertEquals(centreFromBooking, centre1, "Test centre query successful");
	}
	
	//valid booking testing registration number query works
	@Test
	void testRegNum_NoEditRegNum() throws InvalidDateTimeException
	{
		String regNumQueryInput = "16-WH-59741";
		TestCentre centre1 = new TestCentre("Ballinasloe", "Unit 9, Pollboy Industrial Estate, Ballinasloe, Galway H53 NW94");
		NCTBooking booking1 = new NCTBooking(regNumQueryInput, centre1, LocalDateTime.of(2023, Month.SEPTEMBER, 22, 8, 0));
		String regNumQueryResult = booking1.queryRegNum();
		assertEquals(regNumQueryResult, regNumQueryInput, "Registration number query successful");
	}

	
	//valid booking where registration number is edited
	@Test
	void testRegNum_EditRegNum() throws InvalidDateTimeException
	{
		String regNumQueryInput = "16-WH-59741";
		String regNumEdit = "21-DG-95843";
		TestCentre centre1 = new TestCentre("Ballinasloe", "Unit 9, Pollboy Industrial Estate, Ballinasloe, Galway H53 NW94");
		NCTBooking booking1 = new NCTBooking(regNumQueryInput, centre1, LocalDateTime.of(2023, Month.SEPTEMBER, 22, 8, 0));
		booking1.editRegNum(regNumEdit);
		String regNumQueryResult = booking1.queryRegNum();
		assertEquals(regNumQueryResult, regNumEdit, "Registration number edit and query successful");
	}
	
	
	//valid booking created with no date specified
	@Test
	void testCreateBooking_NoDateTimeSpecified_ExternalAPI()
	{
		LocalDateTime webserviceDateTime = LocalDateTime.of(2023, Month.SEPTEMBER, 30, 12, 0);
		
		NCTBookingSlotWebservice testService = new NCTBookingSlotWebservice() {
			@Override
			public LocalDateTime getBookingDateTime(TestCentre testCentre) {
				return webserviceDateTime;
			}
		};
		
		//Create booking with no date/time specified. Our web service will create should set this date time
		TestCentre centre1 = new TestCentre("Ballinasloe", "Unit 9, Pollboy Industrial Estate, Ballinasloe, Galway H53 NW94");
		String regNumQueryInput = "16-WH-59741";
		NCTBooking booking1 = new NCTBooking(regNumQueryInput, centre1);
		
		//Set the web service
		booking1.setSlotWebservice(testService);
		
		//Tell the booking object to obtain a date/time from the web service
		//I am not sure how to do this in the NCTBooking class because "setSlotWebservice(NCTBookingSlotWebservice service)" needs...
		//to be run first (as seen above) and this isn't possible until the object is instantiated. So I cannot make the NCTBooking....
		//get a date/time in the constructor.
		booking1.setDateTimeExternal();
		
		//Assert equals the date/time of the booking, to the date/time the web service will provide
		assertEquals(booking1.queryDateTime(), webserviceDateTime, "Date/time successfully set by external API");
	}
	
	//invalid date time is given
    @Test
    void testInvalidDateTimeException() {
        TestCentre test = new TestCentre("Ballinasloe", "Unit 9, Pollboy Industrial Estate, Ballinasloe, Galway H53 NW94");
        LocalDateTime dateTime = LocalDateTime.of(2023, Month.SEPTEMBER, 12, 8, 0);

        assertThrows(InvalidDateTimeException.class, () -> {
            new NCTBooking("16-WH-59741", test, dateTime);
        });
    }
    
    //valid booking testing to String is as expected
    @Test
    void test_toString() throws InvalidDateTimeException {
        TestCentre testCentre = new TestCentre("Ballinasloe", "Unit 9, Pollboy Industrial Estate, Ballinasloe, Galway H53 NW94");
        LocalDateTime dateTime = LocalDateTime.of(2023, Month.SEPTEMBER, 22, 8, 0);
        NCTBooking booking1 = new NCTBooking("16-WH-59741", testCentre, dateTime);

        String expected = "Booking ID Number: 3\n" +
                "Registration Number: 16-WH-59741\n" +
                "Centre: Ballinasloe\n" +
                "Address: Unit 9, Pollboy Industrial Estate, Ballinasloe, Galway H53 NW94\n" +
                "Date & Time: 2023-09-22T08:00\n";

        assertEquals(expected, booking1.toString());
    }
}