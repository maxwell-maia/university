/*
Maxwell Maia
21236277
18/01/2022
*/

#include <stdio.h>
#include <math.h>

//Global variables for the ships actual location
int shipX = -1;
int shipY = -1;

int gridSize = 10;

//Compares random values for X and Y within the grid to the ship's location.
//Prints the ship's location when found and the number of locations checked.
void randomSearch(int gridSize);

//Sequentially counts up the grid comparing X and Y to the ship's actual location. Looped using recursion.
//Prints the ship's location when found and the number of locations checked.
void gridSearch(int gridSize, int gridLocationsTried);

void main()
{
	//Set the ship's actual location randomly.
	shipX = rand() % 9;
	shipY = rand() % 9;

	printf("The ship is located at %d, %d.\n\n", shipX, shipY);

	printf("Random search\n---------------------------\n");
	randomSearch(gridSize);
	printf("\n---------------------------\n\n");
	
	int gridLocationsTried = 0;
	printf("Grid search\n---------------------------\n");
	gridSearch(gridSize, gridLocationsTried);
	printf("\n---------------------------\n");

	printf("\n\n====================NEW GRID====================\n\n");
	//grid size is 7
	shipX = rand() % 6;
	shipY = rand() % 6;

	printf("The ship is located at %d, %d.\n\n", shipX, shipY);

	printf("Random search\n---------------------------\n");
	randomSearch(7);
	printf("\n---------------------------\n\n");

	gridLocationsTried = 0;
	printf("Grid search\n---------------------------\n");
	gridSearch(7, gridLocationsTried);
	printf("\n---------------------------\n");
}

void randomSearch(int gridSize)
{
	int found = 0; //will be used to end loop in while loop.
	int locationsTried = 0;

	//delcare.
	int randX = -1;
	int randY = -1;
	
	while (found == 0) //loop until ship is found.
	{
		//generate random numbers for X and Y
		randX = rand() % (gridSize - 1);
		randY = rand() % (gridSize - 1);

		locationsTried++;

		if (randX == shipX && randY == shipY) //compare random X Y to actual location.
		{
			//when the ship is found...
			found = 1; //make sure the loop will end
			printf("The ship was found at %d, %d.\n", randX, randY); //and print location and number of tries.
			printf("%d locations were tried.", locationsTried);
		}
	}
}

void gridSearch(int gridSize, int gridLocationsTried)
{
	//find current X and Y

	int X = -1;
	int Y = -1;

	X = gridLocationsTried % gridSize;
	Y = floor(gridLocationsTried / gridSize);


	if (X == shipX && Y == shipY) //compare current X and Y to ship's actual location.
	{
		printf("The ship was found at %d, %d. :)\n%d locations were tried.", X, Y, gridLocationsTried);
		return;
	}
	
	gridLocationsTried++;

	gridSearch(gridSize, gridLocationsTried); //send to next loop with new locations tried.
	//Sending the locations tried variable allows the program to find the next X and Y in the sequence.
}