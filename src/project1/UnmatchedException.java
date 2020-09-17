package project1;

public class UnmatchedException extends Exception
{
    public UnmatchedException() 
    {
        super("The passwords do not match.");
    }
    public UnmatchedException(String str) 
    {
        super(str);
    }
}
