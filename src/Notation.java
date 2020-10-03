
/**
 * @author Joseph ghormley
 * Cmsc204
 * Dr.Robert Alexander
 *
 */
public class Notation 
{

	public Notation()
	{
	}
	/*
	 * Evaluates a postfix expression from a string to a double
	Parameters:postfixExpr - the postfix expression in String format
	Returns:the evaluation of the postfix expression as a double
	Throws:InvalidNotationFormatException - if the postfix expression format is invalid
	 */
	public static double evaluateInfixExpression(String infixExpr) throws InvalidNotationFormatException{
            {
			NotationStack<String> opStack = new NotationStack<>(infixExpr.length());
			for(int i=0; i<infixExpr.length();i++)
			{
				String thisChar= String.valueOf(infixExpr.charAt(i));
				if (thisChar.equals(" "))
				{
					continue;
				}
				else if(Character.isDigit(thisChar.charAt(0))|| thisChar.equals("("))
				{
						opStack.push(thisChar);		
				}
				else if (thisChar.equals("+")||thisChar.equals("-")||thisChar.equals("*")||thisChar.equals("/"))
				{
					if (opStack.size()<2)
					{
						throw new  InvalidNotationFormatException();
					}
					double right1=Double.parseDouble(opStack.pop());	
					double left2=Double.parseDouble(opStack.pop());
					double result=0;
					switch(thisChar)
					{
					case "+" :
						result=left2 + right1;
						break;
					case "-" :
						result=left2 - right1;
						break;
					case "*" :
						result=left2 * right1;
						break;
					case "/" :
						result=left2 / right1;
						break;
					}
					opStack.push(String.valueOf(result));
					
				}
				if(opStack.size()!=1)
				{
					throw new InvalidNotationFormatException();
				}
				else
				{
					return Double.parseDouble(opStack.toString());
				}
			}
			String inToPost = convertInfixToPostfix(infixExpr);
			double inToPostEval = evaluateInfixExpression(inToPost);

			return inToPostEval;
            }

			
		
            
            
            
	/*
	 * Convert the Postfix expression to the Infix expression
	Parameters:postfix - the postfix expression in string format
	Returns:the infix expression in string format
	Throws:InvalidNotationFormatException - if the postfix expression format is invalid
	 */
	public static String convertPostfixToInfix(String postfix)
            throws InvalidNotationFormatException, StackOverflowException, StackUnderflowException
            {
				NotationStack<String> opStack = new NotationStack<>(postfix.length());
				
				for(int i=0;i<postfix.length();i++)
				{
					String thisChar= String.valueOf(postfix.charAt(i));
					if (thisChar.equals(" "))
					{
						continue;
					}
					else if (thisChar.equals("+")||thisChar.equals("-")||thisChar.equals("*")||thisChar.equals("/"))
					{
						if(opStack.size()==1||opStack.isEmpty())
						{
							throw new InvalidNotationFormatException();
						}
													
						String value1=opStack.pop();	
						String value2=opStack.pop();	
						String temp=value2+thisChar+value1;
						String temp2="(" + temp +")";
						opStack.push(temp2);
															
					}
            	
				}
	
				if(opStack.size()!= 1)
				{
					throw new InvalidNotationFormatException();
				}
				return opStack.toString();
				
            }
				
				
       			
            
          

	/*
	 * Convert an infix expression into a postfix expression
	Parameters:infix - the infix expression in string format
	Returns:the postfix expression in string format
	Throws:InvalidNotationFormatException - if the infix expression format is invalid
	 */
	public static String convertInfixToPostfix(String infix)
            throws InvalidNotationFormatException, Exception
            {
				NotationStack<Character> opStack = new NotationStack<>(infix.length());
				
				NotationQueue<Character>pSolution= new NotationQueue<>(infix.length());
				
				for(int i=0;i<infix.length();i++)
				{
					char thisChar= infix.charAt(i);
					if (thisChar==' ')
					{
						continue;
					}
					else if (Character.isDigit(thisChar))
					{
						pSolution.enqueue(thisChar);
					}
					else if(thisChar=='(')
					{
						opStack.push(thisChar);
					}
					else if (thisChar=='+'||thisChar=='-'||thisChar=='*'||thisChar=='/')
					{
						boolean higherPrecedence=false;
						boolean lowPrecedence=false;
						if(thisChar=='+'||thisChar=='-')
						{
							lowPrecedence=true;
						}
						else
						{
							higherPrecedence=true;
						}
						while(!opStack.isEmpty()&&(opStack.top()=='+'||opStack.top()=='-'||opStack.top()=='*'||opStack.top()=='/'))
						{
							if((lowPrecedence ||(opStack.top()=='*'|| opStack.top()=='/')))
							{
								pSolution.enqueue(opStack.pop());
							}
							else 
								break;
						}
						opStack.push(thisChar);
					}
					else if(thisChar==')')
					{
						while(!opStack.isEmpty()&&(opStack.top()=='+'||opStack.top()=='-'||opStack.top()=='*'||opStack.top()=='/'))
						{
							pSolution.enqueue(opStack.pop());							
						}
						if(opStack.top()!='(')
						{
							throw new InvalidNotationFormatException();
						}
						else
						{
							opStack.pop();
						}
						while(!opStack.isEmpty()&&(opStack.top()=='+'||opStack.top()=='-'||opStack.top()=='*'||opStack.top()=='/'))
						{
							pSolution.enqueue(opStack.pop());	
						//2.	Pop (and discard) the left parenthesis from the stack 
						//When the infix expression has been read, Pop any remaining operators and insert them in postfix solution queue.
						}
						
					}		
				}
				
				while(!opStack.isEmpty()&&(opStack.top()=='+'||opStack.top()=='-'||opStack.top()=='*'||opStack.top()=='/'))
				{
					pSolution.enqueue(opStack.pop());
				}
				return pSolution.toString();
			
				

				
            }
}
