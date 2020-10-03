import java.util.ArrayList;
public class NotationQueue<T> implements QueueInterface<T>
{
	private ArrayList<T>theQueuce=new ArrayList<T>();
	private int queueSize;
	private int frontQue=-1,backQue=-1;
	private int counterSize;
	/** provide two constructors 
	 * 1. takes an int as the size of the queue
	 * 2. default constructor - uses a default as the size of the queue
	 * 
	 */
	public NotationQueue(int queueSize)
	{
		this.queueSize= queueSize;
		for(int i =0; i<queueSize;i++)
		{
			theQueuce.add(null);
		}
	}
	public NotationQueue() 
	{
		this.queueSize=10;
		for(int i =0; i<queueSize;i++)
		{
			theQueuce.add(null);
		}
	}
	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	public boolean isEmpty()
	{
		if(frontQue==-1&&backQue==-1)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 */
	public T dequeue() throws QueueUnderflowException
	{
		if(isEmpty())
		{
			throw new QueueUnderflowException();
		}
		T storage = theQueuce.get(frontQue);
		if(frontQue==backQue)
		{
			frontQue=backQue=-1;
		}
		else
		{
			//move the element over 1
				frontQue=(frontQue+1) % queueSize;			
		}
		counterSize--;
		return storage;
		
	}
	
	/**
	 * Determines of the Queue is empty
	 * @return
	 */
	public boolean isFull()
	{
		if(((backQue+1) % queueSize)==frontQue)
			{
			return true;
			}
		else
		{
			return false;
		}
	}
/**
 * Adds an element to the end of the Queue
 * @param e the element to add to the end of the Queue
 * @return true if the add was successful, false if not
 */
public boolean enqueue(T e) throws QueueOverflowException
{
	if(isFull())
	{
		throw new QueueOverflowException();
	}
	else if(isEmpty())
	{
		frontQue += 1;
		backQue += 1;
		theQueuce.set(backQue, e);
		counterSize++;
	}
	else
	{
		backQue=(backQue+1) % queueSize;	
		theQueuce.set(backQue, e);
		counterSize++;
	}
	
	return true;
}
/**
 * Number of elements in the Queue
 * @return the number of elements in the Queue
 */
public int size() 
{
	
	return counterSize;
}

/**
 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
 * Place the delimiter between all elements of the Queue
 * @return string representation of the Queue with elements separated with the delimiter
 */
public String toString(String delimiter) 
{
	 String str = "";
     int front = frontQue;
     if(!isEmpty())
     {
    	for(int count =0; count< counterSize; ++count)
    	{ 
    		str+= theQueuce.get(front);
    		if (front != backQue)
    			str +=delimiter;
    		front = (front +1)% queueSize;
    	
    		 
    	 }
     }
     	return str;
}
/**
 * Returns the string representation of the elements in the Queue, 
 * the beginning of the string is the front of the queue
 * @return string representation of the Queue with elements
 */
public String toString()
{
	 String str = "";
     int front = frontQue;
     if(!isEmpty())
     {
    	for(int count =0; count< counterSize; ++count)
    	{ 
    		str+= theQueuce.get(frontQue);
    		front = (front +1)% queueSize;
    	
    		 
    	 }
     }
     	return str;
     
}
/**
 * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
 * is the first element in the Queue
 * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
 * list reference within your Queue, you will be allowing direct access to the data of
 * your Queue causing a possible security breech.
 * @param list elements to be added to the Queue
 */
	public void fill(ArrayList<T> list)
	{
		frontQue=backQue=-1;
		counterSize =0;
		ArrayList<T> copyList = new ArrayList<T>();
		for ( T i:list)
			copyList.add(i);
		try 
		{	
			
			for(T item : copyList)
			{
				enqueue(item);
			}
		}
		catch (QueueOverflowException e) {}
	
	}
	
}
