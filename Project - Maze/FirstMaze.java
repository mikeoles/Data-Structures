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
	int columns;
	int rows;

	
	/**
	 * Constructor
	 * @param aWidth the number of chambers in each row
	 * @param aHeight the number of chamber in each column
	 */
	public Maze(int aWidth, int aHeight)
	{
		// TO DO: Constructor
		//maze = new int[aWidth][aHeight];
		columns = aWidth;
		rows = aHeight;
		east = new boolean[rows][columns];
		west = new boolean[rows][columns];
		north = new boolean[rows][columns];		
		south = new boolean[rows][columns];	
		
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
		
		Random randomNum = new Random();

		int splitColumn = 0;
		int splitRow = 0;
		
		splitColumn = 1 + randomNum.nextInt(columns-1);
		splitRow = 1 + randomNum.nextInt(rows-1);	

		for(int i = 0;i<rows;i++)
		{
			east[i][splitColumn-1] = true;
			west[i][splitColumn] = true;
		}
		
		for(int i = 0;i<columns;i++)
		{
			north[splitRow][i] = true;
			south[splitRow-1][i] = true;
		}

	}

	/**
	 * getWidth
	 * @return the width of this maze
	 */
	public int getWidth()
	{
		// TO DO
		return columns;
	}
	
	/**
	 * getHeight
	 * @return the height of this maze
	 */
	public int getHeight()
	{
		// TO DO
		return rows;
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
