

/* program to test the expression evaluator; evaluates input arithmetic expressions, each
   represented as a string without blanks, and with negative numbers indicated by underscore.
   Declare an expression using either ExprEvaluator exp = new ExprEvaluator() 
   (and respond to the prompt), or as ExprEvaluator exp = new ExprEvaluator("expression string").
   The expression can be returned as a String using exp.getExpression().
   The expression can be evaluated using exp.evaluator(), which also displays the result
   rounded to 7 decimal places, supressing a decimal point if the result is an integer. 
   The computed value is also returned as a double.
*/

public class ExprEvaluatorTest2
{
 
   public static void main(String[] args)
   {
 
    //initialize several expressions, but do not evaluate them immediately
    // ExprEvaluator2 expr1 = new ExprEvaluator2("1+2*3+4"); //construct an expression via keyboard
    // ExprEvaluator2 expr2 = new ExprEvaluator2("5.1 + .5 * .2 - 4.1"); //enter an expression via string parameter
    ExprEvaluator2 expr3 = new ExprEvaluator2("(3.+4*(1+2*(4+3*(2-_7)))-1)/2.0"); 
    ExprEvaluator2 expr4 = new ExprEvaluator2("1+2*3+4"); 
    ExprEvaluator2 expr5 = new ExprEvaluator2("1 + 2 * 3 + 4"); 
    ExprEvaluator2 expr6 = new ExprEvaluator2("1+9/3-4"); 
    ExprEvaluator2 expr7 = new ExprEvaluator2("121.75 + 3.25"); 
    ExprEvaluator2 expr8 = new ExprEvaluator2("4+3*(1+2.*(6/(3+3.))-1)+2.0"); 
    ExprEvaluator2 expr9 = new ExprEvaluator2("0.1*0.2"); 
    ExprEvaluator2 expr10 = new ExprEvaluator2("0.011*.022"); 
//    ExprEvaluator2 expr11 = new ExprEvaluator2("_(2-(_1))"); 
//    ExprEvaluator2 expr13 = new ExprEvaluator2("3.1415927^2"); 
//    ExprEvaluator2 expr14 = new ExprEvaluator2("2^(2*(2+3))"); 
//    ExprEvaluator2 expr15 = new ExprEvaluator2("(_3)^(_(3))"); 
     

    // 1) 2 - (-7) = 9
    // (27) + 4 = 31
    // (62) + 1 = 63
    // (252) + 3 - 1 = 254
    // 254 / 2 = 127

   
    //display the input for expr1
    // System.out.println(expr1.getExpression() + '\n');
    
    System.out.println("(3.+4*(1+2*(4+3*(2-_7)))-1)/2.0");
    expr3.evaluator(); //evaluate expr1
    System.out.println(" :should be 127"); 
  
    System.out.println("1+2*3+4");
    expr4.evaluator(); //evaluate expr1
    System.out.println(" :should be 11"); 
    
    
    System.out.println("1 + 2 * 3 + 4");
    expr5.evaluator(); //evaluate expr1
    System.out.println(" :should be 11");
    
    System.out.println("1+9/3-4");
    expr6.evaluator(); //evaluate expr1
    System.out.println(" :should be 0"); 
    
    System.out.println("121.75 + 3.25");
    expr7.evaluator(); //evaluate expr1
    System.out.println(" :should be 125"); 
    
    System.out.println("4+3*(1+2.*(6/(3+3.))-1)+2.0");
    expr8.evaluator(); //evaluate expr1
    System.out.println(" :should be 12"); 
    
    System.out.println("0.1*0.2");
    expr9.evaluator(); //evaluate expr1
    System.out.println(" :should be .02"); 
    
    System.out.println("0.011*.022");
    expr10.evaluator(); //evaluate expr1
    System.out.println(" :should be .000242"); 
//    
//    System.out.println("_(2-(_1))");
//    expr11.evaluator();
//    System.out.println(" :should be -3");
//    
//    System.out.println("3.1415927^2");
//    expr13.evaluator();
//    System.out.println(" :should be 9.87");
//    
//    System.out.println("2^(2*(2+3))");
//    expr14.evaluator();
//    System.out.println(" :should be 1024");
//    
//    System.out.println("(_3)^(_(3))");
//    expr15.evaluator();
//    System.out.println(" :should be -1/27");
//    
    // expr2.evaluator(); //evaluate expr1
  //  expr2.evaluator(); //evaluate expr2; the result will appear as an int value

  //  System.out.println(expr3.evaluator()); 
    //the evaluator() method will display the result as an int value
    //the value is also returned as a double to the print statement
  //                                  
  //  expr1 = new ExprEvaluator2("3.1415927"); //a single number is also an expression
  //  System.out.println(Math.pow(expr1.evaluator(),2)); //the call to evaluator displays 
                                                       //the value, which is then squared by pow

   // expr2 = new ExprEvaluator2("3.1415927*3.1415927"); 
   // expr2.evaluator(); //we can compare (to 7 places) the Java value using pow to the value using evaluator 
/*
   1) 1+2*3+4

2) 1 + 2 * 3 + 4

  In the rest of the examples, spacing is optional (within the limits

described in the problem description, e.g.,  3.145 is ok, but not 3  .  14  5, etc.)

3) 1+9/3-4 

4) 121.75 + 3.25

5) 4+3*(1+2.*(6/(3+3.))-1)+2.0

6) 0.1*0.2

7) 0.011*.022

8) -(2-(-1))  (If you don't want to code the logic for -x (negation), where x is an expression,

                 you can enter this as 0 -(2-(-1)), i.e., ordinary subtraction. Some extra credit

                 if your program handles negation ( - as a unary operator).

9) 3.1415927^2

10) 3.14159^1+1

11) 2^(2*(2+3))

12)  (-3)^(-(3)) (or (-3)^(-3), whichever your program can do)

 13) -(-(-(-1)))) (extra credit if you have coded negation (the unary -).
   */
  }

}
