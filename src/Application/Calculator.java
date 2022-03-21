package Application;
import java.lang.Math;

public class Calculator{

    public static void main(String args[]){
        String y = "a*b/(c-a)+d*e";
        String q = "a/b*(c+(d-e))";
        String m = "a-b+c";
        //should return ab*ca-/de*+
        System.out.println(convertToPostfix(y));
        System.out.println(convertToPostfix(q));
        System.out.println(convertToPostfix(m));
        /*
        a = 2;
        b = 3;
        c = 4;
        d = 5;
        e = 6; */
        
        System.out.println(evaluatePostfix("23*42-/56*+"));
    }

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
                while (!operatorStack.isEmpty() && getPrecedence(nextCharacter) <= getPrecedence(operatorStack.peek())){ // (see getPrecedence) user input gets sanitized here. while loop will not execute if nextCharacter is not +, -, *, /, ^
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

    private static int getPrecedence(Character x){
               if (x.equals('+') || x.equals('-')){
            return 1;
        } else if (x.equals('*') || x.equals('/')){
            return 2;
        } else if (x.equals('(')){
            return 0;
        }
         //else if (x.equals('^')){
            //return 3;}
          else { //Should never get to this else statement because input is already sanitized inside convertToPostfix
            throw new IllegalArgumentException("Operator unknown: " + x);
        }
    } //end getPrecedence

}
