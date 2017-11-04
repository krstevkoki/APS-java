package mk.ukim.finki.lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckXML {
    private static boolean validClosingTag(String tag, String closingTag) {
        String closingText = closingTag.substring(2, closingTag.length() - 1);
        String stackText = tag.substring(1, tag.length() - 1);
        return closingText.equals(stackText);
    }

    private static int validator(String[] rows) {
        LinkedStack<String> tagStack = new LinkedStack<>();
        for (String tag : rows) {
            if (tag.startsWith("[/") && tag.endsWith("]")) {
                if (tagStack.isEmpty() || !(validClosingTag(tagStack.peek(), tag)))
                    return 0;
                tagStack.pop();
            } else if (tag.startsWith("[") && tag.endsWith("]"))
                tagStack.push(tag);
            else if (tag.endsWith("]"))  // ako tagot e nevalden h1]
                return 0;
            else if (tag.startsWith("["))  // ako tagot e nevaliden [h1
                return 0;
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String s = br.readLine();
            int n = Integer.parseInt(s);
            String[] redovi = new String[n];
            for (int i = 0; i < n; i++)
                redovi[i] = br.readLine();
            int valid;
            // Vasiot kod tuka
            // Moze da koristite dopolnitelni funkcii ako vi se potrebni
            valid = validator(redovi);
            System.out.println(valid);
        }
    }
}
