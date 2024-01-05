using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;
using System.Text;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using System;

// Enum for game states
public enum GameState {
	none,
	main_menu,
	level_select,
	in_game,
	race,
	post_game
}

// Struct for each player in the game (AI and players included)
[System.Serializable]
public struct PlayerStruct {
	public GameObject player;
	public GameObject playerRepresentationUI; // Cube used in Race Progress Bar UI
	public float distanceFromStartLine;

	// The % of the race that has been completed
    // i.e. player is 90% complete the race (based on distance from start to finish line)
	public float currentRacePercent;
	public bool isAI;
	public float chanceToTouchTrap;
	public float speed;
	public int position; // 1st, 2nd, 3rd, etc...
	public bool canMove; // (Used for AI) Only true when racing. i.e. Not before race, not once this player reaches the end of the race
	public int playerID;
}

public class GameManager : MonoBehaviour {

	// GUI Components
	public GameObject mainMenuCanvas, levelSelectCanvas, inGameCanvas, raceCanvas, raceProgressPanel, postGameCanvas, loadingCanvas;
	public GameObject txtPosition, txtPositionPostGame; // TextMeshProUGUI
	public Button levelSelectPlayButton;
	public int levelSelected; // Level Selected int
	public Button level1;
	public Button level2;
	public Button level3;
	public GameObject selectedLevel;

	// Menu navigation is set in the inspector via onclick methods that use functions in this class
	// Onclick methods call menu navigation methods seen below

	public static GameManager instance; // Singleton pattern

	public static int TrapLayer;

	public static Vector3 levelStartPoint = new Vector3(5f, 2f, 5f); // Where the player spawns

	private static WaitForSeconds wait10s = new WaitForSeconds(10f);

	public static GameState gameState = GameState.none;

	// Number of players in the game. This game is singleplayer there is 1 player and 5 AI
	public int numPlayers = 6;

	// Array of materials for the cubes in the Race Progress Bar
	public Material[] playerRepresentationMaterials;

	// Array of cubes for the Race Progress Bar
	public GameObject playerRepresentationPrefab;

	// Value used to position the cubes in the Race Progress Bar
	private float currentRacePercentUIOffset = -12f; // -12 to be off screen intially

	// Array of PlayerStructs. 1 for each player in the game (AI and players combined)
	public PlayerStruct[] players;

	// Array used to sort to find position of players e.g. 1st, 2nd, 3rd etc
	public PlayerStruct[] playersSortCurrentRacePercent;

	// centre of start line of the current race
	public Vector3 startLinePoint;

	// Player one's position in the gameworld
	private Vector3 playerOnePosition;

	// race distance of the current race
	public float raceDistance;

	// Sounds
	// Audio Sources
	public AudioSource audioSource;
	public AudioSource musicAudioSource;

	// Audio Clips
	public AudioClip hitByTrapClip;
	public AudioClip startEndRaceClip;
	public AudioClip menuMusic;
	public AudioClip inGameMusic;

	// Only have this reference to disable/enable the audio listener on the camera
	public GameObject canvasCamera;


	// Awake is called before the Start method and is guaranteed to be executed before Start in other scripts
	void Awake()
	{
		instance = this;
	}

	void Start () {
		// Set the loading canvas inactive
		loadingCanvas.gameObject.SetActive(false);

		// Get the TrapLayer
		TrapLayer = LayerMask.NameToLayer("Trap");

		// Set the level selected to 0 (not valid initally)
		levelSelected = 0;

		GoToMainMenu();

		// Add listener to Play button in Level Select screen that calls a method to start the game when clicked
		levelSelectPlayButton.onClick.AddListener(PlayNewGame);

		// Creating an array of PlayerStructs. 1 for each player in the game (AI and players combined)
		players = new PlayerStruct[numPlayers];
	}

