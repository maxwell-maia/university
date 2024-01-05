#include <stdio.h>
#include <stdlib.h>
#include "genericLinkedList.h"

listElement* createEl(void* data, void (*printFunction)(void*))
{
    listElement* e = malloc(sizeof(listElement));
    if (e == NULL)
    {
        return NULL;
    }
    e->data = data;
    e->printFunction = printFunction;
    e->next = NULL;
    return e;
}

void traverse(listElement* start)
{
    listElement* current = start;
    while (current != NULL)
    {
        current->printFunction(current->data);
        current = current->next;
    }
}

void insertAfter(listElement* el, void* data, void (*printFunction)(void*))
{
    listElement* newEl = createEl(data, printFunction);
    listElement* next = el->next;
    newEl->next = next;
    el->next = newEl;
}

void deleteAfter(listElement* after)
{
    listElement* delete = after->next;
    listElement* newNext = delete->next;
    after->next = newNext;
    free(delete);
}

int length(listElement* list)
{
    int length = 0;
    listElement* current = list;
    while (current != NULL)
    {
        length++;
        current = current->next;
    }
    return length;
}

void push(listElement** list, void* data, void (*printFunction)(void*))
{
    listElement* newEl = createEl(data, printFunction);
    newEl->next = *list;
    *list = newEl;
}

listElement* pop(listElement** list)
{
    if (*list == NULL){
        return NULL;
    }

    listElement* head = *list;
    *list = head->next;
    head->next = NULL;
    return head;
}

void enqueue(listElement** list, void* data, void (*printFunction)(void*))
{
    listElement* newEl = createEl(data, printFunction);
    newEl->next = *list;
    *list = newEl;
}

listElement* dequeue(listElement** list)
{
    if (*list == NULL)
    {
        return NULL;
    }

    if ((*list)->next == NULL)
    {
        listElement* dequeued = *list;
        *list = NULL;
        return dequeued;
    }

    listElement* current = *list;
    while (current->next->next != NULL)
    {
        current = current->next;
    }

    listElement* dequeued = current->next;
    current->next = NULL;

    return dequeued;
}
