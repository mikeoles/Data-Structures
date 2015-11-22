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
		if(this.isNeg && !anInfiniteInteger.isNeg)
		{
			String intString = this.toString();
			intString = intString.substring(1);
			InfiniteInteger newThis = new InfiniteInteger(intString);
			return anInfiniteInteger.minus(newThis);
			
		}

		if(!this.isNeg && anInfiniteInteger.isNeg)
		{
			String intString = anInfiniteInteger.toString();
			intString = intString.substring(1);
			InfiniteInteger newThis = new InfiniteInteger(intString);
			return this.minus(newThis);
		}
		
		InfiniteInteger smaller = new InfiniteInteger(0);
		InfiniteInteger larger = new InfiniteInteger(0);
		
		if(this.infNumber.length >= anInfiniteInteger.infNumber.length)
		{	
			smaller = new InfiniteInteger(anInfiniteInteger.toString());
			larger = new InfiniteInteger(this.toString());
		}
		else
		{
			larger = new InfiniteInteger(anInfiniteInteger.toString());
			smaller = new InfiniteInteger(this.toString());		
		}
		
		boolean carried = false;
		int difference = larger.infNumber.length - smaller.infNumber.length;
		String sum = "";
		int index = -1;
		for(int i = larger.infNumber.length-1; i >= difference; i--)
		{		
			int columnTotal = larger.infNumber[i] + smaller.infNumber[i-difference];
			if(carried)
			{
				columnTotal += 1;
			}
			if (columnTotal < 10)
			{
				sum = Integer.toString(columnTotal) + sum;
				carried = false;
			}
			else
			{
				sum = Integer.toString(columnTotal%10) + sum;
				carried = true;
			}
			index = i-1;
		}	
		
		int carriedNumber = 0;
		if(carried)
		{
			carriedNumber = 1;
		}
		
		boolean carriedAgain = false;
		
		for (int i=index; i>=0; i--)
		{			
			carried = false;
			int columnTotal = larger.infNumber[i] + carriedNumber;
			if(carriedAgain)
			{
				columnTotal += 1;
			}			
			if (columnTotal < 10)
			{
				sum = Integer.toString(columnTotal) + sum;
				carriedAgain = false;
			}
			else
			{
				sum = Integer.toString(columnTotal%10) + sum;
				carriedAgain = true;
			}	
			carriedNumber = 0;
		}
		
		if(carried||carriedAgain)
		{
			sum = "1" + sum;
		}
		
		if(this.isNeg && anInfiniteInteger.isNeg)
		{
			sum = "-" + sum;	
		}
		
		final InfiniteInteger finalSum = new InfiniteInteger(sum);	
		return finalSum;		

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
		boolean addNegative = false;
		
		if (this.isNeg && !anInfiniteInteger.isNeg)
		{
			String intString = "-" + anInfiniteInteger.toString();
			InfiniteInteger negInt = new InfiniteInteger(intString);			
			return this.plus(negInt);
		}
		if (!this.isNeg && anInfiniteInteger.isNeg)
		{
			String intString = "-" + this.toString();
			InfiniteInteger negInt = new InfiniteInteger(intString);			
			return anInfiniteInteger.plus(negInt);
		}		
		
		InfiniteInteger smaller = new InfiniteInteger(0);
		InfiniteInteger larger = new InfiniteInteger(0);
		
		if(this.compareTo(anInfiniteInteger) == 0)
		{
			InfiniteInteger zero = new InfiniteInteger(0);
			return zero;
		}
		
		if(this.compareTo(anInfiniteInteger) == 1)
		{	
			smaller = new InfiniteInteger(anInfiniteInteger.toString());
			larger = new InfiniteInteger(this.toString());
		}
		else
		{
			larger = new InfiniteInteger(anInfiniteInteger.toString());
			smaller = new InfiniteInteger(this.toString());	
			addNegative = true;
		}		
		
		int index = -1;
		int difference = larger.infNumber.length - smaller.infNumber.length;
		String total = "";
		for(int i = larger.infNumber.length-1; i >= difference; i--)	
		{
			int columnTotal = larger.infNumber[i] - smaller.infNumber[i-difference];
			
			if(columnTotal >= 0)
			{
				total = Integer.toString(columnTotal) + total;
			}
			else
			{
				total = Integer.toString(columnTotal + 10) + total;
				larger.infNumber[i-1] = (larger.infNumber[i-1] - 1);
			}	
			
			index = i;
		}
		
		for (int i=index-1; i>=0; i--)
		{			
			total = Integer.toString(larger.infNumber[i]) + total;
		}		
		
		if(addNegative)
		{
			total = "-" + total;
		}
		
final InfiniteInteger finalDifference = new InfiniteInteger(total);	
		
		return finalDifference;
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
