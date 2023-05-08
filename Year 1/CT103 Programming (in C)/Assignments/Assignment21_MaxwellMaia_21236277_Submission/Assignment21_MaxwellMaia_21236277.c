#include <stdio.h> 
#include <string.h>
#include <ctype.h>

float readability(int syllables, int words, int sentences);

int isVowel(char letter);

void main()
{
    int count = 0;

    FILE* fptr;
    fopen_s(&fptr, "C:\\Users\\Maxie\\Desktop\\Week 21\\sample.txt", "r"); // open for reading

    if (fptr == NULL)
    {
        printf("Could not open article\n");
    }
    else
    {
        printf("Analysing text document...");
        printf("\n----------------------------");
        char word[50];
        int spaceCounter = 0;
        int j = 0;
        int i = 0;
        int endSentenceCounter = 0;
        int syllable = 0;
        int noSyllable = 1; //initially, there are no syllables in a word (that hasnt been scanned).
        int vowelGroupSize = 0;

        while (!feof(fptr)) 
        {
            fscanf_s(fptr, "%s ", word, 50);
            spaceCounter++;
            printf("\n\nword = %s", word); //get text until a space. save that as 1 word.
            printf("\nspaceCounter = %d", spaceCounter);


            //(Couting syllables)
            for (i = 0; i < strlen(word); i++) //FOR each letter...
            {
                
                if (word[i] == '\'') //change all ' to Z
                {
                    word[i] = 'Z';
                }

                //FOR each letter...
                if (isVowel(word[i])) //If the letter is a vowel...
                {

                    //the following is a reason not to count a syllable
                    if (tolower(word[i]) == 'e' && !( isalpha(word[i + 1]) ) ) //... and the vowel is an 'e' AND is the last letter in a word.
                        // graveyard: (word[i + 1] == " " || isEndOfSentenceCharacter(word[i + 1]) || 
                    {
                        //dont do anything :)
                    }
                    else
                    {
                        vowelGroupSize++;
                        noSyllable = 0;
                    }

                }
                else //letter is not a vowel then.
                {
                    //GROUP OF ADJACENT VOWELS COUNTS AS ONE SYLLABLE
                    if (vowelGroupSize >= 1) //As soon as the letter is not a vowel, check if there was a vowel group before.
                    {
                        syllable++;
                        noSyllable = 0;
                        printf("\nvowelGroupSize = %d", vowelGroupSize);
                    }
                    vowelGroupSize = 0;
                }
                
            }

            if (word[i] == '\0') //Do this check again at the end in case the word ends on a vowel (group).
            {
                if (vowelGroupSize >= 1) //apply syllable vounter increase. check if there was a vowel group before.
                {
                    syllable++;
                    noSyllable = 0;
                }
                vowelGroupSize = 0;
            }

            if (noSyllable == 1) //at the end of the word. If a syllable wasnt found. Add a syllable.
            {
                syllable++;
            }
            noSyllable = 1; //reset the flag
            printf("\nsyllable = %d", syllable);


            //Counting sentences
            for (j = 0; j < strlen(word); j++)
            {
                //endSentenceCounter++;
                if (isEndOfSentenceCharacter(word[j]))
                {
                    endSentenceCounter++;
                }
            }
            printf("\nendSentenceCounter = %d", endSentenceCounter);
            
        }
        printf("\n\n----------------------------");
        printf("\nAnalysis complete.\n");

        printf("\nsyllable = %d", syllable);
        printf("\nspaceCounter = %d", spaceCounter);
        printf("\nend = %d", endSentenceCounter);

        float answer = readability(syllable, spaceCounter, endSentenceCounter);
        printf("\n\nThe Flesch Readbility Index of the input text file is = %0.3f\n", answer);

        fclose(fptr);
    }
}


float readability(int syllables, int words, int sentences) //calculates readability
{
    return 206.835 - (84.6 * (syllables / words)) - (1.015 * (words / sentences));
}

int isVowel(char letter) //retuns 1 if the letter is a vowel. 0 if not.
{
    letter = tolower(letter);
    if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u' || letter == 'y')
    {
        return 1;
    }
    else
    {
        return 0;
    }
}

int isEndOfSentenceCharacter(char letter) //returns 1 if the letter is a sentence terminator. 0 if not.
{
    if (letter == '.' || letter == ':' || letter == ';' || letter == '?' || letter == '!')
    {
        return 1;
    }
    else
    {
        return 0;
    }
}