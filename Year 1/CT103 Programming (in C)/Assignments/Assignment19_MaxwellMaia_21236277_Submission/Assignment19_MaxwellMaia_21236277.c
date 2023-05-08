/*
Maxwell Maia
21236277
22/02/2022
*/

#include <stdio.h>
#include <string.h>

typedef struct {
	char make[20];
	char model[20];
	int year;
}car;

car garage[10];

void readCars(char myfilePath[], int numCars);
void displayGarage(int numCars);
int checkYear(int numCars, int year);

void main()
{
    char myfilePath[] = "C:\\Users\\Maxwell SSD\\Desktop\\W19\\carsYear.csv";
    readCars(myfilePath, 10);
    displayGarage(10);
    int ans = checkYear(10, 2016);
    printf("There are %d cars with year %d in the garage.\n", ans, 2016);
}

void readCars(char myfilePath[], int numCars)
{
    FILE* fptr;
    char line[200];
    char delims[] = ",";
    char delimsM[] = " ";
    fopen_s(&fptr, myfilePath, "r");
    int i = 0;
    int yearInt = 0;

    if (fptr != NULL)
    {
        for(int j = 0; j <numCars; j++)
        {
            fgets(line, 200, fptr);
            //printf("\nLine = %s\n", line); //out
            

            char* next = NULL;
            char* first = strtok_s(line, delims, &next); //get make and model

            //printf("makemodel = %s\n\n", first);
            char makeModel[200];
            strcpy_s(makeModel, 200, first); //temporarily save the make and model string

            first = strtok_s(NULL, delims, &next); //get year as string
            char yearString[200];
            strcpy_s(yearString, 200, first);
            //printf("year = %s\n\n", first);

            yearInt = atoi(yearString); //convert yearString to integer
            garage[i].year = yearInt; //STORE YEAR

            //Separate Make and model
            char* nextM = NULL;
            char* firstM = strtok_s(makeModel, delimsM, &nextM);
            //printf("make = %s\n\n", firstM);
            strcpy_s(garage[i].make, 20, firstM); //STORE MAKE


            firstM = strtok_s(NULL, delimsM, &nextM);
            //printf("model = %s\n\n", firstM);
            strcpy_s(garage[i].model, 20, firstM); //STORE MODEL

            strcpy_s(line, 200, next);

            i++;
        }
    }
}

void displayGarage(int numCars)
{
    for (int i = 0; i < numCars; i++)
    {
        printf("Car: %d\n", i);
        printf("Make = %s\nModel = %s\nYear = %d\n\n", garage[i].make, garage[i].model, garage[i].year);
    }
}

int checkYear(int numCars, int year)
{
    int count = 0;
    for (int i = 0; i < numCars; i++)
    {
        if (garage[i].year == year)
        {
            count++;
        }
    }
    return count;
}