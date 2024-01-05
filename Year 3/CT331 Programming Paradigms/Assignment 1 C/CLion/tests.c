#include <stdio.h>
#include "tests.h"
//#include "linkedList.h"
#include "genericLinkedList.h"

// Custom print function for integers
void printInt(void* data) {
    printf("%d\n", *(int*)data);
}

// Custom print function for strings
void printStr(void* data) {
    printf("%s\n", (char*)data);
}


void runTests(){

/*
  printf("Tests running...\n");
  listElement* l = createEl("Test String (1).", 30);
  //printf("%s\n%p\n", l->data, l->next);
  //Test create and traverse
  traverse(l);
  printf("\n");

  //Test insert after
  listElement* l2 = insertAfter(l, "another string (2)", 30);
  insertAfter(l2, "a final string (3)", 30);
  traverse(l);
  //Test length
  printf("length = %d\n", length(l));
  printf("\n");

  // Test delete after
  deleteAfter(l);
  traverse(l);
  printf("\n");

  printf("\nTest push\nThis is the list before the push\n");

  traverse(l);

  //Test push
  printf("\nPushing a string to the head...\n");
  push(&l, "If you see this then push worked!", 30);
  traverse(l);

  printf("\n\nTest pop\nThis is the list before the pop\n");

  //Test pop
  printf("\nPop from the head...\n");
  pop(&l);
  traverse(l);

  printf("\n\nTest enqueue\nThis is the list before the enqueue\n");
  traverse(l);

  //Test enqueue
  printf("\nEnqueue a string to the head...\n");
  enqueue(&l, "If you see this then enqueue worked!", 30);
  traverse(l);

  printf("\n\nTest dequeue\nThis is the list before the dequeue\n");
  traverse(l);

  //Test dequeue
  printf("\nDequeue from the head...\n");
  dequeue(&l);
  traverse(l);

  printf("\nTests complete.\n");

*/





  printf("\n\n\nTesting Question 3.\n");

  printf("\nCreate an empty list...\n");

  // Create a generic linked list
  listElement* myList = NULL;

  printf("\nPush an integer onto the head of the list...\n");
  // Push an integer onto the head of the list
  int intValue = 42;
  push(&myList, &intValue, printInt);

  printf("\nEnqueue a string onto the head of the list...\n");

  // Enqueue a string onto the head of the list
  char* strValue = "Hello, world!";
  enqueue(&myList, strValue, printStr);

  printf("\n\nLet's look at the list...\n");

  traverse(myList);

  printf("\n\nPop an element from the head of the list and print it...\n");

  // Pop an element from the head of the list and print it
  listElement* poppedElement = pop(&myList);
  if (poppedElement) {
      poppedElement->printFunction(poppedElement->data);
  }

  printf("\n\nDequeue an element from the tail of the list and print it...\n");

  // Dequeue an element from the tail of the list and print it
  listElement* dequeuedElement = dequeue(&myList);
  if (dequeuedElement) {
      dequeuedElement->printFunction(dequeuedElement->data);
  }

  printf("\n\nLet's look at the list again\n");

  // Traverse the list and print the remaining elements
  traverse(myList);

    printf("\n\n\nQuestion 3 tests complete.\n");
}