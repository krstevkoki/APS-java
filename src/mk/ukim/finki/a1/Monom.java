package mk.ukim.finki.a1;

class Monom implements Comparable<Monom> {
    protected int koeficient;
    protected int eksponent;

    public Monom(int koeficient, int eksponent) {
        this.koeficient = koeficient;
        this.eksponent = eksponent;
    }

    public int getKoeficient() {
        return koeficient;
    }

    public void setKoeficient(int koeficient) {
        this.koeficient = koeficient;
    }

    public int getEksponent() {
        return eksponent;
    }

    public void setEksponent(int eksponent) {
        this.eksponent = eksponent;
    }

    public Monom soberi(Monom other) {
        if (this.eksponent != other.eksponent) {
            System.out.println("Koeficientite ne mozhat da se soberat!");
            return null;
        }
        return new Monom((this.koeficient + other.koeficient), this.eksponent);
    }

    @Override
    public String toString() {
        if (eksponent == 0)
            return koeficient + "";
        else if (eksponent == 1)
            return koeficient + "x";
        return koeficient + "x^" + eksponent;
    }

    @Override
    public int compareTo(Monom o) {
        if (this.eksponent > o.eksponent)
            return 1;
        else if (this.eksponent < o.eksponent)
            return -1;
        return 0;
    }
}
