/*
Maxwell Maia
21236277
25/01/2022
*/

#include <stdio.h>


void main()
{
	FILE *fptr;
	fopen_s(&fptr, "C:\\Users\\Maxie\\source\\repos\\Assignment15_MaxwellMaia_21236277\\Assignment15_MaxwellMaia_21236277\\SampleData.txt", "r");

	if (fptr == NULL)
	{
		puts("Error opening file.\nExiting..."); //the file didn't open correctly
		return;
	}
	else
	{
		char c = fgetc(fptr);
		while (c != EOF)
		{
			printf("%c", c);
			c = fgetc(fptr);
		}
	}
	fclose(fptr);


	//Question 2
	printf("\n\n\n----------------------PART 2----------------------\n\n");

	//open file for reading
	//FILE *fptr;
	fopen_s(&fptr, "C:\\Users\\Maxie\\source\\repos\\Assignment15_MaxwellMaia_21236277\\Assignment15_MaxwellMaia_21236277\\SampleData.txt", "r");
	
	int day = 0;
	int month = 0;
	int year = 0;
	char region[20];
	char rep[20];
	char item[20];
	int units;
	float unitCost;
	float total = 0;

	float totalIncome = 0.0;

	//check if file opened.
	char line[101];
	if (fptr == NULL)
	{
		puts("Error Opening File.\nExiting..."); //the file didn't open correctly
		return;
	}
	else
	{
		fgets(line, 101, fptr);
		printf(line, 101);

		int k = 0;
		
		while (!feof(fptr)) //loop throught the orders until the end of the file
		{
			//scan until delimiter. store value in variable for each piece of information for a order.
			fscanf_s(fptr, "%d/", &month);
			fscanf_s(fptr, "%d/", &day);
			fscanf_s(fptr, "%d\t", &year);
			fscanf_s(fptr, "%s\t", region, 20);
			fscanf_s(fptr, "%s\t", rep, 20);
			fscanf_s(fptr, "%s\t", item, 20);
			fscanf_s(fptr, "%d\t", &units);
			fscanf_s(fptr, "%f\t", &unitCost);
			fscanf_s(fptr, "%f\n", &total);

			//increase total
			totalIncome += total;

			//print info to file
			printf_s("%d/%d/%d\t%s\t%s\t%s\t%d\t%f\t%f\n", month, day, year, region, rep, item, units, unitCost, total);


			k++; //count amount of orders scanned
		}

		//print total and average income
		printf("\nCurrent Total: %0.2f", totalIncome);
		printf("\nAverage income: %0.2f\n", totalIncome/k);
	}


	fclose(fptr); //close the file


	printf("\n\n\n----------------------PART 3----------------------\n\n");

	//set the variables to what we want to enter
	day = 25;
	month = 1;
	year = 2022;
	region[20] = "Galway";
	rep[20] = "Maia";
	item[20] = "Pen";
	units = 77;
	unitCost = 19.99;
	total = units*unitCost;

	//open file for appending
	fopen_s(&fptr, "C:\\Users\\Maxie\\source\\repos\\Assignment15_MaxwellMaia_21236277\\Assignment15_MaxwellMaia_21236277\\SampleData.txt", "a");
	fprintf(fptr, "%d/%d/%d\t%s\t%s\t%s\t%d\t%0.2f\t%0.2f\n", month, day, year, "Galway", "Maia", "Pen", units, unitCost, total); //print info into file
	fclose(fptr); //close file
}