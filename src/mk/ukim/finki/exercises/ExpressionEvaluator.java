package mk.ukim.finki.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {
    private static int multiply(int a, int b) {
        return a * b;
    }

    private static int add(int a, int b) {
        return a + b;
    }

    private static int evaluateExpression(String expression) {
        // Vasiot kod tuka
        Stack<Integer> operandsStack = new Stack<>();
        Stack<Character> operatorsStack = new Stack<>();
        StringBuilder sb = new StringBuilder();  // useful for extracting numbers from chars
        for (int i = 0; i < expression.length(); ++i) {
            char currentChar = expression.charAt(i);
            // if the char is operator, put the number and operator to the Stacks
            if (currentChar == '+' || currentChar == '*') {
                operandsStack.push(Integer.parseInt(sb.toString()));
                operatorsStack.push(currentChar);
                sb = new StringBuilder();
            } else  // add the next digit to the number
                sb.append(currentChar);
        }
        operandsStack.push(Integer.parseInt(sb.toString()));  // add the last number to the Stack
        while (!(operatorsStack.isEmpty())) {
            int operand1 = operandsStack.pop(), operand2 = operandsStack.pop();  // pop the operands
            char operator = operatorsStack.pop();  // pop the operator
            if (operator == '*')  // if the operator is '*', multiply no matter what
                operandsStack.push(multiply(operand1, operand2));
            else {  // operator == '+'
                // if the Stack is empty or the top operator is '+', add the operands no matter what
                if (operatorsStack.isEmpty() || operatorsStack.peek() == '+')
                    operandsStack.push(add(operand1, operand2));
                else {  // if the top operator is '*', first multiply the operands
                    int tempOperand = operandsStack.pop();
                    operandsStack.push(multiply(operand2, tempOperand));
                    operandsStack.push(operand1);  // push back the first operand to wait for its turn
                    operatorsStack.pop();  // pop the '*' operator
                    operatorsStack.push(operator);  // push back the '+' operator to wait for its turn
                }
            }
        }
        return operandsStack.peek();  // result is stored on the top of the stack
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(evaluateExpression(input.readLine()));
        }
    }
}
