package project1;

public class IsWeakPasswordException extends Exception 
{
    public IsWeakPasswordException() 
    {
        super("The password is OK but weak - it contains fewer than 10 characters.");
    }
}
