using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AutoDestroy : MonoBehaviour
{
    private float lifetime = 2.85f;

    // Start is called before the first frame update
    void Start()
    {
        lifetime = Random.Range(1.15f, 3.45f);
    }

    void Awake()
    {
        StartCoroutine(ProcessLifetime());
    }

    private IEnumerator ProcessLifetime()
    {
        yield return new WaitForSeconds(lifetime);
        Destroy(this.gameObject);
    }
}