	void Update()
    {
		// If the raceProgressPanel is active i.e. We are either: in-game, race or post-game
		if (raceProgressPanel.activeSelf)
        {
			// Two functions are done in this if statement
			// Function 1. Position Text UI (i.e. 1st, 2nd or 3rd etc)
			// Function 2. RaceProgress UI. (The cubes at the bottom that show player's progress)
			// Function 1 and 2 are together for efficiency. We only need one for loop in Update
			// that runs for numPlayers iterations.
			// Function 1 iterates players[] but Function 2 iterates playersSortCurrentRacePercent[].

			// Function 1. Position Text UI (i.e. 1st, 2nd or 3rd etc)
			// Get the position of each player
			// Sort a copy of the PlayerStruct array on the attribute: currentRacePercent in descending order.
			playersSortCurrentRacePercent = players;
			Array.Sort(playersSortCurrentRacePercent, (x, y) => y.currentRacePercent.CompareTo(x.currentRacePercent));


			// Function 2. RaceProgress UI. (The cubes at the bottom that show player's progress)
			// Draw player representations (cubes) in RaceProgress UI area
			// Get a reference point in 3D space to position cubes on canvas
			Vector3 refPoint = raceProgressPanel.GetComponent<RectTransform>().position;

			// Update all player representation (cube) positions
			for (int i = 0; i < numPlayers; i++)
            {
				// Update non-AI player's currentRacePercent
				if (!players[i].isAI)
                {
					// Get the player's distance from the start line
					players[i].distanceFromStartLine = players[i].player.transform.position.z - startLinePoint.z;
					// Turn that into a percent
					players[i].currentRacePercent = (players[i].distanceFromStartLine / raceDistance) * 100f;

				}

				// Update AI player's currentRacePercent
				if (players[i].isAI && players[i].canMove)
				{
					// Update AI's race percent according to speed and race distance
					// Race distance is included so that the rate of course completion is the same for any length of map
					players[i].currentRacePercent += players[i].speed * Time.deltaTime * (raceDistance/3000);

					// When the AI reaches the end of the race, they stop moving
					if (players[i].currentRacePercent >= 100)
                    {
						players[i].canMove = false;
					}

					// Apply race percent penalty when the AI hits a trap (random chance)
					if (UnityEngine.Random.value < players[i].chanceToTouchTrap)
                    {
						// Race distance is included so that the rate of course completion is the same for any length of map
						players[i].currentRacePercent -= 20f * (raceDistance / 3000);
						players[i].currentRacePercent = Mathf.Clamp(players[i].currentRacePercent, 0f, 100f);
                    }
				}

				// Convert race percent into a value from -6 to 6 for position on the Race Progress Bar UI
				currentRacePercentUIOffset = Map(players[i].currentRacePercent, 0f, 100f, -6f, 6f);
				currentRacePercentUIOffset = Mathf.Clamp(currentRacePercentUIOffset, -6f, 6f);

				// Note on the vector space:
				// from refPoint.x-6 -> refPoint.x+6 is 0->100 % race completion.
				// refPoint.y more negative values are closer to the camera.

				// Set the position of the cube
				players[i].playerRepresentationUI.transform.position = new Vector3(refPoint.x + currentRacePercentUIOffset, refPoint.y + 0.5f, refPoint.z - 1f + (((float)i)/50f));
				// The "i" in this line of code offsets the cubes a little so they aren't all fully on top of each other


				// Function 1. TEXT UI (i.e. 1st, 2nd or 3rd etc)
				// Get the non-AI player position
				// The only non-AI player is always playerID = 0
				if (playersSortCurrentRacePercent[i].playerID == 0)
                {
					// Update the Player position on the UI (i.e. 1st, 2nd or 3rd etc)
					SetPosition((i + 1), false);
				}				
			}
		}
    }

	void initialisePlayerStructArray()
    {
		// Destroy game objects if there are any already there
		if (players.Length != 0)
        {
			DestroyPlayerRespresentationObjects();
        }

		for (int i = 0; i < numPlayers; i++)
		{
			players[i].player = null;  // AI don't have one. Player assigns it's own later
			GameObject playerRepresentation = Instantiate(playerRepresentationPrefab); // Instantiate cube for Race Progress Bar
			playerRepresentation.transform.GetChild(0).GetComponent<Renderer>().sharedMaterial = playerRepresentationMaterials[i]; // Material for Cube
			DontDestroyOnLoad(playerRepresentation);
			players[i].playerRepresentationUI = playerRepresentation;  // The Cube in the UI that represents the player/AI
			players[i].distanceFromStartLine = 0f;
			players[i].currentRacePercent = 0f;
			players[i].isAI = true;
			players[i].chanceToTouchTrap = UnityEngine.Random.Range(0.005f, 0.0065f);
			players[i].speed = UnityEngine.Random.Range(12f, 16f);
			players[i].position = numPlayers; // Everyone is last to begin with
			players[i].canMove = false;
			players[i].playerID = i; // This is necessary because a copy of this array will be sorted to get positions 1st, 2nd etc
		}
	}

