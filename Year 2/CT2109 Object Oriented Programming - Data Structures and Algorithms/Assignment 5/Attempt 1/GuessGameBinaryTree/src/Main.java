import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args)
    {
        //Create an initial binary tree
        BinaryTree<String> initialTree = new BinaryTree<String>();
        createInitialTree(initialTree);

        BinaryTree<String> tree = null;

        //Start menu
        startMenu(tree);

        while(true)
        {
            //If the user did not load a tree, or the tree could not be loaded
            // use the initial tree
            if(tree == null)
            {
                tree = initialTree;
            }

            int yesNoResponse = -1;
            String gameMessage = "";

            //Game loop
            while(true)
            {
                BinaryNodeInterface<String> currentNode = tree.getRootNode();
                //Navigating the tree
                while(!currentNode.isLeaf())
                {
                    //Ask question
                    System.out.println(currentNode.getData());
                    gameMessage = currentNode.getData();

                    //Get response
                    yesNoResponse = getUserResponseGame(gameMessage);

                    //Update current node
                    if(yesNoResponse == 0)
                    {
                        currentNode = currentNode.getLeftChild();
                    }
                    else
                    {
                        currentNode = currentNode.getRightChild();
                    }
                }

                //We are at the leaf: got an answer that is either right or wrong
                //Present guess to user, and store their answer
                System.out.println("Is it a/an " + currentNode.getData() + "?");
                String computerGuess = currentNode.getData();
                //Get guess
                gameMessage = "Is it a/an " + computerGuess + "?";
                yesNoResponse = getUserResponseGame(gameMessage);
                System.out.println("User response (0 = Yes. 1 = No): " + yesNoResponse + "\n");

                //Compare answer to guess. Two possibilities to proceed:
                //1. Answer is correct. Display options for user to continue
                    //1. Play again
                    //2. Store the tree
                    //3. Load a stored tree
                    //4. Quit
                if(yesNoResponse == 0)
                {
                    endGameMenu(tree);
                }
                else if(yesNoResponse == 1)
                {
                    //2. The answer was wrong. We need to expand the tree.

                    //Get the correct answer
                    String questionMessage = "Well done!\nI couldn't guess your answer.\n\nWhat was the correct answer?";
                    String correctAnswer = JOptionPane.showInputDialog(null, questionMessage);

                    //Get new question from user
                    questionMessage = "Thank you.\nYou did great!\n";
                    questionMessage += "\n\nPlease can you give me a question that will differentiate ";
                    questionMessage += "between '" + computerGuess + "' and '" + correctAnswer + "'.\n\n";
                    questionMessage +=  "\n\nAn example question would be: Is it usually found in the Antarctic?";
                    String userQuestion = JOptionPane.showInputDialog(null, questionMessage);

                    //User's question gives what answer
                    questionMessage = "Your question is: '" + userQuestion + "' \n\nIs this the right answer for '";
                    questionMessage += computerGuess + "'?";
                    int rightAnswerComputer = getUserResponseGame(questionMessage);

                    //Print tree in text representation
                    System.out.println("Before updating tree");
                    printBinaryTree(tree);

                    //Replace the current node with this question
                    currentNode.setData(userQuestion);

                    //Add left & right children with the answers
                    BinaryNode<String> left = new BinaryNode<>(computerGuess, null, null);
                    BinaryNode<String> right = new BinaryNode<>(correctAnswer, null, null);

                    //If the computer's guess is the answer to the user's question
                    if(rightAnswerComputer == 0)
                    {
                        currentNode.setLeftChild(left);
                        currentNode.setRightChild(right);
                    }
                    else
                    {
                        //else the user's answer is the answer to the user's question
                        currentNode.setLeftChild(right);
                        currentNode.setRightChild(left);
                    }

                    //Thank you message
                    questionMessage = "Okay, thank you.\nYour tree has been updated.\nIf you save your tree, you can load it at any time,";
                    questionMessage += " and your question and answer will be a part of the game!";
                    JOptionPane.showMessageDialog(null, questionMessage);

                    //Print tree in text representation
                    System.out.println("After updating tree");
                    printBinaryTree(tree);

                    //End of game menu
                    endGameMenu(tree);
                }

            }

        }

    }

    public static BinaryTree<String> startMenu(BinaryTree<String> tree)
    {
        //Get user response
        int userResponse = 0;
        String message = "Enter your response:\n\n1. Play\n2. Load a stored tree\n3. Quit";

        //Start Menu
        //The start menu is different from the end game menu
        // because there is no tree to save in the start menu,
        // hence they are separate.
        //Useful trick to be able to break out of this loop
        startMenuLoop:
        while (true)
        {
            //Get user response
            userResponse = getUserResponseMenu(message, 3);
            //-1. Invalid response
            //1. Play
            //2. Load a stored tree
            //3. Quit
            switch(userResponse)
            {
                case -1:
                    //Try again
                    System.out.println("Invalid response, please try again.");
                    break;
                case 1:
                    //Start the game
                    break startMenuLoop;
                case 2:
                    //Try to load tree
                    tree = loadTree();
                    if(tree == null)
                    {
                        System.out.println("Error loading tree");
                    }
                    else
                    {
                        System.out.println("Successfully loaded tree, Ready to play");
                    }
                    break;
                case 3:
                    //Quit the program
                    System.out.println("Thanks for playing");
                    System.exit(1);
            }
        }

        return tree;
    }

    public static BinaryTree<String> endGameMenu(BinaryTree<String> tree)
    {
        //Get user response
        int userResponse = 0;
        String message = "CONGRATS! YOU WON!\n\nYou can store the tree to play again later.\n\n\n";
        message += "\n\nEnter your response:\n\n1. Play\n2. Store the tree\n3. Load a stored tree\n4. Quit";

        //End of Game Menu
        //The different menus could have different functionality in the future
        //so for maintainability they are separated.
        //This menu has an option to save.

        //Useful trick to be able to break out of this loop
        endGameMenuLoop:
        while (true)
        {
            //Get user response
            userResponse = getUserResponseMenu(message, 4);
            //-1. Invalid response
            //1. Play
            //2. Store the tree
            //3. Load a stored tree
            //4. Quit
            switch(userResponse)
            {
                case -1:
                    //Try again
                    System.out.println("Invalid response, please try again.");
                    break;
                case 1:
                    //Start the game
                    break endGameMenuLoop;
                case 2:
                    //Try to save the tree
                    saveTree(tree);

                    //No runtime exception means success
                    System.out.println("Successfully saved the tree.");
                    break;
                case 3:
                    //Try to load the tree
                    tree = loadTree();
                    if(tree == null)
                    {
                        System.out.println("Error loading tree");
                    }
                    else
                    {
                        System.out.println("Successfully loaded tree, Ready to play");
                    }
                    break;
                case 4:
                    //Quit the program
                    System.out.println("Thanks for playing");
                    System.exit(1);
            }
        }

        return tree;
    }

    public static int getUserResponseGame(String message)
    {
        //Asks the user a question and accepts an input.
        //Will continue to ask the user the question until a valid response is given.
        //Returns a 0 for YES
        //Returns a 1 for NO

        boolean validInput = false;
        int ret = -1;
        //Ask the user the first time
        String userInput = JOptionPane.showInputDialog(null, message);

        //Update message for retries.
        message += "\nSorry, that is an invalid input. Try again. Please enter only 'yes' or 'no'.";

        while(!validInput)
        {
            //Make the text lower case and check in lower case so that the user input is not case-sensitive
            userInput.toLowerCase();

            if(userInput.equals("y") || userInput.equals("yes"))
            {
                ret = 0;
                validInput = true;
            }
            else if(userInput.equals("n") || userInput.equals("no"))
            {
                ret = 1;
                validInput = true;
            }
            else
            {
                //User try again
                userInput = JOptionPane.showInputDialog(null, message);
            }
        }

        return ret;
    }

    public static int getUserResponseMenu(String message, int maximumValidOptions)
    {
        //Returns the user response when prompted with a message
        // and given a number of valid options
        //Returns -1 for an invalid response
        int userResponse = Integer.parseInt(JOptionPane.showInputDialog(null, message));

        //Response must be from 1 to maximumValidOptions
        if (userResponse > maximumValidOptions || userResponse < 1)
        {
            userResponse = -1;
        }
        return userResponse;
    }

    public static BinaryTree<String> loadTree()
    {
        BinaryTree<String> tree = null;

        try
        {
            File f = new File("save.txt");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tree = (BinaryTree<String>)ois.readObject();
            ois.close();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        return tree;
    }

    public static void saveTree(BinaryTree<String> tree)
    {
        try
        {
            File f = new File("save.txt");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tree);
            oos.close();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void printBinaryTree(BinaryTree<String> tree)
    {
        //TLDR; The level indicator only works correctly when for the initial tree.
        //Note: My print function correctly performs a breadth-first traversal, displaying
        // the data in each node in the correct order every time.
        // I also added code which prints out "Level _". When the tree is not full, this "Level _" functionality
        // acts unexpectedly. This is because the height of the two nodes on the same level could be different
        //in a non-full tree.

        System.out.println("\n\nPrinting my binary tree");

        //Breadth-first traversal
        //Create an ArrayQueue
        ArrayQueue q = new ArrayQueue();
        //Put the root into the queue
        q.enqueue(tree.getRootNode());
        BinaryNode currNode = null;
        //Variables for finding when the level changes
        int prevLevel = 0;
        int currLevel = 1;
        int treeHeight = tree.getHeight();
        while(!q.isEmpty())
        {
            //Dequeue the front node
            currNode = (BinaryNode)q.dequeue();

            //Get the current node's level
            currLevel = treeHeight + 1 - currNode.getHeight();

            //Print the current level of the tree
            if(prevLevel != currLevel)
            {
                System.out.println("-----------------------------");
                System.out.println("Level: " + currLevel);
                prevLevel = currLevel;
                //The level won't be printed again until current level changes
            }

            //Enqueue the current node's children, if it has any
            if(currNode.hasLeftChild())
            {
                q.enqueue(currNode.getLeftChild());
            }
            if(currNode.hasRightChild())
            {
                q.enqueue(currNode.getRightChild());
            }

            //Visit the current node
            //Check if the node is a leaf node. Leaves are guesses
            //Output the contents of the node
            if(currNode.isLeaf())
            {
                System.out.println("Guess: " + currNode.getData() + "\t");
            }
            else
            {
                System.out.println("Question: " + currNode.getData() + "\t");
            }
        }
        System.out.println("-----------------------------");
        System.out.println("End of tree\n");


    }

    public static void createInitialTree(BinaryTree<String> tree)
    {
        //When creating a tree,
        // I will create each subtree starting from the leaves,
        // Keep linking subtrees until I reach the root

        //Create leaves
        //The leaves are the guesses
        //H - O are the guesses, They are the leaf nodes of the inital BinaryTree.
        //Level 4 (assuming the root node of the tree we are building is level 1)
        BinaryTree<String> hTree = new BinaryTree<String>("H");
        BinaryTree<String> iTree = new BinaryTree<String>("I");
        BinaryTree<String> jTree = new BinaryTree<String>("penguin");
        BinaryTree<String> kTree = new BinaryTree<String>("K");
        BinaryTree<String> lTree = new BinaryTree<String>("L");
        BinaryTree<String> mTree = new BinaryTree<String>("M");
        BinaryTree<String> nTree = new BinaryTree<String>("N");
        BinaryTree<String> oTree = new BinaryTree<String>("O");

        //Create the subtrees that join the leaves
        //Level 3
        BinaryTree<String> dTree = new BinaryTree<>("D", hTree, iTree);
        BinaryTree<String> eTree = new BinaryTree<>("E: Is it a bird?", jTree, kTree);
        BinaryTree<String> fTree = new BinaryTree<>("F", lTree, mTree);
        BinaryTree<String> gTree = new BinaryTree<>("G", nTree, oTree);

        //Level 2
        BinaryTree<String> bTree = new BinaryTree<>("B: Is it a mammal?", dTree, eTree);
        BinaryTree<String> cTree = new BinaryTree<>("C", fTree, gTree);

        //Level 1 - Root node
        tree.setTree("A: Are you thinking of an animal?", bTree, cTree);
    }
}