using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;

public class DragonTrapManager : MonoBehaviour {
    // Array of the dragon trap objects
    private GameObject[] dragonTraps;

    private static WaitForSeconds wait500ms = new WaitForSeconds(0.5f);
    private static WaitForSeconds wait4s = new WaitForSeconds(4f);
    private static WaitForSeconds wait1s = new WaitForSeconds(1f);

    void Start()
    {
        // Get all of the dragon traps and put them in an array
        dragonTraps = GameObject.FindGameObjectsWithTag("Dragon Trap");
        // Sort the array by their z co-ordinate
        Array.Sort(dragonTraps, (x, y) => y.transform.position.z.CompareTo(x.transform.position.z));
    }
    
    // Method that calls a coroutine that starts all of the coroutines of dragon firing cycles
    public void ActivateDragonTraps()
    {
        StartCoroutine(ActivateAllDragonTraps());
    }

    // Coroutine that starts all of the coroutines of dragon firing cycles
    IEnumerator ActivateAllDragonTraps()
    {
        // For every dragon trap
        foreach (var dragon in dragonTraps)
        {
            // Every 0.5 seconds...
            yield return wait500ms;

            // Start the firing cycle of a dragon trap
            StartCoroutine(DragonTrapCoroutine(dragon));
        }
    }

    // Coroutines of a dragon's firing cycle
    IEnumerator DragonTrapCoroutine(GameObject dragon)
    {
        // Get the position of the Dragon Trap
        Vector3 dPos = dragon.transform.position;

        // Spawn a box trigger with the tag "Trap"
        GameObject trigger = Instantiate(Resources.Load("FireTriggerPrefab", typeof(GameObject))) as GameObject;

        // Set the trigger inactive
        trigger.SetActive(false);

        float startTime = 0f;

        Vector3 pos = new Vector3(-5.4f, 2, dPos.z);
        Vector3 triggerPos = new Vector3(-25f, 2, dPos.z);

        // Spawn a particle emitter
        GameObject particleEmitter = Instantiate(Resources.Load("DragonFireEmitterPrefab", typeof(GameObject))) as GameObject;
        particleEmitter.transform.position = pos;

        // Spawn a smaller particle emitter
        GameObject miniParticleEmitter = Instantiate(Resources.Load("MiniDragonFireEmitterPrefab", typeof(GameObject))) as GameObject;
        miniParticleEmitter.transform.position = pos;

        // Start the fireblast
        while (true)
        {
            // Warn the player the fire is coming with the smaller particle emitter
            miniParticleEmitter.GetComponent<ParticleSystem>().Play();

            // Delay before the actual fire blast
            yield return wait1s;

            // Set the trigger position
            trigger.transform.position = triggerPos;

            // Start particle emitter and set trigger active
            particleEmitter.GetComponent<ParticleSystem>().Play();
            trigger.SetActive(true);

            startTime = Time.time;

            // Duration of fireblast
            while (Time.time - startTime < 2f)
            {
                // Move the trigger around the speed of the fire particles until it has surpassed the width of the track
                if(trigger.transform.position.x < -10f)
                {
                    // Move the trigger
                    trigger.transform.Translate(Vector3.down * 15f * Time.deltaTime);
                }
                
                yield return null;
            }

            // Stop the smaller particle emitter
            miniParticleEmitter.GetComponent<ParticleSystem>().Stop(true, ParticleSystemStopBehavior.StopEmittingAndClear);

            // Set trigger inactive and stop particle emitter
            trigger.SetActive(false);
            particleEmitter.GetComponent<ParticleSystem>().Stop(true, ParticleSystemStopBehavior.StopEmittingAndClear);

            // Delay before the next fire blast
            yield return wait4s;
        }
    }
}
