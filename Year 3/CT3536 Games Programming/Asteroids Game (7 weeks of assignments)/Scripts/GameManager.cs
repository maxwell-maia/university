using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using TMPro;

public class GameManager : MonoBehaviour
{
    public int currentGameLevel;
    public GameObject asteroidPrefab;
    public GameObject spaceshipPrefab;
    public static GameManager gamemanager;
    private bool isPlaying;
    public GameObject menuCanvas;
    public GameObject playingCanvas;
    public Button playButton;
    public TMP_Text txtScore;
    public TMP_Text txtBestScore;
    public TMP_Text txtLives;
    public List<GameObject> activeAsteroids;
    private int score;
    private int bestScore;

    // Start is called before the first frame update
    void Start()
    {
        //Position camera
        Camera.main.transform.position = new Vector3(0, 30, 0);
        Camera.main.transform.LookAt(new Vector3(0, 0, 0), new Vector3(0, 0, 1));

        gamemanager = this;

        isPlaying = false;
        goToMenu();

        playButton.onClick.AddListener(PlayOnClick);
    }

    void PlayOnClick()
    {
        isPlaying = true;
        goToPlaying();
        StartNewGame();
    }

    void StartNewGame()
    {
        currentGameLevel = 0;

        //Set score to 0
        score = 0;
        
        //Load the saved best score into the best score variable
        LoadBestScore();

        //Set the UI to default values
        txtScore.text = "0";
        txtLives.text = "3";
        txtBestScore.text = bestScore.ToString();

        CreatePlayerSpaceship();

        StartNextLevel();
    }

    // Update is called once per frame
    void Update()
    {
        if(isPlaying == true)
        {
            //The active asteroids list is said to be empty when all of the indexes are null
            bool isEmpty = true;

            foreach(GameObject a in activeAsteroids)
            {
                if (a != null)
                {
                    isEmpty = false;
                }
            }

            //When there are no more active asteroids
            //(When the player has destroyed all the asteroids)
            if (isEmpty == true) //activeAsteroids.Count == 0
            {
                //Clear the asteroid list
                activeAsteroids.Clear();

                StartNextLevel();
            }
        }
    }

    //Score system
    public void IncreaseScore(bool isLargeAsteroid)
    {
        //Get current score
        score = int.Parse(txtScore.text);

        //Increase score by 50 for large asteroids and 100 for small asteroids
        if (isLargeAsteroid)
        {
            score += 50;
        }
        else
        {
            score += 100;
        }

        //Update the score text
        txtScore.text = score.ToString();

        //It's nice to see the best score keep increasing as you are playing if you have beat it
        CheckAndUpdateBestScore();
    }

    //BestScore System
    public void CheckAndUpdateBestScore()
    {
        LoadBestScore();

        //If you beat the score update the best score
        if(score > bestScore)
        {
            SaveBestScore(score);

            //Update best score UI
            txtBestScore.text = score.ToString();
        }
    }

    //Helper method to save best score
    public void SaveBestScore(int newBestScore)
    {
        PlayerPrefs.SetInt("localBestScore", newBestScore);
    }

    //Helper method to load best score 
    public void LoadBestScore()
    {
        bestScore = PlayerPrefs.GetInt("localBestScore");
    }

    //Lives system
    public bool DecreaseLives()
    {
        int lives;
        lives = int.Parse(txtLives.text);

        lives--;

        if (lives <= 0)
        {
            //Gameover
            EndGame();
            return false;
        }
        else
        {
            //Update lives
            txtLives.text = lives.ToString();
            return true;
        }
    }

    public void EndGame()
    {
        //Check if you beat the best score and update it if you have
        CheckAndUpdateBestScore();

        //Destory all asteroids
        foreach(GameObject ast in activeAsteroids)
        {
            Destroy(ast);
        }

        goToMenu();
    }

    //Hide the Playing GUI and show the Menu GUI
    void goToMenu()
    {
        //Set the alpha of each GUI
        playingCanvas.GetComponent<CanvasGroup>().alpha = 0;
        menuCanvas.GetComponent<CanvasGroup>().alpha = 1;
    }

    //Hide the Menu GUI and show the Playing GUI
    void goToPlaying()
    {
        //Set the alpha of each GUI
        menuCanvas.GetComponent<CanvasGroup>().alpha = 0;
        playingCanvas.GetComponent<CanvasGroup>().alpha = 1;
    }

    void StartNextLevel()
    {
        currentGameLevel++;
        int numAsteroids = 6 * currentGameLevel;

        //Spawn asteroid depending on currentGameLevel
        for (int i = 0; i < numAsteroids; i++)
        {
            GameObject newAsteroid = Instantiate(asteroidPrefab);
            activeAsteroids.Add(newAsteroid);
        }
    }

    public void CreatePlayerSpaceship()
    {
        GameObject spaceship = Instantiate(spaceshipPrefab);
        spaceship.transform.position = new Vector3(0, 0, 0);
    }
}
