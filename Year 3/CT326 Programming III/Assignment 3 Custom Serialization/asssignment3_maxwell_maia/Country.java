package assignment3_maxwell_maia;

import java.io.Serializable;

/**
 * This class defines the country object.
 * @author Maxwell Maia
 * @student_id 21236277
 */
public class Country implements Serializable
{
	private String countryName;
	
	public Country(String countryName)
	{
		this.countryName = countryName;
	}
	
	/**
	 * 
	 * @return countryName the name of the country as a string
	 */
	public String getCountryName()
	{
		return countryName;
	}
	
	@Override
	public String toString()
	{
		return countryName;
	}
}
