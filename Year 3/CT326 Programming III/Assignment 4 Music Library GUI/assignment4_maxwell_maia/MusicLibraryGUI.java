package assignment4_maxwell_maia;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This class is the JFrame for a music library.
 * Albums are shown with their covers on the album screen.
 * Clicking an album will show the track list of that album.
 * You can click back to return to the album screen.
 * 
 * @author Maxwell Maia, 21236277
 * 
 */
public class MusicLibraryGUI extends JFrame
{
	private List<Album> albums = new ArrayList<>();
	
	private Album selectedAlbum;
	
	/*
	 * Instance variables for the form
	 */
	private JPanel albumPanel;
	
	private JPanel tracklistPanel;
	
	public MusicLibraryGUI()
	{
		//Set the window size
		setSize(800, 600);
		
		//Exit the app when closing the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Load albums from text files
		loadAlbums();
		
		//Create a panel with all albums
		albumPanel = createAlbumPanel();
		
		//Create Panel for tracklist
		tracklistPanel = createTracklistPanel();
		
		//Add the album panel
		add(albumPanel);
		
		//Make the JFrame visible
		setVisible(true);		
	}
	
	public static void main(String[] args)
	{
		new MusicLibraryGUI();
	}
	
	/**
	 * Read from text files and populate albums list
	 */
	public void loadAlbums()
	{
		String artistName = "";
		String albumName = "";
		ImageIcon coverImage = null;
		List<String> tracklist = null;
		
		Album a;
		
		BufferedReader in = null;
		String line;
		Scanner scanner;
		
		BufferedReader inTracklist = null;
		String lineTracklist;
		
		try
		{
			in = new BufferedReader(new FileReader("music_library_resources/music_library.txt"));
			
			//Read the information on the albums one line at a time
			while((line = in.readLine()) != null)
			{
				//Read and store album information in the line
				scanner = new Scanner(line);
				scanner.useDelimiter(", ");
				
				if(scanner.hasNext())
				{
					//Get artist name
					artistName = scanner.next();
					//Get album name
					albumName = scanner.next();
					//Get the image icon...
					String imageFilePath = "music_library_resources/" + scanner.next();
					BufferedImage bi = ImageIO.read(new File(imageFilePath));
					Image dimg = bi.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
					coverImage = new ImageIcon(dimg);
					//Get the tracklist...
					tracklist = new ArrayList<>();
					//Reading the tracklist from the file
					String tracklistFilePath = "music_library_resources/" + scanner.next();
					inTracklist = new BufferedReader(new FileReader(tracklistFilePath));
					while((lineTracklist = inTracklist.readLine()) != null)
					{
						tracklist.add(lineTracklist);
					}
				}
				//Create an album with the information that has just been read
				a = new Album(albumName, artistName, coverImage, tracklist);
				
				//Add the album to the album list
				albums.add(a);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates panel with albums as buttons with their cover images on them
	 * Adds action listener to each that selects the album and shows the track list
	 * @return JPanel with albums
	 */
	public JPanel createAlbumPanel()
	{
		JPanel panel = new JPanel(new GridLayout(0, 3, 30, 10));
		
		//Make a button for every album
		for(Album a : albums)
		{
			//The button has the album name and artist attached to it. This helps uniquely identify it
			JButton albumButton = new JButton(); //a.getAlbumName() + " by " + a.getArtistName()
			albumButton.setIcon(a.getCoverImage());
			albumButton.setActionCommand(a.getAlbumName() + " by " + a.getArtistName());
			/*
			 * Add action listener to each button that when pressed
			 * selects that album, creates a tracklist panel with
			 * the tracks of that album and switches to the tracklist view
			 */
			albumButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e)
				{
					String albumIdentity = e.getActionCommand();
					selectedAlbum = findAlbumByNameAndArtist(albumIdentity);
					tracklistPanel = createTracklistPanel();
					showTracklist();
				}
			});
			
			panel.add(albumButton);
		}
		
		return panel;
	}
	
	/**
	 * Method takes an input album identity (which is a string) e.g. "Abbey Road by Beatles"
	 * and searches the albums list and returns the album with that identity
	 * @param albumIdentity a string e.g. "Abbey Road by Beatles"
	 * @return the album with that identity
	 */
	public Album findAlbumByNameAndArtist(String albumIdentity)
	{
		//Search for the album
		for(Album a : albums)
		{
			String aIdentity = a.getAlbumName() + " by " + a.getArtistName();
			if(aIdentity.equals(albumIdentity))
			{
				return a;
			}
		}
		return null;
	}
	
	/**
	 * Creates panel with tracklist
	 * 
	 * @return JPanel with tracklist
	 */
	public JPanel createTracklistPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		//Create a text area for all tracks in the panel
		JTextArea tracklistTextArea = new JTextArea();
		tracklistTextArea.setEditable(false);
		tracklistTextArea.setText("No.\t\t\tTrack name\t\t\tLength\n");
		
		if(selectedAlbum != null)
		{
			String trackNumber;
			String trackTitle;
			String trackLength;
			Scanner scanner;
			
			//Add each track to the text area using the album's tracklist list
			for(String track : selectedAlbum.getTracklist())
			{
				//Get each attribute of the track
				scanner = new Scanner(track);
				scanner.useDelimiter(",");
				trackNumber = scanner.next().trim();
				trackTitle = scanner.next().trim();
				trackLength = scanner.next().trim();
				
				//Append the track information to the text area
				tracklistTextArea.append(trackNumber + "\t\t\t" + trackTitle + "\t\t\t" + trackLength + "\n");
			}
		}
		
		//Add a back button
		JButton backButton = new JButton("Back");
		//Add action listener to the back button that shows the album view when clicked
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				showAlbums();
			}
		});
		
		panel.add(new JScrollPane(tracklistTextArea), BorderLayout.CENTER);
		panel.add(backButton, BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * Switches view to the tracklist view
	 */
	public void showTracklist()
	{
		getContentPane().removeAll();
		add(tracklistPanel);
		revalidate();
		repaint();
	}
	
	/**
	 * Switches view to the album view
	 */
	public void showAlbums()
	{
		getContentPane().removeAll();
		add(albumPanel);
		revalidate();
		repaint();
	}
}
