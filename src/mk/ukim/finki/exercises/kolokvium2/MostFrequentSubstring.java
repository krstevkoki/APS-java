package mk.ukim.finki.exercises.kolokvium2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MostFrequentSubstring {
    public static void main(String[] args) throws IOException {
        CBHT<String, Integer> tabela = new CBHT<>(300);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String word = br.readLine().trim();
            /*
             *
             * Vashiot kod tuka....
             *
             */
            int minLength = 1;
            List<String> subStrings = new ArrayList<>();
            for (int i = 0; i <= word.length() - minLength; ++i) {
                for (int j = minLength; j <= word.length() - i; ++j) {
                    String substring = word.substring(i, i + j);
                    if (subStrings.contains(substring))
                        tabela.insert(substring, tabela.search(substring).element.value + 1);
                    else
                        tabela.insert(substring, 1);
                    subStrings.add(substring);
                }
            }
            String max = subStrings.get(0);
            int maxLength = subStrings.get(0).length();
            int maxFrequency = tabela.search(max).element.value;
            for (int i = 1; i < subStrings.size(); ++i) {
                int frequency = tabela.search(subStrings.get(i)).element.value;
                String currentElement = subStrings.get(i);
                if (maxFrequency < frequency) {
                    max = currentElement;
                    maxLength = max.length();
                    maxFrequency = frequency;
                } else if (maxFrequency == frequency) {
                    int length = currentElement.length();
                    if (maxLength < length) {
                        max = currentElement;
                        maxLength = max.length();
                        maxFrequency = tabela.search(max).element.value;
                    } else if (maxLength == length) {
                        if (max.compareTo(currentElement) > 0) {
                            max = currentElement;
                            maxLength = max.length();
                            maxFrequency = tabela.search(max).element.value;
                        }
                    }
                }
            }
            System.out.println(max);
        }
    }
}
