import java.util.*;
import java.awt.Graphics;
import java.awt.Image;

public class BadGuy {
	
	Image myImage;
	int x=0,y=0;
	boolean hasPath=false;

	Stack <Node> pathStack = new Stack<Node>();

	private Node nodeMap[][] = new Node[40][40];

	private LinkedList openList = new LinkedList();

	public BadGuy( Image i ) {
		myImage=i;
		x = 30;
		y = 10;
	}
	
	public void reCalcPath(boolean map[][],int targx, int targy) {
		// TO DO: calculate A* path to targx,targy, taking account of walls defined in map[][]

		//Set up
		boolean pathFound = false;
		Node tempNode = null;
		hasPath = false;


		//Update nodeMap
		for(int j = 0; j<40; j++)
		{
			for (int k = 0; k<40; k++)
			{
				//Create a node with only co-ordinate information
				nodeMap[j][k] = new Node(j, k, 0, 0, null);

				//if a wall
				if(map[j][k])
				{
					nodeMap[j][k].setClosed(true);
				}
			}
		}

		//Initial algorithmic step
		//Get starting node
		Node startingNode = nodeMap[x][y];
		//Calculate f, g, h for starting node and set to open and add to open list
		//G is 0 by default
		//H
		startingNode.setH(getManhattan(x, y, targx, targy));
		//F
		startingNode.updateF();
		//Set open
		startingNode.setOpen(true);
		//Add to open list
		openList.add(startingNode);
		System.out.println("Updated starting node");

		//General algorithmic step
		while(1==1)
		{
			//Check if a path has been found
			if(pathFound)
			{
				System.out.println("A path has been found");
				updateStack(tempNode);
				break;
			}

			//If the open list is empty. break out.
			//This shouldn't happen but in case it does
			if(openList.isEmpty())
			{
				System.out.println("Error: open list is empty");
				break;
			}


			//Find the node with the lowest f that is also open in the open list, call it X
			Node X = null;
			int lowestF = 10000;
			//Loop through open list
			Iterator<Node> i = openList.iterator();
			while (i.hasNext())
			{
				tempNode = (Node)i.next();
				if(tempNode.isOpen() && tempNode.getF() < lowestF)
				{
					lowestF = tempNode.getF();
					X = tempNode;
				}
			}

			//If no open nodes were found
			if(lowestF == 10000)
			{
				System.out.println("The maze is unsolvable");
				break;
			}

			//Look at X's neighbours and check if not open and not closed then calc fgh parent

			//Get X's co-ordinates
			int xx = X.getX();
			int yy = X.getY();

			boolean skipNeighbour = false;

			//Not taking into account diagonals
			//There are only 4 neighbours around X's co-ordinates
			//Look at each neighbour
			for(int k = 0; k<4; k++)
			{
				switch(k)
				{
					case 0:
						//Looking at the node above node X
						if(yy == 0)
						{
							//If X is at the top of the screen, there is no neighbour to consider out of the screen
							//Flag to make sure the neighbour is not checked
							skipNeighbour = true;
							//Continue checking the next neighbour
							continue;
						}
						tempNode = nodeMap[xx][yy-1];
						break;
					case 1:
						//Looking at the node to the right of node X
						if(xx == 39)
						{
							//If X is at the right of the screen, there is no neighbour to consider out of the screen
							//Flag to make sure the neighbour is not checked
							skipNeighbour = true;
							//Continue checking the next neighbour
							continue;
						}
						tempNode = nodeMap[xx+1][yy];
						break;
					case 2:
						//Looking at the node below node X
						if(yy == 39)
						{
							//If X is at the bottom of the screen, there is no neighbour to consider out of the screen
							//Flag to make sure the neighbour is not checked
							skipNeighbour = true;
							//Continue checking the next neighbour
							continue;
						}
						tempNode = nodeMap[xx][yy+1];
						break;
					case 3:
						//Looking at the node to the left of node X
						if(xx == 0)
						{
							//If X is at the left of the screen, there is no neighbour to consider out of the screen
							//Flag to make sure the neighbour is not checked
							skipNeighbour = true;
							//Continue checking the next neighbour
							continue;
						}
						tempNode = nodeMap[xx-1][yy];
						break;
				}

				//If the neighbour is a target, we're done searching
				if(tempNode.getX() == targx && tempNode.getY() == targy)
				{
					System.out.println("Neighbour is a target");
					pathFound = true;
					break;
				}

				//Updating the neighbours
				if(!skipNeighbour)
				{
					//Any not closed and not open nodes
					if(!tempNode.isOpen() && !tempNode.isClosed())
					{
						//Set open
						tempNode.setOpen(true);

						//Set g
						tempNode.setG(X.getG() + 1);

						//Set h
						tempNode.setH(getManhattan(tempNode.getX(), tempNode.getY(), targx, targy));

						//Set f
						tempNode.updateF();

						//Record parent X
						tempNode.setParent(X);

						//Add to open list
						openList.add(tempNode);
					}
				}
				else
				{
					//We need to skip the neighbour because the neighbour was the edge of the screen
					//Reset variable
					skipNeighbour = false;
				}

			}

			//Close node X
			X.setClosed(true);
		}
		//End of general algorithmic step

	//should be nothing here after bc if there are no open nodes the maze is unsolvable
	}
	
	public void move(boolean map[][],int targx, int targy) {
		if (hasPath) {
			// TO DO: follow A* path, if we have one defined

			if(pathStack.isEmpty())
			{
				System.out.println("pathStack is empty");
			}
			else
			{
				Node nextStep = (Node)pathStack.pop();

				x = nextStep.getX();
				y = nextStep.getY();
			}
		}
		else {
			// no path known, so just do a dumb 'run towards' behaviour
			int newx=x, newy=y;
			if (targx<x)
				newx--;
			else if (targx>x)
				newx++;
			if (targy<y)
				newy--;
			else if (targy>y)
				newy++;
			if (!map[newx][newy]) {
				x=newx;
				y=newy;
			}
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(myImage, x*20, y*20, null);
	}

	//Returns the Manhattan distance
	public int getManhattan(int nodeX, int nodeY, int targetX, int targetY)
	{
		return Math.abs(targetX-nodeX) + Math.abs(targetY-nodeY);
	}

	//Update stack with path that leads from destination to starting node
	public void updateStack(Node destination)
	{
		pathStack.clear();

		Node tempNode = destination;

		while(tempNode.getParent() != null)
		{
			System.out.println("Added x:" + tempNode.getX() + " y: " + tempNode.getY() + " to stack");
			pathStack.push(tempNode);
			tempNode = tempNode.getParent();
		}

		//Push the starting node too
		System.out.println("Added x:" + tempNode.getX() + " y: " + tempNode.getY() + " to stack");
		pathStack.push(tempNode);

		hasPath = true;
	}

}

