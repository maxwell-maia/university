using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spaceship : MonoBehaviour
{
    public static Spaceship mySpaceship;

    public GameObject bulletPrefab;

    public float fireRate = 0.25f;

    private float nextFire = 0.0f;

    // Start is called before the first frame update
    void Start()
    {
        mySpaceship = this;
    }

    // Update is called once per frame
    void Update()
    {
        if(Input.GetKey(KeyCode.UpArrow))
        {
            this.GetComponent<Rigidbody>().AddRelativeForce(Vector3.forward * 20);
        }

        if (Input.GetKey(KeyCode.LeftArrow))
        {
            this.GetComponent<Rigidbody>().AddTorque(transform.up * -60);
        }

        if (Input.GetKey(KeyCode.RightArrow))
        {
            this.GetComponent<Rigidbody>().AddTorque(transform.up * 60);
        }

        //The spaceship shoots bullets
        //Spawn the bullet in front of the spaceship
        //Set the rotation
        //Set the velocity
        if (Input.GetKeyDown(KeyCode.Space) && Time.time > nextFire)
        {
            //Set the next in game time that the bullet can be fired
            nextFire = Time.time + fireRate;

            //Spawning the bullet

            //Get the spaceship's position
            Vector3 spaceshipPos = mySpaceship.transform.position;

            //Get the spaceship's forward direction
            Vector3 spaceshipDirection = mySpaceship.transform.forward;

            //Get the spaceship's rotation
            Quaternion spaceshipRotation = mySpaceship.transform.rotation;

            //Choose a distance in front of the player to spawn the bullet
            float spawnDistance = 2.05f;

            //Choose a bullet speed
            float bulletSpeed = 25f;

            //Get the bullet velocity
            Vector3 bulletVelocity = spaceshipDirection * bulletSpeed;

            //Get the position in front of the spaceship
            Vector3 bulletSpawnPos = spaceshipPos + spaceshipDirection * spawnDistance;

            //Spawn bullet in calculated position and spaceship rotation
            GameObject bullet = Instantiate(bulletPrefab, bulletSpawnPos, spaceshipRotation);

            //Set the velocity of the bullet
            bullet.GetComponent<Rigidbody>().velocity = bulletVelocity;
        }
    }
}
