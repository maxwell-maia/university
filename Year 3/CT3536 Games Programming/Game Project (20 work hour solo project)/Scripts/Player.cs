using System.Collections;
using System.Collections.Generic;
using UnityEngine;


// Attached to player GameObject (a capsule)
public class Player : MonoBehaviour {
    private Rigidbody rigid;

    // The model is the child of the Player GameObject. This child contains the player's model
    public GameObject modelParent;
    private Transform modelTransform;

    public LayerMask jumpableLayer;

    private float sphereRadius = 0.5f;
    private float distanceFromPlayerCentreToFeet = 1f;
    public float jumpForce = 10000f;
    public float movementForce = 100000f;
    public float customGravity = -19.62f;

    private int raycastHitCount = 0;
    private RaycastHit[] hits = new RaycastHit[1];
    private bool isGrounded = false;

    private float coyoteTime = 0.1f; // Duration of coyote time
    private float coyoteTimer = 0f;
    private bool hasJumped = true;
    // Pre-jump buffer time
    // A prejump is when you press the jump button before touching the ground
    // As long the player pressed jump within a certain amount of time before touching the ground,
    // the game will automatically jump for the player when the Player touches the ground
    private float preJumpBuffer = 0.15f;
    private float preJumpTimer = 0f;
    // Prejump is not allowed immediately after jumping (prevent automatic jump the frame after you jump)
    // Without this boolean you get a double jump every time you jump
    private bool preJumpAllowed = false;

    private Animator playerAnimator;

    public GameObject raceManager;

    public GameObject player;

    private GameObject mainCamera;

    private Camera childCamera;

    void Start () {
        // Get the Player capsule's rigidbody
		rigid = GetComponent<Rigidbody>();

        // Get the model's transform
        modelTransform = modelParent.transform;

        // Get the animator
        playerAnimator = modelTransform.GetComponent<Animator>();

        if (modelTransform == null)
        {
            Debug.LogError("Model transform not found!");
        }

        // Set custom gravity
        Physics.gravity = new Vector3(0, customGravity, 0);

        raceManager = GameObject.Find("RaceManager");

        player = GameObject.Find("Player");

        mainCamera = GameObject.Find("Main Camera");

        childCamera = mainCamera.GetComponentInChildren<Camera>();

        // Updating players struct in GameManager
        // Assign player gameobject
        GameManager.instance.players[0].player = player;
        // Set isAI to false
        GameManager.instance.players[0].isAI = false;
    }
	
	void Update () {
        // Check if the player is grounded
        // Use NonAlloc version for efficiency. Cuts down on new and delete operations
        raycastHitCount = Physics.SphereCastNonAlloc(this.transform.position, sphereRadius, Vector3.down, hits, distanceFromPlayerCentreToFeet - sphereRadius, jumpableLayer);

        // Update isGrounded boolean based on result of raycast
        isGrounded = raycastHitCount > 0;

        // Update isJumping parameter for player's jumping animations
        playerAnimator.SetBool("IsJumping", !isGrounded);

        // Update coyote timer
        if (isGrounded)
        {
            // Movement force on ground
            movementForce = 100000f;

            // Pre-jump automatic jump
            if ((preJumpTimer > 0f) && preJumpAllowed)
            {
                // Set velocity to 0 before jump to ensure all jumps are equal
                rigid.velocity = new Vector3(rigid.velocity.x, 0f, rigid.velocity.z);
                // Jump
                rigid.AddForce(Vector3.up * jumpForce);
                // Cannot prejump until jump button is pressed again (prevent double jump)
                preJumpAllowed = false;

                // Set the jump flag when jumping
                hasJumped = true;
                preJumpTimer = 0f;
            }

            coyoteTimer = coyoteTime;

            // Reset the jump flag when grounded
            hasJumped = false;
        }
        else
        {
            // Movement force in the air
            movementForce = 10000f;

            // Decrement timers while not grounded for jump mechanics
            coyoteTimer -= Time.deltaTime;
            preJumpTimer -= Time.deltaTime;
        }

        // Check for spacebar key or controller button (A on Xbox controller) during coyote time
        if (Input.GetKeyDown(KeyCode.Space) || Input.GetButtonDown("Submit"))
        {
            // Start the pre-jump buffer timer
            preJumpTimer = preJumpBuffer;
            preJumpAllowed = true;

            if ((coyoteTimer > 0f) && !hasJumped)
            {
                // Set velocity to 0 before jump to ensure all jumps are equal
                rigid.velocity = new Vector3(rigid.velocity.x, 0f, rigid.velocity.z);
                // Jump
                rigid.AddForce(Vector3.up * jumpForce);
                // Cannot prejump until jump button is pressed again (prevent double jump)
                preJumpAllowed = false;
                // Set the jump flag when jumping
                hasJumped = true;
            }
        }
    }

    void FixedUpdate() {
        // Move the player with respect to the camera
        float h = Input.GetAxis("Horizontal");
        float v = Input.GetAxis("Vertical");
        
        Vector3 camRight = Camera.main.transform.right;
        camRight.y = 0f;
        camRight = camRight.normalized;
        Vector3 camForward = Camera.main.transform.forward;
        camForward.y = 0f;
        camForward = camForward.normalized;

        // Move Player ignoring contorller deadzones
        if (Mathf.Abs(h) > 0.05f || Mathf.Abs(v) > 0.05f)
        {
            Vector3 moveDirection = h * camRight + v * camForward;
            rigid.AddForce(moveDirection * Time.fixedDeltaTime * movementForce);

            // Rotate the model to face the movement direction
            if (modelTransform != null)
            {
                modelTransform.forward = moveDirection.normalized;
            }

            // Update animator parameter for running
            playerAnimator.SetFloat("Speed", 1f);
        }
        else
        {
            // Update animator parameter for idle
            playerAnimator.SetFloat("Speed", 0f);
        }        
    }

    void OnTriggerEnter(Collider collider) {
        // Hit by trap
		if (collider.gameObject.layer == GameManager.TrapLayer) {
            // Set the player's velocity to 0
            rigid.velocity = Vector3.zero;

            // Play a sound
            GameManager.instance.PlayHitByTrapClip();

            // Get last checkpoint
            float lastCheckpointZ = raceManager.GetComponent<RaceManager>().LastCheckpoint(player.transform.position.z);
            // Teleport player to last checkpoint
            player.transform.position = new Vector3(0f, 1.5f, lastCheckpointZ);
            // Bring the camera too
            childCamera.transform.position = new Vector3(childCamera.transform.position.x, childCamera.transform.position.y, lastCheckpointZ - 3);
            // Shake the camera
            CameraShake.Shake(0.06f, 0.1f);
        }
    }
}