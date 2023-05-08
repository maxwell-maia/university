// CT102 Algorithms
// Selection Sort and Insertion Sort functions
// 01/02/2022

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


void insertionSort(int[], int);
void selectionSort(int[], int);
void bubbleSort(int[], int);
void countSort(int[], int, int);

void quickSort(int arrA[], int startval, int endval);
void quickSortIt(int[], int, int);
int partition(int arrA[], int startval, int endval);
int partition2(int arrA[], int startval, int endval);
void swap(int* a, int* b);
void merge(int[], int, int, int);
void mergeSort(int[], int, int);

#define SIZE 5000
#define biggestValue 500

int swaps = 0;
int comparisons = 0;
int reCalls = 0;

int arrA[SIZE];

//testing functions in main()
void main()
{
	int i;
	FILE* infp;

	//read in to array
	fopen_s(&infp, "C:\\Users\\Maxie\\Desktop\\5000Ints2.txt", "r");
	if (NULL == infp)
	{
		fprintf(stderr, "Error opening file.\n");
		exit(1);
	}
	else
	{
		for (i = 0; fscanf_s(infp, "%d", &arrA[i]) == 1 && i < SIZE; i++);
	}
	fclose(infp);


	//int n = 10;
	//int A[] = { 2, 4, 6, 5, 10, 1, 3, 9, 7, 8 };


	//For calculating time taken.
	clock_t start_t, end_t, total_t; //declare variables
	start_t = clock(); //Get the current clock number. (Before the sort).


	//Sort runs here.
	//selectionSort(arrA, SIZE);
	//insertionSort(arrA, SIZE);
	//bubbleSort(arrA, SIZE);
	
	// COUNT SORT
	printf("\Count sort...");
	countSort(arrA, SIZE, biggestValue);

	/* QUICKSORT
	//quickSortIt(arrA, 0, SIZE);
	printf("\Quicksort...");
	quickSort(arrA, 0, SIZE);
	printf("\nSwaps = %d\n", swaps);
	printf("Comparisons = %d\n", comparisons);
	printf("Recursive calls = %d\n", reCalls);
	*/

	/* // MERGE SORT
	printf("\Merge sort...");
	mergeSort(arrA, 0, SIZE-1);
	printf("\nSwaps = %d\n", swaps);
	printf("Comparisons = %d\n", comparisons);
	printf("Recursive calls = %d\n", reCalls);

	*/


	printf("\nBefore the sort, run time = %ld clock ticks\n", start_t);

	end_t = clock(); //Get the current clock number. (After the sort).
	printf("Before the sort, run time = %ld clock ticks\n", end_t);
	total_t = (end_t - start_t) / (CLOCKS_PER_SEC / 1000); //Total seconds = ((end - start)/clocks_per_second). (then divide 1000 to get miliseconds).

	//Print the time taken to sort in miliseconds.
	printf("Time taken to sort: %ld miliseconds\n\n", total_t);

	/*
	printf("\nPrinting out Sorted array...\n\n");
	for (int i = 0; i < SIZE; i++)
	{
		printf("%d\n", i);
		printf("%d\n", arrA[i]);
	}
	*/
	


	/*
	//write sorted output to a file
	FILE* outfp;
	fopen_s(&outfp, "sortedInts.txt", "w");

	if (outfp == NULL) {
		fprintf(stderr, "Error opening file\n");
		exit(1);
	}
	else {
		for (i = 0; i < SIZE; i++) {
			fprintf(outfp, "%d\n", arrA[i]);
		}
	} 
	fclose(outfp);

	*/
 }


// what counts as a comparison. bc there are comparisons between n and i. Array value comparisons?


