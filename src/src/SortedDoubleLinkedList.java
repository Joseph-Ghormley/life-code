package src;

import java.util.Comparator;
import java.util.ListIterator;
/**
 * @author Joseph Ghormley
 * Professor Robert Alexander
 * Cmsc204
 * 10/4/20
 *
 * @param <T>
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>
{
	private Comparator<T> comparator;
/**
 * Creates an empty list that is associated with the specified comparator.
	Parameters:comparator2 - Comparator to compare data elements
 * @param comparator2
 */
	public SortedDoubleLinkedList(Comparator<T> comparator2)
	{
		comparator= comparator2;
	}
	/**
	 * Inserts the specified element at the correct position in the sorted list. 
	 * Notice we can insert the same element several times.
	 * Your implementation must traverse the list only once in order to perform the insertion. 
	 * Do not implement this method using iterators. Notice that you don't need to call any of the super class methods in order to implement this method.
	Parameters:	data - the data to be added to the list
	Returns:a reference to the current object
	 * @param data
	 * @return
	 */
	public SortedDoubleLinkedList<T> add(T data)
	{
		FBnode hPoint = head;
		FBnode tPoint = tail;
		FBnode newNode = new FBnode(data);
		if (size == 0)
		{
			head = tail = newNode;//the list was empty
			size++;
			return this;
		}
		for (int i = 0; i < size; i++)
		{
			//check if it needs to go right before the head
			if (comparator.compare(data, head.datam) <= 0 && hPoint==head)
			{
				head.previousNode = newNode;
				newNode.nextNode = head;
				head = newNode;
				size++;
				return this;
			}
			// check if needs to go in the middle


			else if (comparator.compare(data,hPoint.datam)<= 0)
			{
				hPoint.previousNode.nextNode =newNode;
				newNode.previousNode= hPoint.previousNode;
				hPoint.previousNode=newNode;
				newNode.nextNode=hPoint;
				size++;
				return this;
			}
			else
			{
				hPoint=hPoint.nextNode;

			}
		}
		tail.nextNode=newNode;
		newNode.previousNode=tail;
		tail=newNode;
		size++;
		return this;

	}
	/**
	 *This operation is invalid for a sorted list. An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	Overrides: addToEnd in class BasicDoubleLinkedList<T>
	Parameters: data - the data for the Node within the linked list
	Returns: reference to the current object
	Throws: java.lang.UnsupportedOperationException - if method is called 
	 * @param data
	 * @return
	 * @throws UnsupportedOperationException
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data)
            throws UnsupportedOperationException
            {
				throw new UnsupportedOperationException("Invalid operation for sorted list");
            }
	/**
	 * This operation is invalid for a sorted list. An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	Overrides:addToFront in class BasicDoubleLinkedList<T>
	Parameters:data - the data for the Node within the linked list
	Returns reference to the current object
	Throws:java.lang.UnsupportedOperationException - if method is called
	 * @param data
	 * @return
	 * @throws UnsupportedOperationException
	 */
	public BasicDoubleLinkedList<T> addToFront(T data)
            throws UnsupportedOperationException
            {
				throw new UnsupportedOperationException("Invalid operation for sorted list");        
            }
	/**
	 *Implements the iterator by calling the super class iterator method.
	Specified by:iterator in interface java.lang.Iterable<T>
	Overrides:iterator in class BasicDoubleLinkedList<T>
	Returns:an iterator positioned at the head of the list   
	 * @return
	 */
	public ListIterator<T> iterator()
	{
		return super.iterator();
	}
	/**
	 * Implements the remove operation by calling the super class remove method.
	Overrides:remove in class BasicDoubleLinkedList<T>
	Parameters:data - the data element to be removed
	comparator - the comparator to determine equality of data elements
	Returns:data element or null
	 * @param data
	 * @param comparator
	 * @return
	 */
	public SortedDoubleLinkedList<T> remove(T data,
           Comparator<T> comparator)
	{
		super.remove(data,comparator);
		return this;
	}
 
}
