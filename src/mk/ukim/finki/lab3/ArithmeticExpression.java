package mk.ukim.finki.lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ArithmeticExpression {
    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    private static int presmetaj(char c[], int left, int right) {
        // Vasiot kod tuka
        Stack<Integer> stackNumbers = new Stack<>();
        Stack<Character> stackOperators = new Stack<>();
        Stack<Character> stackParentheses = new Stack<>();
        int result = 0;
        stackParentheses.push(c[left]);  // add the first '(' to the parentheses stack
        for (int i = left + 1; i <= right && !(stackParentheses.empty()); ++i) {
            if (c[i] == '(') {
                stackParentheses.push(c[i]);
                while (!(stackParentheses.size() == 1)) {
                    ++i;
                    if (c[i] == '(')
                        stackParentheses.push(c[i]);
                    if (c[i] == ')') {
                        stackParentheses.pop();
                        char op = stackOperators.pop();
                        if (op == '+')
                            stackNumbers.push(soberi(stackNumbers.pop(), stackNumbers.pop()));
                        else
                            stackNumbers.push(odzemi(stackNumbers.pop(), stackNumbers.pop()));
                    }
                    if (c[i] == '+' || c[i] == '-')
                        stackOperators.push(c[i]);
                    if (Character.isDigit(c[i]))
                        stackNumbers.push(Integer.parseInt(String.valueOf(c[i])));
                }
            } else {
                if (c[i] == '+' || c[i] == '-')
                    stackOperators.push(c[i]);
                if (Character.isDigit(c[i]))
                    stackNumbers.push(Integer.parseInt(String.valueOf(c[i])));
                if (c[i] == ')') {
                    stackParentheses.pop();
                    char operator = stackOperators.pop();
                    if (operator == '+')
                        result += soberi(stackNumbers.pop(), stackNumbers.pop());
                    else
                        result += odzemi(stackNumbers.pop(), stackNumbers.pop());
                }
            }
        }
        return result < 0 ? result * -1 : result;
    }

    private static int soberi(int a, int b) {
        return a + b;
    }

    private static int odzemi(int a, int b) {
        if (b < a)
            return b - a;
        if (a < b)
            return b - a;
        return a - b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();
        char exp[] = expression.toCharArray();
        int rez = presmetaj(exp, 0, exp.length - 1);
        System.out.println(rez);
        br.close();
    }
}
