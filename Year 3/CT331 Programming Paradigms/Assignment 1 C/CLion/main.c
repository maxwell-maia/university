#include <stdio.h>

int main() {
    //int
    int num = 68;
    printf("\nint\nSize: %llu bytes\n", sizeof(num));

    //int*
    int* numPointer = &num;
    printf("\nint*\nSize: %llu bytes\n", sizeof(numPointer));

    //long
    long bigLongNumber = 123456789;
    printf("\nlong\nSize: %llu bytes\n", sizeof(bigLongNumber));

    //double*
    double bigDoubleNumber = 5.67e+2;
    double* doublePointer = &bigDoubleNumber;
    printf("\ndouble*\nSize: %llu bytes\n", sizeof(doublePointer));

    //char**
    char myChar = 'a';
    char* myCharPointer = &myChar;
    char** myCharPointerToPointer = &myCharPointer;
    printf("\nchar**\nSize: %llu bytes\n", sizeof(myCharPointerToPointer));

    return 0;
}
