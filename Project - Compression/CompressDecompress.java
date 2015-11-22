/**
 * It is okay to use ArrayList class but you are not allowed to use any other
 * predefined class supplied by Java.
 */
import java.util.ArrayList;

public class CompressDecompress
{
	/**
	 * Get a string representing a Huffman tree where its root node is root
	 * @param root the root node of a Huffman tree
	 * @return a string representing a Huffman tree
	 */
	public static String getTreeString(final BinaryNodeInterface<Character> root)
	{
		String allPaths = "";
		
		if(root != null)
		{		
			if(root.isLeaf())
			{
			allPaths  = "L"+root.getData();			
			}
			else
			{
			allPaths = "I";
			}
			allPaths = allPaths + getTreeString(root.getLeftChild());
			allPaths = allPaths + getTreeString(root.getRightChild());				
		}
		
		return allPaths;
	}

	/**
	 * Compress the message using Huffman tree represented by treeString
	 * @param root the root node of a Huffman tree
	 * @param message the message to be compressed
	 * @return a string representing compressed message.
	 */
	public static String compress(final BinaryNodeInterface<Character> root, final String message)
	{
		String allPaths = "";
		if(!message.equals(""))
			allPaths = compressedPath(root,allPaths,allPaths,1);	
		
		ArrayList<String> encodedTable = new ArrayList<String>();		
		String[] arr = allPaths.split("~");
		for(int i = 0;i<arr.length;i++)
		{
			encodedTable.add(arr[i]);
		}
		
		String compressedMessage = "";
		for(int i = 0;i<message.length();i++)
		{
			char character = message.charAt(i);
			String letter = "" + character;		
			int index = encodedTable.indexOf(letter)+1;
			compressedMessage = compressedMessage + encodedTable.get(index);
		}
		return compressedMessage;
	}
	
	public static String compressedPath(final BinaryNodeInterface<Character> root,String path,String allPaths,int level)
	{	
		if(!root.isLeaf())
		{		
			if(root.hasLeftChild())
			{
				allPaths = compressedPath(root.getLeftChild(),path = path + "0",allPaths,level+1);
			}
			if(root.hasRightChild())
			{
				allPaths = compressedPath(root.getRightChild(),path = path.substring(0,path.length()-1) + "1",allPaths,level+1);				
			}
		}
		else
		{	
			allPaths = allPaths + "~" + root.getData() + "~" + path;
		}		
		return allPaths;
	}
	/**
	 * Decompress the message using Huffman tree represented by treeString
	 * @param treeString the string represents the Huffman tree of the
	 * compressed message
	 * @param message the compressed message to be decompressed
	 * @return a string representing decompressed message
	 */
	public static String decompress(final String treeString, final String message)
	{
		if(treeString.length() == 0)
		{
			return "";
		}
	
		BinaryNodeInterface<Character> root = new BinaryNode<Character>();
		ArrayList<Character> tempChars = new ArrayList<Character>();
		ArrayList<Character> chars = new ArrayList<Character>();		
		for (char c : treeString.toCharArray()) {
			tempChars.add(c);
		}	
		
		int index = 0;
		while(index < tempChars.size())
		{
			if(tempChars.get(index) == 'I')
			{
				chars.add(null);
				index++;
			}
			if(tempChars.get(index) == 'L')
			{
				chars.add(tempChars.get(index+1));
				index+=2;
			}
		}		
		

		root = createTree(root,chars);
		String solution = "";
		boolean found = false;
		BinaryNodeInterface<Character> tempNode = root;	

		for(int i=0;i<message.length();i++)
		{
			if(found)
			{
				tempNode = root;
				found = false;
			}
			if(message.charAt(i) == '0')
			{
				tempNode = tempNode.getLeftChild();
			}
			else
			{
				tempNode = tempNode.getRightChild();			
			}
			
			if(tempNode.getData() != null)
			{
				solution = solution + tempNode.getData();
				found = true;
			}
		}
		
		return solution;	// Do not forget to change this line!!!
	}
	
	public static BinaryNodeInterface<Character> createTree(BinaryNodeInterface<Character> root,ArrayList<Character> nodes)
	{			
	
		if(nodes.size()>2)
		{
			root.setData(null);	
			int middle = 1;
			boolean found = false;
			int nulls = 0;
			int letters = 0;
			while(!found)
			{		
				if(nodes.get(middle) == null)
				{
					nulls++;
				}
				else
				{	
					letters++;
				}
				if(letters > nulls)
				{
					found = true;
				}
				middle++;				
			}
			
			ArrayList<Character> leftSide = new ArrayList<Character>();		
			for(int i = 1;i<middle;i++)
			{
				leftSide.add(nodes.get(i));
			}
			ArrayList<Character> rightSide = new ArrayList<Character>();	
			for(int i = middle;i<nodes.size();i++)
			{
				rightSide.add(nodes.get(i));
			}
			BinaryNodeInterface<Character> leftNode = new BinaryNode<Character>();			
			BinaryNodeInterface<Character> rightNode = new BinaryNode<Character>();	
			if(leftSide.size()>0)
			{
			root.setLeftChild(leftNode);	
			createTree(root.getLeftChild(),leftSide);
			}
			if(rightSide.size()>0)
			{
			root.setRightChild(rightNode);					
			createTree(root.getRightChild(),rightSide);
			}
			return root;
		}		
		else if(nodes.size() == 2)
		{
			root.setData(null);			
			BinaryNodeInterface<Character> tempNode = new BinaryNode<Character>();		
			root.setLeftChild(tempNode);
			ArrayList<Character> aList = new ArrayList<Character>();			
			aList.add(nodes.get(1));
			createTree(root.getLeftChild(),aList);
		}
		else if(nodes.size() == 1)
		{	
			root.setData(nodes.get(0));			
		}
		return root;
	}
	
}




















