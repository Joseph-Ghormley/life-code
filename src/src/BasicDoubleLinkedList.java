package src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * @author Joseph Ghormley
 * Professor Robert Alexander
 * Cmsc204
 * 10/5/20
 * 
 * @param <T>
 */
public class BasicDoubleLinkedList<T> implements Iterable<T>
{
	protected int size =0;
	protected FBnode head;
	protected FBnode tail;
	protected FBnode it;
	
	
	/**
	 * Default constructor
	 */
	public BasicDoubleLinkedList()
	{
	}
	/**
	 * Notice you must not traverse the list to compute the size. This method just returns the value of the instance variable you use to keep track of size.
	Returns:the size of the linked list
	 * @return
	 */
	public int getSize()
	{
		return size;
	}
	/**
	 * Adds an element to the end of the list. Do not use iterators to implement this method.
	Parameters:data - the data for the Node within the linked list
	Returns:reference to the current object
	 * @param data
	 * @return
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data)
	{	
		FBnode newNode = new FBnode(data);
			
		if (head ==null)
		{
			head =newNode;	
			tail= head;
		}
		else
		{
			 tail.nextNode= newNode;
			 newNode.previousNode= tail;
			 tail=newNode;
		}
		size++;
		return this;
	}
	/**
	 * Adds element to the head of the list. Do not use iterators to implement this method.
	Parameters:data - the data for the Node within the linked list
	Returns:reference to the current object
	 * @param data
	 * @return
	 */
	public BasicDoubleLinkedList<T> addToFront(T data)
	{
		FBnode newNode = new FBnode(data);
		if(head==null)
		{
			head = newNode;
			tail = newNode;
		}
		else
		{
		newNode.nextNode=head;
		head.previousNode= newNode;
		head=newNode;
		}
		size++;
		return this;
	}
	/**
	 * Returns but does not remove the first element from the list. If there are no elements the method returns null. Do not implement this method using iterators.
	 Returns:the data element or null
	 * @return
	 */
	public T getFirst()
	{
		if(head==null)
		{
			return null;
		}
		return head.datam;
	}
	/**
	 * Returns but does not remove the last element from the list. If there are no elements the method returns null. Do not implement this method using iterators.
	 Returns:the data element or null
	 * @return
	 */
	public T getLast()
	{
		if (tail==null)
		{
			return null;
		}
		return tail.datam;
	}
	/**
	 * This method must be implemented using an inner class that implements ListIterator and defines the methods of hasNext(),
	 * next(), hasPrevious() and previous().
	 *  Remember that we should be able to call the hasNext() method as many times as we want without changing what is considered the next element.
	 *Specified by:	iterator in interface java.lang.Iterable<T>
	Throws:java.util.NoSuchElementException - Your next() method should throw NoSuchElementException if there are no more elements (at the end of the list and calling next() 
	or at the beginning of the list and calling previous()).
    java.lang.UnsupportedOperationException - You don't need to implement the ListIterator's remove(), 
    add(), nextIndex() and previousIndex() and set() methods, throw UnsupportedOperationException if called.
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws NoSuchElementException
	 */
	public ListIterator<T> iterator()
            throws UnsupportedOperationException,
               NoSuchElementException
    {
		DoublyListIterator iteratotor=new DoublyListIterator();
		
		return iteratotor;
    }
	
