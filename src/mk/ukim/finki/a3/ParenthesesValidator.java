package mk.ukim.finki.a3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ParenthesesValidator {
    private static boolean matchingBracket(char leftBracket, char rightBracket) {
        if (leftBracket == '(')
            return rightBracket == ')';
        else if (leftBracket == '{')
            return rightBracket == '}';
        else
            return rightBracket == ']';
    }

    private static boolean validator(String expression) {
        LinkedStack<Character> parenthesesStack = new LinkedStack<>();
        for (int i = 0; i < expression.length(); ++i) {
            if (expression.charAt(i) == '(' || expression.charAt(i) == '{' || expression.charAt(i) == '[')
                parenthesesStack.push(expression.charAt(i));
            if (expression.charAt(i) == ')' || expression.charAt(i) == '}' || expression.charAt(i) == ']') {
                if (parenthesesStack.isEmpty()) // if closing bracket is before opening bracket
                    return false;
                char bracket = parenthesesStack.pop();  // the left bracket from the expression
                if (!matchingBracket(bracket, expression.charAt(i)))  // test whet
                    return false;
            }
        }
        return parenthesesStack.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = bf.readLine()) != null && !(line.length() == 0)) {
                if (validator(line))
                    System.out.println("Korektno");
                else
                    System.out.println("Nekorektno");
            }
        }
    }
}
