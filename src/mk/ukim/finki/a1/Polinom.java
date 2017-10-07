package mk.ukim.finki.a1;

class Polinom {
    protected Array<Integer> monomi;

    public Polinom(Array<Integer> monomi) {
        this.monomi = monomi;
    }

    public Array<Integer> getMonomi() {
        return monomi;
    }

    public void setMonomi(Array<Integer> monomi) {
        this.monomi = monomi;
    }

    public Polinom soberi(Polinom other) {
        Array<Integer> monomiOther = other.getMonomi();
        int numNonZeroFirstPolinom = monomi.get(0);
        int numNonZeroSecondPolinom = monomiOther.get(0);
        Array<Integer> resultMonomi = new Array<>((numNonZeroFirstPolinom * 2) + (numNonZeroSecondPolinom * 2) + 1);
        // i = iterator for the first Polinom; j = iterator for the other Polinom; k = iterator for the resultMonomi
        int i = 1, j = 1, k = 1;

        while (i <= (2 * numNonZeroFirstPolinom) && j <= (2 * numNonZeroSecondPolinom)) {
            if (monomi.get(i).equals(monomiOther.get(j))) {
                resultMonomi.set(k + 1, monomi.get(i + 1) + monomiOther.get(j + 1));  // set coefficient
                resultMonomi.set(k, monomi.get(i));  // set exponent
                k += 2;
                i += 2;
                j += 2;
            } else {
                if (monomi.get(i) < monomiOther.get(j)) {
                    resultMonomi.set(k + 1, monomiOther.get(j + 1));  // set coefficient
                    resultMonomi.set(k, monomiOther.get(j));  // set exponent
                    j += 2;
                } else {
                    resultMonomi.set(k + 1, monomi.get(i + 1));  // set coefficient
                    resultMonomi.set(k, monomi.get(i));  // set exponent
                    i += 2;
                }
                k += 2;
            }
        }

        while (i <= 2 * numNonZeroFirstPolinom) {
            resultMonomi.set(k + 1, monomi.get(i + 1));  // set coefficient
            resultMonomi.set(k, monomi.get(i));  // set exponent
            i += 2;
            k += 2;
        }

        while (j <= 2 * numNonZeroSecondPolinom) {
            resultMonomi.set(k + 1, monomiOther.get(j + 1));  // set coefficient
            resultMonomi.set(k, monomiOther.get(j));  // set exponent
            j += 2;
            k += 2;
        }

        resultMonomi.set(0, k / 2);
        return new Polinom(resultMonomi);
    }

    @Override
    public String toString() {
        String str = new String();
        for (int i = 1; i <= monomi.get(0) * 2; i += 2) {
            str += monomi.get(i + 1) + "x^" + monomi.get(i) + " ";
        }
        return str;
    }
}
