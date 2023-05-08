
/**
 * Customer - set and retrieve information about the Customer.
 *
 * @author Maxwell Maia
 * @version 1.0
 */
public class Customer
{
    private String firstName;
    private String surName;
    private String emailAddress;
    private final long customerId;
    
    
    public Customer(String firstName, String surName, String emailAddress)
    {
        this.firstName = firstName;
        this.surName = surName;
        this.emailAddress = emailAddress;
        this.customerId = generateCustomerId(); //Creates a customer ID
    }
    
    public long getId()
    {
        return customerId;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getSurName()
    {
        return surName;
    }
    
    public String getEmailAddress()
    {
        return emailAddress;
    }
    
    public long generateCustomerId()
    {
        return (long)(Math.random() * 9999L);
    }
}
