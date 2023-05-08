
/**
 * Address - set and retrieve information about an Address.
 *
 * @author Maxwell Maia
 * @version 1.0
 */
public class Address
{
    private String street;
    private String town;
    private String county;
    private String country;
    private String postcode;
    
    
    
    public Address(String street, String town, String county, String country, String postcode)
    {
        this.street = street;
        this.town = town;
        this.county = county;
        this.country = country;
        this.postcode = postcode;
    }
    
    public String getStreet()
    {
        return street;
    }
    
    @Override
    public String toString()
    {
        String out = "Street: " + street + "\nTown: " + town + "\nCounty: " + county + "\nCountry: " + country + "\nPostcode: " + postcode;
        
        return out;
    }
}
