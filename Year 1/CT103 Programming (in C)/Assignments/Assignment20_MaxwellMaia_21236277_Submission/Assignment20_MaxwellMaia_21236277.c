/*
Maxwell Maia
21236277
1st March 2022
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

//char dictionary[50][100000];
#define MAXSTRING 50

int readDictionary(); //read in dictionary to array. And return size of array.
int random(int max);

typedef struct {
	char dictionaryWord[50];
}dictionary;

dictionary word[100000];

void main()
{
	srand(time(NULL)); //seed random


	//Read the dictionary into an array and get size of array.
	int size = readDictionary();
	printf("Successfully loaded %d words from the dictionary.\n\n", size);
	

	//Find a random word from the array
	char randomWord[50];
	
	int randomIndex = random(size);

	strcpy_s(randomWord, 50, word[randomIndex].dictionaryWord);
	

	//Make a string of -'s the length of the random word.
	int randomWordLength = strlen(randomWord);

	char dashedWord[50] = "";

	for (int k = 0; k < randomWordLength; k++)
	{
		dashedWord[k] = '-';
	}
	printf("Dashed word = %s\n\n", dashedWord);


	//GUESSING
	int wordFound = 0;
	int attempt = 1;
	char guess = "";
	char enterKey = "";

	while (wordFound == 0)
	{
		printf("\n\nAttempt %d.\n============\n\n", attempt);

		printf("Enter a 1 letter guess: "); 
		scanf_s("%c", &guess, 1); //Get a guess from user
		scanf_s("%c", &enterKey, 1); //take the enter key character from the input buffer.

		printf("\nYour guess was: %c\n", guess);

		//CHECK IF GUESS WAS IN WORD AND CHANGE AT ALL INSTANCES.
		//Loop through random word. If the letter == guess then set the change.
		int letterFound = 0;
		for (int i = 0; i < randomWordLength; i++)
		{
			if (randomWord[i] == guess)
			{
				dashedWord[i] = guess;
				letterFound = 1;
			}
		}

		if (letterFound == 1)
		{
			printf("Your guess was successful.");
		}
		else
		{
			printf("Try again.");
		}
		printf("\n\nDashed word = %s\n\n", dashedWord);

		//CHECK IF THE WORD IS COMPLETE
		if (strcmp(dashedWord, randomWord) == 0)
		{
			printf("Well done, that took you %d guesses to find %s.", attempt, randomWord);
			wordFound = 1;
		}

		attempt++;
	}
}



int readDictionary()
{
	//Read the dictionary into an array
	dictionary newWord;

	//Open file
	FILE* fptr;
	fopen_s(&fptr, "C:\\Users\\Maxie\\Desktop\\dictionary.txt", "r");

	char tempWord[MAXSTRING];
	int tempLength = 0;
	int size = 0;
	char delims[] = "\n";

	if (fptr == NULL)
	{
		printf("The dictionary could not be read.");
	}
	else
	{
		while (!feof(fptr) && size < 100000)
		{
			fgets(tempWord, 50, fptr); //Get the line from dictionary file.

			//Remove the \n
			char* next = NULL;
			char* first = strtok_s(tempWord, delims, &next);

			if (strlen(first) >= 4 && strlen(first) <= 7) //Check the length.
			{
				strcpy_s(newWord.dictionaryWord, 50, first); //Copy the word into the array.
				word[size] = newWord;
				size++;
			}
		}
		fclose(fptr);
	}

	return size;
}



int random(int max) //return a random intger from 0 to max
{
	return rand() % max;
}