// Selection Sort: integer array arrA [] of size
void selectionSort(int arrA[], int size) {
	printf("\nSelection sort...");

	//Integers for counting swaps/moves and comparisons
	int swaps = 0;
	int comparisons = 0;

	int i, j, min, temp;

	for (i = 0; i < size - 1; i++) {
		min = i;
		//find next smallest
		for (j = min + 1; j < size; j++) {
			if (arrA[min] > arrA[j]) {
				min = j;
			}
			++comparisons;
		}

		//swap values at locations i and min, if i != min
		if (i != min) {
			temp = arrA[i];
			arrA[i] = arrA[min];
			arrA[min] = temp;
			++swaps;
		}
	} //end outer while

	//Print the amount of swaps and comparisons.
	printf("\nSwaps = %d\n", swaps);
	printf("Comparisons = %d\n", comparisons);

}


// Insertion Sort: integer array arrA [] of size
void insertionSort(int arrA[], int size)
{
	printf("\nInsertion sort...");

	//Integers for counting swaps/moves and comparisons
	int swaps = 0;
	int comparisons = 0;


	int i, j, curr;

	for (i = 1; i < size; i++) {

		curr = arrA[i];

		for (j = i - 1; j >= 0 && curr < arrA[j]; j--) {   //compare
			//make room ...
			arrA[j + 1] = arrA[j];
			++swaps;
			++comparisons;
		}
		++comparisons;

		if (i != j + 1) { // if not at the correct position already
			arrA[j + 1] = curr;
			++swaps;
		}
	} // end outer i for

	//Print the amount of swaps and comparisons.
	printf("\nSwaps = %d\n", swaps);
	printf("Comparisons = %d\n", comparisons);

} //return

//Bubble Sort
void bubbleSort(int arrA[], int size)
{
	printf("\nBubble sort...");

	//Integers for counting swaps/moves and comparisons
	int swaps = 0;
	int comparisons = 0;

	int i, k, temp;

	for (k = 0; k < size; k++) {
		for (i = 0; i < size - 1 - k; i++) {
			if (arrA[i] > arrA[i + 1]) {
				//out of order so swap
				++swaps;
				temp = arrA[i];
				arrA[i] = arrA[i + 1];
				arrA[i + 1] = temp;
			}
			++comparisons;
		} //end inner i for 
	} //end outer k for 

	//Print the amount of swaps and comparisons.
	printf("\nSwaps = %d\n", swaps);
	printf("Comparisons = %d\n", comparisons);
}



// sort an array of integers using countSort algorithm from 0 to maxVal
void countSort(int arrA[], int size, int maxVal)
{

	int i, value, count;
	int* freq, * arrB;
	int freqSize;

	freqSize = maxVal + 1;

	freq = (int*)malloc(freqSize * sizeof(int));   //create freq[]
	arrB = (int*)malloc(size * sizeof(int));       //create arrB[] same size as arrA[]

	// initialise freq[]
	for (i = 0; i < freqSize; i++) {
		freq[i] = 0;
	}

	// count
	for (i = 0; i < size; i++) {
		++freq[arrA[i]]; // increment the frequency array at the element that is equal to the value of the number. Do this for all numbers.
	}

	// get <= in freq[]
	for (i = 1; i < freqSize; i++) {
		freq[i] = freq[i] + freq[i - 1];
	}

	// place values from arrA into arrB; update freq[]
	for (i = 0; i < size; i++) {
		value = arrA[i];			//value to sort
		count = freq[value];		//<= freq of value
		arrB[count - 1] = value; 	//place value in arrB
		--freq[value];				//decrement freq[]
	} //next value in arrA

	//write back sorted values to arrA[] now that sorting finished
	for (i = 0; i < size; i++) {
		arrA[i] = arrB[i];
	}
}



//QUICKSORT

void quickSort(int arrA[], int startval, int endval) {

	if ((endval - startval) < 1) {
		return;
	}
	else {
		int k = partition2(arrA, startval, endval);
		//now sort the two sub-arrays
		quickSort(arrA, startval, k - 1);  //left partition
		reCalls++;
		quickSort(arrA, k + 1, endval);   //right partition
		reCalls++;
	}
}

