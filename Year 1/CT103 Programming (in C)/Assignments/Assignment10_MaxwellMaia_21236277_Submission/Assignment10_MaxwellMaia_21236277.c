/*
Maxwell Maia
21236277
31/11/2021
*/

#include <stdio.h>
#include <stdlib.h>

int setPasscode(int n);
void randomPasscodeSearch(int passcode, int n);
void orderedPasscodeSearch(int passcode, int pd);

void main()
{
	printf("WELCOME TO THE PASSCODE CRAKER 9 MILLIon!\n");
	printf("---------------------------------------------------\n\n");

	int pass1 = setPasscode(9);
	printf("First passcode is: %d.\n", pass1);
	randomPasscodeSearch(pass1, 9);
	orderedPasscodeSearch(pass1, 1);
	printf("\n");

	int pass2 = setPasscode(99);
	printf("Second passcode is: %d.\n", pass2);
	randomPasscodeSearch(pass2, 99);
	orderedPasscodeSearch(pass2, 2);
	printf("\n");


	int pass3 = setPasscode(999);
	printf("Third passcode is: %d.\n", pass3);
	randomPasscodeSearch(pass3, 999);
	orderedPasscodeSearch(pass3, 3);
	printf("\n");

	int pass4 = setPasscode(9999);
	printf("4th passcode is: %d.\n", pass4);
	randomPasscodeSearch(pass4, 9999);
	orderedPasscodeSearch(pass4, 4);
	printf("\n");

	int finalPass = 277;
	printf("The final passcode is: %d.\n", finalPass);
	randomPasscodeSearch(finalPass, 999);
	orderedPasscodeSearch(finalPass, 3);
}

int setPasscode(int n)
{
	printf("Now generating passcode. Passcode maximum value is: %d.\n", n);
	return rand() % n; //random number between 0 and n
}

void randomPasscodeSearch(int passcode, int n)
{
	int attempt = rand() % n; //the first attempt
	int count = 1;
	while (attempt != passcode) //keep generating random codes until a match is found
	{
		attempt = rand() % n;
		count++;
	}
	printf("Brute force search...\nPasscode is %d. It took %d attempts to randomly guess the passcode.\n", attempt, count);
}

void orderedPasscodeSearch(int passcode, int pd)
{
	//find the maximum number for the maximum digits
	int maximumAttempt = 0;
	switch (pd)
	{
	case 1 :
		maximumAttempt = 9;
		break;
	case 2 :
		maximumAttempt = 99;
		break;
	case 3 :
		maximumAttempt = 999;
		break;
	case 4 :
		maximumAttempt = 9999;
		break;
	case 5 : 
		maximumAttempt = 99999;
		break;
	case 6 : 
		maximumAttempt = 999999;
		break;
	case 7 : 
		maximumAttempt = 9999999;
		break;
	default :
		maximumAttempt = 0;
	}

	int attempt = 0; //the passcode being checked.
	int count = 1; //count the number of times it takes.
	while (attempt != passcode) //keep counting codes until a match is found
	{
		attempt++;
		count++;
	}

	printf("Ordered search...\nPasscode is %d. It took %d attempts to guess the passcode.\n", attempt, count);
}