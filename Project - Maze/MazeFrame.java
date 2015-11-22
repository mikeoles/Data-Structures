import java.util.ArrayList;
import javax.swing.JFrame;

public class MazeFrame
{
	public static void main(String[] args) throws InterruptedException
	{
		int width = 500;
		int height = 500;
		JFrame frame = new JFrame();
		Maze maze = new Maze(width, height);
		ArrayList<Pair<Integer,Integer>> solution = new ArrayList<Pair<Integer,Integer>>();
		MazeComponent mc = new MazeComponent(maze, solution);
		frame.setSize(800,800);
		frame.setTitle("Maze");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mc);
		frame.setVisible(true);
		
		
		solution.add(new Pair<Integer,Integer>(0,0));
		Thread.sleep(1);
		solveMaze(solution, mc, maze,"north",0);
		mc.repaint();
		
		
	}
	
	/*
	Solve Maze: recursively solve the maze
	 * 
	 * @param solution   : The array list solution is needed so that every recursive call,
	 *                     a new (or more) next position can be added or removed.
	 * @param mc         : This is the MazeComponent. We need that only for the purpose of
	 *                     animation. We need to call mc.repaint() every time a new position
	 *                     is added or removed. For example,
	 *                       :
	 *                     solution.add(...);
	 *                     mc.repaint();
	 *                     Thread.sleep(sleepTime);
	 *                       :
	 *                     solution.remove(...);
	 *                     mc.repaint();
	 *                     Thread.sleep(sleepTime);
	 *                       :
	 * @param maze       : The maze data structure to be solved. 
	 * @return a boolean value to previous call to tell the previous call whether a solution is
	 *         found.
	 * @throws InterruptedException: We need this because of our Thread.sleep(50);
	 */
	 
	public static boolean solveMaze(ArrayList<Pair<Integer,Integer>> solution, MazeComponent mc, Maze maze,String last,int moves) throws InterruptedException
	{
		// TO DO		
		int row = solution.get(moves).fst();
		int column = solution.get(moves).snd();
		boolean worked = true;
		int sleepTime = 1;
		if(!last.equals("north"))
		{
			if(!maze.isNorthWall(row,column))
			{						
				solution.add(new Pair<Integer,Integer>(row-1,column));
	            mc.repaint();
				Thread.sleep(sleepTime);
				worked = solveMaze(solution, mc, maze,"south",moves+1);				
				if(!worked)
				{
					solution.remove(solution.size() - 1);
					mc.repaint();
					//Thread.sleep(sleepTime);										
					row = solution.get(moves).fst();
					column = solution.get(moves).snd();					
				}
				else
				{
					return true;
				}				
			}				
		}
		if((solution.get(moves).fst()==maze.getHeight()-1) && (solution.get(moves).snd()==maze.getWidth()-1))
		{
			return true;
		}		
		if(!last.equals("east"))
		{
			if(!maze.isEastWall(row,column))
			{				
				solution.add(new Pair<Integer,Integer>(row,column+1));
	            mc.repaint();
				//Thread.sleep(sleepTime);	
				worked = solveMaze(solution, mc, maze,"west",moves+1);
				if(!worked)
				{
					solution.remove(solution.size() - 1);
					mc.repaint();
					//Thread.sleep(sleepTime);										
					row = solution.get(moves).fst();
					column = solution.get(moves).snd();						
				}
				else
				{
					return true;
				}
			}			
		}
		if((solution.get(moves).fst()==maze.getHeight()-1) && (solution.get(moves).snd()==maze.getWidth()-1))
		{
			return true;
		}		
		if(!last.equals("south"))
		{			
			if(!maze.isSouthWall(row,column))
			{
				solution.add(new Pair<Integer,Integer>(row+1,column));
	            mc.repaint();
				//Thread.sleep(sleepTime);
				worked = solveMaze(solution, mc, maze,"north",moves+1);				
				if(!worked)
				{
					solution.remove(solution.size() - 1);
					mc.repaint();
					//Thread.sleep(sleepTime);										
					row = solution.get(moves).fst();
					column = solution.get(moves).snd();						
				}
				else
				{
					return true;
				}				
			}			
		}	
		if((solution.get(moves).fst()==maze.getHeight()-1) && (solution.get(moves).snd()==maze.getWidth()-1))
		{
			return true;
		}		
		if(!last.equals("west"))
		{			
			if(!maze.isWestWall(row,column))
			{
				solution.add(new Pair<Integer,Integer>(row,column-1));				
	            mc.repaint();
				//Thread.sleep(sleepTime);	
				worked = solveMaze(solution, mc, maze,"east",moves+1);				
				if(!worked)
				{
					solution.remove(solution.size() - 1);
					mc.repaint();
					//Thread.sleep(sleepTime);					
					row = solution.get(moves).fst();
					column = solution.get(moves).snd();						
				}
				else
				{
					return true;
				}				
			}				
		}
		if((solution.get(moves).fst()==maze.getHeight()-1) && (solution.get(moves).snd()==maze.getWidth()-1))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
