/*
Maxwell Maia
21236277
16 November 2021
*/

#include <stdio.h>
#include <math.h>

//function prototypes
float calculateVolume(float l, float w, float h);

void printMass(float v);

int barrels(float v);

void main()
{
	//declare variables
	float length = 0.0, width = 0.0, height = 0.0;
	float volume = 0.0;

	puts("Pool volume calculator");

	//get length, width, height as input from user
	puts("Please enter information about your pool.");
	printf("Enter length: ");
	scanf_s("%f", &length);
	printf("Enter width: ");
	scanf_s("%f", &width);
	printf("Enter height: ");
	scanf_s("%f", &height);

	printf("\n");

	volume = calculateVolume(length, width, height); //calculate volume
	printf("Volume = %0.2f meters cubed.\n", volume); //print volume

	printMass(volume);

	printf("\nBarrels = %d", barrels(volume));
}

float calculateVolume(float l, float w, float h) //function to calculate volume given length, width and height variables
{
	return (l * w * h);
}

void printMass(float v) //function that prints mass of water given the volume
{
	float mass = v * 1000; //calculate mass of water 
	printf("The mass of the water is %0.2f kg.", mass); //print mass of water
}

int barrels(float v) //function to calculate barrels to fill pool given the volume and each barrel is 0.48 m3
{
	int amount = 0;

	amount = round(v / 0.48);

	return amount;
}