using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// Script that calls a method in the RaceManager when the Player has reached the finish line
public class FinishLineTrigger : MonoBehaviour {
    public GameObject RaceManager;

    private void OnTriggerEnter(Collider other)
    {
        // Check if the entering collider is the player
        if (other.CompareTag("Player"))
        {
            // Tell the RaceManager that the Player has reached the finish line
            RaceManager.GetComponent<RaceManager>().EndRace();
        }
    }
}
