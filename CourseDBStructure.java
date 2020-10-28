package pkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class CourseDBStructure implements CourseDBStructureInterface
{
 LinkedList<CourseDBElement>[] hashTable;

    private int hashSize=0;

    public CourseDBStructure(int numCourse)
    {
        hashSize= numCourse;
        hashTable= new LinkedList[hashSize];
       for(int i= 0; i< numCourse;i++) {
           hashTable[i] = null;
       }
    }
    public CourseDBStructure(String test1, int numCourse)
    {
        hashSize = numCourse;
        hashTable = new LinkedList[hashSize];
        for (int i = 0; i < numCourse; i++)
        {
            hashTable[i] = null;
        }
    }



    @Override
/**
 * Use the hashcode of the CourseDatabaseElement to see if it is
 * in the hashtable.
 *
 * If the CourseDatabaseElement does not exist in the hashtable,
 * add it to the hashtable.
 *
 * @param element the CDE to be added
 */
    public void add(CourseDBElement element)
    {
        int indexTest=0;
        indexTest=((element.hashCode())%(hashSize));        
        if (hashTable[indexTest] == null)
        {
            hashTable[indexTest] = new LinkedList<>();
            hashTable[indexTest].add(element);
        }
        else
        {
            hashTable[indexTest].add(element);
        }


    }

    /**
     * Use the hashcode of the CourseDatabaseElement to see if it is
     * in the hashtable.
     *
     * If the CourseDatabaseElement is in the hashtable, return it
     * If not, throw an IOException
     *
     * @param crn the CDE to be added
     * @throws IOException
     */

    public CourseDBElement get(int crn) throws IOException
    {
        var indexTest = Integer.toString(crn).hashCode()%hashSize;
        if( hashTable[indexTest] == null)
        {
            throw new IOException();
        }
        for(CourseDBElement i: hashTable[indexTest])
        {
            if (Integer.toString(crn).hashCode() == Integer.toString(i.getCRN()).hashCode()) {

                return i;
            }
        }
        throw new IOException();
    }

    /**
     * Returns the size of the ConcordanceDataStructure (number of indexes in the array)
     */
    public int getTableSize()
    {
    return hashSize;
    }

}

