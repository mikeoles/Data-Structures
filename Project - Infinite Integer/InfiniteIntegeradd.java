/*
public InfiniteInteger plus(final InfiniteInteger y)
{
	this
}

x*y = x+x+x.....+x(y times)

  123
  321x
  ---
  123
 246 
369  +
-----
39483
*/
import java.util.*;

public class InfiniteInteger implements Comparable<InfiniteInteger>
{
	// TO DO: Instance Variables
	// Note that it is a good idea to declare them final to
	// prevent you from accidentally modify them.
	final int[] infNumber;
	final boolean isNeg;
	
	/**
	 * Constructor: Constructs this infinite integer from a string
	 * representing an integer.
	 * @param s  a string represents an integer
	 */
	public InfiniteInteger(String s)
	{
		// TO DO: Constructor								
		if(s.charAt(0) == '-')
		{			
			this.isNeg = true;
			s = s.substring(1);
		}
		else
		{
			this.isNeg = false;
		}
		
		
		while(s.charAt(0) == '0')
		{
			if(s.length() == 1)
			{
				break;
			}		
			s = s.substring(1);
		}
		
		infNumber = new int[s.length()];		
		
		for(int i = 0; i < s.length(); i++)
		{
			infNumber[i] = Character.getNumericValue(s.charAt(i));
		}			
	}

	/**
	 * Constructor: Constructs this infinite integer from an integer.
	 * @param anInteger  an integer
	 */
	public InfiniteInteger(int anInteger)
	{
		// TO DO: Constructor
		String s = Integer.toString(anInteger);
		
		if(s.charAt(0) == '-')
		{			
			this.isNeg = true;
			s = s.substring(1);
		}
		else
		{
			this.isNeg = false;
		}
		
		while(s.charAt(0) == '0')
		{
			if(s.length() == 1)
			{
				break;
			}		
			s = s.substring(1);
		}
		
		infNumber = new int[s.length()];		
		
		for(int i = 0; i < s.length(); i++)
		{
			infNumber[i] = Character.getNumericValue(s.charAt(i));
		}
	}
	
	/**
	 * Gets the number of digits of this infinite integer.
	 * @return an integer representing the number of digits
	 * of this infinite integer.
	 */
	public int getNumberOfDigits()
	{
		// TO DO
		return infNumber.length;
	}

	/**
	 * Checks whether this infinite integer is a negative number.
	 * @return true if this infinite integer is a negative number.
	 * Otherwise, return false.
	 */
	public boolean isNegative()
	{
		// TO DO
		return isNeg;
	}

	/**
	 * Calculates the result of this infinite integer plus anInfiniteInteger
	 * @param anInfiniteInteger the infinite integer to be added to this
	 * infinite integer.
	 * @return a NEW infinite integer representing the result of this
	 * infinite integer plus anInfiniteInteger
	 */
	public InfiniteInteger plus(final InfiniteInteger anInfiniteInteger)
	{		
		// TO DO
		int difference = 0;
		String zeroes = "";
		int thisLength = this.infNumber.length;
		int otherLength = anInfiniteInteger.infNumber.length;
		InfiniteInteger II1 = new InfiniteInteger(0);
		InfiniteInteger II2 = new InfiniteInteger(0);		
		
		if(thisLength == otherLength)
		{
			II1 = new InfiniteInteger(this.toString());
			II2 = new InfiniteInteger(anInfiniteInteger.toString());			
		}
		
		if(thisLength > otherLength)
		{
			difference = thisLength - otherLength;
			for (int i = 0;i<difference;i++)
			{
				zeroes = zeroes + "0";
			}
			II1 = new InfiniteInteger(this.toString());
			II2 = new InfiniteInteger(zeroes + anInfiniteInteger.toString());		
		}
		
		if(thisLength < otherLength)
		{
			difference = otherLength - thisLength;
			for (int i = 0;i<difference;i++)
			{
				zeroes = zeroes + "0";
			}			
			II1 = new InfiniteInteger(zeroes + this.toString());
			II2 = new InfiniteInteger(anInfiniteInteger.toString());		
		}		
		
		int length = II1.infNumber.length;
		String added = "";
		boolean carried = false;
		for(int i = length-1; i>=0;i--)
		{		
			int columnTotal = II1.infNumber[i] + II2.infNumber[i];
			if(carried)
			{
				columnTotal += 1;
			}
			
			if (columnTotal < 10)
			{
				added = Integer.toString(columnTotal) + added;
				carried = false;
			}
			else
			{
				added = Integer.toString(columnTotal%10) + added;
				carried = true;
			}
System.out.println(II1.infNumber[i] +" " +II2.infNumber[i]);			
		}
		if(carried)
		{
			added = "1" + added;
		}
		
		if(this.isNeg && anInfiniteInteger.isNeg)
		{
			final InfiniteInteger sum = new InfiniteInteger("-" + added);
			return sum;			
		}
		else
		{
			final InfiniteInteger sum = new InfiniteInteger(added);	
			return sum;		
		}
		

		
		
	}

