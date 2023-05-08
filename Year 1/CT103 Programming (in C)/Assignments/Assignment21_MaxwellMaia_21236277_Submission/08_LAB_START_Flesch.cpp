#include <stdio.h> 
#include <string.h>
#include <ctype.h>

char alltext[200000];
char oneline[1000];

int main() {
    FILE *file_ptr;
    file_ptr = fopen("article-irish-times.txt", "r"); // open for reading

    if (file_ptr == NULL) {
        printf("Could not open article\n");
    }
    else {
        alltext[0] = '\0'; // make sure this string is empty
        while (fgets(oneline, 999, file_ptr)!=NULL) {
            // read the next line and append it (with \n intact)
            strcat(alltext, oneline);
        }

        printf("%s", alltext);

        fclose (file_ptr);
    }
}



