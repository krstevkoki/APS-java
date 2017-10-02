package mk.ukim.finki.a0;

public class Zadaca3 {
    public static boolean isPrime(int num) {
        if (num == 1)
            return false;
        else if (num == 2)
            return true;
        for (int j = 2; j <= (num / 2); ++j) {
            if (num % j == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int sum = 0, counter = 0;
        for (int i = 1; ; ++i) {
            if (isPrime(i)) {
                ++counter;
                sum += i;
            }
            if (counter == 100)
                break;
        }
        System.out.println("Zbirot na prvite 100 prosti broja e: " + sum);
    }
}
