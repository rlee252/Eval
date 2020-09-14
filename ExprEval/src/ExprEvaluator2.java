import java.util.*;

//class for evaluating arithmetic expressions as double values, which are displayed rounded to 7 places,
//with decimal points supressed for whole numbers. The unrounded double value is returned.
public class ExprEvaluator2
{
Scanner consoleScanner =  new Scanner(System.in);

//class-wide variables:
//Since expressions are evaluated one at a time, all evaluations can use the same two queues.
private static Deque<Character> operatorQueue = new ArrayDeque<Character>();  //Queue for operator
private static Deque<Double> digitQueue = new ArrayDeque<Double>();

//class-wide methods:

//method to print a double value that is an integer as an int
public static void expressionOutput(double x)
{
  if(x == (double)Math.round(x)) //if the answer is a whole number,
                                 //display without decimal point
  {
     int intAns = (int)x;
     System.out.print("value = " + intAns);
  }
  else
  {
     System.out.print("value = " + x);
  }
}


/*Expressions are evaluated from left to right, using a queue A of arithmetic
operators and a queue B of operands. 
In this method, a single operation is
evaluated by popping the current operator from A, its 2 operands from B, and by then
performing the operation and adding the result onto B as a double value.*/

private static void eval()
{
    char op = operatorQueue.remove(); //current operator
    double opnd2 = 0;
    double opnd1 = digitQueue.remove(); //operands numbers

    if(op != '_') {
    	opnd2 = digitQueue.remove();
    }
    
    double val = 0.0;
    switch (op) //evaluate
    {
      case '+':
        val = opnd1 + opnd2;
        break;
      case '-':
        val = opnd2 - opnd1;
        break;
      case '*':
        val = opnd1 * opnd2;
        break;
      case '/':
        val = opnd2/opnd1;
        break;
      case '^':
    	  val = Math.pow(opnd2, opnd1);
    	break;
      case '_':
    	  val= opnd1 * -1;
    	  break;
      
    }
    digitQueue.addFirst(val); //add result onto B
}

/* In this method, a parenthesized subexpression is
evaluated by evaluating its operations on queue A left-right
until a left parenthesis is encountered on queue A;
the left parenthesis is also removed from A*/

private static void evalDown()
{
  
  while((operatorQueue.size()>0) && (operatorQueue.peek() != '(')){
	    eval();
  }
  if((operatorQueue.size()>0) && (operatorQueue.peek() == '('))
  {
    operatorQueue.remove();
  }
}


//This method compares the current operator token in the input string to the operator
//on first element of queue A to determine precedence.
private static boolean prec(char token, Deque<Character> DequeA)
{
  char topOp = DequeA.peek();
  if((token == '*') || (token == '/'))
  {
    return true; //token has precedence, so it will be pushed onto A
  }
  else
  {
    if((topOp == '*') || (topOp == '/'))
    {
      return false; //operator at top of A has precedence
    }
    else
    {
      return true; //equal low-precedence operators or token is ')',
                   // which is always pushed onto A
    }
  }
}

//variables for an ExprEvaluator object
private String expressionInput;
private int counter; //pointer to characters within the expression string e

//constructor 
public ExprEvaluator2()
{
  System.out.println("enter an expression");
  expressionInput = consoleScanner.nextLine(); //input an arithmetic expression as a line of keyboard input.
  counter = 0;      
}

//parameterized constructor
public ExprEvaluator2(String expressionString)
{
  expressionInput = expressionString;
  counter = 0;  
  
}

public String getExpression()
     {
        return expressionInput;
     }


//If a substring of e whose leftmost character is at position p 
//represents a number (possibly with a decimal point, possibly negative), 
//return the numerical value of the substring as a double value and
//re-position p just to the right of the substring.

private double formNum()
{
  double total = 0.0;
  int countAfterDec = 0;
  boolean isDecimal = false;
 // boolean isNegative = false;
  double mult = 1.0;
  char currentDigitToken, d;
  do
  {
    currentDigitToken = expressionInput.charAt(counter); //examine the current character in the string (from left to right)
    if(currentDigitToken == '.')
    {
      isDecimal = true; //set a flag to remember that the character is a decimal point
    }
    else
    {
    //if the current character is a digit, convert to its
    //int value and include it in the value corresponding to the string.
      if((currentDigitToken >= '0') && (currentDigitToken<= '9')) 
      {
        if(isDecimal) {
          total = total * 10 + (currentDigitToken-'0');
        } else {
          total = total + mult * (currentDigitToken-'0');
        }
        mult = mult * 10.0;
        if(isDecimal)
        { //count the number of digits to the left of a possible decimal point
          countAfterDec++;
        }
      }
      /*else
      {
        if(currentDigitToken == '_') 
        {
          isNegative = true;
         
        }
      }
      */
    }
    counter++; //Prepare to move to the next character to the right. 
         //This is a private non-static method because it changes the member variable p
    d = ' ';
    if(counter < expressionInput.length())
    {
       d = expressionInput.charAt(counter); //the next character to the left
    }
  }while((counter < expressionInput.length()) && (((d<='9')&&(d>='0'))||(d=='_')||(d=='.')));//check for a valid character
  if(isDecimal)
  {
   total = total/Math.pow(10.0,(countAfterDec)*1.0); //compute the value taking into account
                                           //the number of decimal places
  }
  /*if(isNegative) 
  {
    total = total * -1;
  }*/
  return total;
}

//This method uses the 2-stack approach to evaluate an expression (from left to right).
//The result is rounded to 7 decimal places and displayed as explained in expressionOutput() above.
//The original double value (unrounded) is returned to the calling program.
//The code could be made more efficient (but more difficult to read) by using if-else-if-else...
//as in formNum(); here we (unnecessarily) execute each if statement.
public double evaluator() 
{
  boolean powerFlag = false;
  int powerFlagPar = 0;
  int parenFlag = 0;
  char tempCharacterToken; //current token in the input expression
  boolean negFlag = false;
  int negFlagPar = 0;

  //loop to scan the string right to left
  do
  {    
    tempCharacterToken = expressionInput.charAt(counter);
    if(tempCharacterToken == '(')
    {
        operatorQueue.addFirst(tempCharacterToken); //always push a left parenthesis to delimit a subexpression
        counter++;  
        parenFlag++;
    }
    
    //if the token is a right parenthesis, 
    //evaluate the corresponding subexpression 
    else if(tempCharacterToken == ')' )
    {
      evalDown();
      counter++; //move beyond the right parenthesis
      parenFlag--;
      if(negFlag && parenFlag == negFlagPar) {
    	  eval();
    	  negFlag = false;
      }
      if(powerFlag && parenFlag == powerFlagPar) {
   	   eval();
   	   powerFlag = false;
      }
    }


    //If the token is an arithmetic operator of higher precedence
    //than the leftmost operator on queue A, add the token onto A.    
    else if((tempCharacterToken=='+')||(tempCharacterToken=='-')||(tempCharacterToken=='*')||(tempCharacterToken=='/'))
    {          
      if((operatorQueue.size() == 0) || (prec(tempCharacterToken, operatorQueue) == true))
      {
        operatorQueue.addFirst(tempCharacterToken);
        counter++;         
      }
      //If the token is an arithmetic operator of lower precedence than that
      //at the top of the stack, then evaluate the operation at the top of stack A.
      else
      {
        eval();
      }
    }
    
    //if the token is the rightmost  digit of a number or a decimal point on the right,
    //form the number as a double and push onto stack B
    else if(((tempCharacterToken<='9')&&(tempCharacterToken>='0'))||(tempCharacterToken=='.') /* ||(tempCharacterToken=='_')*/)
    {   
       digitQueue.addFirst(formNum());
       if(negFlag && parenFlag == negFlagPar) {
     	  eval();
     	  negFlag = false;
       }
       if(powerFlag && parenFlag == powerFlagPar) {
    	   eval();
    	   powerFlag = false;
       }
    }
    else if(tempCharacterToken == '^') {
    	powerFlag = true; 
    	operatorQueue.addFirst(tempCharacterToken);
    	counter++;
    	powerFlagPar = parenFlag;
    }
    else if(tempCharacterToken == '_') {
    	negFlag = true; 
    	operatorQueue.addFirst(tempCharacterToken);
    	counter++;
    	negFlagPar = parenFlag;

    }
    else {
      counter++;
    }
 
  }while(counter < expressionInput.length());//continue to scan from right to left


  //after completely scanning the input string, evaluate any remaining operations
  while(operatorQueue.size()>0) 
  {
    eval();
  }
  
  double x = digitQueue.remove();

  //round the result to 7 places and then display
  expressionOutput((double)Math.round(x*10000000)/10000000.0); 

  return x; //return the original double value
} //end of evaluator

} //end of class