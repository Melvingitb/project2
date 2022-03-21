package Application;
import java.lang.Math;

public class Calculator{

    
    /** 
     * Main method for running postfix conversion and evaluation.
     * @param args[]
     */
    public static void main(String args[]){

        String infix1 = "a*b/(c-a)+d*e";
        System.out.println("The infix expression to be converted to postfix is: " 
                             + infix1 + "\nThe resulting postfix expression is: " 
                             + convertToPostfix(infix1) + "\n"); //returns ab*ca-/de*+

        
        /*For below print statement:
            a = 2;
            b = 3;
            c = 4;
            d = 5;
            e = 6;
        for infix expression ab*ca-/de*+
        */
        

        String postfix1 = "23*42-/56*+";
        System.out.println("The postfix expression to be evaluated is: " 
                            + postfix1 + " , where" + 
                            "\na = 2, b = 3," + 
                            "\nc = 4, d = 5," + 
                            "\ne = 6\nThe evaluated postfix with these respective values is: " + 
                            evaluatePostfix(postfix1)); //returns 33
    }

    /** 
     * Converts an infix expression to a postfix expression.
     * @param infix     An infix expression.
     * @return postfix  The postfix expression derived from infix.
     */
    public static String convertToPostfix(String infix){
        //Converts an infix expression to an equivalent postfix expression.
        LinkedStack<Character> operatorStack = new LinkedStack<>();
        String postfix = "";

        for (int i = 0; i < infix.length();i++){
            Character nextCharacter = infix.charAt(i);
            if (Character.isLetter(nextCharacter)){
                postfix += Character.toString(nextCharacter);
                //System.out.println("it is a letter");
            }
            else if (nextCharacter.equals('^')){
                operatorStack.push(nextCharacter);
            } 

            
            else if(nextCharacter.equals('+') || nextCharacter.equals('-') || nextCharacter.equals('*') || nextCharacter.equals('/')){
                //System.out.println("it is an operator");
                while (!operatorStack.isEmpty() && getPrecedence(nextCharacter) <= getPrecedence(operatorStack.peek())){ 
                    
                    // (see getPrecedence) user input gets sanitized here. 
                    // while loop will not execute if nextCharacter is not +, -, *, /, ^
                    
                    postfix += Character.toString(operatorStack.peek());
                    operatorStack.pop();
                }
                operatorStack.push(nextCharacter);
            }

            
            else if (nextCharacter.equals('(')){
                operatorStack.push(nextCharacter);
            }
            else if (nextCharacter.equals(')')){
                Character topOperator = operatorStack.pop();
                while (topOperator != '('){
                    postfix += Character.toString(topOperator);
                    topOperator = operatorStack.pop();
                }
            } 

        }

        while (!operatorStack.isEmpty()){
            Character topOperator = operatorStack.pop();
            postfix += Character.toString(topOperator);
        }

        return postfix;
    } //end convertToPostfix

    
    /** 
     * Evaluates a postfix expression, provided that all variables in the postfix expression are replaced by their respective values (E.g. ab*ca-/de*+ would be 23*42-/56*).
     * @param postfix               Postfix expression where all variables are replaced by their respective values.
     * @return valueStack.peek();   The top value of valueStack.  
     */
    public static int evaluatePostfix(String postfix){
        
        ResizableArrayStack<Integer> valueStack = new ResizableArrayStack<>();
        
        for(int i = 0; i < postfix.length(); i++){
            Character nextCharacter = postfix.charAt(i);
                   if(nextCharacter.equals(" ")){
                i++;
            } else if(Character.isDigit(nextCharacter)){
                valueStack.push(nextCharacter - '0');
            } else {
                int operandOne;
                int operandTwo;

                switch(nextCharacter){
                    //addition operator
                    case '+': 
                        operandTwo = valueStack.pop();
                        operandOne = valueStack.pop();
                        valueStack.push(operandOne + operandTwo);
                        break;
                    //subtraction operator
                    case '-': 
                        operandTwo = valueStack.pop();
                        operandOne = valueStack.pop();
                        valueStack.push(operandOne - operandTwo);
                        break;
                    //multiplication operator
                    case '*': 
                        operandTwo = valueStack.pop();
                        operandOne = valueStack.pop();
                        valueStack.push(operandOne * operandTwo);
                        break;
                    //division operator
                    case '/':
                        operandTwo = valueStack.pop();
                        operandOne = valueStack.pop();
                        valueStack.push((int)(operandOne / operandTwo));
                        break;
                    //exponential operator
                    case '^':
                        operandTwo = valueStack.pop();
                        operandOne = valueStack.pop();
                        valueStack.push((int)Math.pow(operandOne, operandTwo));
                    default:
                        break;


                } // end switch
            } // end else
        } // end for

        return valueStack.peek();

    } // end evaluatePostfix

    
    /** 
     * Retrieves precedence values for respective operand.
     * @param x     The operand.
     * @return int  The precedence value.
     * @throws IllegalArgumentException if the operator is unknown.
     */
    private static int getPrecedence(Character x){
               if (x.equals('+') || x.equals('-')){
            return 1;
        } else if (x.equals('*') || x.equals('/')){
            return 2;
        } else if (x.equals('(')){
            return 0;
        } else { //Should never get to this else statement because input is already sanitized inside convertToPostfix
            throw new IllegalArgumentException("Operator unknown: " + x);
        }
    } //end getPrecedence

} //end class Calculator
