public class Palindrome
{
    //Primitive operation counters for each of the palindrome detecting methods
    //Variables for keeping track of time taken
    private static int stringCount = 0;
    private static long stringTime = 0;
    private static long stringTimeBinary = 0;
    private static int characterCount = 0;
    private static long characterTime = 0;
    private static long characterTimeBinary = 0;
    private static int stackQueueCount = 0;
    private static long stackQueueTime = 0;
    private static long stackQueueTimeBinary = 0;
    private static int recursiveCount = 0;
    private static long recursiveTime = 0;
    private static long recursiveTimeBinary = 0;
    private static int instancesPalindromicBothBases = 0;

    public static void main(String[] args) {
        //Create a test object
        Palindrome t = new Palindrome();

        int decimal = 21;
        System.out.println("Decimal to binary String test");
        System.out.println("================================================");
        System.out.println("Decimal: " + decimal);
        System.out.println("Binary: " + t.decimalToBinary(String.valueOf(decimal)));
        System.out.println("End of decimal to binary String test");
        System.out.println("================================================");

        String input = "";
        String inputBinary = "";

        //My test code for individual functions
//        //Use method 1
//        boolean stringEqualsPalindrome = t.isPalindromeStringEquals(input);
//
//        //Use method 2
//        boolean characterByCharacterPalindrome = t.isPalindromeCharacterByCharacter(input);
//
//        //Use method 3
//        boolean stackQueuePalindrome = t.isPalindromeStackQueue(input);
//
//        //Use method 4
//        boolean recursionReversePalindrome = t.isPalindromeRecursionReverse(input);
//
//        if(characterByCharacterPalindrome)
//        {
//            System.out.println(input + ": is a palindrome");
//        }
//        else
//        {
//            System.out.println(input + " is not a palindrome");
//        }

        //Check the numbers 0-1000000 for palindromes in binary and decimal
        //Do this three times to get an accurate reading of time taken

        System.out.println("\n\nInitiating palindrome testing...");
        for(int r = 1; r <= 3; r++)
        {
            System.out.println("\nRun " + r);
            System.out.println("==================================================");
            System.out.println("CSV data, paste into excel\n");

            //Loop through every number from 1 including 1000000
            long start = 0;
            boolean isPalindromeDecimal = false;
            boolean isPalindromeBinary = false;

            for (int i = 0; i <= 1000000; i++) {
                //Generate CSV
                //Note: CSV data counts primitive operations for both the decimal and binary number together
                //Every 50000th number, display the current operation counts
                //Note: the last value (1000000) will not be included in this csv data
                //because we are checking 1000001 numbers.
                //1000001 numbers do not fit into a graph if we divide the graph into ranges of 50000
                if (i % 50000 == 0) {
                    System.out.println(i + "," + stringCount + "," + characterCount + "," + stackQueueCount + "," + recursiveCount);
                }

                //Get the next number to be checked
                input = String.valueOf(i);

                //Get the binary of that number
                inputBinary = t.decimalToBinary(input);

                //Running the algorithms for the current number
                //Use each algorithm on both the decimal and binary number
                //Every time an algorithm is called, calculate the amount of time it takes to run it
                //and add that time to a variable that is keeping track of its total time taken

                //StringEquals method
                //Decimal
                start = System.currentTimeMillis();
                isPalindromeDecimal = t.isPalindromeStringEquals(input);
                stringTime += System.currentTimeMillis() - start;
                //Binary
                start = System.currentTimeMillis();
                isPalindromeBinary = t.isPalindromeStringEquals(inputBinary);
                stringTimeBinary += System.currentTimeMillis() - start;

                //CharacterByCharacter method
                //Decimal
                start = System.currentTimeMillis();
                isPalindromeDecimal = t.isPalindromeCharacterByCharacter(input);
                characterTime += System.currentTimeMillis() - start;
                //Binary
                start = System.currentTimeMillis();
                isPalindromeBinary = t.isPalindromeCharacterByCharacter(inputBinary);
                characterTimeBinary += System.currentTimeMillis() - start;

                //StackQueue method
                //Decimal
                start = System.currentTimeMillis();
                isPalindromeDecimal = t.isPalindromeStackQueue(input);
                stackQueueTime += System.currentTimeMillis() - start;
                //Binary
                start = System.currentTimeMillis();
                isPalindromeBinary = t.isPalindromeStackQueue(inputBinary);
                stackQueueTimeBinary += System.currentTimeMillis() - start;

                //RecursionReverse method
                //Decimal
                start = System.currentTimeMillis();
                isPalindromeDecimal = t.isPalindromeRecursionReverse(input);
                recursiveTime += System.currentTimeMillis() - start;
                //Binary
                start = System.currentTimeMillis();
                isPalindromeBinary = t.isPalindromeRecursionReverse(inputBinary);
                recursiveTimeBinary += System.currentTimeMillis() - start;

                //How many instances is a number palindromic in both bases?
                //If this current number is palindromic in both bases, increment instancesPalindromicBothBases
                //Note: the assignment of isPalindromeDecimal and isPalindromeBinary is repeated throughout
                //the methods so that the time of one of the algorithms isn't more affected
                if (isPalindromeDecimal && isPalindromeBinary) {
                    instancesPalindromicBothBases++;
                }

            }

            System.out.println("\nResults for run " + r);
            System.out.println("----------------------------------------");
            System.out.println("Time taken for 0-1000000 (decimal only)");
            System.out.println("----------------------------------------");
            System.out.println("String Equals:\t\t\t" + stringTime + "ms");
            System.out.println("Character by Character:\t" + characterTime + "ms");
            System.out.println("Stack Queue:\t\t\t" + stackQueueTime + "ms");
            System.out.println("Recursion Reverse:\t\t" + recursiveTime + "ms");
            System.out.println("----------------------------------------");
            System.out.println("Time taken for 0-1000000 (binary only)");
            System.out.println("----------------------------------------");
            System.out.println("String Equals:\t\t\t" + stringTimeBinary + "ms");
            System.out.println("Character by Character:\t" + characterTimeBinary + "ms");
            System.out.println("Stack Queue:\t\t\t" + stackQueueTimeBinary + "ms");
            System.out.println("Recursion Reverse:\t\t" + recursiveTimeBinary + "ms");
            System.out.println("----------------------------------------");
            System.out.println("Time taken for 0-1000000 (total of decimal and binary)");
            System.out.println("----------------------------------------");
            System.out.println("String Equals:\t\t\t" + (stringTime + stringTimeBinary) + "ms");
            System.out.println("Character by Character:\t" + (characterTime + characterTimeBinary) + "ms");
            System.out.println("Stack Queue:\t\t\t" + (stackQueueTime + stackQueueTimeBinary) + "ms");
            System.out.println("Recursion Reverse:\t\t" + (recursiveTime + recursiveTimeBinary) + "ms");
            System.out.println("----------------------------------------");
            System.out.println("Primitive operations for 0-1000000 (total of decimal and binary)");
            System.out.println("----------------------------------------");
            System.out.println("String Equals:\t\t\t" + stringCount + " primitive operations");
            System.out.println("Character by Character:\t" + characterCount + " primitive operations");
            System.out.println("Stack Queue:\t\t\t" + stackQueueCount + " primitive operations");
            System.out.println("Recursion Reverse:\t\t" + recursiveCount + " primitive operations");
            System.out.println("----------------------------------------");
            System.out.println("Instances both bases palindromic for 0-1000000");
            System.out.println("----------------------------------------");
            System.out.println("Palindromic in both bases in: " + instancesPalindromicBothBases + " instances");
            System.out.println("----------------------------------------");
            System.out.println("End of run " + r);
            System.out.println("==================================================\n");
            //End of a run
            //Reset all variables
            stringTime = 0;
            characterTime = 0;
            stackQueueTime = 0;
            recursiveTime = 0;
            stringTimeBinary = 0;
            characterTimeBinary = 0;
            stackQueueTimeBinary = 0;
            recursiveTimeBinary = 0;
            stringCount = 0;
            characterCount = 0;
            stackQueueCount = 0;
            recursiveCount = 0;
            instancesPalindromicBothBases = 0;
        }
        //End of all runs
        System.out.println("All runs complete");
    }

