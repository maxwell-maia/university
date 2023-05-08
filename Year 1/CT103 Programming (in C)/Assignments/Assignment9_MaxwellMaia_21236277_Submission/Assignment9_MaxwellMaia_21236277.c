/*
Maxwell Maia
21236277
23 November 2021
*/

#include <stdio.h>
#include <string.h>
#include <math.h>


float readInTime();
float roundTime(float timeIn);
float price(float time);
void printPriceTime(float time, float price);

float dayRate = 23.9;
float nightRate = 12.6;

void main()
{
	float time = 0.0;

	time = readInTime(); //get time from user

	printf("Time = %0.2f\n", time); //time time
	
	printf("Rounded up time = %0.2f\n", roundTime(time)); //print rounded time

	printPriceTime(roundTime(time), price(roundTime(time))); //print time and price
}

float readInTime() //asks for user input and returns time
{
	float hour = 0.0;
	float minute = 0.0;
	float time = 0.0;



	printf("Enter current hour: "); //get the hour
	scanf_s("%f", &hour);
	
	if (hour < 0) //check hour ranges
	{
		hour = 0;
	}
	
	if (hour > 24)
	{
		hour = 24;
	}
	
	
	printf("Enter current minute: "); //get the minute
	scanf_s("%f", &minute);

	if (minute < 0) //check minute ranges
	{
		minute = 0;
	}
	
	if (minute > 60)
	{
		minute = 60;
	}

	
	minute = minute / 60; //get minutes

	time = hour + minute;

	return time;
}

float roundTime(float timeIn) //rounds time up
{
	return ceil(timeIn);
}

float price(float time) //returns the elec price depending on time
{
	float elecPrice = 0.0;

	//set electPrice to be day if hour is 8 - 24. else night
	elecPrice = time <= 24 && time >= 9 ? dayRate : nightRate;
	return elecPrice;
}

void printPriceTime(float time, float price)
{
	printf("Electricity price at time %0.2f is %0.2f", time, price);
}