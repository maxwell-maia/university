import java.util.Scanner;
/**
 * Write a description of class GameTest here.
 *
 * @Maxwell Maia
 * @Alphabet Game
 */
public class GameTest
{

    public GameTest()
    {
        
    }

    public static void main(String[] args)
    {
        GameTest p = new GameTest(); //object to test the game with
        
        System.out.println("Play? (y/n): ");
        //Infinitely ask the player to play the game
        //Variables to get user input
        String userInputString = "";
        char userInputChar;
        //Scanner for user input
        Scanner inputScanner = new Scanner(System.in);
        
        //Loop that plays the game if user types 'y'.
        while(true)
        {
            
            //Get user input as a String from Scanner
            userInputString = inputScanner.nextLine();
            //Get the first character of the String
            if(userInputString.length() == 0)
            {
                //In case someone presses enter twice
                userInputChar = 's'; //'s' will be counted as invalid. This will count as invalid and the program will continue without crashing
            }
            else
            {
                userInputChar = userInputString.charAt(0);
            }
            
            //Did the user press f, b or an invalid input?
            if(userInputChar == 'y')
            {
                //Start the game
                p.play();
                
                System.out.println("\n*************************************************");
                System.out.println("\nPlay again? (y/n): ");
            }
            else if(userInputChar == 'n')
            {
                System.out.println("Ok bye ;)");
                break;
            }
            else
            {
                //invalid
                System.out.println("Invalid. enter only 'y' or 'n'");
            }
        }
        
        //p.play(); //// start the game
    }
    