    public Palindrome()
    {

    }

    public boolean isPalindromeStringEquals(String input)
    {
        String reversed = "";
        stringCount++;

        //Reverse the string using loop
        //Count the initialization of i to the length() function
        stringCount = stringCount + 2;
        for(int i = input.length()-1; i >= 0; i--)
        {
            //Count loop condition and i--
            stringCount = stringCount + 2;

            stringCount = stringCount + 3;
            reversed += input.charAt(i);
        }
        //The last evaluation of the conditional statement is false and the loop doesn't run
        stringCount++;

        //Examined String.java and StringLatin1.java to count primitive operations of equals
        //Counted equals to have 7 + 1 + 5*input.length() + 1 + 1 primitive operations
        stringCount = stringCount + (10+(5*input.length()));
        if(input.equals(reversed))
        {
            stringCount++;
            //The input is a palindrome
            return true;
        }
        else
        {
            stringCount++;
            //The input is not a palindrome
            return false;
        }
    }

    public boolean isPalindromeCharacterByCharacter(String input)
    {
        //An input of length 1 is a palindrome
        characterCount = characterCount + 2;
        if(input.length() == 1)
        {
            characterCount++;
            return true;
        }

        //This method compares the first character to the last and keeps comparing, moving towards the centre
        //If the input is odd, then we don't need to compare the middle item to anything
        //121 is a palindrome
        //The length is 3
        //We would compare the first and last, 1 == 1.
        //Don't have anything to compare the middle item to
        //All comparisons were equal: therefore 121 is a palindrome

        //Find the amount of times we need to compare characters
        int i = 0;
        int length = input.length();
        characterCount = characterCount + 3;
        if(length % 2 == 0)
        {
            characterCount = characterCount + 2;
            i = length / 2;
        }
        else
        {
            characterCount = characterCount + 3;
            i = (length - 1) / 2;
        }

        //Compare first and last letters
        //if any of the comparisons are not equal, then the input is not a palindrome
        characterCount++;
        for(int j = 0; j<i; j++)
        {
            //Count loop condition and i++
            characterCount = characterCount + 2;

            //old code if needed: (input.charAt(j)+"").equals(input.charAt(length-j-1)+"")
            characterCount = characterCount + 4;
            if(input.charAt(j) == input.charAt(length-j-1))
            {
                //All good, the characters are equal, so far the input is a palindrome
            }
            else
            {
                characterCount++;
                return false;
            }
        }
        //The last evaluation of the conditional statement is false and the loop doesn't run
        characterCount++;

        characterCount++;
        return true;
    }

