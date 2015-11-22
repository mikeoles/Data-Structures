public class FrequencyBag<T>
{
	// TO DO: Instance Variables
	private Node firstNode;
	private int numberOfEntries;
	
	private class Node
	{
		public Node next;		
		public Pair data;
		
		public Node()
		{
			Pair newData = new Pair<T,Integer>(null,0);
			this(newData,null);
		}
		
		public Node(T dataPortion)
		{
			Pair newData = new Pair<T,Integer>(dataPortion,1);
			this.data = newData;
			this.next = null;
		}
		
		public Node(T dataPortion, Node nextNode)
		{
			Pair newData = new Pair<T,Integer>(dataPortion,1);
			this.data = newData;
			this.next = nextNode;
		}
	}
	
	/**
	 * Constructor
	 * Constructs an empty frequency bag.
	 */
	public FrequencyBag()
	{
		Node firstNode = new Node();
		numberOfEntries = 0;
	}
	
	/**
	 * Adds new entry into this frequency bag.
	 * @param aData the data to be added into this frequency bag.
	 */
	public void add(T aData)
	{
		// TO DO
		Node locationNode = getReferenceTo(aData);	
		
		
		if(locationNode == null)//Check if this aData has been added before
		{		
			Node newNode = new Node(aData);
			newNode.next = firstNode;
			firstNode = newNode;
			numberOfEntries++;
		}			
		else
		{		
			int typeCount = 1+(int)locationNode.data.snd();			
			Pair incrementedPair = new Pair<T,Integer>(aData,typeCount);
			locationNode.data = incrementedPair;
			numberOfEntries++;
		}
	}
	
	/**
	 * Gets the number of occurrences of aData in this frequency bag.
	 * @param aData the data to be checked for its number of occurrences.
	 * @return the number of occurrences of aData in this frequency bag.
	 */
	public int getFrequencyOf(T aData)
	{
		int frequency = 0;
		Node locationNode = getReferenceTo(aData);			
		
		if(this.numberOfEntries == 0)
		{
			frequency = 0;
		}
		else
		{
			if(locationNode == null)
			{
				frequency = 0;
			}else
			{		
				frequency = (int)locationNode.data.snd();
			}
		}
		
		return frequency;	
	}

	/**
	 * Gets the maximum number of occurrences in this frequency bag.
	 * @return the maximum number of occurrences of an entry in this
	 * frequency bag.
	 */
	public int getMaxFrequency()
	{
		// TO DO
		int maxFrequency = 0;
		
		if(isEmpty())
		{
			return maxFrequency;
		}
		
		boolean allSearched = false;
		
		Node currentNode = firstNode;
	
		while(currentNode != null)
		{ 
			if((int)currentNode.data.snd()>maxFrequency) 
			{
				maxFrequency = (int)currentNode.data.snd();
			} 
			else 
			{
				currentNode = currentNode.next; 
			} 
		}
		
		return maxFrequency;		
	}
	
	/**
	 * Gets the probability of aData
	 * @param aData the specific data to get its probability.
	 * @return the probability of aData
	 */
	public double getProbabilityOf(T aData)
	{
		// TO DO
		double probability = 0;
		if(this.size() == 0)
		{
			return probability;
		}
		probability = ((double)getFrequencyOf(aData)/this.size());
		return probability;
		
	}

	/**
	 * Empty this bag.
	 */
	public void clear()
	{
		while (firstNode != null)
		{
			firstNode = firstNode.next;	
		}
		numberOfEntries = 0;		
		
	}
	
	/**
	 * Gets the number of entries in this bag.
	 * @return the number of entries in this bag.
	 */
	public int size()
	{
		// TO DO
		return numberOfEntries;
	}
	
	
	private Node getReferenceTo(T anEntry) 
	{ 
		boolean found = false;
		
		Node currentNode = firstNode;
		
		while(!found && currentNode != null)
		{ 
			if(anEntry.equals(currentNode.data.fst())) 
			{
				found = true; 
			} else 
			{
				currentNode = currentNode.next; 
			} 
		}
		
		return currentNode;
	}	
	
	public boolean isEmpty()
	{
		boolean check = false;
		
		if(this.numberOfEntries == 0)
		{
			check = true;
		}
		
		return check;	
	}
	
}
