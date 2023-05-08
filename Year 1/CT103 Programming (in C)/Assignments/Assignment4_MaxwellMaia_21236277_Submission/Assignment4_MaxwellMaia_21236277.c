/*
Maxwell Maia
21236277
19 October 2021
*/

#include <stdio.h>

void main()
{
	int age = 1;
	int i = 0;
	int youngest = 999999;
	int oldest = 0;
	float totalAge = 0;

	while (age > 0)
	{
		printf("Enter the age of child %d: ", i + 1); //ask for child age
		scanf_s("%d", &age); //retieve input age


		if (age < 0 || age >= 18) //prevent a negative number and over 18 yr old from affecting values
		{
			if (age >= 18) //if they are not a child print message and do nothing else. The loop will restart.
			{
				printf("Not a child.\n");
			}
		}
		else //if a child's age (not negative and not 18 or over) is input, perform calculations
		{
			totalAge = totalAge + age; //this will find the total age by adding each age to a variable

			if (age < youngest) //if the input age is smaller than the current youngest...
				youngest = age; //make the input the youngest

			if (age > oldest) //if the input age is bigger than the current oldest...
				oldest = age; //make the input age the oldest

			i++; //increment to keep track of times the loop has run
		}
	}

	printf("\nExiting while loop...\n");

	printf("There are %d children.\n", i); //the amount of children is the value of i

	float averageAge = totalAge / (i); //calculate the average age as a float to get decimal
	printf("The average age is %0.1f\n", averageAge); //display average age

	printf("The maximum age is %d.\n", oldest); //display oldest age
	printf("The minimum age is %d.\n", youngest); //display youngest age

}