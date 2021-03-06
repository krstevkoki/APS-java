package mk.ukim.finki.a3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PostfixExpressionEvaluator {
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

    private static int evaluator(String expression) {
        LinkedStack<Integer> operandsStack = new LinkedStack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); ++i) {
            char currChar = expression.charAt(i);
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
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = bf.readLine()) != null && !(line.length() == 0)) {
                System.out.println("Rezultatot e: " + evaluator(line));
            }
        }
    }
}