	// Destroy any player representation objects from the last game
	public void DestroyPlayerRespresentationObjects()
    {
		for (int i = 0; i < numPlayers; i++)
		{
			Destroy(players[i].playerRepresentationUI); // Destroy the Cube in the UI that represents the player/AI
		}
	}

	// Method that sets all player's canMove (only the AI utilize the canMove variable)
	public void SetAICanMove(bool cM)
    {
		for (int i = 0; i < numPlayers; i++)
		{
			players[i].canMove = cM;
		}
	}

	// Function to map a value from one range to another
	float Map(float value, float fromMin, float fromMax, float toMin, float toMax)
	{
		return (value - fromMin) / (fromMax - fromMin) * (toMax - toMin) + toMin;
	}

	// Function to set game state
	public static void SetGameState(GameState state) {
		gameState = state;

		if (state == GameState.main_menu) {
			instance.mainMenuCanvas.SetActive (true);
			instance.levelSelectCanvas.SetActive(false);
			instance.inGameCanvas.SetActive (false);
			instance.raceCanvas.SetActive(false);
			instance.raceProgressPanel.SetActive(false);
			instance.postGameCanvas.SetActive(false);
			instance.PlayMenuMusic(); // Switch to menu music
		}
		else if (state == GameState.level_select)
		{
			instance.mainMenuCanvas.SetActive(false);
			instance.levelSelectCanvas.SetActive(true);
			instance.inGameCanvas.SetActive(false);
			instance.raceCanvas.SetActive(false);
			instance.raceProgressPanel.SetActive(false);
			instance.postGameCanvas.SetActive(false);
		}
		else if (state == GameState.in_game) {
			instance.mainMenuCanvas.SetActive(false);
			instance.levelSelectCanvas.SetActive(false);
			instance.inGameCanvas.SetActive(true);
			instance.raceCanvas.SetActive(false);
			instance.raceProgressPanel.SetActive(true); // Can see progress panel in game
			instance.postGameCanvas.SetActive(false);
			instance.canvasCamera.GetComponent<AudioListener>().enabled = false; // Disable canvas audio listener while in game
			instance.musicAudioSource.Stop();
		}
		else if (state == GameState.race)
		{
			instance.mainMenuCanvas.SetActive(false);
			instance.levelSelectCanvas.SetActive(false);
			instance.inGameCanvas.SetActive(false);
			instance.raceCanvas.SetActive(true);
			instance.raceProgressPanel.SetActive(true); // Can see progress panel during race
			instance.postGameCanvas.SetActive(false);
		}
		else if (state == GameState.post_game) {
			instance.mainMenuCanvas.SetActive(false);
			instance.levelSelectCanvas.SetActive(false);
			instance.inGameCanvas.SetActive(false);
			instance.raceCanvas.SetActive(false);
			instance.raceProgressPanel.SetActive(true); // Can see progress panel post game
			instance.postGameCanvas.SetActive(true);
		}
	}

	// Set the position text on GUI
	// @param position integer
    // @param is this for post game or not? bool
    // generates and sets text to the post game Text or the race Text
	public static void SetPosition(int pos, bool isPostGame) {
		// Select suffix
		string suffix = "default";
		if (pos == 1)
		{
			suffix = "st";
		}
		else if (pos == 2)
		{
			suffix = "nd";
		}
		else if (pos == 3)
		{
			suffix = "rd";
		}
		else if (pos > 3)
		{
			suffix = "th";
		}

		// Optimise String concatenation using a string builder
		string pos_ui_text = "You are\n";
		string pos_ui_text_postGame = "You finished\n";
		StringBuilder sb = new StringBuilder();

		// Build string
		// Use a different string depending on postGame bool
		if (isPostGame)
        {
			sb.Append(pos_ui_text_postGame);
		}
        else
        {
			sb.Append(pos_ui_text);
		}
		
		sb.Append(pos);
		sb.Append(suffix);
		sb.Append("\n/ ");
		sb.Append(instance.numPlayers);
		sb.Append(" Players");

		if (isPostGame)
		{
			// Update post game position Text
			instance.txtPositionPostGame.GetComponent<TextMeshProUGUI>().text = $"{sb.ToString()}";
		}
		else
		{
			// Update race position Text
			instance.txtPosition.GetComponent<TextMeshProUGUI>().text = $"{sb.ToString()}";
		}
	}

