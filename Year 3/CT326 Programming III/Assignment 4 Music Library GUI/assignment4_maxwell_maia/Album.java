package assignment4_maxwell_maia;

import java.util.List;

import javax.swing.ImageIcon;

/**
 * This class represents an album.
 * 
 * @author Maxwell Maia, 21236277
 *
 */

public class Album
{
	private String albumName;
	private String artistName;
	private ImageIcon coverImage;
	private List<String> tracklist;
	
	public Album()
	{
		
	}
	
	public Album(String albumName, String artistName, ImageIcon coverImage, List<String> tracklist)
	{
		this.albumName = albumName;
		this.artistName = artistName;
		this.coverImage = coverImage;
		this.tracklist = tracklist;
	}

	//Getters and Setters
	
	/**
	 * @return the albumName
	 */
	public String getAlbumName()
	{
		return albumName;
	}

	/**
	 * @param albumName the albumName to set
	 */
	public void setAlbumName(String albumName)
	{
		this.albumName = albumName;
	}

	/**
	 * @return the artistName
	 */
	public String getArtistName()
	{
		return artistName;
	}

	/**
	 * @param artistName the artistName to set
	 */
	public void setArtistName(String artistName)
	{
		this.artistName = artistName;
	}

	/**
	 * @return the coverImage
	 */
	public ImageIcon getCoverImage()
	{
		return coverImage;
	}

	/**
	 * @param coverImage the coverImage to set
	 */
	public void setCoverImage(ImageIcon coverImage)
	{
		this.coverImage = coverImage;
	}

	/**
	 * @return the tracklistFilePath
	 */
	public List<String> getTracklist()
	{
		return tracklist;
	}

	/**
	 * @param tracklistFilePath the tracklistFilePath to set
	 */
	public void setTracklist(List<String> tracklist)
	{
		this.tracklist = tracklist;
	}
	
	
}
