package mk.ukim.finki.a5;

class ChemicalElement implements Comparable<ChemicalElement> {
    private char symbol1, symbol2;

    public ChemicalElement(String symbol) {
        if (symbol.length() >= 2) {
            symbol1 = Character.toUpperCase(symbol.charAt(0));
            symbol2 = Character.toLowerCase(symbol.charAt(1));
        } else if (symbol.length() >= 1) {
            symbol1 = Character.toUpperCase(symbol.charAt(0));
            symbol2 = '\0';
        } else throw new RuntimeException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChemicalElement that = (ChemicalElement) o;
        if (symbol1 != that.symbol1) return false;
        return symbol2 == that.symbol2;
    }

    @Override
    public int hashCode() {
        return symbol1 - 'A';
    }

    @Override
    public int compareTo(ChemicalElement o) {
        int s1 = Character.compare(this.symbol1, o.symbol1);
        int s2 = Character.compare(this.symbol2, o.symbol2);
        if (s1 > 0)
            return 1;
        else if (s1 < 0)
            return -1;
        else {
            if (s2 > 0)
                return 1;
            else if (s2 < 0)
                return -1;
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%c%c", symbol1, symbol2);
    }
}