	/**
	 * Removes the first instance of the targetData from the list.
	 * Notice that you must remove the elements by performing a single traversal over the list.
	 * You may not use any of the other retrieval methods associated with the class in order to complete the removal process.
	 * You must use the provided comparator (do not use equals) to find those elements that match the target.
	 *  Do not implement this method using iterators.
	 * Parameters: targetData - the data element to be removed comparator - the comparator to determine equality of data elements
	Returns:data element or null 
	 * @param targetData
	 * @param comparator
	 * @return
	 */
	public BasicDoubleLinkedList<T> remove(T targetData,
        Comparator<T> comparator)
	{
		FBnode iter= head;
		if (size==0)
		{
			return this; 
		}
		else if(size==1&&(comparator.compare(iter.datam,targetData)==0))
		{
			head=tail=null;
			size--;
			return this;
		}
		else if(size ==1)
		{
			return this;
		}
		 
		for( int i=0; i<size; i++)
		{	
			
			if(comparator.compare(iter.datam,targetData)==0)
			{						
				break;
			}
			iter=iter.nextNode;
		}
		if(iter==null)
		{
			return this;
		}
		if (iter==head)
		{
			iter= iter.nextNode;
			head.nextNode=null;
			iter.previousNode=null;
			head=iter;
		}
		else if(iter==tail)
		{
			iter=iter.previousNode;
			tail.previousNode=null;
			iter.nextNode=null;
			tail=iter;
		}
		else
		{
			iter.previousNode.nextNode =iter.nextNode;
			iter.nextNode.previousNode =iter.previousNode;
			iter.previousNode=null;
			iter.nextNode=null;
		}
		size--;
		return this;
	}
	/**
	 *Removes and returns the first element from the list. If there are no elements the method returns null. Do not implement this method using iterators.
	Returns:data element or null 
	 * @return
	 */
	public T retrieveFirstElement()
	{
		// Check to see if the list is empty
		if (head == null) {
	        return null;
	    }
		if(size ==1)
		{
			FBnode firstElement = head;
			head=tail=null;
			return firstElement.datam;
		}
		FBnode firstElement = head;
	    FBnode it = head.nextNode;
	    head.nextNode=null;
	    it.previousNode=null;
	    head = it;
	    size--;
	    return firstElement.datam;
	}
	/**
	 * Removes and returns the last element from the list. If there are no elements the method returns null. Do not implement implement this method using iterators.
	Returns:data element or null
	 * @return
	 */
	public T retrieveLastElement()
	{
		if(tail==null)
		{
			return null;
		}
		if(tail==head)
		{
			FBnode lastElement = tail;
			tail=head=null;
			return lastElement.datam;
		}
		FBnode LastElement =tail;
		FBnode it = tail.previousNode;
		tail.previousNode=null;
		it.nextNode=null;
		tail=it;
		size--;
		return LastElement.datam;
	}
	/**
	 * Returns an arraylist of the items in the list from head of list to tail of list
	Returns:an arraylist of the items in the list
	 * @return
	 */
	public ArrayList<T> toArrayList()
	{	
		FBnode pointer= head;
		
		ArrayList<T>mArray= new ArrayList<T>();
		for(int i=0; i <size;i++)
		{
			mArray.add(pointer.datam);
			pointer=pointer.nextNode;
		}
		
		return mArray;
	}
	
	
	
	class  FBnode
	{	
		 protected T datam;
		 protected FBnode previousNode;
		 protected FBnode nextNode;
		public FBnode(T datam)
		{
		this.datam = datam;
		}


	}
	//Inner class in charge of iterating through the doubly linked list
	protected class DoublyListIterator implements ListIterator<T>
	{
		FBnode iter =head;
		int index=0;
		@Override
		public boolean hasNext() 
		{
			if(index==size)
			{
				return false;
			}
			return true;
		}

		@Override
		public T next() {
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}
			T datam;
			if (index==0)
			{
				iter=head;
			}
				
				
			datam =iter.datam;
			iter=iter.nextNode;
			index++;
			
			return datam;
		}

		@Override
		public boolean hasPrevious() 
		{
			if(index ==0)
			{
				return false;
			}
			return true;
		}

		@Override
		public T previous() 
		
		{
			if (!hasPrevious())
			{
				throw new NoSuchElementException();	
			}
			T datam;
			if(index==size)
			{
				iter=tail;
				datam= iter.datam;
			}
			else 
			{
				iter=iter.previousNode;
				datam =iter.datam;
			}
			index--;
			return datam;
		}

		@Override
		public int nextIndex()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public int previousIndex() 
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void remove() 
		{
			throw new UnsupportedOperationException("Not supported yet.");	
			
		}

		@Override
		public void set(T e) 
		{
			throw new UnsupportedOperationException("Not supported yet.");
			
		}

		@Override
		public void add(T e) 
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}
		
	}
}
