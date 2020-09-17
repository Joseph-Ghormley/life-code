package project1;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class PasswordCheckerUtility 
{
	static ArrayList<String> illegalPasswords = new ArrayList<String>();
	//Default Constructor
	public PasswordCheckerUtility() 
	{
	}
/*Parameters:passwordString - - string to be checked for validity
 *Returns:	true if valid password (follows all rules from above), 
 *set up to return false if an invalid password, but throws an exception instead.
 *Throws:LengthException - thrown if length is less than 6 characters
 *NoDigitException - thrown if no digit
 *NoUpperAlphaException - thrown if no uppercase alphabetic
 *NoLowerAlphaException - thrown if no lowercase alphabetic
 *InvalidSequenceException - thrown if more than 2 of same character.
 *NoSpecialSymbolException - thrown if no special character.*/
	public static boolean isValidPassword(String passwordString) throws LengthException, NoDigitException,
	NoUpperAlphaException, NoLowerAlphaException, NoSpecialSymbolException, InvalidSequenceException
	{
		if(passwordString.length()<6) 
			{
			throw new LengthException();
			}

		boolean digitIsHere = false;
		for(int i = 0; i < passwordString.length(); i++)
			{
				if (Character.isDigit(passwordString.charAt(i))) {
					digitIsHere = true;
				}
			}
		if (!digitIsHere) {
			
			throw new NoDigitException();
		}

		boolean upperCaseLetterFound=false;
		for(int i = 0; i < passwordString.length(); i++)
			{
			if (Character.isUpperCase(passwordString.charAt(i)))
				{
				upperCaseLetterFound=true;
				
				}	
				
			}
		if (upperCaseLetterFound == false)
			{
			
			throw new NoUpperAlphaException();
			}
		
		boolean lowerCaseLetterFound=false;
		for(int i = 0; i <passwordString.length(); i++)
		{
			if(Character.isLowerCase(passwordString.charAt(i)))
			{
				lowerCaseLetterFound = true;
			}
					
		}
		if(lowerCaseLetterFound == false)
		{
		
			throw new NoLowerAlphaException();
		}
			
		int x = passwordString.length();
		for(int i = 0; i < x; i++)
		{
			if((i +1 < x) &&(i +2 < x))
			{
				if((passwordString.charAt(i) == passwordString.charAt(i +1))
						&& (passwordString.charAt(i +1) == passwordString.charAt( i+2)))
				{
				
					throw new InvalidSequenceException();
				}
				
			}
			
				
		}
		

		if (!hasSpecialSymbol(passwordString))
			throw new NoSpecialSymbolException();
		return true;
	
	}	
	
		 
/*Parameters:passwordString - - string to be checked if weak password
 *Returns:true if length of password is greater than or equal to 6 but less than or equal to 9*/
	public static boolean isWeakPassword(String passwordString)
	{
		return passwordString.length() > 5 && passwordString.length() <10;
//		if(passwordString.length() > 5 && passwordString.length() <10) {
//			throw new IsWeakPasswordException();
//			}
	}

/*Parameters:passwords - arraylist of passwords to check for validity
 *Returns:arraylist of invalid passwords. It will not return weak passwords.*/
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) 
	{
		ArrayList<String> invalidPassword = new ArrayList<>();

        for (String password : passwords) {
            try {
                isValidPassword(password);
            } catch (Exception ex) {
                invalidPassword.add(password + " " + ex.getMessage() + "\n");
            }
        }

        return invalidPassword;
	
	}
	//Special symbol method
	private static boolean hasSpecialSymbol(String str) 
	{
	       Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
	       Matcher matcher = pattern.matcher(str);
	       return (!matcher.matches());
	}
	
}


