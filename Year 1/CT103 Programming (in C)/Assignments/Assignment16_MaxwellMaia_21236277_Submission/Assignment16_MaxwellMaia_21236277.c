/*
Maxwell Maia
21236277
1/2/2022
*/
#include <stdio.h>



void main()
{
	//Question 1
	FILE* fptr; //make a file pointer

	fopen_s(&fptr, "C:\\Users\\Maxie\\Desktop\\dogs.txt", "r+"); //open file for reading and writing
	if (fptr != NULL) //check that file was opened successfully
	{
		fseek(fptr, 0, SEEK_END); //set the position to the end of the file
		int len = ftell(fptr); //get the current file position. This int is the file size.
		printf("Size of dogs.txt is %d bytes.\n\n", len); //print file size
		fclose(fptr);
	}


	//Question 2

	fopen_s(&fptr, "C:\\Users\\Maxie\\Desktop\\dogs.txt", "r+"); //open file for reading and writing
	fseek(fptr, -3, SEEK_END); //go 3 spaces from end of file
	fputs("Boxer", fptr); //place "Boxer" there. adds some new bytes to file
	fclose(fptr);


	//Question 3
	
	fopen_s(&fptr, "C:\\Users\\Maxie\\Desktop\\countries.txt", "r+"); //open file for reading
	
	char line[200];
	char country[30];
	int population = 0;

	if (fptr != NULL) //make sure the file was read
	{
		int i = 0, j = 0;
		while (!feof(fptr)) //loop through the file until the end of the file
		{//(will be reading line by line)
			fgets(line, 200, fptr); //get the next (or first) line

			//get the country name
			while (line[i] != '\t') //until there isn't a tab charcter...
			{
				country[j] = line[i]; //...save the current character as a character of the country name.
				i++; //increment to move to the next character in line for next loop.
				j++; //increment to store the next character of country.
			}
			country[j] = '\0'; //end of string needs a \0
			i++;
			j = 0; //start at position 0 for next country name.

			population = atoi(&line[i]); //get the population and convert and save it as an integer
			printf("Country: %s\tPopulation: %d\n", country, population); //print a country name and population.
			i = 0, j = 0; //reset counters to 0.
		}
		fclose(fptr); //close file
	}
	


	//Question 4
	
	int mPopulation = 27690000;

	fopen_s(&fptr, "C:\\Users\\Maxie\\Desktop\\countries.txt", "a"); //open file for appending
	if (fptr != NULL)
	{
		fprintf(fptr, "\nMadagascar\t%d", mPopulation); //append text
	}
	
	fclose(fptr); //close file
	
}