import java.util.Random;
// No other import statement is allowed

public class Maze
{
	// TO DO: Instance Variable
	//int[][] maze;
	boolean[][] west;
	boolean[][] east;
	boolean[][] north;
	boolean[][] south;	
	int height;
	int width;

	
	/**
	 * Constructor
	 * @param aWidth the number of chambers in each row
	 * @param aHeight the number of chamber in each column
	 */

	public Maze(int aWidth, int aHeight)
	{
		// TO DO: Constructor
		//maze = new int[aWidth][aHeight];
		width = aWidth;
		height = aHeight;
		east = new boolean[height][width];
		west = new boolean[height][width];
		north = new boolean[height][width];		
		south = new boolean[height][width];	
		
		createMaze(0,0,height,width,east,west,north,south);
	}

	public void createMaze(int startRow, int startColumn, int rows,int columns,boolean[][] east, boolean[][] west,boolean[][] north,boolean[][] south)
	{	
		if(rows <= 1 || columns <= 1)
		{
			return;
		}

		if(startRow == 0 && startColumn == 0 && rows == height && columns == width)
		{
			for(int i=0; i<columns;i++)
			{
				north[0][i] = true;
				south[rows-1][i] = true;
			}
		
			for(int i=0; i<rows;i++)
			{
				west[i][0] = true;
				east[i][columns-1] = true;
			}		
		}
		
		Random randomNum = new Random();

		int splitColumn = 0;
		int splitRow = 0;
		splitColumn = 1 + startColumn + randomNum.nextInt(columns-1);
		splitRow = 1 + startRow + randomNum.nextInt(rows-1);	
		
		for(int i = startRow;i<+startRow+rows;i++)
		{
			east[i][splitColumn-1] = true;
			west[i][splitColumn] = true;
		}
		
		for(int i = startColumn;i<startColumn+columns;i++)
		{
			north[splitRow][i] = true;
			south[splitRow-1][i] = true;
		}
		
		int wall = 0;
		int opening = 0;
		int length = 0;
		wall = randomNum.nextInt(4);


		if(wall != 0)//north wall
		{
			length = splitRow - startRow - 1;
			if(length !=0)
			{
			opening = randomNum.nextInt(length);
			}
			else
			{
			opening = 0;
			}
			east[startRow + opening][splitColumn-1] = false;
			west[startRow + opening][splitColumn] = false;
		}
		if(wall != 1)//south wall
		{
			length = rows - (splitRow-startRow);
			if(length !=0)
			opening = randomNum.nextInt(length);
			else
			opening = 0;			
			east[splitRow + opening][splitColumn-1] = false;
			west[splitRow + opening][splitColumn] = false;			
		}		
		if(wall != 2)//east
		{
			length = columns - (splitColumn - startColumn);
			if(length !=0)
			opening = randomNum.nextInt(length);
			else
			opening = 0;			
			north[splitRow][splitColumn + opening] = false;
			south[splitRow-1][splitColumn + opening] = false;			
		}
		if(wall != 3)//west
		{
			length = splitColumn - startColumn - 1;
			if(length !=0)
			opening = randomNum.nextInt(length);
			else
			opening = 0;			
			north[splitRow][startColumn + opening] = false;
			south[splitRow-1][startColumn + opening] = false;
		}		
		 
		int topHeight = splitRow-startRow;
		int bottomHeight = rows-(splitRow-startRow);
		int topWidth = splitColumn-startColumn;
		int bottomWidth = columns-(splitColumn-startColumn);
		
		createMaze(startRow,startColumn,topHeight,topWidth,east,west,north,south);
		createMaze(startRow,splitColumn,topHeight,bottomWidth,east,west,north,south);
		createMaze(splitRow,startColumn,bottomHeight,topWidth,east,west,north,south);
		createMaze(splitRow,splitColumn,bottomHeight,bottomWidth,east,west,north,south);		
		
	}
	
	/**
	 * getWidth
	 * @return the width of this maze
	 */
	public int getWidth()
	{
		// TO DO
		return width;
	}
	
	/**
	 * getHeight
	 * @return the height of this maze
	 */
	public int getHeight()
	{
		// TO DO
		return height;
	}
	
	/**
	 * isNorthWall
	 * @param row the row identifier of a chamber
	 * @param column the column identifier of a chamber
	 * @return true if the chamber at row row and column column
	 * contain a north wall. Otherwise, return false
	 */
	public boolean isNorthWall(int row, int column)
	{
		// TO DO
		return north[row][column];
	}
	
	/**
	 * isEastWall
	 * @param row the row identifier of a chamber
	 * @param column the column identifier of a chamber
	 * @return true if the chamber at row row and column column
	 * contain an east wall. Otherwise, return false
	 */
	public boolean isEastWall(int row, int column)
	{
		// TO DO
		return east[row][column];
	}
	
	/**
	 * isSouthWall
	 * @param row the row identifier of a chamber
	 * @param column the column identifier of a chamber
	 * @return true if the chamber at row row and column column
	 * contain a south wall. Otherwise, return false
	 */
	public boolean isSouthWall(int row, int column)
	{
		// TO DO
		return south[row][column];
	}
	
	/**
	 * isWestWall
	 * @param row the row identifier of a chamber
	 * @param column the column identifier of a chamber
	 * @return true if the chamber at row row and column column
	 * contain a west wall. Otherwise, return false
	 */
	public boolean isWestWall(int row, int column)
	{
		// TO DO
		return west[row][column];
	}
}
