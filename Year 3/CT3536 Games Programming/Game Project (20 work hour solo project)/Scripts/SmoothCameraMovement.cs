using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SmoothCameraMovement : MonoBehaviour
{
    // A reference to the player assigned in inspector
    public GameObject player;

    // Offset between camera and player set to (0, 2, 5) in inspector
    public Vector3 offset;

    // Time it will take to reach the target
    // The lower the number the quicker the camera moves to the player
    public float smoothTime = 0.3f;

    private Vector3 velocity = Vector3.zero;

    // A reference to the main camera
    private GameObject mainCamera;

    void Start()
    {
        // Get main camera reference
        mainCamera = GameObject.Find("Main Camera");
    }


    void FixedUpdate()
    {
        // Get the terget position that we want the camera to move to. i.e. player position + offset
        Vector3 cameraTargetPos = player.transform.position + offset;

        // Smoothly move the Main Camera towards the target position over time
        mainCamera.transform.position = Vector3.SmoothDamp(mainCamera.transform.position, cameraTargetPos, ref velocity, smoothTime);

        // Update camera's rotation to face them player
        transform.LookAt(player.transform);
    }
}
