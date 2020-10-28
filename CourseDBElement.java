package pkg;

import java.util.Objects;

public class CourseDBElement implements Comparable
{
    private String courseId;
    private int crn;
    private int numberCrd;
    private String roomNum;
    private String instructName;

    public CourseDBElement(String courseId, int crn, int numberCrd, String roomNum, String instructName)
    {
        this.courseId = courseId;
        this.crn = crn;
        this.numberCrd = numberCrd;
        this.roomNum = roomNum;
        this.instructName = instructName;
    }

    public CourseDBElement()
    {

    }

    public String getCourseId()
    {
        return courseId;
    }

    public void setCourseId(String courseId)
    {
        this.courseId = courseId;
    }

    public int getCRN()
    {
        return crn;
    }

    public void setCRN(int crn)
    {
        this.crn = crn;
    }

    public int getNumberCrd()
    {
        return numberCrd;
    }

    public void setNumberCrd(int numberCrd)
    {
        this.numberCrd = numberCrd;
    }

    public String getRoomNum()
    {
        return roomNum;
    }

    public void setRoomNum(String roomNum)
    {
        this.roomNum = roomNum;
    }

    public String getInstructName()
    {
        return instructName;
    }

    public void setInstructName(String instructName) 
    {
        this.instructName = instructName;
    }

    @Override
    public String toString()
    {
        return

                "\nCourse:" + courseId +
                " CRN:" + crn +
                " Credits:" + numberCrd +
                " Instructor:" + instructName +
                " Room:" + roomNum;
    }

    @Override
    public int compareTo(CourseDBElement element)
    {
        if(this.crn< element.crn)
        {
           return -1;
        }
        if (this.crn==element.crn)
        {
            return 0;
        }
        if (this.crn>element.crn)
        {
            return 1;
        }
        return 0;
    }
    @Override
    public int hashCode()
    {
       return Integer.toString(crn).hashCode();
    }

}
