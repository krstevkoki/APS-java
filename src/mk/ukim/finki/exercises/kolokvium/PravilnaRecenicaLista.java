package mk.ukim.finki.exercises.kolokvium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PravilnaRecenicaLista {
    private static SLL<Character> correctSentence(SLL<Character> sentence) {
        SLLNode<Character> tempFirst = sentence.getFirst();
        SLLNode<Character> tempWord = sentence.getFirst();
        SLLNode<Character> tempWhitespace = sentence.find(' ');
        while (tempFirst.next.next != null) {
            if (tempFirst.next.next.element.equals(' ')) {
                SLLNode<Character> ins = new SLLNode<>(tempFirst.next.element, tempWord);
                if (tempWord.equals(sentence.getFirst())) {
                    sentence.setFirst(ins);
                } else {
                    tempWhitespace.next = ins;
                    if (tempFirst.element.equals(' '))
                        tempWhitespace = tempFirst.next.next.next;
                    else
                        tempWhitespace = tempFirst.next.next;
                }
                tempFirst.next = tempFirst.next.next;
                if (tempFirst.element.equals(' '))
                    tempWord = tempFirst.next.next.next;
                else
                    tempWord = tempFirst.next.next;  // pocetokot na sledniot zbor
            } else if (tempFirst.next.next.element.equals('.')) {
                SLLNode<Character> ins = new SLLNode<>(tempFirst.next.element, tempWord);
                if (tempWord.equals(sentence.getFirst())) {
                    sentence.setFirst(ins);
                } else {
                    tempWhitespace.next = ins;
                }
                tempFirst.next = tempFirst.next.next;
                break;
            }
            tempFirst = tempFirst.next;
        }
        return sentence;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bf.readLine();
            SLL<Character> sentence = new SLL<>();
            for (int i = 0; i < line.length(); ++i)
                sentence.insertLast(line.charAt(i));
            System.out.println(correctSentence(sentence).toString());
        }
    }
}
