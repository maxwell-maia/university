/*
Maxwell Maia
21236277
9 November 2021
*/

#include <stdio.h>
#include "string.h"

const float PI = 3.14159265358979323846;

void main()
{
	int loop = 1;

	char choice[50] = "hi";
	puts("Welcome to the Area Calculator!");

	puts("\nYou can calculate the area for: \nTriangle, Rectangle, Circle or Trapezium.\nType 'Done' to exit the program.\n");
	puts("Enter your choice: ");
	gets(choice); //get the user's choice

	while (loop == 1) 
	{
		if (strcmp(choice, "Triangle") == 0) // if the user's choice is Triangle, then calcate area for triangle.
		{
			puts("-------------------------------------------------");

			float base = 0.0;
			float height = 0.0;

			printf("Calculating the area of a triangle.\n");
			printf("Enter base: ");
			scanf_s("%f", &base); //ask user for base

			printf("\nEnter height: ");
			scanf_s("%f", &height); //ask user for height
			printf("Thank you. calculating area...\n");

			float area = 0.5 * base * height; //calculate area
			printf("Area = %0.2f", area); //display area
			puts("\n-------------------------------------------------");
		}
		else if (strcmp(choice, "Rectangle") == 0) // if the user's choice is Rectangle, then calcate area for rectangle.
		{
			puts("-------------------------------------------------");

			float width = 0.0;
			float height = 0.0;

			printf("Calculating the area of a Rectangle.\n");
			printf("Enter width: ");
			scanf_s("%f", &width); //ask user for base

			printf("\nEnter height: ");
			scanf_s("%f", &height); //ask user for height
			printf("Thank you. calculating area...\n");

			float area = width * height; //calculate area
			printf("Area = %0.2f", area); //display area
			puts("\n-------------------------------------------------");
		}
		else if (strcmp(choice, "Circle") == 0) // if the user's choice is Circle, then calcate area for Circle.
		{
			puts("-------------------------------------------------");

			float radius = 0.0;

			printf("Calculating the area of a circle.\n");
			printf("Enter radius: ");
			scanf_s("%f", &radius); //ask user for radius

			printf("Thank you. calculating area...\n");

			float area = radius * radius * PI; //calculate area
			printf("Area = %0.2f", area); //display area
			puts("\n-------------------------------------------------");
		}
		else if (strcmp(choice, "Trapezium") == 0) // if the user's choice is Trapezium or Trapezoid, then calcate area for Trapezium.
		{
			float height = 0.0;
			float a = 0.0;
			float b = 0.0;

			printf("Calculating the area of a Trapezium.\n");


			printf("Enter height: ");
			scanf_s("%f", &height); //ask user for height

			printf("Enter a: ");
			scanf_s("%f", &a); //ask user for a

			printf("Enter b: ");
			scanf_s("%f", &b); //ask user for b

			printf("Thank you. calculating area...\n");

			float aPlusB = a + b; //calculate area
			float area = 0.5 * (aPlusB) * height;
			printf("Area = %0.2f", area); //display area
			puts("\n-------------------------------------------------");
		}
		else if (strcmp(choice, "Done") == 0) 
		{
			//only end the program when the user types 'Done', otherwise loop the program again.
			return 0;
		}
		
		if(strcmp(choice, "Triangle") != 0 && strcmp(choice, "Rectangle") != 0 && strcmp(choice, "Circle") != 0 && strcmp(choice, "Trapezium") != 0 && strcmp(choice, "Done") != 0)
		{
			puts("Invalid choice. try again."); //message if the user didn't enter one of the options
		}

		// get next choice
		puts("\nYou can calculate the area for: \nTriangle, Rectangle, Circle or Trapezium.\nType 'Done' to exit the program.\n");
		puts("Enter your choice: ");
		gets(choice);
	}
}