    public boolean isPalindromeStackQueue(String input)
    {
        //Create a stack
        Stack s = new ArrayStack(20);
        //ArrayStack constructor has 2 time steps in it
        stackQueueCount = stackQueueCount + 2;


        //Create a queue
        Queue q = new ArrayQueue(20);
        //ArrayQueue constructor has 2 time steps in it
        stackQueueCount = stackQueueCount + 2;

        //Add each character to both stack and queue as it is read
        //Count the initialization of i
        stackQueueCount++;
        for(int i = 0; i < input.length(); i++)
        {
            //Count loop condition and i++
            stackQueueCount = stackQueueCount + 2;

            //Push() takes 5 operations
            //1 for if
            //2 for isFull()
            //1 for top++
            //1 for assigning the element to the top index of the array
            s.push(input.charAt(i));
            //charAt(i) is 1 primitive operation
            stackQueueCount = stackQueueCount + 6;

            //Enqueue() takes 5 operations
            //2 for isFull()
            //1 for rear++
            //2 for Q[rear] = n
            q.enqueue(input.charAt(i));
            //charAt(i) is 1 primitive operation
            stackQueueCount = stackQueueCount + 6;
        }
        //The last evaluation of the conditional statement is false and the loop doesn't run
        stackQueueCount++;

        //For calculating dequeue()'s primitive operations
        int r = (input.length() - 1);

        //Compare characters from stack and queue
        while(!s.isEmpty())
        {
            //1 primitive operation per isEmpty()
            stackQueueCount++;

            //Counting pop()'s primitive operations
            // Object element; +1
            // isEmpty() +1
            // element = S[top]; +2
            // S[top] = null; +2
            // top--; +1
            // return element; +1
            // pop() takes 8 primitive operations

            //Counting dequeue()'s primitive operations
            // isEmpty() +2
            // Object toReturn = Q[0]; +2
            // int i = 1; +1
            // while loop:
            // r+1 "while condition"
            // + r "i-1 calculation" + r "access array" + r "access array" + r "assignment" + r "i++ calculation")
            // rear--; calculation +1
            // That is: 6r + 7
            // r is initially equal to the length of the string -1,
            // because rear is equivalent to the amount of items in the ArrayQueue
            // and r is decremented every iteration of this while loop,
            // because an item is dequeued at every iteration

            //Add the primitive operations of the dequeue() function for this iteration
            if(r >= 1)
            {
                stackQueueCount = stackQueueCount + r * 6 + 7;
            }
            r--;

            //Add the primitive operations of the pop() function for this iteration
            stackQueueCount = stackQueueCount + 8;

            //primitive operation for if condition
            stackQueueCount++;
            //old code in case I need it again: if((""+s.pop()).equals(q.dequeue()+""))
            if(s.pop() == q.dequeue())
            {
                //Character at the front matches character at the back
                //Currently, still a palindrome
            }
            else
            {
                //Character at the front does not match character at the back
                //input is not a palindrome
                stackQueueCount++;
                return false;
            }
        }

        //Checked every letter
        //input is definitely a palindrome
        stackQueueCount++;
        return true;
    }

    //Returns a reversed String of the input string
    public String reverse(String input)
    {
        recursiveCount = recursiveCount + 2;
        //Base case
        if(input.length() == 1)
        {
            recursiveCount++;
            return input;
        }

        recursiveCount = recursiveCount + 4;
        //Reduction step is when the function is called by sending the string without the first letter
        //Reduction: reverse(input.substring(1,input.length()))
        return reverse(input.substring(1,input.length())) + input.substring(0, 1);
    }

    public boolean isPalindromeRecursionReverse(String input)
    {
        recursiveCount++;
        //Get the reversed string using recursion
        String reverse = reverse(input);

        //Examined String.java and StringLatin1.java to count primitive operations of equals
        //Counted equals to have 7 + 1 + 5*input.length() + 1 + 1 primitive operations
        recursiveCount = recursiveCount + (10+(5*input.length()));
        //If the reverse string is equal to the input string, then the input is a palindrome
        if(reverse.equals(input))
        {
            recursiveCount++;
            //input is a palindrome
            return true;
        }
        else
        {
            recursiveCount++;
            //input is not a palindrome
            return false;
        }
    }

    public String decimalToBinary(String decimal)
    {
        //Convert the decimal String into an int
        //Then convert the int to a binary String
        //Return the binary String
        return Integer.toBinaryString(Integer.parseInt(decimal));
    }
}