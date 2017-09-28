package mk.ukim.finki.a0;

public class Zadaca3 {
    public static boolean isPrime(int num) {
        if (num == 2)
            return true;
        for (int j = 2; j <= (num / 2); ++j) {
            if (num % j == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int limit = 100;
        int sum = 1;
        for (int i = 2; i <= limit; ++i) {
            if (isPrime(i))
                sum += i;
        }
        System.out.println("Zbirot na prvite 100 prosti broja e: " + sum);
    }
}
