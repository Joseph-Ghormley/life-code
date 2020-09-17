package project1;

public class NoSpecialSymbolException extends Exception 
{
    public NoSpecialSymbolException() 
    {
        super("The password must contain at least one special character.");
    }
}
