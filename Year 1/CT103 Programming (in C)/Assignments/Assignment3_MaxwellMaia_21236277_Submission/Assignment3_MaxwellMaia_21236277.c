/*
Maxwell Maia
21236277
12 October 2021
*/

#include <stdio.h>

void main()
{
	//declare & initialize variables for the total of each day's sales
	float sales1 = 0.0;
	float sales2 = 0.0;
	float sales3 = 0.0;
	float sales4 = 0.0;

	//request user to input the sales
	printf("Please enter the total daily sales for 4 seperate days.\nSeparate each number with a space: ");

	//put the user input into the sales variables
	scanf_s("%f %f %f %f", &sales1, &sales2, &sales3, &sales4);

	//calculate average sales over the 4 days
	float averageSales = (sales1 + sales2 + sales3 + sales4) / 4;

	//print average sales
	printf("\nAverage daily sales: %0.2f euro", averageSales);

	//If sales are below 10 000: say sales are low.
	//If sales are from 10 000 to 15 000: say sales are normal.
	//If sales are above 15 000: say sales are high.
	if (averageSales < 10000)
	{
		printf("\nSales are low.");
	}
	else if (averageSales > 15000)
	{
		printf("\nSales are high.");
	}
	else
	{
		printf("\nSales are normal.");
	}

	//calculate and say the average spend per customer for each day. Average number of customers is 500.
	printf("\nAverage spend per customer for day 1: %0.2f euro", sales1 / 500);
	printf("\nAverage spend per customer for day 2: % 0.2f euro", sales2 / 500);
	printf("\nAverage spend per customer for day 3: %0.2f euro", sales3 / 500);
	printf("\nAverage spend per customer for day 4: %0.2f euro", sales4 / 500);
}