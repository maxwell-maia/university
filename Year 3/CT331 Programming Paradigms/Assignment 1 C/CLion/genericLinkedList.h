#ifndef GENERICLINKEDLIST_H
#define GENERICLINKEDLIST_H

//Updated struct that stores a function pointer
typedef struct listElementStruct
{
    void* data;
    struct listElementStruct* next;
    void (*printFunction)(void*);
} listElement;

//Create a new linked list element with the given data and print function.
listElement* createEl(void* data, void (*printFunction)(void*));

//Traverse the linked list and print each element using its print function.
void traverse(listElement* start);

//Insert a new element with the given data after the specified element.
void insertAfter(listElement* el, void* data, void (*printFunction)(void*));

//Delete the element after the specified element.
void deleteAfter(listElement* after);

//Calculate the number of elements in the linked list.
int length(listElement* list);

//Push a new element onto the head of the list.
void push(listElement** list, void* data, void (*printFunction)(void*));

//Pop the element from the head of the list.
listElement* pop(listElement** list);

//Enqueue a new element onto the head of the list.
void enqueue(listElement** list, void* data, void (*printFunction)(void*));

//Dequeue an element from the tail of the list.
listElement* dequeue(listElement** list);


#endif
