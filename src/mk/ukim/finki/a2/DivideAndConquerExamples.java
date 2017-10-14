package mk.ukim.finki.a2;

public class DivideAndConquerExamples {
    private static final int INF = 1000000;

    public void merge(int a[], int left, int middle, int right) {
        int i = left;
        int j = middle + 1;
        int[] temp = new int[right - left + 1];  // allocate enough space for storing the sorted elements
        int k = 0;  // index for the temp[] array

        while ((i <= middle) && (j <= right)) {
            if (a[i] < a[j])
                temp[k++] = a[i++];
            else
                temp[k++] = a[j++];
        }

        while (i <= middle)
            temp[k++] = a[i++];

        while (j <= right)
            temp[k++] = a[j++];

        for (i = 0; i < (right - left + 1); ++i) {
            a[left + i] = temp[i];
        }
    }

    public void mergeSort(int a[], int left, int right) {
        if (left == right)
            return;
        int middle = (left + right) / 2;
        mergeSort(a, left, middle);
        mergeSort(a, middle + 1, right);
        merge(a, left, middle, right);
    }

    public int pow(int number, int power) {
        if (power == 0)
            return 1;
        int result;
        if (power % 2 == 0) {  // 2^8 = 2^4 * 2^4
            result = pow(number, power / 2);
            return result * result;
        } else {
            result = pow(number, power / 2);
            return result * result * number;  // 2^7 = 2^6 * 2
        }
    }

    public DvaNajmali find(int a[], int left, int right) {
        if (left == right)
            return new DvaNajmali(a[left], INF);
        int middle = (left + right) / 2;
        DvaNajmali d1 = find(a, left, middle);
        DvaNajmali d2 = find(a, middle + 1, right);
        DvaNajmali d3 = new DvaNajmali();

        if (d1.getA() < d2.getA()) {
            d3.setA(d1.getA());
            if (d1.getB() < d2.getA())
                d3.setB(d1.getB());
            else
                d3.setB(d2.getA());
        } else {
            d3.setA(d2.getA());
            if (d2.getB() < d1.getA())
                d3.setB(d2.getB());
            else
                d3.setB(d1.getA());
        }
        return d3;
    }
}