void quickSortIt(int arrA[], int startval, int endval) {

	int size = endval - startval + 1;
	int* next;
	next = (int*)malloc(size * sizeof(int));

	// initialize top to access next[]
	int top = -1;

	// add initial values of startval and endaval to next[]
	next[++top] = startval;
	next[++top] = endval;

	// Start partitioning while some values left in next[]
	while (top >= 0) {
		endval = next[top--];
		startval = next[top--];

		// partition and get k
		int k = partition2(arrA, startval, endval);

		// Values on lhs of pivot? if so, add to next[]
		if (k - 1 > startval) {
			next[++top] = startval;
			next[++top] = k - 1;
		}

		// Values on rhs of pivot? if so, add to next[]
		if (k + 1 < endval) {
			next[++top] = k + 1;
			next[++top] = endval;
		}
	}
}

int partition(int arrA[], int startval, int endval)
{
	int i = startval + 1;
	int k = endval;
	int pivot = arrA[startval];

	while (k >= i) {
		while (arrA[i] <= pivot && i <= k) {
			++comparisons;
			i++;
		}
		while (arrA[k] > pivot && k >= i) {
			++comparisons;
			k--;
		}
		if (k > i) { //swap values at k and i
			swap(&arrA[i], &arrA[k]);
			++swaps;
		}
	}
	//out of this loop when k >= i not true
	swap(&arrA[startval], &arrA[k]);
	++swaps;
	return(k);
}


// better version of partition .. no nested loop
// pivot at startval as before
int partition2(int arrA[], int startval, int endval)
{
	int k;
	int pivot = arrA[startval];
	int i = startval;

	for (k = startval + 1; k <= endval; k++) { // k keeps incrementing to the end
		if (arrA[k] <= pivot) {
			i++;   // i only increments when there is a new value to add to the <= portion
			if (i != k) {
				swap(&arrA[i], &arrA[k]);
				swaps++;
			}
		}
		comparisons++;
	}
	swap(&arrA[i], &arrA[startval]);  // put pivot in correct location i
	swaps++;
	return(i);
}

// call with  ... swap(&arrA[i], &arrA[j]) to swap the values at positions i and j
void swap(int* a, int* b)
{
	//++cnt_swap_calls;
	int temp = *a;
	*a = *b;
	*b = temp;
}


//MERGE SORT

//mergeSort to sort values in an integer array arrA[]
// lb = 0 and ub = size - 1 for the first call
void mergeSort(int arrA[], int lb, int ub) {

	int mid;

	if (lb < ub) {
		mid = (int)((lb + ub) / 2);
		mergeSort(arrA, lb, mid);
		reCalls++;
		mergeSort(arrA, mid + 1, ub);
		reCalls++;
		merge(arrA, lb, mid, ub);
	}
}

// merge two sorted portions of an integer array arrA[]: 
// portions are lb to mid and mid+1 to ub
void merge(int arrA[], int lb, int mid, int ub) {

	int i, j, k;
	int size = ub - lb + 1;
	int* arrC;
	//create arrC[] to be of size needed for current merge
	arrC = (int*)malloc(size * sizeof(int));

	i = lb;
	j = mid + 1;
	k = 0;

	while (i <= mid && j <= ub) {
		if (arrA[i] <= arrA[j]) {
			arrC[k] = arrA[i];
			i++;
		}
		else {
			arrC[k] = arrA[j];
			j++;
		}
		comparisons++;
		k++;
	} //end while

	// write out anything left in i part
	while (i <= mid) {
		arrC[k] = arrA[i];
		//swaps++;
		i++;
		k++;
	}
	// write out anything left in j part
	while (j <= ub) {
		arrC[k] = arrA[j];
		//swaps++;
		j++;
		k++;
	}

	//write back from arrC to arrA so correct values are in place for next merge
	i = lb;
	k = 0;
	while (i <= ub) {
		arrA[i] = arrC[k];
		swaps++;
		i++;
		k++;
	}
}