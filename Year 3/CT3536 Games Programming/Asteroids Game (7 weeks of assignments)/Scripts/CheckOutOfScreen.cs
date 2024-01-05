using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CheckOutOfScreen : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        //Wrap object to other side of screen, check every 0.2 seconds. 5 times a second
        InvokeRepeating("CheckOutOfScreenMethod", 0.2f, 0.2f);
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void CheckOutOfScreenMethod()
    {
        Vector3 currentWorldPos = this.transform.position;
        Vector3 viewPos = Camera.main.WorldToViewportPoint(currentWorldPos);
        if (viewPos.x > 1f)
        {
            //If the object is a bullet destroy it, the bullet has the "Bullet" tag, set in inspector
            if (this.CompareTag("Bullet"))
            {
                Destroy(this.gameObject);
                return;
            }

            //Wrap to other side of screen
            this.transform.position = new Vector3(-currentWorldPos.x + 1, currentWorldPos.y, currentWorldPos.z);
            return;
        }

        if (viewPos.x < 0f)
        {
            //If the object is a bullet destroy it, the bullet has the "Bullet" tag, set in inspector
            if (this.CompareTag("Bullet"))
            {
                Destroy(this.gameObject);
                return;
            }

            //Wrap to other side of screen
            this.transform.position = new Vector3(-currentWorldPos.x - 1, currentWorldPos.y, currentWorldPos.z);
            return;
        }

        if (viewPos.y > 1f)
        {
            //If the object is a bullet destroy it, the bullet has the "Bullet" tag, set in inspector
            if (this.CompareTag("Bullet"))
            {
                Destroy(this.gameObject);
                return;
            }

            //Wrap to other side of screen
            this.transform.position = new Vector3(currentWorldPos.x, currentWorldPos.y, -currentWorldPos.z + 1);
            return;
        }

        if (viewPos.y < 0f)
        {
            //If the object is a bullet destroy it, the bullet has the "Bullet" tag, set in inspector
            if (this.CompareTag("Bullet"))
            {
                Destroy(this.gameObject);
                return;
            }

            //Wrap to other side of screen
            this.transform.position = new Vector3(currentWorldPos.x, currentWorldPos.y, -currentWorldPos.z - 1);
            return;
        }
    }
}
