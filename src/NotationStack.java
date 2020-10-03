import java.util.ArrayList;

public class NotationStack<T> implements StackInterface<T>
{
	/**
	 * Provide two constructors
	 * 1. takes in an int as the size of the stack
	 * 2. default constructor - uses default as the size of the stack
	 */
	private ArrayList<T>theStack=new ArrayList<T>();
	private int stackSize;
	private int Top=-1;
	
	public NotationStack(int stackSize)
	{
		this.stackSize= stackSize;
		for(int i =0; i<stackSize;i++)
		{
			theStack.add(null);
		}
	}
	
	public NotationStack()
	{
		this.stackSize=10;
		for(int i =0; i<stackSize;i++)
		{
			theStack.add(null);
		}
	}
	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty() 
	{
		 if(Top == -1)
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	}
	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull() 
	{
		
		return size()==stackSize;
	}
	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 */
	public T pop() throws StackUnderflowException 
	{
		if(isEmpty())
		{
			throw new StackUnderflowException();
		}
		T storage = theStack.get(Top);
		 Top--;
		return storage;
	}
	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 */
	public T top() throws StackUnderflowException 
	{
		if(isEmpty())
		{
			throw new StackUnderflowException();
		}
		T storage = theStack.get(Top);
		return storage;
	}
	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	public int size() 
	{
		int element= Top +1;
	
		return element;
	}
	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 */
	public boolean push(T e) throws StackOverflowException
	{
		if(isFull())
		{
		throw new StackOverflowException();
		}
		else
		{
			Top++;
			theStack.set(Top, e);	
		}return true;
	}
	/**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString()
	{
		String str = "";
		if(!isEmpty())
		{
			for(int i=0; i<Top+1; i++)
				str += theStack.get(i);
			
		}
	        return str;	
	}
	/**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	public String toString(String delimiter)
	{
		String str = "";
		if(!isEmpty())
			{
				for(int i=0; i<Top+1; i++)
				{
					str += theStack.get(i);
					if(i!=Top)
						{
							str +=delimiter;
						}
				}
			}	
		return str;	
	}
	/**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	  * list reference within your Stack, you will be allowing direct access to the data of
	  * your Stack causing a possible security breech.
	  * @param list elements to be added to the Stack from bottom to top
	  */
	public void fill(ArrayList<T> list)
	{
		Top=-1;
		
		ArrayList<T> copyList = new ArrayList<T>();
		for ( T i:list)
			copyList.add(i);
		try 
		{	
			
			for(T item : copyList)
			{
				push(item);
			}
		}
		catch (StackOverflowException e) {}
	}
	
}
