package mk.ukim.finki.exercises;

import java.util.Scanner;

public class StackBukvi {
    private static int proveri_t_posle_s(char[] St) {
        // Vasiot kod tuka
        ArrayStack<Character> sStack = new ArrayStack<>(St.length);
        ArrayStack<Character> charStack = new ArrayStack<>(St.length - 2);
        boolean flag = false;
        for (int i = 0; i < St.length; ++i) {
            if (St[i] == 'S') {
                flag = true;
                if (sStack.isEmpty())
                    sStack.push(St[i]);
                else
                    sStack.pop();
            }
            if (St[i] == 'T') {
                if (!(sStack.isEmpty()))
                    charStack.push(St[i]);
                else {
                    if (flag)
                        charStack.pop();
                }
            }
        }
        return charStack.isEmpty() ? 1 : 0;
    }

    public static void main(String[] args) {
        char[] niza;
        Scanner f = new Scanner(System.in);
        String st = f.next();
        niza = st.toCharArray();
        int rez = proveri_t_posle_s(niza);
        System.out.println(rez);
    }
}
