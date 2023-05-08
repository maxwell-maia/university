/*
Maxwell Maia
21236277
15 February 2022
*/

#include <stdio.h>
#include <time.h>
#include <string.h>
#include <stdlib.h>


typedef struct {
	int day, month, year;
} date;

typedef struct {
	char name[50];
	int accountNumber;
	double balance;
	date lastTrans;
} customer;


void getCustomerName(customer* cptr);
void getAccountNumber(customer* cptr);
void getLastTransDate(customer* cptr);
void getBalance(customer* cptr);
void printCustomer(customer* cptr);


void main() {
	int i;
	char temp[100];
	customer customers[3];

	for (i = 0; i < 3; i++) {
		getCustomerName(&customers[i]);
		getAccountNumber(&customers[i]);
		getLastTransDate(&customers[i]);
		getBalance(&customers[i]);
		gets_s(temp, 100);
		printf("\n");
	}
	printf("\n\n%25s\t%13s\t%12s\t%s\n\n", "Name", "Account Number",
		"Balance", "Date of Last Transaction");
	for (i = 0; i < 3; i++) {
		printCustomer(&customers[i]);
	}
}



void getCustomerName(customer* cptr)
{
	printf("Enter Customer Name: ");
	scanf_s("%s", cptr->name, 50);
}


void getAccountNumber(customer* cptr)
{
	printf("Enter Account Number: ");
	scanf_s("%d", &cptr->accountNumber);
}



void getLastTransDate(customer* cptr)
{
	printf("Enter last transaction date (dd/mm/yyyy): ");
	scanf_s("%d/%d/%d", &cptr->lastTrans.day, &cptr->lastTrans.month, &cptr->lastTrans.year);
}

void getBalance(customer* cptr)
{
	printf("Enter Current balance: ");
	scanf_s("%lf", &cptr->balance);
}

void printCustomer(customer* cptr) //print the info of input customer pointed to
{
	printf("\t\t%s\t\t%d\t\t%.2lf\t\t%d/%d/%d\n\n", cptr->name, cptr->accountNumber, cptr->balance, (*cptr).lastTrans.day, (*cptr).lastTrans.month, (*cptr).lastTrans.year);
}