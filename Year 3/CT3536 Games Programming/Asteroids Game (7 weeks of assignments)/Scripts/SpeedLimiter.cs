using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpeedLimiter : MonoBehaviour
{
    public float maxSpeed = 1000f;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void FixedUpdate()
    {
        if (this.GetComponent<Rigidbody>().velocity.magnitude > maxSpeed)
        {
            this.GetComponent<Rigidbody>().velocity = Vector3.ClampMagnitude(this.GetComponent<Rigidbody>().velocity, maxSpeed);
        }
    }
}