	/**
	 * Calculates the result of this infinite integer subtracted by anInfiniteInteger
	 * @param anInfiniteInteger the infinite integer to subtract.
	 * @return a NEW infinite integer representing the result of this
	 * infinite integer subtracted by anInfiniteInteger
	 */
	public InfiniteInteger minus(final InfiniteInteger anInfiniteInteger)
	{
		// TO DO
		
		return anInfiniteInteger;
	}

	/**
	 * Calculates the result of this infinite integer multiplied by anInfiniteInteger
	 * @param anInfiniteInteger the multiplier.
	 * @return a NEW infinite integer representing the result of this
	 * infinite integer multiplied by anInfiniteInteger.
	 */
	public InfiniteInteger multiply(final InfiniteInteger anInfiniteInteger)
	{
		// TO DO
		return anInfiniteInteger;	
	}
	
	/**
	 * Generates a string representing this infinite integer. If this infinite integer
	 * is a negative number a minus symbol should be in the front of numbers. For example,
	 * "-12345678901234567890". But if the infinite integer is a positive number, no symbol
	 * should be in the front of the numbers (e.g., "12345678901234567890").
	 * @return a string representing this infinite integer number.
	 */
	public String toString()
	{
		// TO DO	
		
		String s = new String();
		for(int i=0; i<infNumber.length; i++)
		{
			s = s +(Integer.toString(infNumber[i]));
		}
				
		
		if(isNeg)
		{
			s = "-" + s;
		}
		
		return s;
	}
	
	/**
	 * Compares this infinite integer with anInfiniteInteger
	 * @return either -1, 0, or 1 as follows:
	 * If this infinite integer is less than anInfiniteInteger, return -1.
	 * If this infinite integer is equal to anInfiniteInteger, return 0.
	 * If this infinite integer is greater than anInfiniteInteger, return 1.
	 */
	public int compareTo(final InfiniteInteger anInfiniteInteger)
	{
		// TO DO
		if(this.isNeg && !anInfiniteInteger.isNeg)
		{
			return -1;
		}
		else if(!this.isNeg && anInfiniteInteger.isNeg)
		{	
			return 1;
		}
		else
		{
			if(this.infNumber.length > anInfiniteInteger.infNumber.length)
			{
				return 1;
			}
			else if(this.infNumber.length < anInfiniteInteger.infNumber.length)
			{
				return -1;
			}
			else
			{
				for(int i=0; i<this.infNumber.length;i++)
				{				
					if(this.infNumber[i] > anInfiniteInteger.infNumber[i])
					{
						return 1;
					}
					
					if(this.infNumber[i] < anInfiniteInteger.infNumber[i])
					{
						return -1;
					}
				}
				return 0;
			}
		}
	}
}
