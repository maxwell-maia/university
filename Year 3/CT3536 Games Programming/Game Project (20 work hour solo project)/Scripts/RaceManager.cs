using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// This class is to manage the race. Starts when a level is loaded
// Note this class enforces a constraint that all levels need to have the same width path (but any length)
// and be built along the positive z direction.
public class RaceManager : MonoBehaviour {
    private float raceTimer;
    private GameObject startLineTrigger, finishLineTrigger;
    public GameObject dragonTrapManager;

    private List<float> checkpoints = new List<float>();

    // Total length of the level
    private float raceDistance;

    private int numCheckpoints;

    private Vector3 startLinePoint;

    private float distanceBetweenCheckpoints = 20f;

    // Used for finding suitable checkpoints
    private float backstep = 0f;

    // Used for finding the last checkpoint
    private float prevCheckpoint = 0f;

    // Sounds
    public AudioSource audioSource;

    public AudioSource musicAudioSource;

    void Start()
    {
        startLineTrigger = GameObject.Find("Start Line Trigger");
        finishLineTrigger = GameObject.Find("Finish Line Trigger");
        
        // Distance is start to finish
        raceDistance = Mathf.Abs(finishLineTrigger.transform.position.z - startLineTrigger.transform.position.z);

        // Checkpoint every 20 units
        numCheckpoints = (int) Mathf.Floor(raceDistance / distanceBetweenCheckpoints);

        // Checkpoints are measured from the start line
        startLinePoint = new Vector3(0f, 0f, startLineTrigger.transform.position.z);

        // Populate list of checkpoints
        RaycastHit hit;
        for (int i = 1; i <= numCheckpoints; i++)
        {
            // Check that a potential checkpoint location has the path below it (not above a hole)
            while (!Physics.Raycast(new Vector3(0f, 2f, (startLineTrigger.transform.position.z + i * distanceBetweenCheckpoints - backstep)), Vector3.down, out hit, 1.6f))
            {
                // Not suitable checkpoint go back some units and try again
                backstep += 5;

                // Breaking from situation where a checkpoint is not possible
                if (backstep > 1000)
                {
                    break;
                }
            }
            // Suitable checkpoint location found, add it to the list of checkpoints
            checkpoints.Add(startLinePoint.z + i * distanceBetweenCheckpoints - backstep);
            backstep = 0f;

            // Sounds
            GameManager.instance.SetInGameAudioSources(audioSource, musicAudioSource);
        }

        // Update startLinePoint in GameManager
        GameManager.instance.startLinePoint = startLinePoint;

        // Update raceDistance in GameManager
        GameManager.instance.raceDistance = raceDistance;
    }

    // Method to start the race
    public void StartRace()
    {
        // Change game state when the race starts
        GameManager.instance.GoToRace();

        // Check if the start line trigger gameobject was found
        if (startLineTrigger != null)
        {
            // Set the start Line Trigger to inactive
            startLineTrigger.SetActive(false);
        }
        else
        {
            // Show an error
            Debug.LogError("Start Line Trigger not found in the hierarchy.");
        }

        // Play sound
        GameManager.instance.PlayStartEndRaceClip();

        EnableDragonTraps();

        // Make it so that all AI starts moving (in the race progress bar at the bottom)
        GameManager.instance.SetAICanMove(true);
    }

    // Call the method to activate all dragon traps
    private void EnableDragonTraps()
    {
        dragonTrapManager.GetComponent<DragonTrapManager>().ActivateDragonTraps();
    }

    // Method for when the player reaches the end of the race
    public void EndRace()
    {
        // Check if the finish line trigger gameobject was found
        if (finishLineTrigger != null)
        {
            // Set the Finish Line Trigger to inactive
            finishLineTrigger.SetActive(false);
        }
        else
        {
            // Show an error
            Debug.LogError("Finish Line Trigger not found in the hierarchy.");
        }

        // Play a sound
        GameManager.instance.PlayStartEndRaceClip();

        // Update the position i.e. 1st, 2nd etc, for post game text
        GameManager.instance.UpdatePositionUIPostGame();

        // Change GameState and therefore UI
        GameManager.instance.GoToPostGame();
    }

    // Get the z co-ordinate of the last checkpoint a player passes
    // @param pz the current z co-ordinate of the player that wants their last checkpoint
    // @return the z co-ordinate of the last checkpoint behind the the player
    public float LastCheckpoint(float pz)
    {
        // If the player respawns before the first checkpoint then they will go to the start line
        prevCheckpoint = startLinePoint.z;

        // Check that there are checkpoints and the list is not null
        if(checkpoints.Count != 0 || checkpoints != null)
        {
            // Find the biggest checkpoint that is behind the player
            foreach (float checkpoint in checkpoints)
            {
                if (checkpoint > pz)
                {
                    // Return last checkpoint
                    return prevCheckpoint;
                }

                prevCheckpoint = checkpoint;
            }
        }
        // If there are no checkpoints that meet this criteria, return start line
        return startLinePoint.z;
    }
}
