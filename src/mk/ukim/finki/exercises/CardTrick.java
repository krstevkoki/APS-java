package mk.ukim.finki.exercises;

import mk.ukim.finki.a3.LinkedQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class CardTrick {
    private static LinkedQueue<Integer> insertCards() {
        LinkedQueue<Integer> cards = new LinkedQueue<>();
        for (int i = 1; i <= 51; ++i)
            cards.enqueue(i);
        return cards;
    }

    private static int count(int N) {
        // Vasiot kod tuka
        int counter = 0;
        Stack<Integer> cardsStack = new Stack<>();
        LinkedQueue<Integer> cards = insertCards();
        int i = 1;
        while (true) {
            if (i <= 7)
                cardsStack.push(cards.dequeue());
            else {
                while (i != 1) {
                    cards.enqueue(cardsStack.pop());
                    cards.enqueue(cards.dequeue());
                    --i;
                }
                i = 0;  // it will increment at the end of the cycle
                ++counter;
                if (cards.peek() == N)
                    return counter;
            }
            ++i;
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(count(Integer.parseInt(br.readLine())));
        }
    }
}