	// Method that finds the player position and updates the post game UI with the position (used at end of race)
	public void UpdatePositionUIPostGame()
    {
		for (int i = 0; i < numPlayers; i++)
        {
			// Get the player position
			// The only player is always playerID = 0
			if (playersSortCurrentRacePercent[i].playerID == 0)
			{
				// Update the Player position on the UI (i.e. 1st, 2nd or 3rd etc)
				SetPosition((i + 1), true);
				return;
			}
		}
	}

	// Menu navigation methods

	// Start the game with the currently selected level
	public void PlayNewGame()
	{
		// Reset the player struct array
		initialisePlayerStructArray();

		// Currently only 3 playable levels
		if (levelSelected > 0 && levelSelected < 4)
		{
			// Show fake loading screen for 10 seconds
			StartCoroutine(ShowFakeLoadingScreenAndLoadScene());
		}
	}

	private IEnumerator ShowFakeLoadingScreenAndLoadScene()
	{
		// Show fake loading screen
		loadingCanvas.gameObject.SetActive(true);
		loadingCanvas.GetComponent<Canvas>().sortingOrder = 1;

		// Wait for the loading screen duration
		yield return wait10s;

		// Set loading screen inactive
		loadingCanvas.gameObject.SetActive(false);

		// Set Game state to in game after loading screen
		SetGameState(GameState.in_game);

		// Load the selected level's scene
		string sceneToLoad = "GameScene_Level_" + levelSelected;
		SceneManager.LoadScene(sceneToLoad); // SceneManager.LoadScene("GameScene_Level_1");
	}

	// Close the game
	public void QuitGame()
	{
		Application.Quit();
	}

	// Switch to main menu
	public void GoToMainMenu()
	{
		SetGameState(GameState.main_menu);
	}

	// Switch to level select
	public void GoToLevelSelect()
	{
		SetGameState(GameState.level_select);
	}

	// Set game state to race
	public void GoToRace()
	{
		SetGameState(GameState.race);
	}

	// Set game state to post game
	public void GoToPostGame()
	{
		SetGameState(GameState.post_game);
	}


	// Select level stores the choice made as an integer and updates the level selected image's sources in Level Select GUI
	public void SelectLevel(int level)
	{
		levelSelected = level;

		switch(levelSelected)
        {
			case 1:
				selectedLevel.GetComponent<Image>().sprite = level1.GetComponent<Image>().sprite;
				break;
			case 2:
				selectedLevel.GetComponent<Image>().sprite = level2.GetComponent<Image>().sprite;
				break;
			case 3:
				selectedLevel.GetComponent<Image>().sprite = level3.GetComponent<Image>().sprite;
				break;
			default:
				break;
		}
	}

	// Assign in game Audio Source
	// This overwrites the audio sources of the main menu scene
	// In this version of the game the player never returns to the main menu scene after clicking play the first time
	// So the audio sources of the level can be used when the main menu's GUI appears on screen after Play Again is clicked
	public void SetInGameAudioSources(AudioSource a, AudioSource aMusic)
    {
		audioSource = a;
		musicAudioSource = aMusic;

		PlayInGameMusic(); // Switch to in game music
	}

	// Play the audio clip of the player being hit by a trap
	public void PlayHitByTrapClip()
    {
		// Assign the audio clip to the AudioSource component
		audioSource.clip = hitByTrapClip;

		// Play the sound
		PlaySound(audioSource);
	}

	// Play the audio clip for the start of a race
	public void PlayStartEndRaceClip()
	{
		// Assign the audio clip to the AudioSource component
		audioSource.clip = startEndRaceClip;

		// Play the sound
		PlaySound(audioSource);
	}

	// Play the audio clip for menu music
	public void PlayMenuMusic()
	{
		// Assign the audio clip to the AudioSource component
		musicAudioSource.clip = menuMusic;

		// Play the sound
		PlaySound(musicAudioSource);
	}

	// Play the audio clip for in game music
	public void PlayInGameMusic()
	{
		// Assign the audio clip to the AudioSource component
		musicAudioSource.clip = inGameMusic;

		// Play the sound
		PlaySound(musicAudioSource);
	}

	// Method to play a sound as long as there is an audio clip in the audio source
	void PlaySound(AudioSource aS)
	{
		// Check if an audio clip is assigned
		if (aS.clip != null)
		{
			// Play the audio clip
			aS.Play();
		}
		else
		{
			// Show an error
			Debug.LogError("No audio clip assigned. Please assign an AudioClip in the Unity Editor.");
		}
	}
}
