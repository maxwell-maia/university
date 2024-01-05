using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Asteroid : MonoBehaviour
{
    public GameObject asteroidObject;

    private Vector3 spawn;

    public GameObject tinyAsteroid;
    private int numTinyAsteroidsToSpawn;

    private float timeOfLastSpawn = 0f;

    //Is the asteroid a large asteroid or debris fragment
    public bool isLarge = true;

    // Start is called before the first frame update
    void Start()
    {
        //Set the asteroid's position at a random position near the edges of the screen
        //Select top, bottom, left or right to spawn the asteroid and pick a random position along that edge
        if(Random.Range(0, 2) > 0.5f)
        {
            //Spawn along the top or bottom of screen
            if(Random.Range(0, 2) > 0.5f)
            {
                //Spawn along the bottom of screen
                spawn = Camera.main.ViewportToWorldPoint(new Vector3(Random.Range(0f, 1f), 0, 30));
            }
            else
            {
                //Spawn along the top of screen
                spawn = Camera.main.ViewportToWorldPoint(new Vector3(Random.Range(0f, 1f), 1, 30));
            }
            
        }
        else
        {
            //Spawn along the left or right of the screen
            if (Random.Range(0, 2) > 0.5f)
            {
                //Spawn along the left of screen
                spawn = Camera.main.ViewportToWorldPoint(new Vector3(0, Random.Range(0f, 1f), 30));
            }
            else
            {
                //Spawn along the right of screen
                spawn = Camera.main.ViewportToWorldPoint(new Vector3(1, Random.Range(0f, 1f), 30));
            }
        }

        //Set the asteroid's position
        asteroidObject.transform.position = spawn;

        //Set the asteroid moving in a random direction
        //Set the rotation of the asteroid randomly
        //asteroidObject.transform.Rotate(0.0f, 0.0f, Random.Range(0, 360));
        //Move the asteroid in a random direction
        asteroidObject.GetComponent<Rigidbody>().AddForce(new Vector3(Random.Range(-500f, 500f), 0, Random.Range(-500f, 500f)));
    }

    void OnCollisionEnter(Collision collision)
    {
        //Create particle effect using smaller non-colliding game objects
        numTinyAsteroidsToSpawn = Random.Range(1, 3);
        for (int i = 0; i < numTinyAsteroidsToSpawn; i++)
        {
            //Spawn the tiny asteroid and set it's position and velocity in a random direction
            GameObject newTinyAsteroid = Instantiate(tinyAsteroid);
            newTinyAsteroid.transform.position = collision.contacts[0].point;
            newTinyAsteroid.GetComponent<Rigidbody>().velocity = new Vector3(Random.Range(-2f, 2f), 0, Random.Range(-2f, 2f));
        }

        //If the asteroid hit the player ship, destroy the player ship and re-create it in the centre of the screen
        //Also add some spawn protection for 3 seconds
        //Also Decrease the lives
        if (collision.gameObject.CompareTag("Spaceship") && (Time.time - timeOfLastSpawn > 3f))
        {
            timeOfLastSpawn = Time.time;

            Destroy(collision.gameObject);

            bool stillAlive = GameManager.gamemanager.DecreaseLives();

            if (stillAlive == true)
            {
                GameManager.gamemanager.CreatePlayerSpaceship();
            }
            else
            {
                return;
            }
        }

        //If the asteroid hits the bullet, destroy the bullet and asteroid and create small debris fragments
        if (collision.gameObject.CompareTag("Bullet"))
        {
            //Destroy the bullet
            Destroy(collision.gameObject);

            //If the asteroid hit is a large asteroid
            //Only create small asteroids
            //And increase the score by 50
            if (this.isLarge == true)
            {
                //Create small asteroids (asteroid fragments)
                for (int i = 0; i < 2; i++)
                {
                    //Spawn the fragments
                    GameObject fragmentAsteroid = Instantiate(asteroidObject);

                    //Add the fragment asteroids to the active asteroid list
                    GameManager.gamemanager.activeAsteroids.Add(fragmentAsteroid);

                    //Position the fragments where the large asteroid was
                    fragmentAsteroid.transform.position = this.transform.position;

                    //Set the scale of the fragments
                    fragmentAsteroid.transform.localScale = new Vector3(0.045f, 0.045f, 0.045f);

                    //Set the fragments isLarge boolean to false
                    fragmentAsteroid.GetComponent<Asteroid>().isLarge = false;

                    //Set the fragments velocity in a random direction
                    fragmentAsteroid.GetComponent<Rigidbody>().AddForce(new Vector3(Random.Range(-300f, 300f), 0, Random.Range(-300f, 300f)));
                }

                //Hit larger asteroid
                //Increase the score by 50
                //Argument is Large asteroid = true
                GameManager.gamemanager.IncreaseScore(true);
            }
            else
            {
                //Hit small asteroid
                //Increase the score by 100
                //Argument is Large asteroid = false
                GameManager.gamemanager.IncreaseScore(false);
            }

            //Destroy the asteroid (large or small) that was hit by the bullet
            Destroy(this.asteroidObject);
        }
    }

    // Update is called once per frame
    void Update()
    {

    }
}