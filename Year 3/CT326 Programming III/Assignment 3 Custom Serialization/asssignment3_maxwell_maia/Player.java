package assignment3_maxwell_maia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class defines the Player object and has information regarding a player's profile
 * @author Maxwell Maia
 * @student_id 21236277
 */

public class Player implements Serializable
{
	private String id;
	private String username;
	private Country country;
	private LocalDate joinDate;
	private transient List<Achievement> achievements;
	
	public Player(String id, String username, Country country, LocalDate joinDate, List<Achievement> achievements)
	{
		this.id = id;
		this.username = username;
		this.country = country;
		this.joinDate = joinDate;
		this.achievements = achievements;
	}
	
	/**
	 * 
	 * @return id of the player
	 */
	public String getId()
	{
		return id;
	}
	
	/**
	 * 
	 * @return username
	 */
	public String getUsername()
	{
		return username;
	}
	
	/**
	 * 
	 * @return country object. Country object has the name of the country in it as a string
	 */
	public Country getCountry()
	{
		return country;
	}
	
	/**
	 * 
	 * @return joinDate
	 */
	public LocalDate getJoinDate()
	{
		return joinDate;
	}
	
	/**
	 * 
	 * @return list of the achievements that this player has achieved
	 */
	public List<Achievement> getAchievements()
	{
		return achievements;
	}
	
	/**
	 * 
	 * @param achievements sets the list of achievements
	 */
	public void setAchievements(List<Achievement> achievements)
	{
		this.achievements = achievements;
	}
	
	//ToString
	@Override
	public String toString()
	{
		return id + " " + username + " " + country.toString() + " " + joinDate.toString() + ".";
	}
	
	//writeObject
	private void writeObject(ObjectOutputStream os) throws IOException
	{
		os.defaultWriteObject();
		
		PrintWriter out = null;
		
		String outString;
		
		try
		{
			out = new PrintWriter("achievements.csv");

			for(Achievement a : achievements)
			{
				//Build String
				outString = String.format("%s,%s,%s,%s%n", id, a.getAchievementName(), a.getDescription(), a.getDateOfAward());
				
				//Put in csv file
				out.format("%s", outString);
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (out != null)
				out.close();
		}
	}
	
	//readObject
	private void readObject(ObjectInputStream is) throws IOException
	{
		BufferedReader in = null;
		String line;
		Scanner scanner;
		Achievement a;
		
		String achievementName;
		String description;
		LocalDate dateOfAward;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
		
		try
		{
			is.defaultReadObject();
			
			//Create list
			List<Achievement> ach = new ArrayList<>();
			
			in = new BufferedReader(new FileReader("achievements.csv"));
			
			//Read the achievements one line at a time
			while((line = in.readLine()) != null)
			{
				scanner = new Scanner(line);
				scanner.useDelimiter(",");
				
				if(scanner.hasNext() && id.equals(scanner.next()))
				{
					achievementName = scanner.next();
					description = scanner.next();
					dateOfAward = LocalDate.parse(scanner.next());
					
					//Create the achievement object
					a = new Achievement(achievementName, description, dateOfAward);
					
					//Add the achievement to the list
					ach.add(a);
				}
			}
			
			
			//Set the list of achievement
			this.setAchievements(ach);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
}
