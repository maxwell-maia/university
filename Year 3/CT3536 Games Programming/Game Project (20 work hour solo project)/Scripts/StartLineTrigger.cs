using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// Script that calls a method in the RaceManager when the Player has reached the start line
public class StartLineTrigger : MonoBehaviour {
    public GameObject RaceManager;

    private void OnTriggerEnter(Collider other)
    {
        if (other.CompareTag("Player"))
        {
            // Tell the RaceManager that the Player has reached the start line
            RaceManager.GetComponent<RaceManager>().StartRace();
        }
    }
}
