/*
Maxwell Maia
21236277
08/02/2022
*/

#include <stdio.h>


void printDoubleArray(double* dp, int len);
void swapIntegerValues(int* i1, int* i2);
void squareIntArray(int* i1, int len);
void printIntegerArrayBackwards(int* arr, int len);
void randomIntArray(int* arr, int len, int max);

void main()
{
	int num1 = 4;
	int num2 = 12;
	double arr1[] = { 1.50, 2.30, 4.70, 8.90 };
	int arr2[] = { 1, 2, 3, 4, 5 };

	printDoubleArray(arr1, 4);
	swapIntegerValues(num1, num2);
	squareIntArray(arr2, 5);
	printIntegerArrayBackwards(arr2, 5);
	randomIntArray(arr2, 5, 100);

	for (int i = 0; i < 5; i++)
	{
		printf("%d\t", arr2[i]);
	}
}

void printDoubleArray(double* dp, int len)
{
	printf("Q1 Double array...\n");
	for (int i = 0; i < len; i++) //loop through and print
	{
		printf("%0.2lf\t", *(dp + i));
	}
}

void swapIntegerValues(int* i1, int* i2)
{
	printf("Q2 Integer swap...\n");
	prinf("x = %d, y = %d\n", i1, i2);

	int temp = 0;
	temp = i1; //temporarily store value to swap
	i1 = i2; //swap
	i2 = temp;
	printf("x = %p, y = %p\n", i1, i2);
}

void squareIntArray(int* i1, int len)
{
	printf("Q3 Square array...\n");
	for (int i = 0; i < len; i++)
	{
		*i1 = (*i1) * (*i1);
		printf("%d\t", *i1);
		i1++;
	}
}

void printIntegerArrayBackwards(int* arr, int len)
{
	printf("Q4 Array backwards...\n");
	arr += (len - 1);
	for (int i = len - 1; i >= 0; i--) //reverse loop and print.
	{
		printf("%d\t", *arr);
		arr--;
	}
}

void randomIntArray(int* arr, int len, int max)
{
	printf("Q5 Random integers...\n");
	for (int i = 0; i < len; i++) //loop array
	{
		*(arr + 1) = (rand() % (max + 1)); //set array value to be a number from 1 to 100.
	}
}