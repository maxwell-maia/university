/*
Maxwell Maia
21236277
26 October 2021
*/

#include <stdio.h>

void main()
{
	//declare variables
	float sales[1000]; //array big enough not to be filled for this use program's use

	int i = 0;

	float totalSales = 0.0;
	float averageSales = 0.0;

	int additionalDays = 0;

	//ask for the daily sales 7 times
	for (i = 0; i < 7; i++)
	{
		printf("Enter sales total (Euro) for day %d: ", i + 1);
		scanf_s("%f", &sales[i]);
	}


	//calculate total sales the first time
	for (int j = 0; j < i; j++)
	{
		totalSales = totalSales + sales[j];
	}

	//display total sales
	printf("\nTotal sales is: %0.2f", totalSales);

	//calculate and then display average sales
	averageSales = totalSales / i;
	printf("\nAverage daily sales is: %0.2f", averageSales);

	//ask user amount of additional days to record and then store variable
	printf("\n\nHow many additional days would you like to record? ");
	scanf_s("%d", &additionalDays);


	//while there is an additonal day, ask the user for daily sales
	//(store the sales for additional days)
	int k = 0;
	while (k < additionalDays)
	{
		printf("Enter sales total (Euro) for day %d: ", i + 1);
		scanf_s("%f", &sales[i]);
		i++; //increase i to keep track of amount of sales entered into array.
		k++; //increment to run this loop again.
	}

	//if the user has added additonal days, display the updated values
	if (additionalDays > 0)
	{
		//calculate total sales the second time
		totalSales = 0.0; //set totalSales to zero to recalculate
		for (int j = 0; j < i; j++)
		{
			totalSales = totalSales + sales[j];
		}

		//display total sales
		printf("\n\nThe updated total sales is: %0.2f", totalSales);

		//calculate and display average sales for the second time.
		averageSales = totalSales / i;
		printf("\nThe updated average daily sales is: %0.2f\n", averageSales);
	}


	//print the sales for each day
	for (int m = 0; m < i; m++) //loop the size of the array (i)
	{
		printf("\nSales for day %d: %0.2f euro.", m + 1, sales[m]);
	}
}