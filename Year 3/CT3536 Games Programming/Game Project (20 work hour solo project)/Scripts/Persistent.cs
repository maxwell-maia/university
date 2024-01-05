using System.Collections;
using System.Collections.Generic;
using UnityEngine;

// Makes the object that this is attached to persist when changing scenes
public class Persistent : MonoBehaviour {
    void Awake()
    {
        DontDestroyOnLoad(this.gameObject);
    }
}