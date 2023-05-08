/*
Maxwell Maia
21236277
2 November 2021
*/

#include <stdio.h>
#include <math.h>

void main()
{
	int i = 0;

	int rating[21];
	int tempRating = 1;

	float totalRating = 0.0;
	float averageRating = 0.0;

	int ratingFrequency[5] = { 0,0,0,0,0 };

	//read in ratings
	for (i = 0; i < 21;)
	{
		printf("Enter rating number %d: ", i + 1);
		scanf_s("%d", &tempRating);

		if (tempRating > 0 && tempRating < 6) //only allow input of numbers 1 - 5
		{
			rating[i] = tempRating; //add rating to array of ratings
			totalRating = totalRating + rating[i]; //sum ratings (will to calculate average later)


			switch (rating[i]) //increment the rating frequency array depending on the number (1-5)
			{
			case 1:
				ratingFrequency[0] = ratingFrequency[0] + 1;
				break;
			case 2:
				ratingFrequency[1] = ratingFrequency[1] + 1;
				break;
			case 3:
				ratingFrequency[2] = ratingFrequency[2] + 1;
				break;
			case 4:
				ratingFrequency[3] = ratingFrequency[3] + 1;
				break;
			case 5:
				ratingFrequency[4] = ratingFrequency[4] + 1;
				break;
			default: printf("\nError: default is printed in switch case\n");
			}

			i++;
		}
		else
		{
			printf("\nRating is a number from 1 - 5 stars.\n");
		}
	}

	//average rating
	averageRating = totalRating / i; //calculate
	printf("\nAverage rating = %0.1f", averageRating); //display




	printf("\n\nRating frequency\n--------------------\n");

	//printing the rating frequency of 1 star ratings
	int a = 0;
	printf("1 star\t");
	for (a = 0; a < ratingFrequency[0]; a++) //print a star for every 1 star rating made
	{
		printf("*");
	}
	printf("\t%d\n", a);
	
	//printing the rating frequency of 2 star ratings
	int b = 0;
	printf("2 star\t");
	for (b = 0; b < ratingFrequency[1]; b++) //print a star for every 2 star rating made
	{
		printf("*");
	}
	printf("\t%d\n", b);

	int c = 0;
	printf("3 star\t");
	for (c = 0; c < ratingFrequency[2]; c++) //print a star for every 3 star rating made
	{
		printf("*");
	}
	printf("\t%d\n", c);

	int d = 0;
	printf("4 star\t");
	for (d = 0; d < ratingFrequency[3]; d++) //print a star for every 4 star rating made
	{
		printf("*");
	}
	printf("\t%d\n", d);

	int e = 0;
	printf("5 star\t");
	for (e = 0; e < ratingFrequency[4]; e++) //print a star for every 5 star rating made
	{
		printf("*");
	}
	printf("\t%d\n", e);




	printf("\n\nRating Percent\n--------------------\n");

	//printing the rating percentage is the same process as before, but the amount of stars is calculated as a percentage and rounded
	printf("1 star\t");
	for (a = 0; a < (round((ratingFrequency[0] / 21.0) * 100)); a++) //print a star for the percentage 1 star of ratings made
	{
		printf("*");
	}
	printf("\t%0.1f%%\n", (ratingFrequency[0] / 21.0) * 100);

	printf("2 star\t");
	for (b = 0; b < (round((ratingFrequency[1] / 21.0) * 100)); b++) //print a star for the percentage 2 star of ratings made
	{
		printf("*");
	}
	printf("\t%0.1f%%\n", (ratingFrequency[1] / 21.0) * 100);

	printf("3 star\t");
	for (c = 0; c < (round((ratingFrequency[2] / 21.0) * 100)); c++) //print a star for the percentage 3 star of ratings made
	{
		printf("*");
	}
	printf("\t%0.1f%%\n", (ratingFrequency[2] / 21.0) * 100);

	printf("4 star\t");
	for (d = 0; d < (round((ratingFrequency[3] / 21.0) * 100)); d++) //print a star for the percentage 4 star of ratings made
	{
		printf("*");
	}
	printf("\t%0.1f%%\n", (ratingFrequency[3] / 21.0) * 100);

	printf("5 star\t");
	for (e = 0; e < (round((ratingFrequency[4] / 21.0) * 100)); e++) //print a star for the percentage 5 star of ratings made
	{
		printf("*");
	}
	printf("\t%0.1f%%\n", (ratingFrequency[4] / 21.0) * 100);

}