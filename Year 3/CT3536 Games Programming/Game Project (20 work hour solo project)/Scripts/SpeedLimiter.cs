using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// Script that limits the maximum speed that the GameObject that this is attached to can move at
public class SpeedLimiter : MonoBehaviour {
    public float maxSpeed = 1000f;

    void FixedUpdate()
    {
        // Limit this GameObject's maximum velocity to the maxSpeed
        if (this.GetComponent<Rigidbody>().velocity.magnitude > maxSpeed)
        {
            this.GetComponent<Rigidbody>().velocity = Vector3.ClampMagnitude(this.GetComponent<Rigidbody>().velocity, maxSpeed);
        }
    }
}
