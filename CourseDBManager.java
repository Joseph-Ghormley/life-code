package pkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface
{
    CourseDBStructure tAdd;

    public CourseDBManager()
    {
        tAdd= new CourseDBStructure(100);
    }

    public CourseDBManager(int size)
    {
        tAdd= new CourseDBStructure(size);
    }

    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor)
    {
        var zAdd= new CourseDBElement(id, crn,credits,roomNum,instructor);
        tAdd.add(zAdd);
    }

    @Override
    public CourseDBElement get(int crn)
    {
        try
        {
            return tAdd.get(crn);
        }
        catch (IOException e)
        {
           e.printStackTrace();
        }
        return null;
    }

    @Override
    public void readFile(File input) throws FileNotFoundException
    {
        var textFile = new Scanner(input);
        while (textFile.hasNextLine())
        {
            String[] display =textFile.nextLine().split(" ", 5);
            var line = new CourseDBElement(display[0],Integer.parseInt(display[1]),Integer.parseInt(display[2]),display[3],display[4]);
            tAdd.add(line);
        }
    }

    @Override
    public ArrayList<String> showAll()
    {
        ArrayList<String>word =new ArrayList<>();
        for(int i=0; i <tAdd.getTableSize(); i++)
        {
            if(tAdd.hashTable[i]!=null)
            {
                for(var ele: tAdd.hashTable[i])
                {
                   word.add(ele.toString());
                }
            }
        }


        return word;
    }
}
