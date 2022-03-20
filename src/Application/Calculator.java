package Application;
import java.lang.Math;

public class Calculator{

    public static int a = 2;
    public static void main(String args[]){
        String y = "a*b/(c-a)+d*e";
        String q = "a/b*(c+(d-e))";
        String m = "a-b+c";
        //should return ab*ca-/de*+
        System.out.println(convertToPostfix(y));
        System.out.println(convertToPostfix(q));
        System.out.println(convertToPostfix(m));
/*
        int a = 2;
        int b = 3;
        int c = 4;
        int d = 5;
        int e = 6; */
        
        //System.out.println(evaluatePostfix(convertToPostfix(y)));
    }
/*
    public static String testing(String x){
        System.out.println(x);
        return x;
        //System.out.println(x);
    } */

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
            } else if(Character.isLetter(nextCharacter)){
                valueStack.push((int)nextCharacter);
            } else {
                int operandOne = valueStack.pop();
                int operandTwo = valueStack.pop();

                switch(nextCharacter){
                    //addition operator
                    case '+': valueStack.push(operandOne + operandTwo);
                        break;
                    //subtraction operator
                    case '-': valueStack.push(operandOne - operandTwo);
                        break;
                    //multiplication operator
                    case '*': valueStack.push(operandOne * operandTwo);
                        break;
                    //division operator
                    case '/': valueStack.push(operandOne / operandTwo);
                        break;
                    //exponential operator
                    case '^': valueStack.push((int)Math.pow(operandOne, operandTwo));
                    default:
                            valueStack.push(operandTwo);
                            valueStack.push(operandOne);
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
