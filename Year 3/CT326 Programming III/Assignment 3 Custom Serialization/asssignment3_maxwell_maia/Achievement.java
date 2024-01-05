package assignment3_maxwell_maia;

import java.time.LocalDate;

/**
 * This class defines the achievement object.
 * @author Maxwell Maia
 * @student_id 21236277
 */

public class Achievement
{
	private String achievementName;
	private String description;
	private LocalDate dateOfAward;
	
	public Achievement(String achievementName, String description, LocalDate dateOfAward)
	{
		this.achievementName = achievementName;
		this.description = description;
		this.dateOfAward = dateOfAward;
	}
	
	/**
	 * 
	 * @return achievement name
	 */
	public String getAchievementName()
	{
		return achievementName;
	}
	
	/**
	 * 
	 * @return description of the achievement
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * 
	 * @return date the achievement was awarded to the player
	 */
	public LocalDate getDateOfAward()
	{
		return dateOfAward;
	}
	
	/**
	 * @return boolean indicating that an achievement is equal to another achievement
	 */
	public boolean equals(Object a)
	{
		if(this.achievementName.equals(((Achievement)a).getAchievementName()) && this.description.equals(((Achievement)a).getDescription()) && this.dateOfAward.equals(((Achievement)a).getDateOfAward()))
			return true;
		return false;
	}
	
	/**
	 * @return toString of all information of the achievement
	 */
	@Override
	public String toString()
	{
		return achievementName + " " + description + " " + dateOfAward.toString() + ".";
	}
}
