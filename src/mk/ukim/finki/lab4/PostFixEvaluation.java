package mk.ukim.finki.lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PostFixEvaluation {
    private static int doMath(char operator, int operand1, int operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                throw new RuntimeException();  // if not valid operator
        }
    }

    private static int evaluator(char[] expression) {
        LinkedStack<Integer> operandsStack = new LinkedStack<>();
        StringBuilder sb = new StringBuilder();
        for (char currChar : expression) {
            if (currChar == ' ') {
                if (sb.toString().length() != 0)
                    operandsStack.push(Integer.parseInt(sb.toString()));
                sb = new StringBuilder();
            } else if (Character.isDigit(currChar)) {
                sb.append(currChar);
            } else {
                int operand2 = operandsStack.pop();
                int operand1 = operandsStack.pop();
                operandsStack.push(doMath(currChar, operand1, operand2));
            }
        }
        return operandsStack.peek();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String expression = br.readLine();
            char[] exp = expression.toCharArray();
            System.out.println(evaluator(exp));
        }
    }
}