    public void play()
    {
        //Instructing player
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("HEY! Welcome to the game... \nType the alphabet in order!\n\nHit enter between letters");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n***");
        
        //initilizing char array with alphabet loaded
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        
        
        //scanner for user input
        Scanner inputScanner = new Scanner(System.in);
        
        //Getting the choice of forwards or backwards from user
        //Initilize the variables needed
        int playerChoice = -1;
        String userInputString = "";
        char userInputChar;
        
        while(playerChoice == -1)
        {
            System.out.println("Hmm, do you want to play forwards or backwards?\n(f/b)\nEnter your answer:");
            //Get user input as a String from Scanner
            userInputString = inputScanner.nextLine();
            //Get the first character of the String
            if(userInputString.length() == 0)
            {
                //In case someone presses enter twice
                userInputChar = 's'; //'s' will be counted as invalid. This will count as invalid and the program will continue without crashing
            }
            else
            {
                userInputChar = userInputString.charAt(0);
            }
            
            //Did the user press f, b or an invalid input?
            if(userInputChar == 'f')
            {
                playerChoice = 0;
            }
            else if(userInputChar == 'b')
            {
                playerChoice = 1;
            }
            else
            {
                //invalid
                System.out.println("Invalid. You must enter either 'f' or 'b' to start.");
            }
        }
        
        
        int gameInProgress = 0;
        //Gamemode has been chosen.
        //Time to start the game based on the gamemode
        //note: inputScanner and String + char variables will be reused to save on memory
        if(playerChoice == 0)
        {
            //playing forwards
            System.out.println("\nPlaying forwards");
            System.out.println("*************************************************");
            System.out.println("Get ready and have fun :D \nIt's not a competition.\n");
            System.out.println("\nEnter the first letter of the alphabet and press enter to start: ");
            
            //get the first input
            //Get user input as a String from Scanner
            userInputString = inputScanner.nextLine();
            //Get the first character of the String
            if(userInputString.length() == 0)
            {
                //In case someone presses enter twice
                userInputChar = 's'; //'s' will be counted as invalid. This will count as invalid and the program will continue without crashing
            }
            else
            {
                userInputChar = userInputString.charAt(0);
            }
            
            gameInProgress = 0;
            char nextChar = 'G'; //initialized to G to help with debugging
            int i = 0;
            long startTime = 0;
            long elapsedTime = 0;
            //Loop until the user gets the last letter correct
            while(true)
            {
                //Find the value of the next correct character
                if(i <= 25)
                {
                    nextChar = alphabet[i];
                }
                
                //Check if input was correct
                if(userInputChar == nextChar)
                {
                    //Display output message saying you were correct
                    if(i < 25)
                    {
                        System.out.println("["+nextChar+": Correct! Now type "+ alphabet[i+1] + "]");
                    }
                    else
                    {
                        System.out.println("[Correct! Well done!] \n");
                    }
                    
                    
                    //Also,if it was the first one he got right,
                    //start the timer.
                    if(gameInProgress == 0)
                    {
                        //Start timer
                        startTime = System.currentTimeMillis();
                        
                        gameInProgress = 1;
                    }
                    
                    //Increment i only if a correct answer was given
                    i++;
                }
                
                //Exit the loop if he has gotten the last letter correct
                if(i == 26)
                {
                    //Stop timer
                    //elapsedTime is currentTime - startTime.
                    elapsedTime = System.currentTimeMillis() - startTime;
                    
                    gameInProgress = 0;
                    break;
                }
                
                //Get next user input as a String from Scanner
                userInputString = inputScanner.nextLine();
                //Get the first character of the String
                if(userInputString.length() == 0)
                {
                    //In case someone presses enter twice
                    userInputChar = 's'; //'s' will be counted as invalid. This will count as invalid and the program will continue without crashing
                }
                else
                {
                    userInputChar = userInputString.charAt(0);
                }
        
                
            }
            
            //Display timer result
            System.out.println("Time taken: " + (float)elapsedTime / 1000 + "seconds\n\nCongratulations!!! :)");
        }
        else if (playerChoice == 1)
        {
            //playing backwards
            //All the same code, except when the alphabet array is referenced "25 - " is added in the index e.g. alphabet[25 - i]
            //This allows the game to be played backwards.
            System.out.println("\nPlaying backwards");
            System.out.println("*************************************************");
            System.out.println("Get ready and have fun :D \nIt's not a competition.\n");
            System.out.println("\nEnter the last letter of the alphabet and press enter to start: ");
            
            //get the first input
            //Get user input as a String from Scanner
            userInputString = inputScanner.nextLine();
            //Get the first character of the String
            if(userInputString.length() == 0)
            {
                //In case someone presses enter twice
                userInputChar = 's'; //'s' will be counted as invalid. This will count as invalid and the program will continue without crashing
            }
            else
            {
                userInputChar = userInputString.charAt(0);
            }
            
            gameInProgress = 0;
            char nextChar = 'G'; //initialized to G to help with debugging
            int i = 0;
            long startTime = 0;
            long elapsedTime = 0;
            //Loop until the user gets the last letter correct
            while(true)
            {
                //Find the value of the next correct character
                if(i <= 25)
                {
                    nextChar = alphabet[25-i]; //25 - i because we are going backwards
                }
                
                //Check if input was correct
                if(userInputChar == nextChar)
                {
                    //Display output message saying you were correct
                    if(i < 25)
                    {
                        System.out.println("["+nextChar+": Correct! Now type "+ alphabet[25-i-1] + "]");
                    }
                    else
                    {
                        System.out.println("[Correct! Well done!] \n");
                    }
                    
                    
                    //Also,if it was the first one he got right,
                    //start the timer.
                    if(gameInProgress == 0)
                    {
                        //Start timer
                        startTime = System.currentTimeMillis();
                        
                        gameInProgress = 1;
                    }
                    
                    //Increment i only if a correct answer was given
                    i++;
                }
                
                //Exit the loop if he has gotten the last letter correct
                if(i == 26)
                {
                    //Stop timer
                    //elapsedTime is currentTime - startTime.
                    elapsedTime = System.currentTimeMillis() - startTime;
                    
                    gameInProgress = 0;
                    break;
                }
                
                //Get next user input as a String from Scanner
                userInputString = inputScanner.nextLine();
                //Get the first character of the String
                if(userInputString.length() == 0)
                {
                    //In case someone presses enter twice
                    userInputChar = 's'; //'s' will be counted as invalid. This will count as invalid and the program will continue without crashing
                }
                else
                {
                    userInputChar = userInputString.charAt(0);
                }
        
                
            }
            
            //Display timer result
            System.out.println("Time taken: " + (float)elapsedTime / 1000 + "seconds\n\nCongratulations!!! :)");
        }
    }
}
